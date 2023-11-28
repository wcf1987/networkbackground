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
public class HomeResourceEntity {
    private String all ;
    private String cpu ;
    private String memory;

    public void setAll(){
        int a=Integer.valueOf(cpu);
        int b=Integer.valueOf(memory);
        int c=Integer.valueOf(disk);
        int d=Integer.valueOf(network);
        int e=((a+b+c+d)/4);
        all=String.valueOf(e);
    }
    private String disk ;
    private String network;


}
