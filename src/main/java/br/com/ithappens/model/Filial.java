package br.com.ithappens.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode( of = "id")
public class Filial {
    private Long id;
    private String cnpj;
//    private Empresa empresa;
//    private List<Produto> produtos;

    public Filial () {}

    public Filial(Long id, String cnpj){
        this.id = id;
        this.cnpj = cnpj;
    }
}
