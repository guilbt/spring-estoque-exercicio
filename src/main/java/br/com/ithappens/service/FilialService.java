package br.com.ithappens.service;

import br.com.ithappens.exceptions.BadRequestException;
import br.com.ithappens.exceptions.NotFoundException;
import br.com.ithappens.mapper.FilialMapper;
import br.com.ithappens.mapper.ProdutoMapper;
import br.com.ithappens.model.Filial;
import br.com.ithappens.model.Produto;
import br.com.ithappens.model.dto.ProdutoEstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilialService {

    FilialMapper filialMapper;
    ProdutoMapper produtoMapper;

    @Autowired
    FilialService(
            FilialMapper filialMapper,
            ProdutoMapper produtoMapper){
        this.filialMapper = filialMapper;
        this.produtoMapper = produtoMapper;
    }

    public Long createFilial(Filial filial){
        filialMapper.insert(filial);
        return filial.getId();
    }

    /**
     * @param filial with id
     * @return produto id
     */
    public Long updateFilial(Filial filial){
        Optional<Filial> optionalProduto = Optional.ofNullable(filialMapper.getById(filial.getId()));
        if(optionalProduto.isPresent()){
            filialMapper.update(filial);
            return filial.getId();
        } else {
            throw new BadRequestException("Não existe filial com o id inserido");
        }
    }


    /**
     * @param filialId
     * @return list of estoques de produtos por filial
     */
    public List<ProdutoEstoqueDTO> buscarEstoquesDeProdutosPorFilial(Long filialId){
        Filial filial = Optional.ofNullable(filialMapper.getById(
                filialId
            )).orElseThrow(
                () -> new NotFoundException("A filial com id "+filialId+ " não foi encontrada")
        );

        return produtoMapper.getProdutoEstoqueDTOByFilialId(filialId);
    }


}
