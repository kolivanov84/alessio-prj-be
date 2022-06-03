package it.alessio.prj.prj.controller;

import io.swagger.annotations.ApiOperation;
import it.alessio.prj.prj.service.UtenteService;
import it.alessio.prj.prj.config.TokenProvider;
import it.alessio.prj.prj.dto.LoginRequestDto;
import it.alessio.prj.prj.dto.LoginResponseDto;
import it.alessio.prj.prj.dto.UserCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired UtenteService userService;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired TokenProvider jwtTokenUtil;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "Register", notes = "New user")
    public ResponseEntity<Integer> saveUser(@RequestBody UserCreateRequestDto user) {
        Integer newId = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newId);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "Login", notes = "Login user")
    public ResponseEntity<String> generateToken(
            @RequestBody LoginRequestDto loginUser, HttpServletRequest req)
            throws AuthenticationException {

        final Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginResponseDto loggedUser = (LoginResponseDto) authentication.getPrincipal();

        final String token =
                jwtTokenUtil.generateToken(
                        authentication, loggedUser.getIdUtente(), loggedUser.getUsername());

        return ResponseEntity.ok(token);
    }
}
