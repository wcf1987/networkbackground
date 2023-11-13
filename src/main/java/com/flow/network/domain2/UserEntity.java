package com.flow.network.domain2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserEntity {
    private Integer id;
    private String userName;
    private String userNickname;
    private String menustr;
    private String password;
    private Integer roleSign;
    private String roleStr;
    private String department;
    private String phone;
    private String email;
    private String status;
    private String describes;
    private String createTime;

}
