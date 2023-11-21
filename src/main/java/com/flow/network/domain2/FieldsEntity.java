package com.flow.network.domain2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import cn.afterturn.easypoi.excel.annotation.Excel;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class FieldsEntity {
    private Integer ID;
    @Excel(name = "名称")
    private String Name;
    private String Type;
    @Excel(name = "标识号")
    private String IDNO;

    @Excel(name = "版本")
    private String Version;
    @Excel(name = "简称")
    private String ShortName;
    @Excel(name = "适用消息")
    private String ApplicableMess;

    @Excel(name = "注释")
    private String Describes;
    private String CreateTime;
    private Integer AuthorID;


    public FieldsEntity(FieldsUploadEntity fieldsUploadEntity) {
        this.Name= fieldsUploadEntity.getName();
        this.IDNO= fieldsUploadEntity.getIdNO();
        this.Version= fieldsUploadEntity.getVersion();
        this.ShortName= fieldsUploadEntity.getShortName();
        this.ApplicableMess= fieldsUploadEntity.getApplicableMess();
        this.Describes= fieldsUploadEntity.getDescribes();
        this.AuthorID=1;
    }
    public FieldsEntity(String dfino) {
        this.IDNO= dfino;
        this.setName("");
    }
}
