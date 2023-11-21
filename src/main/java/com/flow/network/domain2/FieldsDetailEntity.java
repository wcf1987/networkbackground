package com.flow.network.domain2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class FieldsDetailEntity {
    private Integer ID;
    private String Name;
    private String Type;
    private Integer DFIID;
    private String DFINO;
    private String DFIVersion;
    private String DUINO;
    private String DUIVersion;
    private String EName;
    private String ShortName;
    private String TypeCode;
    private String Length;
    private String TableName;
    private String TableSaveName;

    private String Describes;
    private String CreateTime;
    private Integer AuthorID;


    public FieldsDetailEntity(FieldsDetailUploadEntity up) {
        Name=up.getName();
        DUINO=up.getDUINO();
        DUIVersion=up.getDUIVersion();
        EName=up.getEName();
        ShortName=up.getShortName();
        Type=up.getType();
        TypeCode=up.getTypeCode();
        Length=up.getLength();
        TableName=up.getTableName();
        TableSaveName=up.getTableSaveName();
        Describes=up.getDescribes();
        this.AuthorID=1;
    }
    public FieldsDetailEntity(FieldsDetailUploadAllEntity up) {
        Name=up.getName();
        DFINO=up.getDFINO();
        DUINO=up.getDUINO();
        DUIVersion=up.getDUIVersion();
        EName=up.getEName();
        ShortName=up.getShortName();
        Type=up.getType();
        TypeCode=up.getTypeCode();
        Length=up.getLength();
        TableName=up.getTableName();
        TableSaveName=up.getTableSaveName();
        Describes=up.getDescribes();
        this.AuthorID=1;
    }

}
