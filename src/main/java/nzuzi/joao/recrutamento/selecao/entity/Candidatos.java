package nzuzi.joao.recrutamento.selecao.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidatos implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "perfil", nullable = false)
    private String perfil;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "pretensao_salarial", nullable = false)
    private Double pretensaoSalarial;
    @Column(name = "curriculum_vitae", nullable = false)
    private String curriculumVitae;
    @Column(name = "data_registo")
    private LocalDateTime dataRegisto;
    @ManyToMany
    @JoinTable(
            name = "candidatos_tecnologias",
            joinColumns = @JoinColumn(name = "candidato_id"),
            inverseJoinColumns = @JoinColumn(name = "tecnologias_id"))
    Set<Tecnologias> tecnologias;
}
