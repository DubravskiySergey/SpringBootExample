package z_spring.com.example.z_spring_demo.controller.bean.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
	public enum Type {INFO, ERROR}
	private String message;
	private Type type;
}
