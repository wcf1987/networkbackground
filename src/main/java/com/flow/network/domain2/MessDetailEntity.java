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
public class MessDetailEntity {
    private Integer ID;
    private Integer SortID ;
    private String Name;
    private String Flag;
    private List<MessDetailEntity> children;
    private String TType;
    private Double OrderID;
    private Integer OutID;
    private String OutType;
    private Integer NestID;
    private Integer PID;

    private String Type;
    private String ShortName;
    private String EName;
    private String Length;


    private String Describes;
    private String CreateTime;
    private Integer AuthorID;
    private Integer FieldsID;
    private Integer DFIID;
    private String MaxGroupNum;
    private String DefaultVal;

}
