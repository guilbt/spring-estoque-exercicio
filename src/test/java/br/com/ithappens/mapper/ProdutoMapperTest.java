package br.com.ithappens.mapper;

import br.com.ithappens.BaseTest;
import br.com.ithappens.controller.ProdutoController;
import br.com.ithappens.model.Produto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default, prod")
@Transactional
public class ProdutoMapperTest extends BaseTest {

  @Autowired
  private ProdutoMapper produtoMapper;

  @Test
  public void criarProdutoTest() {
    Produto produto = criarProdutoDeTeste();
    assertNotNull(produto.getId());
  }

}