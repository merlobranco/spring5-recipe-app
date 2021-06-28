package merlobranco.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import merlobranco.springframework.services.RecipeService;

@Slf4j
@Controller
@RequestMapping("/recipe/{id}/ingredients")
public class IngredientController {
	
	private final RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
	
	@GetMapping({"", "/"})
	public String showIngredients(@PathVariable(value = "id") Long id, Model model) {
		log.debug("Getting ingredient list for recipe id: " + id);

        // Using Command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/ingredient/list";
	}

}
