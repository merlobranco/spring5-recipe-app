package merlobranco.springframework.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import merlobranco.springframework.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	Optional<Category> findByDescription(String description);
}
