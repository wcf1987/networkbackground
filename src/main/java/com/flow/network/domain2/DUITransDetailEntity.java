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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE)
public class DUITransDetailEntity {
    private String sourceMessID;
    private String  targetMessID;
    private String ID;
    private String targetFieldID;
    private String TName;
    private Integer TransID;
    private String Optional;

    private String Transrule;
    private String Describes;
    private String SourceData;



}
