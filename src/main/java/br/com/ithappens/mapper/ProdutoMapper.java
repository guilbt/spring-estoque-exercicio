package br.com.ithappens.mapper;

import br.com.ithappens.model.Produto;
import br.com.ithappens.model.dto.ProdutoEstoqueDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProdutoMapper {

//    List<Produto> getProdutos();

    List<Produto> selectAll();

    Produto getById(@Param("id") Long id);

    void insert(@Param("produto") Produto produto);

    void update(@Param("produto") Produto produto);

    List<ProdutoEstoqueDTO> getProdutoEstoqueDTOByFilialId(
            @Param("filialId") Long filialId
    );
}
