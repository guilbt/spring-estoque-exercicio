package br.com.ithappens.mapper;

import br.com.ithappens.model.ItemPedidoEstoque;
import br.com.ithappens.model.PedidoEstoque;
import br.com.ithappens.model.Produto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ItemPedidoEstoqueMapper {

    void insertByParams(
            @Param("pedidoEstoqueId") Long pedidoEstoqueId,
            @Param("produtoId") Long produtoId,
            @Param("quantidade") int quantidade
    );

}
