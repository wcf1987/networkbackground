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
public class SysplugEntity {
    private Integer ID;
    private String Name;
    private String Type;
    private String InputPar;
    private String Version;
    private String OutputPar;
    private String Plugfile;
    private String Status;


    private String Describes;
    private String CreateTime;
    private Integer AuthorID;


}
