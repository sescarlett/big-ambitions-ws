package com.sscarlett.big_ambitions_companion.model;

import lombok.Data;

@Data
public class Users {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer userRole;
}
