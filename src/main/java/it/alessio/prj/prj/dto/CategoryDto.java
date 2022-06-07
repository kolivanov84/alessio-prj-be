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
public class CategoryDto implements Serializable {

  private Integer id;

  private String name;

  private boolean attivo;

  private String description;

  private Date dataCreazione;

  private Date dataModifica;
}
