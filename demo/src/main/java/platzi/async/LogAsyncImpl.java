package platzi.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LogAsyncImpl implements LogAsync {

	public LogAsyncImpl() {
	}
	
	@Override
	@Async
	public void log(String message) {
		try{
			Thread.sleep(4000);
			System.out.println(message);
		}catch(Exception e){
			//dont do this!!
		}
	}
}
