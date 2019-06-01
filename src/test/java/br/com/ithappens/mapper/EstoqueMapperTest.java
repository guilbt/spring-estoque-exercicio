package br.com.ithappens.mapper;

import br.com.ithappens.BaseTest;
import br.com.ithappens.model.Estoque;
import br.com.ithappens.model.Filial;
import br.com.ithappens.model.Produto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("default, prod")
@Transactional
public class EstoqueMapperTest extends BaseTest {
  @Autowired
  private EstoqueMapper estoqueMapper;

  @Test
  public void criarEstoqueTest() {
    Estoque estoque = criarEstoqueDeTesteCom50Qtd();
    assertNotNull(estoque.getId());
  }

  @Test
  public void alterarQuantidadeEstoque(){
    Estoque estoque = criarEstoqueDeTesteCom50Qtd();
    Integer quantidadeInicial = estoque.getQuantidade();
    estoque.addQuantidade(quantidadeItemPedidoEstoqueBase);
    assertEquals(quantidadeInicial+quantidadeItemPedidoEstoqueBase, estoque.getQuantidade());
    estoqueMapper.updateQuantidade(estoque.getId(), estoque.getQuantidade());
    Estoque estoqueBanco = estoqueMapper.getByProdutoIdFilialId(
            estoque.getProduto().getId(),
            estoque.getFilial().getId()
    );
    assertEquals(estoqueBanco, estoque);
    assertEquals(estoqueBanco.getQuantidade(), estoque.getQuantidade());
  }

  @Test
  public void verificaQueEstoqueNaoExisteParaNovoProdutoEmNovaFilial() {
    Produto produto = criarProdutoDeTeste();
    Filial filial = criarFilialDeTeste();

    Estoque estoque = estoqueMapper.getByProdutoIdFilialId(produto.getId(), filial.getId());
    assertNull(estoque);
  }
}