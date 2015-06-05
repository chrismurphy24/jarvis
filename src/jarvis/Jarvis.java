package jarvis;

import java.io.IOException;

import listeners.AlphaListener;

import com.leapmotion.leap.*;

import controllers.JarvisController;

public class Jarvis {
	
	public static void main(String[] args){
		
//		AlphaListener al = new AlphaListener();
		Controller controller = new Controller();
		
		JarvisController.INSTANCE.setController(controller);
		TestEngine.INSTANCE.setControllers(controller, JarvisController.INSTANCE);
//		al.setJarvisController(JarvisController.INSTANCE);
		
		// Keep this process running until Enter is pressed
		System.out.println("Press Enter to quit...");
		while(true){
			if(controller.isConnected()){
				System.out.println("Device connected!");
				break;
			}
		}
		Thread thread = new Thread(TestEngine.INSTANCE);
		thread.start();
	}
	
}
