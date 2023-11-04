package com.adam.buzas.onlab.main.converter;

import com.adam.buzas.onlab.main.dto.UserDTO;
import com.adam.buzas.onlab.main.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;


public class UserToDTOConverter implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User source) {
        return new UserDTO(source.getId(), source.getName(), source.getUsername(), source.getEmail(), source.getRole());
    }
}
