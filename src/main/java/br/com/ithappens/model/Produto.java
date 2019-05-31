package br.com.ithappens.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( of = "id")
@AllArgsConstructor
public class Produto {
    private Long id;
    private String descricao;

    public Produto () {
    }
}
