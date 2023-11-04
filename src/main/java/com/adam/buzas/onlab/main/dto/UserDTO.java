package com.adam.buzas.onlab.main.dto;

import com.adam.buzas.onlab.main.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String name;
    private String userName;
    private String email;
    private Role role;

}
