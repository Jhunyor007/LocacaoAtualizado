
package com.locacao.ImoveisLocacao.Service;

import com.locacao.ImoveisLocacao.Model.Descricao;
import com.locacao.ImoveisLocacao.Repository.DescricaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescricaoService {
    @Autowired
    DescricaoRepository descricaoRepository;
    
    public Descricao criar (Descricao descricao){
        descricao.setId(null);
        descricaoRepository.save(descricao);
        return descricao;
    }
    
    public List<Descricao> listar(Integer id){
        return descricaoRepository.findByCadastroId(id);
    }
    
     public void excluirTodasDescricoesPorCadastro(Integer idCadastro){
        for(Descricao reg: listar(idCadastro)){
            excluirDescricao(reg.getId());
        }
    }
     
     public void excluirDescricao(Integer idDescricao){
        Descricao objEncontrado = descricaoRepository.findById(idDescricao).orElseThrow();
        descricaoRepository.deleteById(objEncontrado.getId());
    }
}
