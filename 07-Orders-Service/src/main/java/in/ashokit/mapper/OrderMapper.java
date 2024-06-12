package in.ashokit.mapper;

import org.modelmapper.ModelMapper;

import in.ashokit.dto.ProductOrderDto;
import in.ashokit.entity.Orders;

public class OrderMapper {

	private static final ModelMapper mapper = new ModelMapper();

	public static ProductOrderDto convertToDto(Orders order) {
		return mapper.map(order, ProductOrderDto.class);
	}

	public static Orders convertToEntity(ProductOrderDto productOrderDto) {
		return mapper.map(productOrderDto, Orders.class);
	}

}
