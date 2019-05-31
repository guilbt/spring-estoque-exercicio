package br.com.ithappens.mapper;

import br.com.ithappens.model.Estoque;
import br.com.ithappens.model.Filial;
import br.com.ithappens.model.Produto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EstoqueMapper {

    Estoque getByProdutoIdFilialId(
        @Param("produtoId") Long produtoId,
        @Param("filialId") Long filialId
    );

    void updateQuantidade(@Param("id") Long id,
                                 @Param("quantidade") int quantidade);

    void insert(@Param("estoque") Estoque estoque);
}
