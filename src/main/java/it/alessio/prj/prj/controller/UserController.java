package it.alessio.prj.prj.controller;

import io.swagger.annotations.ApiOperation;
import it.alessio.prj.prj.dto.UserDto;
import it.alessio.prj.prj.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UtenteService userService;

    @RequestMapping(value = "/{idUser}", method = RequestMethod.GET)
    @ApiOperation(value = "Get User", notes = "return a user")
    public ResponseEntity getUser(@PathVariable Integer idUser) {
        UserDto user = userService.findById(idUser);
        if(user != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
