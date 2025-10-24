
package com.locacao.ImoveisLocacao.Controller;

import com.locacao.ImoveisLocacao.Model.Descricao;
import com.locacao.ImoveisLocacao.Service.DescricaoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/descricao")

public class DescricaoAPIController {
    @Autowired
    DescricaoService descricaoService;
    
   
    @PostMapping("/adicionar")
    public ResponseEntity <Descricao> addDescricao(@RequestBody Descricao descricao){
        var novaDescricao = descricaoService.criar(descricao);
        return new ResponseEntity<>(novaDescricao, HttpStatus.CREATED);
    }
    
    
    @GetMapping("/pesquisar/{idCadastro}")
    public ResponseEntity <List> listarDescricoes(@PathVariable Integer idCadastro){
        List <Descricao> lista = descricaoService.listar(idCadastro);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
