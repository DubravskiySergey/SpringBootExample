package z_spring.com.example.z_spring_demo.controller.bean;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import z_spring.com.example.z_spring_demo.controller.bean.data.Message;
import z_spring.com.example.z_spring_demo.dto.CompanyDTO;
import z_spring.com.example.z_spring_demo.service.CompanyService;

@Component("selectCompanyPage")
@Scope("session")

@RequiredArgsConstructor
public class SelectCompanyPage {
	private final CompanyService companyService;
	private final MessageList messageList;
	@Setter
	private List<CompanyDTO> companyList;
	@Setter @Getter
	private CompanyDTO selectedCompany;
	
	public void init() {
		companyList = companyService.findAll();
	}
	
	public List<CompanyDTO> getCompanyList() {
		if (companyList == null || companyList.isEmpty()) {
			init();
		}
		return companyList;
	}
	
	public void setSelectedById(Integer id) {
		selectedCompany = getCompanyList().stream().filter(i -> i.getCompanyId() == id).findFirst().orElse(null);
	}
	
	public boolean goNext() {
		
		messageList.clear();
		System.out.println("session: " + RequestContextHolder.currentRequestAttributes().getSessionId() + 
				"; SelectCompanyPage.goNext selectedData: " + selectedCompany);
		if (selectedCompany != null && selectedCompany.getCompanyId() > 0) {
			messageList.add(new Message("Company checked sucsesfully ", Message.Type.INFO));
			return true;
		} else {
			messageList.add("Error: company has not found", Message.Type.ERROR);
			return false;
		}
	}
	
}
