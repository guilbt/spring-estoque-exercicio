package br.com.ithappens.controller;

import br.com.ithappens.BaseTest;
import br.com.ithappens.exceptions.BadRequestException;
import br.com.ithappens.mapper.ProdutoMapper;
import br.com.ithappens.model.Produto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default, prod")
@Transactional
public class ProdutoControllerTest extends BaseTest {
    @Autowired
    private ProdutoController produtoController;
    @Autowired
    private ProdutoMapper produtoMapper;

    @Test
    public void criarProdutoTest() {
        Produto produto = new Produto(null, "novo produto");
        ResponseEntity<Long> produtoIdResponse = produtoController.createUpdate(produto);
        assertEquals(HttpStatus.CREATED, produtoIdResponse.getStatusCode());
        produto.setId(produtoIdResponse.getBody());
        Produto produtoCriado = produtoMapper.getById(produto.getId());
        assertEquals(produtoCriado, produto);
    }

    @Test
    public void updateProdutoTest() {
        Produto produto = criarProdutoDeTeste();
        ResponseEntity<Long> produtoIdResponse = produtoController.createUpdate(produto);
        assertEquals(HttpStatus.OK, produtoIdResponse.getStatusCode());
        Produto produtoCriado = produtoMapper.getById(produto.getId());
        assertEquals(produtoCriado, produto);
    }

    @Test(expected = BadRequestException.class)
    public void failUpdateProdutoTest(){
        Produto produto = new Produto(new Random().nextLong(), "novo produto");
        produtoController.createUpdate(produto);
    }

    @Test
    public void getAllProdutos() {
        produtoController.getAll();
    }

}
