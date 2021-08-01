package com.tenglong.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tenglong.Util.StringUtil;
import com.tenglong.entity.Encyclopedia;
import com.tenglong.entity.HistoryRecord;
import com.tenglong.entity.Result;
import com.tenglong.exception.BusinessException;
import com.tenglong.sevice.HistoryRecordService;
import com.tenglong.sevice.UploadImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    public static final int PORT = 8089;

    @Resource(name = "uploadDirPath")
    String uploadDirPath;

    @Resource
    UploadImageService uploadImageService;

    @Autowired
    private HistoryRecordService historyRecordService;

    @CrossOrigin
    @PostMapping(value = "/image")
    private Result upLoadImage(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 获取文件的名称
        String fileName = file.getOriginalFilename();
        // 使用工具类根据上传文件生成唯一图片名称
        String imgName = StringUtil.getRandomImgName(fileName);

        Path imgPath=Paths.get(uploadDirPath,imgName);

        file.transferTo(imgPath);

        Map<String,Object> result= new HashMap<>();
        if (!file.isEmpty()) {
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            String path = uploadImageService.uploadQNImg(fileInputStream, imgName);
            System.out.println("七牛云返回的图片链接:" + path);
            try (
                //connect to local service to identify
                Socket socket = new Socket ("localhost", PORT);
                InputStream inputStream = socket.getInputStream ();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                OutputStream outputStream = socket.getOutputStream ()) {
                //send image path
                String jsonStr = "{\"path\":\"" + imgPath.toAbsolutePath().toString() + "\"}";
                outputStream.write (jsonStr.getBytes (StandardCharsets.UTF_8));
                //read content
                String str = bufferedReader.readLine();
                Map map = JSON.parseObject(str, Map.class);
                String status = map.get("status").toString();
                float rate=0f;
                String diseases = null;
                if(status.equals("success")){
                    Map<String,Object> prob = (Map<String,Object>)map.get("prob");
                    for (Object o : prob.keySet()) {
                        float t = Float.parseFloat(prob.get(o).toString());
                        if(t>rate){
                            rate=t;
                            diseases=o.toString();
                        }
                    }
                    result.put("diseases",diseases);
                    result.put("rate",rate);
                    result.put("imagePath",path);
                    HistoryRecord historyRecord=new HistoryRecord();
                    historyRecord.setUserloadimg(path);
                    historyRecord.setDiseasename(diseases);
                    historyRecord.setAccuracy(new DecimalFormat("0.00%").format(rate));
                    historyRecord.setUsingTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    historyRecordService.save(historyRecord);
                }
                else throw new BusinessException("识别失败");
            } catch (IOException e) {
                e.printStackTrace ();
                throw new BusinessException("系统出错");
            }finally {

            }
        }

        return new Result(true,200,"识别成功",result);
    }
}