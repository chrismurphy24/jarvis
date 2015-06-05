package jarvis;

import com.leapmotion.leap.*;

import controllers.JarvisController;

public enum TestEngine implements Runnable {
	INSTANCE;
	
	private Controller leapController;
	private JarvisController jarvisController;
	private Frame cf;
	private Frame pcf;
	private final int FRAMES = 15;
	
	protected void setControllers(Controller c, JarvisController jc){
		this.leapController = c;
		this.jarvisController = jc;
	}
	
	public void run(){
		while(true){
			cf = leapController.frame();
			pcf = leapController.frame(FRAMES);
			
			for(Finger f:cf.hands().get(0).fingers()){
				System.out.println(f.type());
			}
			try {
				Thread.sleep(2000);
				continue;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
}
