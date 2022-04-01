package z_spring.com.example.z_spring_demo.service.impl;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import z_spring.com.example.z_spring_demo.dto.RoleDTO;
import z_spring.com.example.z_spring_demo.dto.UserDTO;
import z_spring.com.example.z_spring_demo.entities.Role;
import z_spring.com.example.z_spring_demo.entities.User;
import z_spring.com.example.z_spring_demo.repository.RoleRepository;
import z_spring.com.example.z_spring_demo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	
	@Bean
	public PasswordEncoder passwordEncoding() {
		return new BCryptPasswordEncoder();
	}
	
	private User getUserBylogin(String login) {
		return userRepository.findByLogin(login);	
	}
	
	//for spring internal security(login)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userFromDB = getUserBylogin(username);
		if (userFromDB == null) {
			throw new UsernameNotFoundException(String.format("User with login %s not found", username));
		}
		return new org.springframework.security.core.userdetails.User(
				username, userFromDB.getPwd(), getAutorities(userFromDB.getRoles()));
		
	}

	private Collection<? extends GrantedAuthority> getAutorities(Set<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());
	}
	
	public UserDTO getUser(String login) {
		User userFromDB = userRepository.findByLogin(login);
		if (userFromDB == null) {
			return null;
		}
		UserDTO user = new UserDTO(userFromDB.getUserId(),
				userFromDB.getLogin(),
				userFromDB.getName(), 
				userFromDB.getSurname(), 
				userFromDB.getEmail(),
				userFromDB.getPwd(), 
				userFromDB.getRoles().stream().map(s -> new RoleDTO(s.getRoleId(), s.getRole()))
					.collect(Collectors.toSet()));
		return user;
	}
	
	public boolean saveUser(UserDTO user) {
		User userToDB = new User(user.getUserId(),
				user.getLogin(),
				user.getName(), 
				user.getSurname(), 
				user.getEmail(),
				passwordEncoding().encode(user.getPwd()), 
				user.getRoles().stream().map(s -> new Role(s.getRoleId(), s.getRole()))
					.collect(Collectors.toSet()));
		//TODO what is about failure during the saving?
		userRepository.saveAndFlush(userToDB);
		return true;
	}
	
	public Set<RoleDTO> getAllRoles(){
		return roleRepository.findAll().stream()
				 .map(s -> new RoleDTO(s.getRoleId(), s.getRole()))
				 .collect(Collectors.toSet());
	}
	
}
