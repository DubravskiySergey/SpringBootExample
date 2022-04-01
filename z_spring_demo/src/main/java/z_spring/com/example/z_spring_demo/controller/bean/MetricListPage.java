package z_spring.com.example.z_spring_demo.controller.bean;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import z_spring.com.example.z_spring_demo.dto.MetricDTO;
import z_spring.com.example.z_spring_demo.service.MetricService;

@Component("metricListPage")
@Scope("session")

@RequiredArgsConstructor
public class MetricListPage {
	private final MetricService metricService;
	@Getter @Setter
	private int companyId;
	
	@Setter @Getter
	private List<MetricDTO> metricList;
	
	public void init(int companyId) {
		this.companyId = companyId;
		metricList = metricService.findByCompany(companyId);
	}
	
	public void refresh() {
		metricList = metricService.findByCompany(companyId);
	}
	
}
