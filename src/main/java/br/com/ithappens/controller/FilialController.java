package br.com.ithappens.controller;


import br.com.ithappens.model.Filial;
import br.com.ithappens.model.Produto;
import br.com.ithappens.model.dto.ProdutoEstoqueDTO;
import br.com.ithappens.service.FilialService;
import br.com.ithappens.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Stateless
@RestController()
@RequestMapping("/filiais")
public class FilialController {
    FilialService filialService;

    @Autowired
    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    @RequestMapping(
            value = "",
            method = { RequestMethod.PUT, RequestMethod.POST }
    )
    public ResponseEntity<Long> createUpdate(@RequestBody Filial filial) {
        if(Optional.ofNullable(filial.getId()).isPresent()){
            return new ResponseEntity<>(
                    filialService.updateFilial(filial),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    filialService.createFilial(filial),
                    HttpStatus.CREATED
            );
        }
    }

    @RequestMapping(
            value = "/{filialId}/estoque",
            method = { RequestMethod.GET }
    )
    public ResponseEntity<List<ProdutoEstoqueDTO>> buscarEstoque(
            @PathVariable("filialId") Long filialId
    ) {
        return new ResponseEntity<>(
                filialService.buscarEstoquesDeProdutosPorFilial(filialId),
                HttpStatus.OK
        );
    }
}
