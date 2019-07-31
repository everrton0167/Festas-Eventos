package br.ifpe.web.missao01.convidado;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoService convidadoService;
	
	@GetMapping("/listaconvidado")
	public ModelAndView listarConvidado() {
		ModelAndView mv= new ModelAndView("listaconv");
		mv.addObject("convidados", convidadoService.ListarTodos());
		mv.addObject(new Convidado());
		return mv;
	}
	
	@PostMapping("/convidados")
	public String salvar(@Valid @ModelAttribute Convidado convidado,BindingResult br,RedirectAttributes ra) throws Exception{
		
		if (br.hasErrors()) {
			ra.addFlashAttribute("Errors",br.getAllErrors());
		} else {
			try {
				this.convidadoService.salvar(convidado);
			}catch(Exception e) {
				ra.addFlashAttribute("mesagem","Não foi possível adicionar convidado a lista: " +e.getMessage());
			}
		}
		
		return "redirect:/listaconvidado";
	}

	
}
