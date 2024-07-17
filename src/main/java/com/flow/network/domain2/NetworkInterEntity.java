package com.flow.network.domain2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class NetworkInterEntity {
    private Integer ID;

    @NotBlank(message = "名称不能为空")
    private String Name;
    private String Type;
    private String IP;
    private String Port;
    private String Protocol;
    private String Describes;
    private String CreateTime;
    private Integer AuthorID;
    private String Pic;

}
