package z_spring.com.example.z_spring_demo.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import z_spring.com.example.z_spring_demo.dto.RoleDTO;

@Component
public class UserDTOConverter implements Converter<RoleDTO , String>{

	@Override
	public String convert(RoleDTO role) {
		return role != null? role.getRoleId().toString(): "-1";
	}


}
