package malloc.hongikv1.thread;

import malloc.hongikv1.sori.Anony;

public class WebhookThread extends Thread{
	
	String message;
	
	public WebhookThread(String msg) {
		message = msg;
	}
	
	@Override
	public void run() {
		Anony.sendDiscord(message);
	}

}
