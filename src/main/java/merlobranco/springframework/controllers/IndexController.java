package merlobranco.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
	
	@GetMapping({"", "/", "/index"})
	public String getIdenxPage() {
		return "index";
	}

}
