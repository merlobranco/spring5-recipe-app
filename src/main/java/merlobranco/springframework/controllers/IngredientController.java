package merlobranco.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import merlobranco.springframework.commands.IngredientCommand;
import merlobranco.springframework.commands.UnitOfMeasureCommand;
import merlobranco.springframework.services.IngredientService;
import merlobranco.springframework.services.RecipeService;
import merlobranco.springframework.services.UnitOfMeasureService;

@Slf4j
@Controller
@RequestMapping("/recipe/{id}/ingredients")
public class IngredientController {
	
	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
		this.uomService = uomService;
    }
	
	@GetMapping({"", "/"})
	public String showIngredients(@PathVariable(value = "id") Long id, Model model) {
		log.debug("Getting ingredient list for recipe id: " + id);

        // Using Command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("ingredients", ingredientService.findCommadsByRecipeId(id));
        model.addAttribute("recipeId", id);

        return "recipe/ingredient/list";
	}
	
	@GetMapping("/show/{ingredientId}")
	public String showIngredient(@PathVariable(value = "id") Long id, @PathVariable(value = "ingredientId") Long ingredientId, Model model) {

        model.addAttribute("ingredient", ingredientService.findCommandById(ingredientId));

        return "recipe/ingredient/show";
	}
	
	@GetMapping("/form")
    public String createIngredient(@PathVariable(value = "id") Long id, Model model) {
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(id);
		ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("uomList", uomService.findAllCommands());
        
        return "recipe/ingredient/form";
    }
	
	@GetMapping("/form/{ingredientId}")
	public String updateIngredient(@PathVariable(value = "id") Long id, @PathVariable(value = "ingredientId") Long ingredientId, Model model) {

        model.addAttribute("ingredient", ingredientService.findCommandById(ingredientId));
        model.addAttribute("uomList", uomService.findAllCommands());

        return "recipe/ingredient/form";
	}
	
	@PostMapping({"", "/"})
    public String saveIngredient(@ModelAttribute IngredientCommand command){
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/show/" + savedCommand.getId();
    }

}
