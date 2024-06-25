package com.lixega.blog.model.mapper;
import com.lixega.blog.model.dto.request.RegistrationRequest;
import com.lixega.blog.model.dto.response.RegistrationResponse;
import com.lixega.blog.model.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@Mapper(imports = Instant.class)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "name", source = "registrationRequest.name"),
            @Mapping(target = "surname", source = "registrationRequest.surname"),
            @Mapping(target = "username", source = "registrationRequest.username"),
            @Mapping(target = "birthday", source = "registrationRequest.birthday"),
            @Mapping(target = "email", source = "registrationRequest.email"),
            @Mapping(target = "password", source = "registrationRequest.password")
    })
    UserAccount mapToUser(RegistrationRequest registrationRequest);

    default String mapPassword(String password, @Autowired PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }

    @Mappings({
            @Mapping(target ="name", source = "userAccount.name"),
            @Mapping(target = "surname", source = "userAccount.surname"),
            @Mapping(target = "username", source = "userAccount.email"),
            @Mapping(target = "password", source = "userAccount.password"),
            @Mapping(target = "createdAt", expression = "java(Instant.now())"),
            @Mapping(target = "birthday", source = "userAccount.birthday")
    })
    RegistrationResponse mapToResponse(UserAccount userAccount);
}
