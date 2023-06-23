package com.flow.network.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AppDetailEntity {
    private Integer id;
    private String name;
    private String ename;
    private String length;
    private String valuestr;
    private String optional;
    private Integer appID;


}
