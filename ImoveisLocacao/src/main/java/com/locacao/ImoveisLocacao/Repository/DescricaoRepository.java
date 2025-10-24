
package com.locacao.ImoveisLocacao.Repository;

import com.locacao.ImoveisLocacao.Model.Descricao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescricaoRepository extends JpaRepository <Descricao,Integer>{
    List <Descricao> findByCadastroId(Integer id);
}
