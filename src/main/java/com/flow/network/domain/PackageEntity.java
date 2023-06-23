package com.flow.network.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PackageEntity {
    private Integer id;
    private String name;
    private String type;
    private Integer packID;
    private Integer unpackID;
    private String packName;
    private String unpackName;
}
