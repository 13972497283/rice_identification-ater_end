package com.tenglong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("encyclopedia")
@Data
@NoArgsConstructor
public class Encyclopedia implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "diseaseName")
    private String diseaseName;

    private String harm;

    private String symptom;

    @TableField(value = "preventionMethod")
    private String preventionMethod;

    private String image;

    private String other;

    private Integer visits;
}
