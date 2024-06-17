package in.ashokit.service;

import java.time.LocalDate;
import java.util.List;

import in.ashokit.dto.ProductOrderDto;

public interface OrderService {
	
	public List<ProductOrderDto> addOrder(List<ProductOrderDto> productOrders);
	
	public ProductOrderDto updateOrder(ProductOrderDto productOrderDto);
	
	public List<ProductOrderDto> getOrdersByUserId(Integer userId);
	
	public List<ProductOrderDto> getOrdersByDateAndStatus(String orderDate,
			String orderStatus);
	
	public List<ProductOrderDto> getAllOrders();
	
}