package in.ashokit.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import in.ashokit.dto.ProductOrderDto;
import in.ashokit.response.ApiResponse;

@FeignClient(name = "07-ORDERS-SERVICE")
public interface OrderClient {
	
	@PutMapping("/updateOrder")
	public ResponseEntity<ApiResponse<ProductOrderDto>> updateOrder(@RequestBody ProductOrderDto productOrderDto);
	
}
