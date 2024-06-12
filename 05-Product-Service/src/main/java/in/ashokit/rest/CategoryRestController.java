package in.ashokit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.CategoryDto;
import in.ashokit.props.AppProps;
import in.ashokit.response.ApiResponse;
import in.ashokit.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AppProps props;

	@PostMapping
	public ResponseEntity<ApiResponse<CategoryDto>> addCategory(@RequestBody CategoryDto categoryDto) {
		
		Map<String, String> messages = props.getMessages();
		
		CategoryDto addedCategory = categoryService.addCategory(categoryDto);

		ApiResponse<CategoryDto> response = new ApiResponse<>();

		if (addedCategory != null) {
			response.setStatusCode(201);
			response.setMessage(messages.get("categoryAdded"));
			response.setData(addedCategory);
		} else {
			response.setStatusCode(500);
			response.setMessage(messages.get("categoryAddErr"));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@PathVariable("id") Integer categoryId,
			@RequestBody CategoryDto categoryDto) {
		
		Map<String, String> messages = props.getMessages();
		CategoryDto updatedCategory = categoryService.updateCategory(categoryId, categoryDto);
		ApiResponse<CategoryDto> response = new ApiResponse<>();

		if (updatedCategory != null) {
			response.setStatusCode(200);
			response.setMessage(messages.get("categoryUpdate"));
			response.setData(updatedCategory);
		} else {
			response.setStatusCode(500);
			response.setMessage(messages.get("categoryUpdateErr"));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories() {
		
		Map<String, String> messages = props.getMessages();
		List<CategoryDto> categories = categoryService.getAllCategories();

		ApiResponse<List<CategoryDto>> response = new ApiResponse<>();

		if (categories != null) {
			response.setMessage(messages.get("categoryFetch"));
			response.setStatusCode(200);
			response.setData(categories);
		} else {
			response.setMessage(messages.get("categoryFetchErr"));
			response.setStatusCode(200);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(@PathVariable("id") Integer categoryId) {
		Map<String, String> messages = props.getMessages();
		CategoryDto category = categoryService.getCategoryById(categoryId);

		ApiResponse<CategoryDto> response = new ApiResponse<>();

		if (category != null) {
			response.setStatusCode(200);
			response.setMessage(messages.get("categorySelect"));
			response.setData(category);
		} else {
			response.setStatusCode(500);
			response.setMessage(messages.get("categorySelectErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> deleteCategoryById(@PathVariable("id") Integer categoryId) {
		
		Map<String, String> messages = props.getMessages();
		CategoryDto deletedCategory = categoryService.deleteCategoryById(categoryId);

		ApiResponse<CategoryDto> response = new ApiResponse<>();

		if (deletedCategory != null) {
			response.setStatusCode(200);
			response.setMessage(messages.get("categoryDelete"));
			response.setData(deletedCategory);
		} else {
			response.setStatusCode(500);
			response.setMessage(messages.get("categoryDeleteErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
