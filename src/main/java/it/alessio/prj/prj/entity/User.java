package it.alessio.prj.prj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        private String password;

        @Column(name = "attivo")
        private boolean attivo;

        @Column(name = "email")
        private String email;


        @Column(name = "nome")
        private String nome;

        @Column(name = "cognome")
        private String cognome;


        @Column(name = "data_creazione")
        private Date dataCreazione;

        @Column(name = "data_modifica")
        private Date dataModifica;


        @ManyToMany(
                cascade = {CascadeType.MERGE},
                fetch = FetchType.EAGER)
        @JoinTable(
                name = "user_role",
                joinColumns = @JoinColumn(name = "utente_id"),
                inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
        private Set<Role> ruoli = new HashSet<>();

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
