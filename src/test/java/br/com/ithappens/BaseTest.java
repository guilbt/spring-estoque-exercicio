package br.com.ithappens;


import br.com.ithappens.mapper.EstoqueMapper;
import br.com.ithappens.mapper.FilialMapper;
import br.com.ithappens.mapper.ProdutoMapper;
import br.com.ithappens.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    @Autowired
    private EstoqueMapper estoqueMapper;
    @Autowired
    private ProdutoMapper produtoMapper;
    @Autowired
    private FilialMapper filialMapper;

    protected final int quantidadeEstoqueBase = 50;
    protected final int quantidadeItemPedidoEstoqueBase = 10;

    protected Produto criarProdutoDeTeste() {
        Produto produto = new Produto();
        produto.setDescricao("teste");
        produtoMapper.insert(produto);
        return produto;
    }

    protected Filial criarFilialDeTeste() {
        Filial filial = new Filial();
        filial.setCnpj("999999999");
        filialMapper.insert(filial);
        return filial;
    }

    protected Estoque criarEstoqueDeTesteCom50Qtd() {
        Produto produto = criarProdutoDeTeste();
        Filial filial = criarFilialDeTeste();
        Estoque estoque = new Estoque(null, produto, filial, quantidadeEstoqueBase);
        estoqueMapper.insert(estoque);
        return estoque;
    }

    protected Estoque criarEstoqueDeTesteEmFilialCom50Qtd(Filial filial) {
        Produto produto = criarProdutoDeTeste();
        Estoque estoque = new Estoque(null, produto, filial, quantidadeEstoqueBase);
        estoqueMapper.insert(estoque);
        return estoque;
    }

    protected ItemPedidoEstoque criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(Produto produto) {
        ItemPedidoEstoque itemPedidoEstoque = new ItemPedidoEstoque(null, null, produto, quantidadeItemPedidoEstoqueBase);
        return itemPedidoEstoque;
    }

    protected PedidoEstoque criarPedidoEstoqueMemoria(Boolean isEntrada){
        List<ItemPedidoEstoque> itensPedidosEstoque = new ArrayList<>();
        itensPedidosEstoque.add(criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(criarProdutoDeTeste()));
        itensPedidosEstoque.add(criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(criarProdutoDeTeste()));

        Filial filial = criarFilialDeTeste();
        PedidoEstoque pedidoEstoque = new PedidoEstoque(null, filial, itensPedidosEstoque, isEntrada);
        return pedidoEstoque;
    }

}
