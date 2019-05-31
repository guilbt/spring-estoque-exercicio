package br.com.ithappens.mapper;

import br.com.ithappens.model.Filial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FilialMapper {

    Filial getById(@Param("id") Long id);

    void insert(@Param("filial") Filial filial);

    void update(@Param("filial") Filial filial);

}
