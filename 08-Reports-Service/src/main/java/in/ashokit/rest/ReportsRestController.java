package in.ashokit.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.Service.ReportsService;
import in.ashokit.dto.OrderDto;
import in.ashokit.entity.OrderStatus;
import in.ashokit.exception.ReportsServiceException;
import in.ashokit.utils.ExcelGenerator;
import in.ashokit.utils.PdfGenerator;

@RestController
public class ReportsRestController {

	@Autowired
	private ReportsService reportsService;

	@GetMapping("/allOrderExcel")
	public ResponseEntity<InputStreamResource> downloadExcel() {
		List<OrderDto> orders = reportsService.getAllOrders();
		if (orders != null) {
			try {
				ByteArrayInputStream in = ExcelGenerator.generateExcel(orders);
				InputStreamResource resource = new InputStreamResource(in);
				HttpHeaders headers = new HttpHeaders();
				return ResponseEntity.ok()
									 .headers(headers)
									 .contentType(MediaType.APPLICATION_OCTET_STREAM)
									 .body(resource);
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/allOrderPdf")
	public ResponseEntity<InputStreamResource> allOrderPdf() throws ReportsServiceException {
		List<OrderDto> orders = reportsService.getAllOrders();
		if (orders != null) {
			ByteArrayInputStream in = PdfGenerator.generatePdf(orders);
			InputStreamResource resource = new InputStreamResource(in);
			HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok()
								 .headers(headers)
								 .contentType(MediaType.APPLICATION_OCTET_STREAM)
								 .body(resource);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/OrderPdf/{status}")
	public ResponseEntity<InputStreamResource> OrderStatusPdf(@PathVariable("status") OrderStatus status)
			throws ReportsServiceException {
		List<OrderDto> orders = reportsService.orderByStatus(status);
		if (orders != null) {
			ByteArrayInputStream in = PdfGenerator.generatePdf(orders);
			InputStreamResource resource = new InputStreamResource(in);
			HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok()
							     .headers(headers)
							     .contentType(MediaType.APPLICATION_OCTET_STREAM)
							     .body(resource);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/OrderExcel/{status}")
	public ResponseEntity<InputStreamResource> OrderStatusExcel(@PathVariable("status") OrderStatus status)
			throws ReportsServiceException, IOException {
		List<OrderDto> orders = reportsService.orderByStatus(status);
		if (orders != null) {
			ByteArrayInputStream in = ExcelGenerator.generateExcel(orders);
			InputStreamResource resource = new InputStreamResource(in);
			HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok()
							     .headers(headers)
							     .contentType(MediaType.APPLICATION_OCTET_STREAM)
							     .body(resource);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}

	@GetMapping("/OrderdatesExcel")
	public ResponseEntity<InputStreamResource> OrderBetweenDates(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
			throws ReportsServiceException, IOException {
		List<OrderDto> orders = reportsService.getOrdersBetweenDate(startDate, endDate);
		if (orders != null) {
			ByteArrayInputStream in = ExcelGenerator.generateExcel(orders);
			InputStreamResource resource = new InputStreamResource(in);
			HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok()
							     .headers(headers)
							     .contentType(MediaType.APPLICATION_OCTET_STREAM)
							     .body(resource);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/OrderdatesPdf")
	public ResponseEntity<InputStreamResource> OrderBetweenDatesPdf(
			@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate)
			throws ReportsServiceException {
		List<OrderDto> orders = reportsService.getOrdersBetweenDate(startDate, endDate);
		if (orders != null) {
			ByteArrayInputStream in = PdfGenerator.generatePdf(orders);
			InputStreamResource resource = new InputStreamResource(in);
			HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok()
								 .headers(headers)
								 .contentType(MediaType.APPLICATION_OCTET_STREAM)
								 .body(resource);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

}