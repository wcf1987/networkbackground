package com.flow.network.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MessageDetailEntity {
    private Integer id;
    private String name;
    private String ename;
    private String length;
    private String valuestr;
    private String optional;
    private Integer messageID;
    private Integer pid;
    private String type;

    private List<MessageDetailEntity> children;


}
