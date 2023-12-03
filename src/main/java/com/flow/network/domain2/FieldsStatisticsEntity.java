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
public class FieldsStatisticsEntity implements Comparable<FieldsStatisticsEntity> {

    private String DFINO;
    private String DUINum;
    private String DUIPer;
    @Override
    public int compareTo(FieldsStatisticsEntity p) {
        return Integer.valueOf(p.getDUINum()) - Integer.valueOf(this.getDUINum());
    }


}
