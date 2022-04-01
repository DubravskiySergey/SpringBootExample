package z_spring.com.example.z_spring_demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "metric", schema = "test_spring", uniqueConstraints = {@UniqueConstraint(name="name_owner_uc", columnNames = {"name", "owner_id"})})
@EnableAutoConfiguration
public class Metric {
	@Id
	@SequenceGenerator(name = "sequanceGenerator", schema = "testSpring", allocationSize = 1, initialValue = 100000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequanceGenerator")
	@Column(name = "id", nullable = false, updatable = false)
	private Integer id;
	@Column(name = "name", nullable = false, columnDefinition = "text")
	private String name;
	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false, foreignKey = @ForeignKey(name = "metric_ticket_type_FK"))
	private TicketType ticketType;
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false, foreignKey = @ForeignKey(name = "metric_company_FK"))
	private Company company;
	
}
