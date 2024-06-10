package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

}
