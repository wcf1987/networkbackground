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
public class SerialInterEntity {
    private Integer ID;
    private String Name;
    private String Type;
    private String SerialNO;
    private String BAUD;
    private String DataBit;
    private String StopBit;
    private String FlowControl;
    private String Describes;
    private String CreateTime;
    private Integer AuthorID;


}
