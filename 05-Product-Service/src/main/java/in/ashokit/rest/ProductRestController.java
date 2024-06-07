package in.ashokit.rest;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {
package in.ashokit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Product;
import in.ashokit.service.ProductServiceImpl;

@RestController
public class ProductRest {
	
	@Autowired
	private ProductServiceImpl productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody Product product){
		 if (product.getCategory() == null || product.getCategory().getId() == null) {
		        return new ResponseEntity<>("Category must be provided", HttpStatus.BAD_REQUEST);
		    }else {
		product.setCategory(product.getCategory());
		productService.saveProduct(product);
		return new ResponseEntity<>("product Added",HttpStatus.CREATED);
	}
	}
	@GetMapping("/products")
	public ResponseEntity<List<Product> > getCategories(){
		List<Product> products = productService.getProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getCategory(@PathVariable("id") Integer id){
		 Product product= productService.getProductById(id);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@PutMapping("/updatedProduct/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product updatedProduct) {
	    Product existingProduct = productService.getProductById(id);
	    
	    if (existingProduct == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    // Update fields
	    if (updatedProduct.getProductName() != null) {
	        existingProduct.setProductName(updatedProduct.getProductName());
	    }
	    if (updatedProduct.getPrice() != null) {
	        existingProduct.setPrice(updatedProduct.getPrice());
	    }
	    if (updatedProduct.getDescription() != null) {
	        existingProduct.setDescription(updatedProduct.getDescription());
	    }
	    if (updatedProduct.getStock() != null) {
	        existingProduct.setStock(updatedProduct.getStock());
	    }
	    if (updatedProduct.getImage() != null) {
	        existingProduct.setImage(updatedProduct.getImage());
	    }
	    if (updatedProduct.getDiscount() != null) {
	        existingProduct.setDiscount(updatedProduct.getDiscount());
	    }
	    if (updatedProduct.getPriceBeforeDiscount() != null) {
	        existingProduct.setPriceBeforeDiscount(updatedProduct.getPriceBeforeDiscount());
	    }
	    if (updatedProduct.getCategory() != null) {
	        existingProduct.setCategory(updatedProduct.getCategory());
	    }
	    
	    productService.saveProduct(existingProduct);
	    
	    return new ResponseEntity<>(existingProduct, HttpStatus.OK);
	}

	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable("id") Integer id){
		  productService.deleteProduct(id);
		return new ResponseEntity<>(" Product deleted",HttpStatus.OK);
	}
	
}

}
