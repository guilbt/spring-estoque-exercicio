package br.com.ithappens.service;

import br.com.ithappens.exceptions.BadRequestException;
import br.com.ithappens.exceptions.ConflictException;
import br.com.ithappens.exceptions.NotFoundException;
import br.com.ithappens.mapper.*;
import br.com.ithappens.model.*;
import br.com.ithappens.model.dto.PedidoEstoqueDTO;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoEstoqueService {

    PedidoEstoqueMapper pedidoEstoqueMapper;
    ProdutoMapper produtoMapper;
    FilialMapper filialMapper;
    EstoqueMapper estoqueMapper;
    ItemPedidoEstoqueMapper itemPedidoEstoqueMapper;

    @Autowired
    PedidoEstoqueService(
            PedidoEstoqueMapper pedidoEstoqueMapper,
            ProdutoMapper produtoMapper,
            FilialMapper filialMapper,
            EstoqueMapper estoqueMapper,
            ItemPedidoEstoqueMapper itemPedidoEstoqueMapper
    ){
        this.pedidoEstoqueMapper = pedidoEstoqueMapper;
        this.produtoMapper = produtoMapper;

        this.filialMapper = filialMapper;
        this.estoqueMapper = estoqueMapper;
        this.itemPedidoEstoqueMapper = itemPedidoEstoqueMapper;
    }

    @Transactional
    public Long criarPedidoEstoque(PedidoEstoque pedidoEstoque){
        Filial filial = Optional.ofNullable(filialMapper.getById(
            pedidoEstoque.getFilial().getId())
        ).orElseThrow(
            () -> new NotFoundException("A filial do pedido não foi encontrada")
        );

        pedidoEstoqueMapper.insert(pedidoEstoque);

        pedidoEstoque.getItems().stream().forEach(
                itemPedidoEstoque -> {
                    Optional.ofNullable(itemPedidoEstoque)
                            .orElseThrow(
                                    () -> new BadRequestException("Um item do pedido é nulo")
                            );
                    criarItemPedidoEstoque(itemPedidoEstoque, filial, pedidoEstoque);
                }
        );
        return pedidoEstoque.getId();
    }


    private void criarItemPedidoEstoque(ItemPedidoEstoque itemPedidoEstoque, Filial filial, PedidoEstoque pedidoEstoque) {
        if(itemPedidoEstoque.getQuantidade()==0){
            throw new BadRequestException("Item de pedido estoque sem quantidade");
        }

        Produto produtoDeItem = Optional.ofNullable(itemPedidoEstoque.getProduto())
                .orElseThrow(
                        () -> new BadRequestException("Produto de um item do pedido")
                );
        Produto produtoBanco = Optional.ofNullable(
                produtoMapper.getById(produtoDeItem.getId())
        ).orElseThrow(
            () -> new NotFoundException("Produto no banco de um item do pedido")
        );
        Estoque estoqueBanco = Optional.ofNullable(
                estoqueMapper.getByProdutoIdFilialId(
                    produtoBanco.getId(),
                    filial.getId()
        )).orElseGet(
                () -> {
                    if(!pedidoEstoque.getIsEntrada()) {
                        throw new BadRequestException("Não existe estoque de um dos items do pedido");
                    }
                    Estoque estoque = new Estoque(null, produtoBanco, filial, 0);
                    estoqueMapper.insert(estoque);
                    return estoque;
                }
        );
        if(!pedidoEstoque.getIsEntrada()){
            estoqueBanco.retirarQuantidade(itemPedidoEstoque.getQuantidade());
        } else {
            estoqueBanco.addQuantidade(itemPedidoEstoque.getQuantidade());
        }
        this.itemPedidoEstoqueMapper.insertByParams(
                pedidoEstoque.getId(),
                produtoBanco.getId(),
                itemPedidoEstoque.getQuantidade()
        );
        this.estoqueMapper.updateQuantidade(estoqueBanco.getId(), estoqueBanco.getQuantidade());

    }

}



//        List<Estoque> estoque = pedidoEstoque.getItems()
//        .stream().map(
//        itemPedidoEstoque -> Optional.ofNullable(itemPedidoEstoque)
//        .orElseThrow(
//        () -> new NotFoundException("Item do pedido")
//        )
//        ).map(
//        itemPedidoEstoque -> Optional.ofNullable(itemPedidoEstoque.getProduto())
//        .orElseThrow(
//        () -> new NotFoundException("Produto de um item do pedido")
//        )
//        ).map(
//        produtoT -> Optional.ofNullable(produtoMapper.getById(produtoT.getId()))
//        .orElseThrow(
//        () -> new NotFoundException("Produto de um item do pedido")
//        )
//        ).map(
//        produtoBanco -> Optional.ofNullable(estoqueMapper.getByProdutoIdFilialId(
//        produtoBanco.getId(),
//        filial.getId()
//        )).orElseGet(
//        () -> {
//        if(!pedidoEstoque.getIsEntrada()) {
//        throw new ConflictException("Não existe estoque de um dos items do peddo");
//        }
//        return new Estoque(null, produtoBanco, filial, 0);
//        }
//        )
//        ).collect(Collectors.toList());