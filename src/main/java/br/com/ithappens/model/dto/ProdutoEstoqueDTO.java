package br.com.ithappens.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( of = "id")
@AllArgsConstructor
public class ProdutoEstoqueDTO {
    Long produtoId;
    String produtoDesc;
    Long estoqueId;
    int quantidade;
}
