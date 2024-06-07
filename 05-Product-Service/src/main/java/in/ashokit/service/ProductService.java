package in.ashokit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.ashokit.dto.ProductDto;

public interface ProductService {

	public ProductDto addProduct(ProductDto productDto, MultipartFile file);

	public ProductDto updateProduct(Integer productId, ProductDto productDto, MultipartFile file);

	public List<ProductDto> getAllProducts();

	public ProductDto getProductById(Integer productId);

	public ProductDto deleteProductById(Integer productId);

	public boolean updateStock(Integer productId, Integer quantity);

}
