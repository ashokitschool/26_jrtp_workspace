package in.ashokit.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Order;
import in.ashokit.entity.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("Select o From Order o Where o.status= :status")
	List<Order> findbyStatus(OrderStatus status);

}
