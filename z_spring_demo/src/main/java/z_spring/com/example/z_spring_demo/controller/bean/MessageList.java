package z_spring.com.example.z_spring_demo.controller.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import z_spring.com.example.z_spring_demo.controller.bean.data.Message;

@Component("messageList")
@Scope("session")

public class MessageList {
	@Getter
	private List<Message> messages = new ArrayList<Message>();
	
	public void add(Message message) {
		messages.add(message);
	}
	public void add(String message, Message.Type type){
		messages.add(new Message(message, type));
	}
	public void clear() {
		messages.clear();
	}
}
