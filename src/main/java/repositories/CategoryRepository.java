package repositories;

import org.springframework.data.repository.CrudRepository;

import merlobranco.springframework.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
