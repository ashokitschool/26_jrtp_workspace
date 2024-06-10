package in.ashokit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.ashokit.constants.AppConstants;
import in.ashokit.dto.CartDto;
import in.ashokit.entity.Cart;
import in.ashokit.exception.CartNotFoundException;
import in.ashokit.mapper.CartMapper;
import in.ashokit.props.AppProps;
import in.ashokit.repo.CartRepo;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private AppProps appProps;

    @Override
    public CartDto addToCart(CartDto cartDto) {
        Cart cart = CartMapper.convertToEntity(cartDto);
        Cart savedCart = cartRepo.save(cart);
        return CartMapper.convertToDto(savedCart);
    }

    @Override
    public CartDto updateCartQuantityById(CartDto cartDto) {
    	
        Optional<Cart> optionalCart = cartRepo.findById(cartDto.getCartId());
        
        Cart cart = optionalCart.orElseThrow(() -> new CartNotFoundException(appProps.getMessages()
        		.get(AppConstants.CART_NOT_FOUND), HttpStatus.NOT_FOUND));
        
        
        cart.setQuantity(cartDto.getQuantity());
        Cart updatedCart = cartRepo.save(cart);
        return CartMapper.convertToDto(updatedCart);
    }

    @Override
    public CartDto getCartByUserId(Integer userId) {
    	
        Optional<Cart> optionalCart = cartRepo.findByUserId(userId);
        
        Cart cart = optionalCart.orElseThrow(() -> new CartNotFoundException(appProps.getMessages()
        		.get(AppConstants.CART_NOT_FOUND_WITH_USERID), HttpStatus.NOT_FOUND));
        
        return CartMapper.convertToDto(cart);
    }

    @Override
    public void deleteCartById(Integer cartId) {
        if (cartRepo.existsById(cartId)) {
            cartRepo.deleteById(cartId);
        } else {
            throw new CartNotFoundException(appProps.getMessages()
            		.get(AppConstants.CART_NOT_FOUND_WITH_ID), HttpStatus.NOT_FOUND);
        }
    }
}
