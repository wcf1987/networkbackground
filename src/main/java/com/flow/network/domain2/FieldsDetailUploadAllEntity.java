package com.flow.network.domain2;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class FieldsDetailUploadAllEntity {
    private Integer ID;
    @Excel(name = "名称")
    private String name;
    private Integer DFIID;

    @Excel(name="DFI标识号")
    private String dFINO;

    @Excel(name = "类型")
    private String type;
    private String DFIVersion;
    @Excel(name = "DUI标识号")
    private String dUINO;
    @Excel(name = "DUI版本")
    private String dUIVersion;
    @Excel(name = "引用名")
    private String eName;
    @Excel(name = "简称")
    private String shortName;
    @Excel(name = "格式内码")
    private String typeCode;
    @Excel(name = "位数")
    private String length;
    @Excel(name = "标准表名")
    private String tableName;
    @Excel(name = "表存储名")
    private String tableSaveName;

    @Excel(name = "说明")
    private String describes;
    private String CreateTime;
    private Integer AuthorID;


}
