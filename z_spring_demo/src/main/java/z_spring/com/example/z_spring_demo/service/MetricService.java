package z_spring.com.example.z_spring_demo.service;

import java.util.List;

import z_spring.com.example.z_spring_demo.dto.MetricDTO;
import z_spring.com.example.z_spring_demo.dto.TicketTypeDTO;

public interface MetricService {
	List<MetricDTO> findByCompany(int companyId);
	List<MetricDTO> findAll();
	
	void save(MetricDTO metricDTO);
	
	List<TicketTypeDTO> getTicketTypes();
}
