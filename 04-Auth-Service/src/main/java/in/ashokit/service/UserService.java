package in.ashokit.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import in.ashokit.entity.User;

public interface UserService{

	public User addUser(User user, MultipartFile file) throws Exception;

	public User login(User user);

	public User getUserById(Integer userId);

	public List<User> getAllUsers();

	public User deleteUserById(Integer userId);

}
