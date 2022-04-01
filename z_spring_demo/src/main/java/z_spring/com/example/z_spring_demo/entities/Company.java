package z_spring.com.example.z_spring_demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company", schema = "test_spring")
@EnableAutoConfiguration
public class Company {
	@Id
	@Column(name = "company_id", nullable = false, unique = true, updatable = false)
	private int companyid;
	@Column(name = "company_name", nullable = false)
	private String companyName;
	@Column(name = "companyDescr")
	private String companyDescr;

}
