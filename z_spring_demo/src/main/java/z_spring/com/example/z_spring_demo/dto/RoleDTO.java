package z_spring.com.example.z_spring_demo.dto;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
	private static final Set<String> ADMIN_STATUSES = Stream.of("ADMIN", "SUPERADMIN")
							.collect(Collectors.toSet());
	@EqualsAndHashCode.Include
	private Short roleId;
	@EqualsAndHashCode.Exclude
	private String role;
	
	public boolean isAdmin() {
		return ADMIN_STATUSES.contains(role);
	}
	
} 
