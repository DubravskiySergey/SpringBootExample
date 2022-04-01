package z_spring.com.example.z_spring_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CompanyDTO {
	private int companyId;
	private String companyName;
	private String companyDescr;

}
