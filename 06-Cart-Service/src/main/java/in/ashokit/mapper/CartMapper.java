package in.ashokit.mapper;

import org.modelmapper.ModelMapper;

import in.ashokit.dto.CartDto;
import in.ashokit.entity.Cart;

public class CartMapper {

	public static CartDto convertToDto(Cart cart) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(cart, CartDto.class);
	}

	public static Cart convertToEntity(CartDto cartDto) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(cartDto, Cart.class);
	}

}
