package com.tenglong.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tenglong.entity.HistoryRecord;

import java.util.List;

public interface HistoryRecordService  extends IService<HistoryRecord> {
    List<HistoryRecord> findAll();

    HistoryRecord findById(String id);

    List<HistoryRecord> findBySearch(String searchName);

    List<HistoryRecord> findByTime(String begin, String end);

    void deleteById(Integer id);
}
