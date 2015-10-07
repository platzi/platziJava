package platzi.servlet;

public class Message {

    private long size;
    private String message;

    public Message(){
    }
    
    public Message(String message) {
        this.size = message.length();
        this.message = message;
    }

    public long getSize() {
        return message.length();
    }

    public String getMessage() {
        return message;
    }

	public void setMessage(String message) {
		this.message = message;
	}
   
}
