package it.alessio.prj.prj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

  private Integer id;

  private String username;

  private boolean attivo;

  private String email;

  private String nome;

  private String cognome;

  private Date dataCreazione;

  private Date dataModifica;

  private String ruolo;
}
