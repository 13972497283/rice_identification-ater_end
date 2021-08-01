package com.tenglong.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tenglong.entity.Encyclopedia;

import java.util.List;

public interface EncyclopediaService  extends IService<Encyclopedia> {
    List<Encyclopedia> findAll();

    Encyclopedia findByName(String name);
}
