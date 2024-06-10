package in.ashokit.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.ashokit.constants.AppConstants;
import in.ashokit.dto.ProductDto;
import in.ashokit.entity.Product;
import in.ashokit.exception.ProductServiceException;
import in.ashokit.mapper.CategoryMapper;
import in.ashokit.mapper.ProductMapper;
import in.ashokit.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public ProductDto addProduct(ProductDto productDto, MultipartFile file) {
		// Handle file processing if needed
		Product product = ProductMapper.convertToEntity(productDto);
		Product savedProduct = productRepo.save(product);
		return ProductMapper.convertToDto(savedProduct);
	}

	@Override
	public ProductDto updateProduct(Integer productId, ProductDto productDto, MultipartFile file) {
		// Handle file processing if needed
		Product existingProduct = productRepo.findById(productId)
				.orElseThrow(() -> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND,AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
		existingProduct.setName(productDto.getName());
		existingProduct.setDescription(productDto.getDescription());
		existingProduct.setPrice(productDto.getPrice());
		existingProduct.setStock(productDto.getStock());
		existingProduct.setImage(productDto.getImage());
		existingProduct.setDiscount(productDto.getDiscount());
		existingProduct.setPriceBeforeDiscount(productDto.getPriceBeforeDiscount());

		// Assuming you have a method to convert CategoryDto to Category entity
		existingProduct.setCategory(CategoryMapper.convertToEntity(productDto.getCategory()));

		Product updatedProduct = productRepo.save(existingProduct);
		return ProductMapper.convertToDto(updatedProduct);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepo.findAll();
		return products.stream().map(ProductMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND,AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
		return ProductMapper.convertToDto(product);
	}

	@Override
	public ProductDto deleteProductById(Integer productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND,AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
		productRepo.delete(product);
		return ProductMapper.convertToDto(product);
	}

	@Override
	public boolean updateStock(Integer productId, Integer quantity) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductServiceException(AppConstants.PRODUCT_NOT_FOUND,AppConstants.PRODUCT_NOT_FOUND_ERR_CD));
		product.setStock(quantity);
		productRepo.save(product);
		return true;
	}
}
