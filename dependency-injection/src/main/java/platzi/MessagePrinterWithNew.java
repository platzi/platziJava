package platzi;


public class MessagePrinterWithNew {

    private MessageService messageService;

    public MessagePrinterWithNew() {
    }

    public void printMessage() {
        System.out.println(this.messageService.getMessage());
    }

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
    
    
}
