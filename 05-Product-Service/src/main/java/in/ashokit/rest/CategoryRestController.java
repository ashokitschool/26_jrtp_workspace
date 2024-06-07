package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.CategoryDto;
import in.ashokit.service.CategoryService;

@RestController
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;
	
	
	
	@GetMapping("/addCategory")
	public String addCategoryPage(Model model)
	{
		model.addAttribute("addCategory", new CategoryDto());
		
		return "addCategory";
	}
	
	
/*
@PostMapping("/addCategory")
	public String addCategory(CategoryDto categoryDto, Model model)
	{
		CategoryDto id = categoryService.getCategoryById(categoryDto.getCategoryId());
		
		if(id!=null)
		{
			model.addAttribute("emsg", "Given Category already exist..");
			
		}
		else
		{
			CategoryDto category = categoryService.addCategory(categoryDto);
			model.addAttribute("smsg","new Category added" );
		}
		
		return"addCategory";
	}
	
	*/
	
	 @PostMapping("/addCategory")
	    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
	        // Call the service layer to add the category
	        CategoryDto addedCategory = categoryService.addCategory(categoryDto);
	        
	        // Return response with the added category DTO
	        return ResponseEntity.status(HttpStatus.CREATED).body(addedCategory);
	    }
	 
	 
	  @PutMapping("/{id}")
	    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Integer categoryId,
	                                                      @RequestBody CategoryDto categoryDto) {
	        
	        CategoryDto updatedCategory = categoryService.updateCategory(categoryId, categoryDto);
	        
	    
	        return ResponseEntity.ok(updatedCategory);
	    }
	  
	  @DeleteMapping("/{id}")
	    public String deleteCategory(@PathVariable Integer id) {
	        try {
	            CategoryDto categoryById = categoryService.deleteCategoryById(id);
	            return "Category deleted successfully";
	        } catch (RuntimeException e) {
	            return "Category not found";
	        }
	    }
}
