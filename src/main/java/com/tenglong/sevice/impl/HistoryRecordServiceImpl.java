package com.tenglong.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tenglong.entity.HistoryRecord;
import com.tenglong.mapper.HistoryRecordMapper;
import com.tenglong.sevice.HistoryRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryRecordServiceImpl  extends ServiceImpl<HistoryRecordMapper, HistoryRecord> implements HistoryRecordService {

    @Override
    public List<HistoryRecord> findAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public HistoryRecord findById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<HistoryRecord> findBySearch(String searchName) {
        return baseMapper.selectList(new LambdaQueryWrapper<HistoryRecord>().like(HistoryRecord::getDiseasename,searchName));
    }

    @Override
    public List<HistoryRecord> findByTime(String begin, String end) {
        return baseMapper.selectList(new LambdaQueryWrapper<HistoryRecord>().between(HistoryRecord::getUsingTime,begin,end));
    }

    @Override
    public void deleteById(Integer id) {
        baseMapper.deleteById(id);
    }
}
