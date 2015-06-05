package controllers;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.leapmotion.leap.*;

public enum JarvisController {

	INSTANCE;
	
	private Controller leapController;
	private final ScriptEngineManager ASM = new ScriptEngineManager();
	private final ScriptEngine ENG = ASM.getEngineByName("AppleScriptEngine");
	
	public void setController(Controller c){
		this.leapController = c;
	}
	
//	public void onSwipe(SwipeGesture sg){
//		System.out.println("Swiping!!");
//		System.out.println("Swipe speed: " + sg.speed());
//		String cmd = "";
//		if(sg.hands().get(0).isRight()){
//			System.out.println("Right - Decreasing!");
//			cmd = "set vol to output volume of (get volume settings)\n" + 
//						"if vol < 10 then # 0 is min \n" + 
//							"set volume output volume 0 \n" +
//						"else \n" +
//							"set volume output volume (vol - 10) \n " + 
//						"end if";
//		}else if(sg.hands().get(0).isLeft()){
//			System.out.println("Left - Increasing!");
//			cmd = "set vol to output volume of (get volume settings) \n" +
//					"if vol > 90 then # 100 max \n" +
//						"set volume output volume 100 \n" +
//					"else \n" +
//						"set volume output volume (vol + 10) \n" +
//					"end if";
//		}
//    	try {
//			ENG.eval(cmd);
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void increaseVolume(){
		String cmd = "set vol to output volume of (get volume settings) \n" +
				"if vol > 90 then # 100 max \n" +
					"set volume output volume 100 \n" +
				"else \n" +
					"set volume output volume (vol + 10) \n" +
				"end if";
		try {
			ENG.eval(cmd);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void decreaseVolume(){
		String cmd = "set vol to output volume of (get volume settings)\n" + 
				"if vol < 10 then # 0 is min \n" + 
				"set volume output volume 0 \n" +
			"else \n" +
				"set volume output volume (vol - 10) \n " + 
			"end if";
		try {
			ENG.eval(cmd);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void nextSpotifyTrack(){
		String cmd = "tell application \"Spotify\" \n"+
						"next track \n" +
					 "end tell";
		try {
			ENG.eval(cmd);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void previousSpotifyTrack(){
		String cmd = "tell application \"Spotify\" \n"+
						"previous track \n" +
					 "end tell";
		try {
			ENG.eval(cmd);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void playpauseSpotify(){
		String cmd = "tell application \"Spotify\" \n"+
						"playpause \n" +
					 "end tell";
		try {
			ENG.eval(cmd);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void computerSleep(){
		String cmd = "tell application \"Finder\" to sleep";
		try {
			ENG.eval(cmd);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
}
