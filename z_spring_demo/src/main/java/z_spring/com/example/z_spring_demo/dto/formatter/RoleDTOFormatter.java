package z_spring.com.example.z_spring_demo.dto.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import z_spring.com.example.z_spring_demo.dto.RoleDTO;

public class RoleDTOFormatter implements Formatter<RoleDTO>{

	@Override
	public String print(RoleDTO role, Locale locale) {
		return String.valueOf(role.getRoleId());
	}

	@Override
	public RoleDTO parse(String roleId, Locale locale) throws ParseException {
		return new RoleDTO(Short.valueOf(roleId), null);
	}
	
}
