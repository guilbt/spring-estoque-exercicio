package br.com.ithappens.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( of = "id")
@AllArgsConstructor
public class ItemPedidoEstoque {
    Long id;
    PedidoEstoque pedidoEstoque;
    Produto produto;
    int quantidade;
//    double valorUnidade;
}
