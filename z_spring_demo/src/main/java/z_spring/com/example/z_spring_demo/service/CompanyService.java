package z_spring.com.example.z_spring_demo.service;

import java.util.List;

import z_spring.com.example.z_spring_demo.dto.CompanyDTO;

public interface CompanyService {
	List<CompanyDTO> findAll();
	
	void save(CompanyDTO companyDTO);
}
