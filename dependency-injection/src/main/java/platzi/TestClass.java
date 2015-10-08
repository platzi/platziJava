package platzi;

import org.springframework.beans.factory.annotation.Autowired;

public class TestClass {
	
	public static void main(String[] args) {
		MessagePrinterWithNew printer = new MessagePrinterWithNew();
		printer.setMessageService(new SadMessageService());
		printer.printMessage();
	}

}
