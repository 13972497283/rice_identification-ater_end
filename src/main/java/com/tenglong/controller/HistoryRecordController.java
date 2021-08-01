package com.tenglong.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.tenglong.entity.HistoryRecord;
import com.tenglong.entity.Result;
import com.tenglong.sevice.HistoryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historyRecord")
@RequiredArgsConstructor
public class HistoryRecordController {
    private final HistoryRecordService historyRecordService;
    @GetMapping("/findAll")
    public Result<HistoryRecord> findAll(){
        List<HistoryRecord> result = historyRecordService.findAll();
        return  new Result<HistoryRecord>(true,200,"查询成功", result);
    }

    @GetMapping("/findById")
    public Result<HistoryRecord> findById(String id){
        HistoryRecord result = historyRecordService.findById(id);
        return new Result<>(true,200,"查询成功",result);
    }
    @GetMapping("/findBySearch")
    public Result<HistoryRecord> findBySearch(String searchName){
        List<HistoryRecord>result=historyRecordService.findBySearch(searchName);
        return new Result<>(true,200,"查询成功",result);
    }

    @GetMapping("/findByTime")
    public  Result<HistoryRecord> findByTime(String begin,String end){
        List<HistoryRecord> result=historyRecordService.findByTime(begin,end);
        return new Result<>(true,200,"查询成功",result);
    }

    @GetMapping("deleteById")
    public Result deleteById(Integer id){
        historyRecordService.deleteById(id);
        return new Result(true,200,"删除成功",null);
    }
}
