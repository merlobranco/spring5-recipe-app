package merlobranco.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import merlobranco.springframework.commands.RecipeCommand;
import merlobranco.springframework.services.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("/show/{id}")
	public String showById(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("recipe", recipeService.findById(id));
		return "recipe/show";
	}
	
	@GetMapping("/form")
    public String create(Model model){
        model.addAttribute("recipe", new RecipeCommand());
		return "recipe/form";
    }
	
	@GetMapping("/form/{id}")
    public String update(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/form";
    }
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id){
		recipeService.deleteById(id);
		return "redirect:/";
	}
	
	
	@PostMapping("/")
    public String save(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/show/" + savedCommand.getId();
    }
}
