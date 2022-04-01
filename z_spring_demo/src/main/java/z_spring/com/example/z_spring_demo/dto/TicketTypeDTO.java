package z_spring.com.example.z_spring_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TicketTypeDTO {
	@EqualsAndHashCode.Include
	private int typeId;
	private String typeName;
	private String typeDescr;
}
