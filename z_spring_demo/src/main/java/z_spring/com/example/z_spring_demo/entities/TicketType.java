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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket_type", schema = "test_spring")
@EnableAutoConfiguration
public class TicketType {
	@Id
	@Column(nullable = false, updatable = false, unique = true)
	private int typeId;
	@Column(nullable = false)
	private String typeName;
	@Column
	private String typeDescr;
	
}
