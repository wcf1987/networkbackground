package com.flow.network.domain2;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class FieldsUploadEntity implements Serializable {
    private Integer ID;
    @Excel(name = "名称")
    private String name;
    private String Type;
    @Excel(name = "标识号")
    private String idNO;

    @Excel(name = "版本")
    private String version;
    @Excel(name = "简称")
    private String shortName;
    @Excel(name = "适用消息")
    private String applicableMess;

    @Excel(name = "注释")
    private String describes;
    private String CreateTime;
    private Integer AuthorID;


    public FieldsUploadEntity(FieldsDetailUploadEntity fieldsDetailUploadEntity) {

    }
}
