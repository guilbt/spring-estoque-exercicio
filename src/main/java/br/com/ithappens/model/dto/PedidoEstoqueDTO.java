package br.com.ithappens.model.dto;

import br.com.ithappens.model.Filial;
import br.com.ithappens.model.ItemPedidoEstoque;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode( of = "id")
@AllArgsConstructor
public class PedidoEstoqueDTO {
    Long id;
    Long filialId;
    List<Long> itemsIds;
}
