package in.ashokit.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.constants.AppConstants;
import in.ashokit.dto.ProductOrderDto;
import in.ashokit.props.AppProperties;
import in.ashokit.response.ApiResponse;
import in.ashokit.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private AppProperties props;

	@PostMapping("/addOrders")
	public ResponseEntity<ApiResponse<List<ProductOrderDto>>> addOrders(@RequestBody List<ProductOrderDto> ordersDto) {
		ApiResponse<List<ProductOrderDto>> response = new ApiResponse<>();
		Map<String, String> messages = props.getMessages();
		List<ProductOrderDto> ordersAdded = orderService.addOrder(ordersDto);
		if (ordersAdded != null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.ADD_ORDERS));
			response.setData(ordersAdded);
		} else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.ADD_ORDERS_ERR));
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/updateOrder")
	public ResponseEntity<ApiResponse<ProductOrderDto>> updateOrder(@RequestBody ProductOrderDto productOrderDto) {
		ApiResponse<ProductOrderDto> response = new ApiResponse<>();
		Map<String, String> messages = props.getMessages();
		ProductOrderDto updatedOrder = orderService.updateOrder(productOrderDto);
		if (updatedOrder != null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.UPDATE_ORDER));
			response.setData(updatedOrder);
		} else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.UPDATE_ORDER_ERR));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/orders/{userId}")
	public ResponseEntity<ApiResponse<List<ProductOrderDto>>> getOdersByUserId(@PathVariable("userId") Integer userId) {
		ApiResponse<List<ProductOrderDto>> response = new ApiResponse<>();
		Map<String, String>  messages= props.getMessages();
		List<ProductOrderDto> ordersByUserId = orderService.getOrdersByUserId(userId);
		if (ordersByUserId != null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.SELECT_ORDERS_USERID));
			response.setData(ordersByUserId);
		} else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.SELECT_ORDERS_USERID_ERR));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/orders/{orderDate}/{orderStatus}")
	public ResponseEntity<ApiResponse<List<ProductOrderDto>>> getOrderByDateAndStatus(
			@PathVariable("orderDate")String orderDate,
			@PathVariable("orderStatus") String orderStatus) {

		ApiResponse<List<ProductOrderDto>> response = new ApiResponse<>();
		Map<String, String> messages = props.getMessages();
		List<ProductOrderDto> ordersByDateAndStatus = orderService.getOrdersByDateAndStatus(orderDate, orderStatus);
		if (ordersByDateAndStatus != null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.SELECT_ORDERS_DATE_AND_STATUS));
			response.setData(ordersByDateAndStatus);
		} else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.SELECT_ORDERS_DATE_AND_STATUS_ERR));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/orders")
	public ResponseEntity<ApiResponse<List<ProductOrderDto>>> getAllOrders() {
		ApiResponse<List<ProductOrderDto>> response = new ApiResponse<>();
		Map<String, String> messages = props.getMessages();
		List<ProductOrderDto> allOrders = orderService.getAllOrders();
		if (allOrders != null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.FETCH_ORDERS));
			response.setData(allOrders);
		} else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.FETCH_ORDERS_ERR));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
