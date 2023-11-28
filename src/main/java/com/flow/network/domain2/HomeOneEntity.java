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
public class HomeOneEntity {

    private String flownums ;
    private String flowadd;


    private String fieldsnums ;
    private String fieldsadd;

    private String diswnums ;
    private String disadd;

    private String usernums ;
    private String useradd;

}
