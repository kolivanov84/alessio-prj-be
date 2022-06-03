package it.alessio.prj.prj.service;

import it.alessio.prj.prj.dto.LoginResponseDto;
import it.alessio.prj.prj.dto.UserCreateRequestDto;
import it.alessio.prj.prj.entity.User;
import it.alessio.prj.prj.mapper.UserMapper;
import it.alessio.prj.prj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service(value = "userService")
public class UtenteService implements UserDetailsService {

    @Value("${expire.token.forgot.password.minutes}")
    public long EXPIRE_TOKEN_AFTER_MINUTES;

    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired UserMapper utentemapper;

    public Integer createUser(UserCreateRequestDto utenteDto) {
        return userRepository.save(utentemapper.toEntity(utenteDto)).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        LoginResponseDto userDetail =
                new LoginResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        getAuthority(user));
        return userDetail;
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRuoli()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    private String generateToken() {
        return UUID.randomUUID().toString() + UUID.randomUUID();
    }

    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }
}
