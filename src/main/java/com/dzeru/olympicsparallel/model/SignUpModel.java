package com.dzeru.olympicsparallel.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
public class SignUpModel {
    private String username;
    private String password;
    private String repeatPassword;
}
