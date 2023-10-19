package com.flow.network.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PageParmInfo {
    private Integer pageNum;
    private Integer pageSize;
    private Integer pid;
    private Integer uid;
    private String name;
    private Integer pidNear;
}
