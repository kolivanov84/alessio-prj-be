package it.alessio.prj.prj.mapper;

import it.alessio.prj.prj.dto.UserCreateRequestDto;
import it.alessio.prj.prj.dto.UserDto;
import it.alessio.prj.prj.entity.Role;
import it.alessio.prj.prj.entity.User;
import it.alessio.prj.prj.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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

    ModelMapper modelMapper = new ModelMapper();

    public UserDto toDto(User user){
    if (this.modelMapper.getTypeMap(User.class, UserDto.class) == null) {
      TypeMap<User, UserDto> propertyMapper =
          this.modelMapper.createTypeMap(User.class, UserDto.class);
      propertyMapper.addMappings(
          mapper ->
              mapper.map(
                  src -> user.getRuoli().stream().findFirst().get().getName(), UserDto::setRuolo));
    }
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User toEntity(UserCreateRequestDto request) {
        Date now = new Date();
        return User.builder()
                .username(request.getEmail())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nome(request.getNome())
                .cognome(request.getCognome())
                .attivo(true)
                .ruoli(
                        new HashSet<Role>(
                                Arrays.asList(roleRepository.findById(request.getIdRuolo()).get())))
                .build();
    }
}
