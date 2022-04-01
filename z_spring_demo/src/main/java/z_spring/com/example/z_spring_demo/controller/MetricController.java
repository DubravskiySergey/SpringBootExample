package z_spring.com.example.z_spring_demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import z_spring.com.example.z_spring_demo.controller.bean.EditMetricPage;
import z_spring.com.example.z_spring_demo.controller.bean.MetricListPage;
import z_spring.com.example.z_spring_demo.controller.bean.RegistrationPage;
import z_spring.com.example.z_spring_demo.controller.bean.SelectCompanyPage;
import z_spring.com.example.z_spring_demo.dto.MetricDTO;
import z_spring.com.example.z_spring_demo.dto.formatter.RoleDTOFormatter;
import z_spring.com.example.z_spring_demo.dto.formatter.TicketTypeFormatter;

@org.springframework.stereotype.Controller
@Scope("session")

@AllArgsConstructor
public class MetricController {

	private final MetricListPage metricListPage;
	private final SelectCompanyPage selectCompanyPage;
	private final RegistrationPage registrationPage;
	private final EditMetricPage editMetricPage;

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/metricList")
	public String getMetricList(@RequestParam(name = "id", required = false, defaultValue = "0") int companyId,
			Model model) {
		model.addAttribute("metricListPage", metricListPage);
		return "metricList";
	}

	@PostMapping(value = "/metricList", params = "action=newMetric")
	public String createNewMetric(Model model) {
		editMetricPage.init(selectCompanyPage.getSelectedCompany());
		model.addAttribute(editMetricPage);
		return "/metric";
	}

	@PostMapping(value = "/metricList", params = "metricId")
	public String editMetric(Model model, HttpServletRequest request) {
		int metricId = Integer.parseInt(request.getParameter("metricId"));
		MetricDTO metric = metricListPage.getMetricList().stream()
				.filter(m -> m.getId() == metricId).findAny().orElseThrow();
		editMetricPage.init(metric);
		model.addAttribute(editMetricPage);
		return "/metric";
	}
	
	@GetMapping("/companyList")
	public String getCompanyList(Model model) {
		model.addAttribute("selectCompanyPage", selectCompanyPage);
		return "companyList";
	}

	@PostMapping("/companyList")
	public String goNext(SelectCompanyPage selectCompanyPageForm, Model model) {
		selectCompanyPage.setSelectedById(selectCompanyPageForm.getSelectedCompany().getCompanyId());
		if (selectCompanyPage.goNext()) {
			metricListPage.init(selectCompanyPage.getSelectedCompany().getCompanyId());
			model.addAttribute("metricListPage", metricListPage);
			return "metricList";
		} else {
			model.addAttribute("selectCompanyPage", selectCompanyPage);
			return "companyList";
		}
	}

	@GetMapping("/admin/registration")
	public String goRegistration(Model model) {
		model.addAttribute(registrationPage);
		return "admin/registration";
	}

	@PostMapping("/admin/registration")
	public String doRegistration(RegistrationPage registrationPageForm, Model model) {
		this.registrationPage.setUser(registrationPageForm.getUser());
		if (registrationPage.saveUser()) {
			return "index";
		} else {
			return "admin/registration";
		}
	}

	@GetMapping("/metric")
	public String getMetric(Model model) {
		model.addAttribute(editMetricPage);
		return "metric";
	}

	@PostMapping(value = "/metric", params = "action=save")
	public String saveMetric(Model model, EditMetricPage editMetricPageForm) {
		//set only changed fields
		this.editMetricPage.getMetric().setName(editMetricPageForm.getMetric().getName());
		this.editMetricPage.getMetric().setTicketType(
				this.editMetricPage.getTicketTypes().stream()
						//object from form has just id; 
						//equals possible here because anywhere (equals, hashcode, converter, formatter) 
						//is used only id for identifying object
						.filter(t -> t.equals(editMetricPageForm.getMetric().getTicketType()))
						.findAny().orElseThrow());
		
		model.addAttribute(this.editMetricPage);
		
		String nextPage = this.editMetricPage.save();
		metricListPage.refresh();
		return nextPage;
	}
	
	@PostMapping(value = "/metric", params = "action=metricList") 
	public String goToMetricList(Model model, EditMetricPage editMetricPage) { 
		model.addAttribute("metricListPage", metricListPage);
		return "/metricList";
	  }

	/*
	 * TODO possible to use only with own Error handler
	 * 
	 * @RequestMapping("/error") public String goErrorPage(HttpServletRequest
	 * request) { ApplicationContext ctx =
	 * RequestContextUtils.findWebApplicationContext(request); String session =
	 * RequestContextHolder.currentRequestAttributes().getSessionId(); UserDTO user
	 * = null; try { user = (UserDTO) ctx.getBean("userDTO"); } catch (Exception e){
	 * System.out.println("session: " + session +
	 * " Can not get user bean for error below (after exception details). Exception details: "
	 * ); e.printStackTrace(); } System.out.println("session: " + session +
	 * "; ERROR " + "\r\nUSER: " + user + "\r\nREQUEST: " + request);
	 * 
	 * return "error"; }
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addCustomFormatter(new RoleDTOFormatter());
		webDataBinder.addCustomFormatter(new TicketTypeFormatter());
	}

}
