package z_spring.com.example.z_spring_demo.controller.bean;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import z_spring.com.example.z_spring_demo.controller.bean.data.Message.Type;
import z_spring.com.example.z_spring_demo.dto.CompanyDTO;
import z_spring.com.example.z_spring_demo.dto.MetricDTO;
import z_spring.com.example.z_spring_demo.dto.TicketTypeDTO;
import z_spring.com.example.z_spring_demo.service.MetricService;

@Component
@Scope("session")
@RequiredArgsConstructor
public class EditMetricPage {
	private final MessageList messageList;
	private final MetricService metricService;
	
	@Getter @Setter
	private MetricDTO metric;
	@Getter
	private List<TicketTypeDTO> ticketTypes;
	
	public void init(MetricDTO metric) {
		this.metric = metric;
		if(ticketTypes == null) {
			ticketTypes = metricService.getTicketTypes();
		}
	}
	
	public void init(CompanyDTO company) {
		this.metric = new MetricDTO(null, null, null, company);
		if(ticketTypes == null) {
			ticketTypes = metricService.getTicketTypes();
		}
	}
	
	
	public String save() {
		messageList.clear();
		System.out.println("session: " + RequestContextHolder.currentRequestAttributes().getSessionId() + 
				"; EditMetricPage.save metric: " + metric);
		if (!validate()) {
			return "metric";
		}
		
		metricService.save(metric);
		
		messageList.add("Metric was saved successfully", Type.INFO);

		return "metric";
	}
	
	private boolean validate() {
		if (metric == null || metric.getCompany() == null) {
			messageList.add("Something went wrong. Try again from the scratch", Type.ERROR);
			return false;
		}
		if (metric.getName() == null || metric.getName().isBlank()) {
			messageList.add("Metric Name can not be empty", Type.ERROR);
			return false;
		}
		return true;
	}
}
