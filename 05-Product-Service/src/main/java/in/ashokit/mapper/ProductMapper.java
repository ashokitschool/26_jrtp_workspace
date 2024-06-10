package in.ashokit.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import in.ashokit.dto.ProductDto;
import in.ashokit.entity.Product;
@Component
public class ProductMapper {

	private static final ModelMapper mapper = new ModelMapper();

	public static ProductDto convertToDto(Product product) {
		return mapper.map(product, ProductDto.class);
	}

	public static Product convertToEntity(ProductDto productDto) {
		return mapper.map(productDto, Product.class);
	}

}