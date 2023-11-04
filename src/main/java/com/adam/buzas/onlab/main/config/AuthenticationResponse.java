package com.adam.buzas.onlab.main.config;

import com.adam.buzas.onlab.main.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String username;
    private Role role;
    private String token;
}
