package z_spring.com.example.z_spring_demo.dto.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import z_spring.com.example.z_spring_demo.dto.TicketTypeDTO;

public class TicketTypeFormatter implements Formatter<TicketTypeDTO>{

	@Override
	public String print(TicketTypeDTO type, Locale locale) {
		return String.valueOf(type.getTypeId());
	}

	@Override
	public TicketTypeDTO parse(String id, Locale locale) throws ParseException {
		return new TicketTypeDTO(Integer.parseInt(id), null, null);
	}

}
