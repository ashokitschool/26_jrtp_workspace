package in.ashokit.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.Repository.OrderRepository;
import in.ashokit.dto.OrderDto;
import in.ashokit.entity.Order;
import in.ashokit.entity.OrderStatus;
import in.ashokit.mapper.OrderMapper;

@Service
public class ReportsServiceImpl implements ReportsService{

	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public List<OrderDto> getAllOrders() {
		// TODO Auto-generated method stub
		
		List<Order> orders = orderRepo.findAll();
		
		return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<OrderDto> orderByStatus(OrderStatus status) {
		List<Order>  byStatus= orderRepo.findbyStatus(status);
		return byStatus.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
	}
	@Override
	public List<OrderDto> getOrdersBetweenDate(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
