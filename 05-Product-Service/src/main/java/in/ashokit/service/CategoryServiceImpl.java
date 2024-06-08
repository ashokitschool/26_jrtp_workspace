package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dto.CategoryDto;
import in.ashokit.entity.Category;
import in.ashokit.mapper.CategoryMapper;
import in.ashokit.repo.CategoryRepo;


<<<<<<< HEAD
@Service
=======
>>>>>>> 34845250af9c179c617660009dc3358659931237
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private CategoryMapper cMapper;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		
		Category category= categoryRepo.findById(categoryDto.getCategoryId()).orElseThrow();
		
		if(category==null)
		{
			Category c=cMapper.convertToEntity(categoryDto);
			
			Category category2 = categoryRepo.save(c);
			
			return cMapper.convertToDto(category2);
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		
	Category c=new Category();
		
		 Category id = categoryRepo.findById(categoryId).orElseThrow();
		 
		 
		 if(id!=null)
		 {
			 c.setCategoryId(categoryId);
			 c.setCategoryName(categoryDto.getCategoryName());
			 
			 return CategoryMapper.convertToDto(c);
			 
		 }
		 else
		 {
			 return null;
		 }
	
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		
			List<Category> all = categoryRepo.findAll();
			
			List<CategoryDto> dtos= new ArrayList<>();
			
			 for (Category category : all) {
		            
		            CategoryDto toDto = CategoryMapper.convertToDto(category);
		              dtos.add(toDto);
		        }
			
		
		return dtos;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		
		Category id = categoryRepo.findById(categoryId).orElseThrow();
		
			if(id!=null)
			{
				return cMapper.convertToDto(id);
			}
			else
			{

				return null;
			}
		
	}

	@Override
	public CategoryDto deleteCategoryById(Integer categoryId) {
		
		Category category = categoryRepo.getById(categoryId);
		
		if(category!=null)
		{
		CategoryDto cDto= cMapper.convertToDto(category);
		
		categoryRepo.deleteById(categoryId);
		
		return cDto;
		}
		else
		{
			return null;
		}
	}

}
