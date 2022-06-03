package it.alessio.prj.prj.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserCreateRequestDto {

    @ApiModelProperty(notes = "nome", example = "Mario")
    private String nome;

    @ApiModelProperty(notes = "cognome", example = "Rossi")
    private String cognome;

    @ApiModelProperty(notes = "email", example = "test@test.it", required = true)
    @NotNull
    private String email;

    @ApiModelProperty(notes = "password", required = true)
    @NotNull
    private String password;

    @ApiModelProperty(notes = "id ruolo", required = true, example = "1")
    @NotNull
    private Integer idRuolo;
}
