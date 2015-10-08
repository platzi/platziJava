package platzi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class Application {

	@Bean
	MessageService mockMessageService() {
		return new MessageService() {
			public String getMessage() {
				return "Hello Platzi!!!";
			}
		};
	}

	@Bean(name="happyMessageService")
	MessageService happyMessageService() {
		return new HappyMessageService();
	}	
	
	/*@Bean
	MessageService mockMessageService() {
		return () -> "Hello Platzi with Lambda!!!";
	}*/	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(Application.class);
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();
	}
}
