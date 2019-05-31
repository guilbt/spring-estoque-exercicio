package br.com.ithappens.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode( of = "id")
@AllArgsConstructor
public class PedidoEstoque implements Cloneable {
    Long id;
    Filial filial;
    List<ItemPedidoEstoque> items;
    Boolean isEntrada;
}
