package br.ifpe.web.missao01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	//pagina para cadastra eventos
	@GetMapping("/cadastra")
	public ModelAndView exibirAddEvento() {
		ModelAndView mv = new ModelAndView("cadastra_evento");
		mv.addObject("evento", new Evento());
		return mv;
	}
	
	//adicionando as informações ao banco
	@PostMapping("/add")
	public String addEvento(@ModelAttribute Evento evento) {
		System.out.println(evento);
		eventoRep.save(evento);
		return"redirect:/evento/lista_eventos";
	}
	
	//exibindo informações do banco
	@GetMapping("/lista_eventos")
	public ModelAndView lista() { 
		ModelAndView mv = new ModelAndView("lista_eventos");
		mv.addObject("lista",eventoRep.findAll());
		return mv;
	}
	
	//exibir evento para alteração de informações ou criar evento
	@GetMapping("/exibirEvento")
	public ModelAndView exibir(@RequestParam Integer codigo) {
		ModelAndView mv = new ModelAndView("cadastra_evento");
		mv.addObject("evento",eventoRep.getOne(codigo));
		return mv;
	}
	
	//remover um evento
	@GetMapping("/removerEvento")
	public ModelAndView remover(@RequestParam Integer codigo) {
		eventoRep.deleteById(codigo);
		return lista();
	}
	
	//codigo abaixo será responsavel por o local do evento
	
	//lista os locais de eventos
	@GetMapping("/localEvento")
	public ModelAndView localEvento() {
		ModelAndView mv = new ModelAndView("localEvento");
		mv.addObject("localEvento", new LocalEvento());
		mv.addObject("listaEvento",localEventoRep.findAll());
		return mv;
	}
	
	//chamará o formulario para cadastra local
	@GetMapping("/cad_localEvento")
	public ModelAndView cad_localEvento(){
		ModelAndView mv = new ModelAndView("cad-localEvento");
		mv.addObject("localEvento", new LocalEvento());
		return mv;
	}
	
	//salvará o no banco
	@PostMapping("/salvarLocal")
	public String salvarLocal(@ModelAttribute LocalEvento localEvento){
		this.localEventoRep.save(localEvento);
		return "redirect:/evento/localEvento";
	}
	
	
	
}
