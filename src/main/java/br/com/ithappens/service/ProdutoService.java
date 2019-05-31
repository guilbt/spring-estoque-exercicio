package br.com.ithappens.service;

import br.com.ithappens.exceptions.BadRequestException;
import br.com.ithappens.exceptions.ConflictException;
import br.com.ithappens.mapper.ProdutoMapper;
import br.com.ithappens.model.Filial;
import br.com.ithappens.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    ProdutoMapper produtoMapper;

    @Autowired
    ProdutoService(ProdutoMapper produtoMapper){
        this.produtoMapper = produtoMapper;
    }

    /**
     * @param produto without id
     * @return new produto id
     */
    public Long createProduto(Produto produto){
        produtoMapper.insert(produto);
        return produto.getId();
    }

    /**
     * @param produto with id
     * @return produto id
     */
    public Long updateProduto(Produto produto){
        Optional<Produto> optionalProduto = Optional.ofNullable(produtoMapper.getById(produto.getId()));
        if(optionalProduto.isPresent()){
            produtoMapper.update(produto);
            return produto.getId();
        } else {
            throw new BadRequestException("Não existe produto com o id inserido");
        }
    }


    public List<Produto> getAll() {
        return produtoMapper.selectAll();
    }

}
