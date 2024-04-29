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
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class TransClassfyEntity {
    private Integer ID;
    private String Name;
    private String Type;
    private String Code;
    private String Describes;
    private String CreateTime;
    private Integer AuthorID;
    private List<TransTemplateEntity> children;

}
