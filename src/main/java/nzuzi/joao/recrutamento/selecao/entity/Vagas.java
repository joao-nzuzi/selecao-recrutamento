package nzuzi.joao.recrutamento.selecao.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vagas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "titulo", nullable = false)
    private String titulo;
    @Column(columnDefinition = "TEXT", name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "tipo_vaga", nullable = false)
    private String tipoVaga;
    @Column(name = "salario", nullable = false)
    private Double salario;
    @Column(name = "data_registo")
    LocalDateTime dataRegisto;
}
