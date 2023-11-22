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
public class LogEntity {
    private Integer ID;
    private String Username;
    private String Type;

    private String Logs;
    private String CreateTime;


    public LogEntity(String type, String name, String msg) {
        Type=type;
        Username=name;
        Logs=msg;
    }
}
