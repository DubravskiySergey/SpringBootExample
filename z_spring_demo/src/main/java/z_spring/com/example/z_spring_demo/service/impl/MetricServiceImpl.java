package z_spring.com.example.z_spring_demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import z_spring.com.example.z_spring_demo.dto.CompanyDTO;
import z_spring.com.example.z_spring_demo.dto.MetricDTO;
import z_spring.com.example.z_spring_demo.dto.TicketTypeDTO;
import z_spring.com.example.z_spring_demo.entities.Company;
import z_spring.com.example.z_spring_demo.entities.Metric;
import z_spring.com.example.z_spring_demo.entities.TicketType;
import z_spring.com.example.z_spring_demo.repository.MetricRepository;
import z_spring.com.example.z_spring_demo.repository.TicketTypeRepository;
import z_spring.com.example.z_spring_demo.service.MetricService;

@Service
@AllArgsConstructor
public class MetricServiceImpl implements MetricService {
	private final MetricRepository metricRepository;
	private final TicketTypeRepository ticketTypeRepository; 

	@Override
	public List<MetricDTO> findByCompany(int companyId) {
		List<Metric> metricFromDBList = metricRepository.findAllByCompany(new Company(companyId, null, null));// cannot be null

		return metricFromDBList.stream().map(metricFromDB -> convertToDTO(metricFromDB)).collect(Collectors.toList());

	}

	@Override
	public void save(MetricDTO metricDTO) {
		metricRepository.save(convertFromDTO(metricDTO));
	}
	
	@Override
	public List<MetricDTO> findAll() {
		List<Metric> metricsFromDB = metricRepository.findAll();
		return metricsFromDB.stream().map(e -> convertToDTO(e)).collect(Collectors.toList());
	}

	@Override
	public List<TicketTypeDTO> getTicketTypes() {
		return ticketTypeRepository.findAll()
				.stream().map(e -> convertTicketTypeToDTO(e)).collect(Collectors.toList());
	}
	
	private MetricDTO convertToDTO(Metric metricFromDB) {
		return new MetricDTO(metricFromDB.getId(), metricFromDB.getName(),
				new TicketTypeDTO(metricFromDB.getTicketType().getTypeId(), metricFromDB.getTicketType().getTypeName(),
						metricFromDB.getTicketType().getTypeDescr()),
				new CompanyDTO(metricFromDB.getCompany().getCompanyid(), metricFromDB.getCompany().getCompanyName(),
						metricFromDB.getCompany().getCompanyDescr()));
	}

	private Metric convertFromDTO(MetricDTO metricDTO) {
		return new Metric(metricDTO.getId(), metricDTO.getName(),
				new TicketType(metricDTO.getTicketType().getTypeId(), metricDTO.getTicketType().getTypeName(),
						metricDTO.getTicketType().getTypeDescr()),
				new Company(metricDTO.getCompany().getCompanyId(), metricDTO.getCompany().getCompanyName(),
						metricDTO.getCompany().getCompanyDescr()));
	}

	private TicketTypeDTO convertTicketTypeToDTO(TicketType ticketTypeFromDB) {
		return new TicketTypeDTO(ticketTypeFromDB.getTypeId(), ticketTypeFromDB.getTypeName(), ticketTypeFromDB.getTypeDescr());
	}
}
