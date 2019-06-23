package br.ifpe.web.missao01;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/evento")
public class EventoController {
	
	@Autowired
	private EventoDAO eventoRep;
	
	@Autowired
	private LocalEventoDAO localEventoRep;
	
	//chama pagina inicial
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	//chama a lista de convidados
	@GetMapping("/listaconv")
	public String lista_convidados() {
		return "listaconv";
	}
	
	//pagina para gerar senhas
	@GetMapping("/gerarsenha")
	public String gera_senha() {
		return "gerarsenha";
	}
	
	//pagina para alterar senha
	@GetMapping("/alterarsenha")
	public String alterar_senha(){
		return "alterarsenha";
	}
	
	//pagina para ecluir senha
	@GetMapping("/excluirsenha")
	public String excluir_senha() {
		return "excluirsenha";
	}
	
	//formulario de eventos
	@GetMapping("/cadastra")
	public ModelAndView exibirAddEvento() {
		ModelAndView mv = new ModelAndView("cadastra_evento");
		mv.addObject("listaEvento", localEventoRep.findAll());
		mv.addObject("evento", new Evento());
		return mv;
	}
	
	//adicionando as informações ao banco
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
	
	//exibindo informações do banco
	@GetMapping("/lista_eventos")
	public ModelAndView lista() { 
		ModelAndView mv = new ModelAndView("lista_eventos");
		mv.addObject("listaEventos",eventoRep.findAll());
		return mv;
	}
	
	//criar evento ou exibir para alterar
	@GetMapping("/exibirEvento")
	public ModelAndView exibir(@RequestParam Integer codigo) {
		ModelAndView mv = new ModelAndView("cadastra_evento");
		mv.addObject("evento",eventoRep.getOne(codigo));
		mv.addObject("listaEvento", localEventoRep.findAll());
		return mv;
	}
	
	//remover um evento
	@GetMapping("/removerEvento")
	public ModelAndView remover(@RequestParam Integer codigo) {
		eventoRep.deleteById(codigo);
		return lista();
	}
	
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
	//os metodos abaixo será responsavel para gerencia local do evento

	//exibi o locais de evento já cadastrado
	@GetMapping("/localEvento")
	public ModelAndView localEvento() {
		ModelAndView mv = new ModelAndView("localEvento");
		mv.addObject("localEvento", new LocalEvento());
		mv.addObject("listaEvento", localEventoRep.findAll());
		return mv;
	}
	
	//chamara a pagina o formulario local de evento
	@GetMapping("/cad_localEvento")
	public ModelAndView cad_localEvento() {
		ModelAndView mv = new ModelAndView("cad-localEvento");
		mv.addObject("localEvento", new LocalEvento());
		return mv;
	}
	
	//salvar local de evento
	@PostMapping("/salvarLocal")
	public String cad_localEvento(@ModelAttribute LocalEvento localEvento) {
		this.localEventoRep.save(localEvento);
		return "redirect:/evento/localEvento";
	} 
	
	//
	//remover um local de evento
	@GetMapping("/removerLocal")
	public ModelAndView removerlocal(@RequestParam Integer codigo) {
		localEventoRep.deleteById(codigo);
		return localEvento();
	}
	
	//editar local do evento
	@GetMapping("/editar")
	public ModelAndView editar(@RequestParam Integer codigo) {
		ModelAndView mv = new ModelAndView("cad-localEvento");
		mv.addObject("localEvento", localEventoRep.getOne(codigo));
		return mv;
	}
	
}
