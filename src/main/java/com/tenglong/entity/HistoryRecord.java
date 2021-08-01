package com.tenglong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@TableName("history_record")
@Data
@NoArgsConstructor
public class HistoryRecord  implements Serializable {
    @TableId(value = "SearchID",type = IdType.AUTO)
    private Integer SearchID;

    private String diseasename;

    @TableField(value = "usingtime")
    private String usingTime;

    @TableField(value = "UsersID")
    private String UsersID;

    private String accuracy;

    private String userloadimg;

}
