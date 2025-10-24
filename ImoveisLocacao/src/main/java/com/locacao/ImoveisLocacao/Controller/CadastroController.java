package com.locacao.ImoveisLocacao.Controller;

import com.locacao.ImoveisLocacao.Model.Cadastro;
import com.locacao.ImoveisLocacao.Model.Descricao;
import com.locacao.ImoveisLocacao.Model.Preferencia;
import com.locacao.ImoveisLocacao.Service.CadastroService;
import com.locacao.ImoveisLocacao.Service.DescricaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroController {

    @Autowired
    CadastroService cadastroService;
    
    @Autowired
    DescricaoService descricaoService;

    List<Cadastro> listaCadastro = new ArrayList<>();
    List<Descricao> listaDescricoes = new ArrayList<>();
    
     @PostMapping("/preferencias")
    public ModelAndView gravarPreferencias (@ModelAttribute Preferencia pref, HttpServletResponse response){
        Cookie cookiePrefEstilo = new Cookie("pref-estilo",pref.getEstilo());
        
        cookiePrefEstilo.setDomain("localhost");//disponivel apenas no dominio "localhost"
        cookiePrefEstilo.setHttpOnly(true);//acessivel apenas por HTTP, JS não
        cookiePrefEstilo.setMaxAge(86400);//1 dia
        
        response.addCookie(cookiePrefEstilo);
        
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/")//eh a definição da URL que serah chamada
    public String home(@CookieValue(name="pref-estilo", defaultValue="claro")String tema, Model model) {
         model.addAttribute("css", tema);
        return "inicio";//sera apontado o arquivo da view, HTML a ser chamado
    }

    @GetMapping("/inicio")//eh a definição da URL que serah chamada
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/cadastro")//eh a definição da URL que serah chamada
    public String formulario(Model model) {
        model.addAttribute("cadastro", new Cadastro());
        return "cadastro";
    }
    
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Cadastro cadastro, Model model) {
        
        cadastro.setId(listaCadastro.size() + 1);

        cadastroService.criar(cadastro);
        return "redirect:/listagem";
    } 
   
    @GetMapping("/listagem")
    public String listagem(Model model) {
        model.addAttribute("cadastros", cadastroService.listarTodos());
        return "lista";
    }

    @GetMapping("/exibir")
    public String detalhes(Model model, @RequestParam String codigo) {
        //receber o id do Cadastro
        Integer idCadastro = Integer.parseInt(codigo);
        Cadastro cadastroEncontrado = cadastroService.buscarPorId(idCadastro);
        
          List <Descricao> descricoesEncontradas = new ArrayList<>();
        descricoesEncontradas = descricaoService.listar(idCadastro);

        //retornar os dados do cadastro
        model.addAttribute("cadastro", cadastroEncontrado);
        model.addAttribute("descricao", new Descricao());
        model.addAttribute("descricoes", descricoesEncontradas);

        //chmar a pagina de detalhes do cadastro
        return "exibir";
    }
    @PostMapping("/gravar-descricao")
    public String gravarDescricaoUsuario(@ModelAttribute Cadastro cadastro, @ModelAttribute Descricao descricao ,Model model){
       
        descricao.setCadastro(cadastro);
   
        descricaoService.criar(descricao);
        return"redirect:/listagem";
    }

    @GetMapping("/excluir")
    public String excluir(Model model, @RequestParam String codigo) {
        Integer idCadastro = Integer.parseInt(codigo);
        descricaoService.excluirTodasDescricoesPorCadastro(idCadastro);
        cadastroService.excluir(idCadastro);
        return "redirect:/listagem";
    }

    }
