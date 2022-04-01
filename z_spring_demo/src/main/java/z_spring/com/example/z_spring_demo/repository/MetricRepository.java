package z_spring.com.example.z_spring_demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import z_spring.com.example.z_spring_demo.entities.Company;
import z_spring.com.example.z_spring_demo.entities.Metric;

@Repository
public interface MetricRepository extends JpaRepository<Metric, Integer>{
	List<Metric> findAllByCompany(Company company);
	
}
