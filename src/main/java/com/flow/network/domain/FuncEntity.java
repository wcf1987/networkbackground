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
public class FuncEntity {
    private Integer id;
    private String name;
    private String type;
    private String descri;
    private List<FuncEntity> children;
    //private String hasChildren;
}
