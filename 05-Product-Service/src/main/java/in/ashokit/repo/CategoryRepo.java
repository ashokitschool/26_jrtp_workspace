package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Category;
import jakarta.validation.constraints.AssertFalse.List;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	public Category getById(Integer categoryId);
	
}
