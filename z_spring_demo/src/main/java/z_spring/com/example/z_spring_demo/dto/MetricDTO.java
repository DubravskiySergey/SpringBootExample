package z_spring.com.example.z_spring_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetricDTO {
	private Integer id;
	private String name;
	private TicketTypeDTO ticketType;
	private CompanyDTO company;

}
