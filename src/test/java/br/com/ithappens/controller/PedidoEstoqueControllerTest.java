package br.com.ithappens.controller;

import br.com.ithappens.BaseTest;
import br.com.ithappens.exceptions.BadRequestException;
import br.com.ithappens.exceptions.NotFoundException;
import br.com.ithappens.mapper.EstoqueMapper;
import br.com.ithappens.mapper.ProdutoMapper;
import br.com.ithappens.model.*;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default, prod")
@Transactional
public class PedidoEstoqueControllerTest extends BaseTest {
    @Autowired
    private PedidoEstoqueController pedidoEstoqueController;
    @Autowired
    private ProdutoMapper produtoMapper;
    @Autowired
    private EstoqueMapper estoqueMapper;

    @Test
    public void criarPedidoEstoqueEntrada() {

        PedidoEstoque pedidoEstoque = criarPedidoEstoqueMemoria(true);

        ResponseEntity<Long> pedidoEstoqueIdResponse = pedidoEstoqueController.create(pedidoEstoque);
        assertEquals(HttpStatus.CREATED, pedidoEstoqueIdResponse.getStatusCode());
        assertNotNull(pedidoEstoqueIdResponse.getBody());
    }

    @Test
    public void verificarSeEntradasESaidasEstaoAtualizandoCorretamente(){
        PedidoEstoque pedidoEstoqueEntrada = criarPedidoEstoqueMemoria(true);

        pedidoEstoqueController.create(pedidoEstoqueEntrada);

        PedidoEstoque pedidoEstoqueSaida = new PedidoEstoque(
                null,
                pedidoEstoqueEntrada.getFilial(),
                pedidoEstoqueEntrada.getItems(),
                false
        );
        pedidoEstoqueController.create(pedidoEstoqueSaida);

        Long filialId = pedidoEstoqueSaida.getFilial().getId();
        pedidoEstoqueSaida.getItems().forEach(
                itemPedidoEstoque -> {
                    Long produtoId = itemPedidoEstoque.getProduto().getId();
                    Estoque estoque = estoqueMapper.getByProdutoIdFilialId(produtoId, filialId);
                    assertEquals(0, estoque.getQuantidade());
                }
        );
        pedidoEstoqueController.create(pedidoEstoqueEntrada);
        pedidoEstoqueEntrada.getItems().forEach(
                itemPedidoEstoque -> {
                    Long produtoId = itemPedidoEstoque.getProduto().getId();
                    Estoque estoque = estoqueMapper.getByProdutoIdFilialId(produtoId, filialId);
                    assertEquals(itemPedidoEstoque.getQuantidade(), estoque.getQuantidade());
                }
        );
    }

    @Test(expected = BadRequestException.class)
    public void failPedidoEstoqueOnPedidoEstoqueSaidaSemEstoque() {
        pedidoEstoqueController.create(criarPedidoEstoqueMemoria(false));
    }


    @Test(expected = BadRequestException.class)
    public void failPedidoEstoqueCreateOnNullItem() {
        List<ItemPedidoEstoque> itensPedidosEstoque = new ArrayList<>();
        itensPedidosEstoque.add(null);
        itensPedidosEstoque.add(criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(criarProdutoDeTeste()));

        Filial filial = criarFilialDeTeste();
        PedidoEstoque pedidoEstoque = new PedidoEstoque(null, filial, itensPedidosEstoque, true);
        pedidoEstoqueController.create(pedidoEstoque);
    }

    @Test(expected = BadRequestException.class)
    public void failPedidoEstoqueCreateOnItemSemQuantidade() {
        List<ItemPedidoEstoque> itensPedidosEstoque = new ArrayList<>();
        ItemPedidoEstoque iPESemQuantidade = criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(criarProdutoDeTeste());
        iPESemQuantidade.setQuantidade(0);
        itensPedidosEstoque.add(iPESemQuantidade);
        itensPedidosEstoque.add(criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(criarProdutoDeTeste()));

        Filial filial = criarFilialDeTeste();
        PedidoEstoque pedidoEstoque = new PedidoEstoque(null, filial, itensPedidosEstoque, true);
        pedidoEstoqueController.create(pedidoEstoque);
    }

    @Test(expected = BadRequestException.class)
    public void failPedidoEstoqueCreateOnItemSemProduto() {
        List<ItemPedidoEstoque> itensPedidosEstoque = new ArrayList<>();
        ItemPedidoEstoque iPESemQuantidade = criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(criarProdutoDeTeste());
        iPESemQuantidade.setProduto(null);
        itensPedidosEstoque.add(iPESemQuantidade);
        itensPedidosEstoque.add(criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(criarProdutoDeTeste()));

        Filial filial = criarFilialDeTeste();
        PedidoEstoque pedidoEstoque = new PedidoEstoque(null, filial, itensPedidosEstoque, true);
        pedidoEstoqueController.create(pedidoEstoque);
    }

    @Test(expected = NotFoundException.class)
    public void failPedidoEstoqueCreateOnItemSemProdutoExistenteNoBanco() {
        List<ItemPedidoEstoque> itensPedidosEstoque = new ArrayList<>();
        ItemPedidoEstoque iPESemQuantidade = criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(criarProdutoDeTeste());
        iPESemQuantidade.setProduto(new Produto());
        itensPedidosEstoque.add(iPESemQuantidade);
        itensPedidosEstoque.add(criarItemPedidoEstoqueMemoriaSemPedidoEstoqueCom10Qtd(criarProdutoDeTeste()));

        Filial filial = criarFilialDeTeste();
        PedidoEstoque pedidoEstoque = new PedidoEstoque(null, filial, itensPedidosEstoque, true);
        pedidoEstoqueController.create(pedidoEstoque);
    }
}
