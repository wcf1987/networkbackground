package com.flow.network.domain2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class MessTraslateDetailEntity {
    private Integer ID;
    private Integer OutID;
    private String Name;
    private String OutType;
    private List<MessTraslateDetailEntity> children;
    private Integer NestID;
    private Integer PID;
    private String ShortName;
    private String EName;

    private String TName;
    private Integer TransID;
    private Integer TransDetailID;
    private String Optional;
    private String Transrule;
    private String Describes;
    private String CreateTime;
    private String Funcrule;
    private String SourceData;

    private String Flag;
    private String Type;
    private String Length;

}
