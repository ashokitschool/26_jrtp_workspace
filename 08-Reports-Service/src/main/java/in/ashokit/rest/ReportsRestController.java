package in.ashokit.rest;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import in.ashokit.Service.ReportsService;
import in.ashokit.dto.OrderDto;
import in.ashokit.entity.OrderStatus;
import in.ashokit.exception.ReportsServiceException;
import in.ashokit.props.AppProperties;
import in.ashokit.response.ApiResponse;
import in.ashokit.utils.ExcelGenerator;
import in.ashokit.utils.PdfGenerator;

import org.springframework.http.HttpStatus;

@RestController
public class ReportsRestController {

	@Autowired
	private ReportsService reportsService;
	
	@Autowired
	private AppProperties props;
	
	@GetMapping("/allOrderExcel")
	public ResponseEntity<InputStreamResource> downloadExcel(){
//		Map<String, String> messages = props.getMessages();
		List<OrderDto> orders = reportsService.getAllOrders();
//		ApiResponse<InputStreamResource> response = new ApiResponse<>();
		if(orders!=null) {
			try {
				ByteArrayInputStream in = ExcelGenerator.generateExcel(orders);
                InputStreamResource resource = new InputStreamResource(in);

                HttpHeaders headers = new HttpHeaders();
				
//				response.setMessage(messages.get("orderFetch"));
//				response.setStatusCode(200);
//				response.setData(resource);
				
				return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
			}
			catch(IOException e) {
//				response.setMessage(messages.get("orderFetchErr"));
//                response.setStatusCode(500);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		else {
//			 response.setMessage(messages.get("orderFetchErr"));
//	            response.setStatusCode(500);
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();}
			
		}
		
		@GetMapping("/allOrderPdf")
		public ResponseEntity<InputStreamResource> allOrderPdf() throws ReportsServiceException{		
			List<OrderDto> orders = reportsService.getAllOrders();		
			if(orders!=null) {
				ByteArrayInputStream in = PdfGenerator.generatePdf(orders);
				InputStreamResource resource = new InputStreamResource(in);

				HttpHeaders headers = new HttpHeaders();
				
				
				return ResponseEntity
				        .ok()
				        .headers(headers)
				        .contentType(MediaType.APPLICATION_OCTET_STREAM)
				        .body(resource);
			}
			else {
			
		            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
		
		
		@GetMapping("/OrderPdf/{status}")
		public ResponseEntity<InputStreamResource> OrderStatusPdf(@PathVariable ("status")OrderStatus status) throws ReportsServiceException{		
			List<OrderDto> orders= reportsService.orderByStatus(status);
			if(orders!=null) {
				ByteArrayInputStream in = PdfGenerator.generatePdf(orders);
				InputStreamResource resource = new InputStreamResource(in);

				HttpHeaders headers = new HttpHeaders();
				
				
				return ResponseEntity
				        .ok()
				        .headers(headers)
				        .contentType(MediaType.APPLICATION_OCTET_STREAM)
				        .body(resource);
			}
			else {
			
		            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
		
		}	
		
		
		@GetMapping("/OrderExcel/{status}")
		public ResponseEntity<InputStreamResource> OrderStatusExcel(@PathVariable ("status")OrderStatus status) throws ReportsServiceException, IOException{		
			List<OrderDto> orders= reportsService.orderByStatus(status);
			if(orders!=null) {
				ByteArrayInputStream in = ExcelGenerator.generateExcel(orders);
				InputStreamResource resource = new InputStreamResource(in);

				HttpHeaders headers = new HttpHeaders();
				
				
				return ResponseEntity
				        .ok()
				        .headers(headers)
				        .contentType(MediaType.APPLICATION_OCTET_STREAM)
				        .body(resource);
			}
			else {
			
		            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
		
		}	
		
		
		
		
}