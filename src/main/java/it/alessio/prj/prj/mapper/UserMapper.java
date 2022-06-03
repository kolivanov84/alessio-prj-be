package it.alessio.prj.prj.mapper;

import it.alessio.prj.prj.dto.UserCreateRequestDto;
import it.alessio.prj.prj.entity.Role;
import it.alessio.prj.prj.entity.User;
import it.alessio.prj.prj.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Component
public class UserMapper {

    @Autowired PasswordEncoder passwordEncoder;
    @Autowired RoleRepository roleRepository;

    public User toEntity(UserCreateRequestDto request) {
        Date now = new Date();
        return User.builder()
                .username(request.getEmail())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .dataCreazione(now)
                .dataModifica(now)
                .nome(request.getNome())
                .cognome(request.getCognome())
                .ruoli(
                        new HashSet<Role>(
                                Arrays.asList(roleRepository.findById(request.getIdRuolo()).get())))
                .build();
    }
}
