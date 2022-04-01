package z_spring.com.example.z_spring_demo.controller.bean;

import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import z_spring.com.example.z_spring_demo.controller.bean.data.Message;
import z_spring.com.example.z_spring_demo.dto.RoleDTO;
import z_spring.com.example.z_spring_demo.dto.UserDTO;
import z_spring.com.example.z_spring_demo.service.impl.UserService;

@Component
@Scope("session")
@RequiredArgsConstructor
public class RegistrationPage {
	private final MessageList messageList;
	private final UserService userService;
	@Getter @Setter
	private UserDTO user;
	private Set<RoleDTO> roles;
	
	public Set<RoleDTO> getRoles() {
		if(roles == null || roles.isEmpty()) {
			roles = userService.getAllRoles();
		}
		return roles;
	}
	
	public boolean saveUser() {
		if (!validateUser(user)) {
			return false;
		}
		boolean result = userService.saveUser(user);
		
		if (result) {
			messageList.add(String.format("User %s was added successfully", user.getLogin()), Message.Type.INFO);
			return true;
		} else {
			messageList.add(String.format("An error ocurred while saving user %s", user.getLogin()), Message.Type.ERROR);
			return false;
		}
	}
	//TODO add validation by fields
	private boolean validateUser(UserDTO user) {
		if (user == null) {
			messageList.add("Error: user is empty", Message.Type.ERROR);
			return false;
		}
		return true;
	}
}
