package com.tenglong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tenglong.entity.Encyclopedia;
import org.apache.ibatis.annotations.Param;

public interface EncyclopediaMapper  extends BaseMapper<Encyclopedia> {

    Encyclopedia findByName(@Param("name") String name);
}
