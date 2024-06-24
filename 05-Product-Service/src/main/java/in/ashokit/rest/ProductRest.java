package in.ashokit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.ashokit.dto.ProductDto;
import in.ashokit.props.AppProps;
import in.ashokit.response.ApiResponse;
import in.ashokit.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRest {

    @Autowired
    private ProductService productService;
    
    @Autowired
	private AppProps props;

    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse<ProductDto>> addProduct(@RequestBody ProductDto productDto, @RequestParam("file") MultipartFile file) {
    	

		Map<String, String> messages = props.getMessages();
		
        ProductDto addedProduct = productService.addProduct(productDto, file);
        ApiResponse<ProductDto> response = new ApiResponse<>();
        
        
        

		if (addedProduct != null) {
			response.setStatusCode(201);
			response.setMessage(messages.get("productAdded"));
			response.setData(addedProduct);
			
		} else {
			response.setStatusCode(500);
			response.setMessage(messages.get("productAddErr"));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
       
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> updateProduct(@PathVariable("id") Integer productId, @RequestPart("product") ProductDto productDto, @RequestPart("file") MultipartFile file) {
    	Map<String, String> messages = props.getMessages();
    	ProductDto updatedProduct = productService.updateProduct(productId, productDto, file);
    	ApiResponse<ProductDto> response = new ApiResponse<>();
        
        
    	if (updatedProduct != null) {
			response.setStatusCode(200);
			response.setMessage(messages.get("productUpdate"));
			response.setData(updatedProduct);
		} else {
			response.setStatusCode(500);
			response.setMessage(messages.get("productUpdateErr"));
		}
        
        
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts() {
    	Map<String, String> messages = props.getMessages();
        List<ProductDto> products = productService.getAllProducts();
        ApiResponse<List<ProductDto>> response = new ApiResponse<>();
       
        if (products != null) {
			response.setMessage(messages.get("productFetch"));
			response.setStatusCode(200);
			response.setData(products);
		} else {
			response.setMessage(messages.get("productFetchErr"));
			response.setStatusCode(200);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> getProductById(@PathVariable("id") Integer productId) {
    	Map<String, String> messages = props.getMessages();
    	
    	ProductDto product = productService.getProductById(productId);
        ApiResponse<ProductDto> response = new ApiResponse<>();

		if (product != null) {
			response.setStatusCode(200);
			response.setMessage(messages.get("productSelect"));
			response.setData(product);
		} else {
			response.setStatusCode(500);
			response.setMessage(messages.get("productSelectErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>>  deleteProductById(@PathVariable("id") Integer productId) {
    	
    	Map<String, String> messages = props.getMessages();
       
    	ProductDto deleteProductById = productService.deleteProductById(productId);
    	 ApiResponse<ProductDto> response = new ApiResponse<>();
    	
    	 if (deleteProductById != null) {
			response.setStatusCode(200);
			response.setMessage(messages.get("productDelete"));
			response.setData(deleteProductById);
		} else {
			response.setStatusCode(500);
			response.setMessage(messages.get("productDeleteErr"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

    @PatchMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(@PathVariable("id") Integer productId, @RequestParam("quantity") Integer quantity) {
    	Map<String, String> messages = props.getMessages();
    	boolean updated = productService.updateStock(productId, quantity);
   	 ApiResponse<ProductDto> response = new ApiResponse<>();
   	 
        if (updated) {
            return ResponseEntity.ok("Stock updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update stock");
        }
    }
}

