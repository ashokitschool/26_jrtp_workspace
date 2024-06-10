package in.ashokit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import in.ashokit.dto.ProductDto;
import in.ashokit.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductRest {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto, @RequestParam("file") MultipartFile file) {
        ProductDto addedProduct = productService.addProduct(productDto, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Integer productId, @RequestPart("product") ProductDto productDto, @RequestPart("file") MultipartFile file) {
        ProductDto updatedProduct = productService.updateProduct(productId, productDto, file);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Integer productId) {
        ProductDto product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") Integer productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(@PathVariable("id") Integer productId, @RequestParam("quantity") Integer quantity) {
        boolean updated = productService.updateStock(productId, quantity);
        if (updated) {
            return ResponseEntity.ok("Stock updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update stock");
        }
    }
}

