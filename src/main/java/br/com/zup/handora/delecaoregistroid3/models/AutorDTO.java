package br.com.zup.handora.delecaoregistroid3.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorDTO {

    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 2500)
    private String descricao;

    public AutorDTO() {}

    public AutorDTO(@NotBlank String nome, @NotBlank @Size(max = 2500) String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
