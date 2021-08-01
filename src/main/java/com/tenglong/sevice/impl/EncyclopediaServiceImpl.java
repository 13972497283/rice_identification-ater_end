package com.tenglong.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tenglong.entity.Encyclopedia;
import com.tenglong.mapper.EncyclopediaMapper;
import com.tenglong.sevice.EncyclopediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EncyclopediaServiceImpl extends ServiceImpl<EncyclopediaMapper,Encyclopedia> implements EncyclopediaService {
    private final EncyclopediaMapper encyclopediaMapper;
    @Override
    public List<Encyclopedia> findAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public Encyclopedia findByName(String name) {
        return encyclopediaMapper.selectById(name);
    }
}
