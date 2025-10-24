package com.locacao.ImoveisLocacao.Controller;

import com.locacao.ImoveisLocacao.Model.Cadastro;
import com.locacao.ImoveisLocacao.Service.CadastroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")

public class CadastroAPIController {

    @Autowired
    CadastroService cadastroService;
    
    @GetMapping("/listar")
        public ResponseEntity<List> listar() {
            List<Cadastro> listaCadastro = cadastroService.listarTodos();
            return new ResponseEntity<>(listaCadastro, HttpStatus.OK);
        }
        
    @GetMapping("/pesquisar/{id}")
        public ResponseEntity<Cadastro> getCadastroById(@PathVariable Integer id) {
            Cadastro cadastro = cadastroService.buscarPorId(id);
            return new ResponseEntity<>(cadastro, HttpStatus.OK);
        }   
        
        
    @PostMapping("/adicionar")
        public ResponseEntity<Cadastro> addCadastro(@RequestBody Cadastro cad) {
            var novoCadastro = cadastroService.criar(cad);
            return new ResponseEntity<>(novoCadastro, HttpStatus.CREATED);
        }
        
    @DeleteMapping("/excluir/{id}")
        public ResponseEntity<?> delete(@PathVariable Integer id) {
            cadastroService.excluir(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        
    @PutMapping("/atualizar/{id}")
        public ResponseEntity<Cadastro> editCadastro(@PathVariable Integer id, @RequestBody Cadastro cad) {
            var editarCadastro = cadastroService.atualizar(id, cad);
            return new ResponseEntity<>(editarCadastro, HttpStatus.OK);
        }
}
