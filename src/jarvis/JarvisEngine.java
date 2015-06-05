package jarvis;

import java.io.IOException;

import com.leapmotion.leap.*;

import controllers.JarvisController;

public enum JarvisEngine implements Runnable{
	
	INSTANCE;
	
	private Controller leapController;
	private JarvisController jarvisController;
	private Frame cf;
	private Frame pcf;
	private final int FRAMES = 10;
	
	protected void setControllers(Controller c, JarvisController jc){
		this.leapController = c;
		this.jarvisController = jc;
	}
	
	//run engine containing event loop, instead of using listener
	// to prevent multi-threading issues
	//all gesture recognition is here, could get bulky but not sure how else to do it right now
	//maybe add gesture recognition class later
	//TODO: add gesture recognition class
	public void run(){
		leapController.enableGesture(Gesture.Type.TYPE_SWIPE);
		leapController.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		float y, roll, tprob;
		while(true){
			
			cf = leapController.frame();
			pcf = leapController.frame(FRAMES);
			
			if(cf.hands().count() == 2){
				if(Math.abs(cf.hands().leftmost().palmNormal().getX() - cf.hands().rightmost().palmNormal().getX()) < 20){
					if(cf.hands().leftmost().translation(pcf).getX() > 0 && cf.hands().rightmost().translation(pcf).getX() < 0){
						jarvisController.computerSleep();
						break;
					}
				}
			}
			
			for(Gesture gesture:cf.gestures()){
		    	if(gesture.type() == Gesture.Type.TYPE_SWIPE){
		    		if(gesture.hands().get(0).isRight())
		    			jarvisController.previousSpotifyTrack();
		    		else if(gesture.hands().get(0).isLeft())
		    			jarvisController.nextSpotifyTrack();
		    		try {
						Thread.sleep(500);
						continue;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		    	}
//		    	else if(gesture.type() == Gesture.Type.TYPE_KEY_TAP){
//		    		jarvisController.playpauseSpotify();
//		    		try {
//						Thread.sleep(500);
//						continue;
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//		    	}
		    		
		    }
			
			y = cf.hands().get(0).palmNormal().getY();
			roll = cf.hands().get(0).palmNormal().roll();
			tprob = cf.hands().get(0).translationProbability(pcf);

			if(y > 0 && Math.abs(roll) > 2.8 ){
				if(cf.hands().get(0).translation(pcf).getY() > 20 && tprob > 0.8){
					jarvisController.increaseVolume();
					System.out.println("volume up!");
					try {
						Thread.sleep(500);
						continue;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else if( y < 0 && Math.abs(roll) < 0.3){
				if(cf.hands().get(0).translation(pcf).getY() < -20 && tprob > 0.8){
					jarvisController.decreaseVolume();
					System.out.println("volume down!");
					try {
						Thread.sleep(500);
						continue;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		
		}
		System.exit(0);
	}

}
