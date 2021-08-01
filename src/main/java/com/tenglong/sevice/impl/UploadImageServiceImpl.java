package com.tenglong.sevice.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.tenglong.config.oos.CloudStorageConfig;
import com.tenglong.exception.BusinessException;
import com.tenglong.sevice.UploadImageService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
public class UploadImageServiceImpl extends UploadImageService {

    // 七牛文件上传管理器
    private UploadManager uploadManager;
    private String token;
    // 七牛认证管理
    private Auth auth;

    public UploadImageServiceImpl(CloudStorageConfig config){
        this.config = config;
        init();
    }
    private void init(){
        uploadManager = new UploadManager(new Configuration(Zone.zone2()));
        auth = Auth.create(config.getQiniuAccessKey(), config.getQiniuSecretKey());
        token = auth.uploadToken(config.getQiniuBucketName());
    }

    @Override
    public String uploadQNImg(FileInputStream file, String key) {
        try{
            Response res = uploadManager.put(file, key, token, null, null);
            if (!res.isOK()) {
                throw new BusinessException("上传图片出错:" );
            }
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            String path = config.getQiniuDomain() + "/" + putRet.key;
            return "http:"+path;
        }catch (QiniuException e){
            e.printStackTrace();
        }
        return "";
    }
}