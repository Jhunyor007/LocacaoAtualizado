
package com.locacao.ImoveisLocacao.Service;

import com.locacao.ImoveisLocacao.Model.Cadastro;
import com.locacao.ImoveisLocacao.Repository.CadastroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {
    
    @Autowired
    CadastroRepository cadastroRepository;
    
    public Cadastro criar(Cadastro cad){
        cad.setId (null);
        cadastroRepository.save(cad);
        return cad;
    }
    
    public List<Cadastro> listarTodos(){
        return cadastroRepository.findAll();
    }
    
    public Cadastro buscarPorId(Integer id){
        return cadastroRepository.findById(id).orElseThrow();
        
    }
    
    public void excluir (Integer id){
        Cadastro cadastroEncontrado = buscarPorId(id);
        cadastroRepository.deleteById(cadastroEncontrado.getId());
    }
    
    public Cadastro atualizar(Integer id, Cadastro cadastroEnviado){
        Cadastro cadastroEncontrado = buscarPorId(id);
        cadastroEncontrado.setNome(cadastroEnviado.getNome());
        cadastroEncontrado.setCpf(cadastroEnviado.getCpf());
        cadastroEncontrado.setEndereco(cadastroEnviado.getEndereco());
        cadastroEncontrado.setDescricao(cadastroEnviado.getDescricao());
        cadastroEncontrado.setDias(cadastroEnviado.getDias());
        cadastroEncontrado.setValor(cadastroEnviado.getValor());
        cadastroEncontrado.setPagamento(cadastroEnviado.getPagamento());
        cadastroRepository.save(cadastroEncontrado);
        return cadastroEncontrado;
    }
}
