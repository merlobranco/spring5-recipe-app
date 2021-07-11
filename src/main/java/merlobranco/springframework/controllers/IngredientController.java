package merlobranco.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import merlobranco.springframework.services.IngredientService;
import merlobranco.springframework.services.RecipeService;

@Slf4j
@Controller
@RequestMapping("/recipe/{id}/ingredients")
public class IngredientController {
	
	private final RecipeService recipeService;
	private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }
	
	@GetMapping({"", "/"})
	public String showIngredients(@PathVariable(value = "id") Long id, Model model) {
		log.debug("Getting ingredient list for recipe id: " + id);

        // Using Command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("ingredients", ingredientService.findCommadsByRecipeId(id));

        return "recipe/ingredient/list";
	}
	
	@GetMapping("/show/{ingredientId}")
	public String showIngredient(@PathVariable(value = "id") Long id, @PathVariable(value = "ingredientId") Long ingredientId, Model model) {

        model.addAttribute("ingredient", ingredientService.findCommandById(ingredientId));

        return "recipe/ingredient/show";
	}

}
