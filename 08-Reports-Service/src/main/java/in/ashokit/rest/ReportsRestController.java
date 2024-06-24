package in.ashokit.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.Service.ReportsService;
import in.ashokit.dto.OrderDto;
import in.ashokit.dto.ReportsDto;
import in.ashokit.exception.ReportsServiceException;
import in.ashokit.utils.ExcelGenerator;
import in.ashokit.utils.PdfGenerator;

@RestController
@RequestMapping("/reports")
public class ReportsRestController {

	@Autowired
	private ReportsService reportsService;

	@GetMapping("/FilterOrderExcel")
	public ResponseEntity<InputStreamResource> downloadExcel(ReportsDto reportsDto) throws ReportsServiceException {
		List<OrderDto> orders = new ArrayList<>();

		if (reportsDto.getStatus() != null) {
			orders = reportsService.orderByStatus(reportsDto.getStatus());
		}

		if (reportsDto.getStartDate() != null || reportsDto.getEndDate() != null) {
			List<OrderDto> dateFilteredOrders = reportsService.getOrdersBetweenDate(reportsDto.getStartDate(),
					reportsDto.getEndDate());
			if (!orders.isEmpty()) {
				orders.retainAll(dateFilteredOrders);
			} else {
				orders = dateFilteredOrders;
			}
		}

		if (reportsDto.getStatus() == null && reportsDto.getStartDate() == null && reportsDto.getEndDate() == null) {
			orders = reportsService.getAllOrders();
		}

		if (orders != null) {
			try {
				ByteArrayInputStream in = ExcelGenerator.generateExcel(orders);
				InputStreamResource resource = new InputStreamResource(in);
				HttpHeaders headers = new HttpHeaders();
				return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(resource);
			} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/FilterOrderPdf")
	public ResponseEntity<InputStreamResource> downloadPdf(ReportsDto reportsDto) throws ReportsServiceException {

		List<OrderDto> orders = new ArrayList<>();

		if (reportsDto.getStatus() != null) {
			orders = reportsService.orderByStatus(reportsDto.getStatus());
		}

		if (reportsDto.getStartDate() != null || reportsDto.getEndDate() != null) {
			List<OrderDto> dateFilteredOrders = reportsService.getOrdersBetweenDate(reportsDto.getStartDate(),
					reportsDto.getEndDate());
			if (!orders.isEmpty()) {
				orders.retainAll(dateFilteredOrders);
			} else {
				orders = dateFilteredOrders;
			}
		}

		if (reportsDto.getStatus() == null && reportsDto.getStartDate() == null && reportsDto.getEndDate() == null) {
			orders = reportsService.getAllOrders();
		}

		if (orders != null) {
			ByteArrayInputStream in = PdfGenerator.generatePdf(orders);
			InputStreamResource resource = new InputStreamResource(in);
			HttpHeaders headers = new HttpHeaders();
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

}