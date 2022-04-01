package z_spring.com.example.z_spring_demo.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import z_spring.com.example.z_spring_demo.dto.TicketTypeDTO;

@Component
public class TicketTypeDTOConverter implements Converter<TicketTypeDTO, String>{

	@Override
	public String convert(TicketTypeDTO type) {
		return type != null? String.valueOf(type.getTypeId()): "-1";
	}
	
}
