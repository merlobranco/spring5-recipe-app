package merlobranco.springframework.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import merlobranco.springframework.domain.Category;
import merlobranco.springframework.domain.UnitOfMeasure;
import merlobranco.springframework.repositories.CategoryRepository;
import merlobranco.springframework.repositories.UnitOfMeasureRepository;


@Controller
public class IndexController {
	
	private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }
	
	@GetMapping({"", "/", "/index"})
	public String getIdenxPage() {
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        
        System.out.println("Cat Id is: " + categoryOptional.get().getId());
        System.out.println("UOM ID is: " + unitOfMeasureOptional.get().getId());
		
		return "index";
	}

}
