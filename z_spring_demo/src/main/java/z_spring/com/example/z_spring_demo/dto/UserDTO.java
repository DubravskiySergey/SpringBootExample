package z_spring.com.example.z_spring_demo.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long userId;
	private String login;
	private String name;
	private String surname;
	private String email;
	private String pwd;
	private Set<RoleDTO> roles;
}
