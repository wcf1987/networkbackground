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
public class PackageDetailEntity {
    private Integer ID;
    private String Name;
    private String EName;
    private String Type;
    private String Length;

    private String ArrayOr;
    private String DefaultValue;
    private Integer packID;
    private String Describes;
    private String CreateTime;
    private Integer AuthorID;
    private Double OrderID;
    private Integer SortID ;


}
