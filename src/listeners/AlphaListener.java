package listeners;

import com.leapmotion.leap.*;

import controllers.JarvisController;

public class AlphaListener extends Listener {
	
	private JarvisController jc;

	
	public void setJarvisController(JarvisController jcont){
		this.jc = jcont;
	}
	
	public void onConnect(Controller c){
		System.out.println("Leap Motion Device Connected!"); 
		DeviceList dList = c.devices();
		
		if(dList.isEmpty()){
			System.out.println("No device connected!");
		}else{
			System.out.println(dList.count() + " devices connected: \n");
			
			for(Device d:dList){
				System.out.println("Device "+d.serialNumber() + " connected,");
				System.out.println("recommended range is " + d.range());
			}
		}
		c.enableGesture(Gesture.Type.TYPE_SWIPE);
	}
	
	public void onFrame(Controller c){
	    Frame frame = c.frame();
//	    for(Gesture gesture:frame.gestures()){
//	    	if(gesture.type() == Gesture.Type.TYPE_SWIPE)
//	    		jc.onSwipe(new SwipeGesture(gesture));
//	    }
	}
	
}
