package com.flow.network.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RuleEntity {
    private Integer id;
    private String name;
    private String type;
    private Integer sourceid;
    private Integer targetid;
    private String sourcedata;
    private String targetdata;

}
