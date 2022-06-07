package it.alessio.prj.prj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "attivo")
  private boolean attivo;

  @Column(name = "data_creazione")
  private Date dataCreazione;

  @Column(name = "data_modifica")
  private Date dataModifica;

  @PrePersist
  public void setDataCreation() {
    this.dataCreazione = new Date();
    this.dataModifica = new Date();
  }

  @PreUpdate
  public void setDataModifica() {
    if (this.dataCreazione == null) this.dataCreazione = new Date();
    this.dataModifica = new Date();
  }
}
