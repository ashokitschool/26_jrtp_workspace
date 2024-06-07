package in.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Category;
import in.ashokit.entity.Product;
import in.ashokit.repo.CategoryRepo;
import in.ashokit.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public Product saveProduct(Product productEntity) {
		Category categoryEntity = categoryRepo.findById(productEntity.getCategory().getId()).orElseThrow();
		productEntity.setCategory(categoryEntity);
		return productRepo.save(productEntity);
	}
	
	public List<Product> getProducts() {
		List<Product> all = productRepo.findAll();
		return all;
	}
	
	@Override
	public Product getProductById(Integer id) {
		Product productEntity = productRepo.findById(id).orElseThrow();
		return productEntity;
	}
	
	//update the category
	@Override
	public Product updateProduct(Product product) {
		Product productEntity=new Product();
		productEntity.setProductId(product.getProductId());
		productEntity.setProductName(product.getProductName());
		productEntity.setCategory(product.getCategory());
		productEntity.setDescription(product.getDescription());
		productEntity.setDiscount(product.getDiscount());
		productEntity.setImage(product.getImage());
		productEntity.setPrice(product.getPrice());
		productEntity.setStock(product.getStock());
			return productEntity;
		}
	
	@Override
	public void deleteProduct(Integer id) {
		productRepo.deleteById(id);
	}

	@Override
	public boolean updateStock(Integer id, Integer quatity) {
		// TODO Auto-generated method stub
		return false;
	}

}
