package in.ashokit.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import in.ashokit.dto.CartDto;
import in.ashokit.entity.Cart;

@Component
public class CartMapper {
	
	private static final ModelMapper mapper = new ModelMapper();

	public static CartDto convertToDto(Cart cart) {
		return mapper.map(cart, CartDto.class);
	}

	public static Cart convertToEntity(CartDto cartDto) {
		return mapper.map(cartDto, Cart.class);
	}

}
