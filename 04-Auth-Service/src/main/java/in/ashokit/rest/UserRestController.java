package in.ashokit.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ashokit.constants.AppConstants;
import in.ashokit.entity.User;
import in.ashokit.props.AppProperties;
import in.ashokit.response.ApiResponse;
import in.ashokit.service.UserService;

@RestController
@RequestMapping("/auth")
public class UserRestController {

	private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AppProperties props;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<User>> createUser(@RequestParam("user") String userJson,
			@RequestParam("file") MultipartFile file) throws Exception {

		log.info("user registaration process started");

		ApiResponse<User> response = new ApiResponse<>();
		Map<String, String> messages = props.getMessages();

		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(userJson, User.class);

		User addedUser = userService.addUser(user, file);

		if (addedUser != null) {
			response.setStatus(201);
			response.setMessage(messages.get(AppConstants.USER_REG));
			response.setData(addedUser);
		} else {
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.USER_REG_ERR));
		}
		log.info("user registaration process completed");

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<User>> login(@RequestBody User user) {

		log.info("user login process started");

		ApiResponse<User> response = new ApiResponse<>();
		Map<String, String> messages = props.getMessages();

		User loggedInUser = userService.login(user);

		if (loggedInUser != null) {
			response.setStatus(200);
			response.setMessage(messages.get(AppConstants.LOGIN));
			response.setData(loggedInUser);
		} else {
			log.error("user login failed");
			response.setStatus(500);
			response.setMessage(messages.get(AppConstants.LOGIN_ERR));
		}
		log.info("user login process completed");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
