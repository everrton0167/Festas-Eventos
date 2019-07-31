package br.ifpe.web.missao01.evento;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ifpe.web.missao01.localEvento.LocalEvento;
import br.ifpe.web.missao01.localEvento.LocalEventoDAO;

@Controller
public class EventoController {
	
	@Autowired
	private EventoDAO eventoRep;
	
	@Autowired
	private LocalEventoDAO localEventoRep;
	
	//CHAMA PÁGINA INICIAL
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
	
	//PÁGINA PARA GERAR SENHAS
	@GetMapping("/gerarsenha")
	public String gera_senha() {
		return "gerarsenha";
	}
	
	//PÁGINA PARA ALTERAR SENHA
	@GetMapping("/alterarsenha")
	public String alterar_senha(){
		return "alterarsenha";
	}

	
	//FORMULARIO PARA CADASTRAR EVENTOS
	@GetMapping("/cadastra")
	public ModelAndView exibirAddEvento() {
		ModelAndView mv = new ModelAndView("cadastra_evento");
		mv.addObject("listaEvento", localEventoRep.findAll());
		mv.addObject("evento", new Evento());
		return mv;
	}
	
	//ADICIONANDO AS INFORMAÇÕES DO EVENTO NO BANCO
	@PostMapping("/add")
	public ModelAndView addEvento(@Valid Evento evento, BindingResult br) {
		ModelAndView mv = new ModelAndView("cadastra_evento");
		mv.addObject("listaEvento", localEventoRep.findAll());
		mv.addObject("lista_evento", eventoRep.findAll());
		if(br.hasErrors()) {
			return mv;
		}
		this.eventoRep.save(evento);
		return lista();
	}
	
	//EXIBINDO EVENTOS CADASTRADOS NO BANCO
	@GetMapping("/lista_eventos")
	public ModelAndView lista() { 
		ModelAndView mv = new ModelAndView("lista_eventos");
		mv.addObject("listaEventos",eventoRep.findAll());
		return mv;
	}
	
	//CRIAR EVENTO E EXIBIR PARA ALTERAR
	@GetMapping("/exibirEvento")
	public ModelAndView exibir(@RequestParam Integer codigo) {
		ModelAndView mv = new ModelAndView("cadastra_evento");
		mv.addObject("evento",eventoRep.getOne(codigo));
		mv.addObject("listaEvento", localEventoRep.findAll());
		return mv;
	}
	
	//EXCLUIR EVENTO
	@GetMapping("/removerEvento")
	public ModelAndView remover(@RequestParam Integer codigo) {
		eventoRep.deleteById(codigo);
		return lista();
	}
	
	//PESQUISA EVENTO NO BANCO
	@PostMapping("/pesquisa")
	public ModelAndView pesquisaEvento(@RequestParam(required=false) String nomePesquisa) { 
		ModelAndView mv = new ModelAndView("lista_eventos");
		
		List<Evento> listaEventos;
		if (nomePesquisa == null || nomePesquisa.trim().isEmpty()) {
				listaEventos = this.eventoRep.findAll(Sort.by("nome"));
		} else {
			listaEventos = this.eventoRep.findByNomeContainingIgnoreCase(nomePesquisa);
		}
		mv.addObject("listaEventos",listaEventos);
		return mv;
	}
	
	//OS METODOS ABAIXO SERÁ RESPONSÁVEL POR GERENCIA O LOCAL DO EVENTO
	
	//EXIBE OS LOCAIS DE EVENTOS JÁ CADASTRADOS NO BANCO
	@GetMapping("/localEvento")
	public ModelAndView localEvento() {
		ModelAndView mv = new ModelAndView("localEvento");
		mv.addObject("localEvento", new LocalEvento());
		mv.addObject("listaEvento", localEventoRep.findAll());
		return mv;
	}
	
	//EXIBI A PÁGINA DE CADASTRAR LOCAL DO EVENTO
	@GetMapping("/cad_localEvento")
	public ModelAndView cad_localEvento() {
		ModelAndView mv = new ModelAndView("cad-localEvento");
		mv.addObject("localEvento", new LocalEvento());
		return mv;
	}
	
	//SALVAR LOCAL DE EVENTO NO BANCO
	@PostMapping("/salvarLocal")
	public String cad_localEvento(@ModelAttribute LocalEvento localEvento) {
		this.localEventoRep.save(localEvento);
		return "redirect:/localEvento";
	} 
	
	//EXCLUIR LOCAL DE EVENTO
	@GetMapping("/removerLocal")
	public ModelAndView removerlocal(@RequestParam Integer codigo) {
		localEventoRep.deleteById(codigo);
		return localEvento();
	}
	
	//EDITAR O LOCAL DE EVENTO
	@GetMapping("/editar")
	public ModelAndView editar(@RequestParam Integer codigo) {
		ModelAndView mv = new ModelAndView("cad-localEvento");
		mv.addObject("localEvento", localEventoRep.getOne(codigo));
		return mv;
	}
}
