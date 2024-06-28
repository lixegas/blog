package com.lixega.blog.model.mapper;
import com.lixega.blog.model.dto.UserDTO;
import com.lixega.blog.model.dto.request.RegistrationRequest;
import com.lixega.blog.model.dto.response.RegistrationResponse;
import com.lixega.blog.model.entity.UserAccount;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@Mapper(imports = Instant.class, componentModel = "spring")
@Named("UserMapper")
public interface UserMapper {

    UserAccount mapToUser(RegistrationRequest registrationRequest);

    RegistrationResponse mapToRegistrationResponse(UserAccount userAccount);

    @Named("mapToDto")
    @Mappings({
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "surname", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "birthday", ignore = true)
    })
    UserDTO mapToDTO(UserAccount userAccount);

    UserDTO mapToAuthorizedDTO(UserAccount userAccount);
}