package br.com.ithappens.controller;


import br.com.ithappens.model.Produto;
import br.com.ithappens.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//@Stateless
@RestController()
@RequestMapping("/produtos")
public class ProdutoController {
    ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @RequestMapping(
            value = "",
            method = { RequestMethod.PUT, RequestMethod.POST }
    )
    public ResponseEntity<Long> createUpdate(@RequestBody Produto produto) {
        if(Optional.ofNullable(produto.getId()).isPresent()){
            return new ResponseEntity<>(
                    produtoService.updateProduto(produto),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    produtoService.createProduto(produto),
                    HttpStatus.CREATED
            );
        }
    }


    @RequestMapping(
            value = "",
            method = { RequestMethod.GET}
    )
    public ResponseEntity<List<Produto>> getAll() {
            return new ResponseEntity<>(
                    produtoService.getAll(),
                    HttpStatus.OK
            );
    }
}
