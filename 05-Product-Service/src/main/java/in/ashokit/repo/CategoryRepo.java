package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
