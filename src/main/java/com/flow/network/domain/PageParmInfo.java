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
    private Integer gateid;
    private Integer transid;
    private String ttype;
    private Integer id;
    private Integer uid;
    private String name;
    private String ename;
    private String appmessstr;
    private String order;
    private String orderField;
    private Integer pidNear;
    private Integer nestid;
    private Integer gatewayid;
    private String type;
}
