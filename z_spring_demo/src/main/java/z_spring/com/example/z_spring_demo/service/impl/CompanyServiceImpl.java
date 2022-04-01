package z_spring.com.example.z_spring_demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import z_spring.com.example.z_spring_demo.dto.CompanyDTO;
import z_spring.com.example.z_spring_demo.entities.Company;
import z_spring.com.example.z_spring_demo.repository.CompanyRepository;
import z_spring.com.example.z_spring_demo.service.CompanyService;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{
	private final CompanyRepository repository;
	
	@Override
	public List<CompanyDTO> findAll() {
		return repository.findAll().stream().map(e -> convertToDTO(e)).collect(Collectors.toList());
	}
	
	@Override
	public void save(CompanyDTO companyDTO) {
		repository.save(convertFromDTO(companyDTO));
	}

	private Company convertFromDTO(CompanyDTO companyDTO) {
		return new Company(companyDTO.getCompanyId(), companyDTO.getCompanyName(), companyDTO.getCompanyDescr());
	}
	private CompanyDTO convertToDTO(Company company) {
		return new CompanyDTO(company.getCompanyid(), company.getCompanyName(), company.getCompanyDescr());
	}
	
}
