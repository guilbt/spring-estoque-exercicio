package br.com.ithappens.controller;


import br.com.ithappens.model.PedidoEstoque;
import br.com.ithappens.model.Produto;
import br.com.ithappens.model.dto.PedidoEstoqueDTO;
import br.com.ithappens.service.PedidoEstoqueService;
import br.com.ithappens.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@Stateless
@RestController()
@RequestMapping("/pedidos")
public class PedidoEstoqueController {
    private PedidoEstoqueService pedidoEstoqueService;

    @Autowired
    public PedidoEstoqueController(PedidoEstoqueService pedidoEstoqueService) {
        this.pedidoEstoqueService = pedidoEstoqueService;
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.POST
    )
    public ResponseEntity<Long> create(@RequestBody PedidoEstoque pedidoEstoque) {
        return new ResponseEntity<Long>(
                pedidoEstoqueService.criarPedidoEstoque(pedidoEstoque),
                HttpStatus.CREATED
        );
    }
}
