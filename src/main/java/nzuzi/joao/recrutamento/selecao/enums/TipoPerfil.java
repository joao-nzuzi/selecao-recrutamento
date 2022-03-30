package nzuzi.joao.recrutamento.selecao.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TipoPerfil {
    JUNIOR("Júnior"),
    PLENO("Pleno"),
    SENIOR("Sênior");

    private String descricao;

    TipoPerfil(String descricao) {
        this.descricao = descricao;
    }
}
