import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;

public class Panel {
	public LevelClickListener listener;
	public boolean easy = false;
	public boolean medium = false;
	public boolean hard = false;
	
	public int X = 366;
	public int Y = 430;
	
	public int levelX = 2;
	public int levelY = 2;
	
	public int easyX = 2;
	public int easyY = 20;
	
	public int mediumX = 51;
	public int mediumY = 20;
	
	public int hardX = 110;
	public int hardY = 20;
	
	public int easyCenterX = easyX + 3;
	public int easyCenterY = easyY + 18;
	
	public int mediumCenterX = mediumX + 3;
	public int mediumCenterY = mediumY + 18;
	
	public int hardCenterX = hardX + 3;
	public int hardCenterY = hardY + 18;
	
	public int smileyX = 164;
	public int smileyY = 5;
	
	public int smileyCenterX = smileyX + 20;
	public int smileyCenterY = smileyY + 43;
	
	public int flaggerX = 204;
	public int flaggerY = 5;
	
	public int flaggerCenterX = flaggerX+21;
	public int flaggerCenterY = flaggerY+40;
	
	public boolean happiness = true;
	public boolean defeat = false;
	
	public boolean isAutoGamePressed = true;
	
	public boolean victory = false;
	
	public int timeX = 282;
	public int timeY = 8;
	
	public int vicMesX = 450;
	public int vicMesY = -50;
	
	String vicMes = "Nothing yet!";
	
	public int sec = 0;
	
	public int count = 9;
	public int totalM =  9;
	public boolean flagger = false;
	
	Date startDate = new Date();
	Date endDate = new Date();
	
	public void setClickListener(LevelClickListener listener){
		this.listener = listener;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, X, 40);
		
		g.setColor(new Color(255, 255, 255));
		g.fillRect(levelX, levelX, 156, 16);
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g.drawString("Auto Game click : ", levelX+15, levelY+13);
		g.setColor(new Color(0, 255, 8));
		g.fillRect(easyX, easyY, 47, 20);
		g.setColor(new Color(255, 255, 0));
		g.fillRect(mediumX, mediumY, 57, 20);
		g.setColor(new Color(255, 0, 0));
		g.fillRect(hardX, hardY, 48, 20);
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("Tahoma", Font.BOLD, 14));
		g.drawString("EASY", easyCenterX+3, easyCenterY-2);
		g.drawString("MIDDLE", mediumCenterX-2, mediumCenterY-2);
		g.drawString("HARD", hardCenterX+1, hardCenterY-2);

		
		g.setColor(Color.YELLOW);
		g.fillOval(smileyX, smileyY, 34, 34);
		g.setColor(Color.BLACK);
		g.fillOval(smileyX+8, smileyY+10, 5, 5);
		g.fillOval(smileyX+23, smileyY+10, 5, 5);
		if(happiness==true){
			g.fillRect(smileyX+13, smileyY+25, 10, 3);
			g.fillRect(smileyX+11, smileyY+23, 3, 3);
			g.fillRect(smileyX+9, smileyY+20, 3, 3);
			g.fillRect(smileyX+22, smileyY+23, 3, 3);
			g.fillRect(smileyX+24, smileyY+20, 3, 3);
		}else{
			g.fillRect(smileyX+13, smileyY+23, 10, 3);
			g.fillRect(smileyX+9, smileyY+28, 3, 3);
			g.fillRect(smileyX+11, smileyY+25, 3, 3);
			g.fillRect(smileyX+22, smileyY+25, 3, 3);
			g.fillRect(smileyX+24, smileyY+28, 3, 3);
		}
		
		
		
		g.setColor(new Color(0, 0, 0));
		g.fillRect(flaggerX+16 , flaggerY+7, 4, 20);
		g.fillRect(flaggerX+10, flaggerY+25, 15, 4);
		g.setColor(new Color(255, 0, 0));
		g.fillRect(flaggerX+8, flaggerY+8, 10, 8);
		
		g.setColor(new Color(0, 0, 0));
		g.drawRect(flaggerX+8, flaggerY+8, 10, 8);
		g.drawRect(flaggerX+8, flaggerY+8, 9, 7);
		g.drawRect(flaggerX+9, flaggerY+9, 8, 6);
		
		if (flagger == true){
			g.setColor(new Color(255, 0, 0));
		}
		
		g.drawOval(flaggerX, flaggerY, 35, 35);
//		g.drawOval(flaggerX, flaggerY, 35, 35);
		g.drawOval(flaggerX+1, flaggerY, 34, 34);
//		g.drawOval(flaggerX+1, flaggerY+1, 67, 67);
		g.drawOval(flaggerX+1, flaggerY+1, 32, 32);
//		g.drawOval(flaggerX+2, flaggerY+2, 65, 65);
		g.drawOval(flaggerX+2, flaggerY+2, 32, 32);
//		g.drawOval(flaggerX+3, flaggerY+3, 63, 63);
	
		g.setFont(new Font("Tahoma", Font.PLAIN, 18));
		g.setColor(new Color(255, 255, 255));
		g.drawString(Integer.toString(count), flaggerX+40, flaggerY+25);
		
		// time counter painting
		
		g.setColor(Color.BLACK);
		g.fillRect(timeX, timeY-5, 76, 37);
		if(defeat == false && victory ==false){
			sec = (int) ((((new Date().getTime()-startDate.getTime())/1000)));
		}
		if(sec>999){
			sec = 999;
		}
		g.setColor(new Color(255, 255, 255));
		if(victory == true){
			g.setColor(new Color(4, 112, 15));
		}else if(defeat == true){
			g.setColor(new Color(255, 0, 0));
		}
		g.setFont(new Font("Tahoma", Font.PLAIN, 35));
		if(sec<10){
			g.drawString("00" + Integer.toString(sec), timeX+10, timeY+28);
		}else if(sec < 100){
			g.drawString("0" + Integer.toString(sec), timeX+10, timeY+28);	
		}else
		g.drawString(Integer.toString(sec), timeX+10, timeY+28);
	
		if(victory == true){
			g.setColor(new Color(4, 112, 15));
			vicMes = "YOU WINNER";
		}else if(defeat == true){
			g.setColor(new Color(255, 0, 0));
			vicMes = "GAME  OVER";
		}
		
		if(victory == true || defeat == true){
			vicMesY = -50 + (int) (new Date().getTime() - endDate.getTime()) / 10;
			if(vicMesY > 800){
				vicMesY = 505;
			}
			g.setFont(new Font("Tahoma", Font.PLAIN, 100));
			g.drawString(vicMes, vicMesX-380, vicMesY);
		}
	}
	
	public void stopTimer(boolean isVictory){
		victory = isVictory;
		defeat = !isVictory; 
	}
	
	public void startTimer(){		
		startDate = new Date();
		endDate = new Date();
	}
	
	public boolean inSmiley(int mx, int my){
		int dif = (int) Math.sqrt(Math.abs(mx-smileyCenterX)*Math.abs(mx-smileyCenterX)+Math.abs(my-smileyCenterY)*Math.abs(my-smileyCenterY));
			if(dif < 17){
				return true;
			}
		return false;
	}
	
	public boolean inFlagger(int mx, int my){
		int dif = (int) Math.sqrt(Math.pow(mx-flaggerCenterX, 2)+Math.pow(my-flaggerCenterY, 2));
			if(dif < 17){
				return true;
			}
		return false;
	}
	
	public boolean ineasy(int mx, int my){
		if(mx >=2 && mx < 50 && my >= 45 && my < 66 ){
			return true;
		}
	return false;
	} 
	public boolean inMedium(int mx, int my){
	if(mx >=53 && mx <109 && my >= 45 && my < 66 ){
		return true;
	}
	return false;
	}
	
	public boolean inHard(int mx, int my){
	if(mx >= 111 && mx <161 && my >= 45 && my < 66 ){
		return true;
	}
	return false;
	}
	
	public void easyLevel(){
		easy = false;
		medium = false;
		hard = false;
		
		X = 366;
		Y = 430;
		
		count = 9;
		
		totalM = 9;
	
		smileyX = 164;
		
		smileyCenterX = smileyX + 20;
		
		flaggerX = 204;
		
		flaggerCenterX = flaggerX+21;
		
		timeX = 282;
		
		vicMesX = 450;
		vicMesY = -50;
		
		startDate = new Date();

		listener.easyPressed(X, Y, count);
		
	}
	
	public void mediumLevel(){
		easy = false;
		medium = false;
		hard = false;
		
		X = 646;
		Y = 709;
		
		count = 40;
		totalM = 40;
		
		smileyX = 342;
		
		smileyCenterX = smileyX + 20;
		
		flaggerX = 242;
		
		flaggerCenterX = flaggerX+21;
		
		timeX = 562;
		
		vicMesX = 450;
		vicMesY = -50;
		
		startDate = new Date();
		listener.mediumPressed(X, Y, count);
	}
	
	public void hardLevel(){
		easy = false;
		medium = false;
		hard = false;
		
		X = 1206;
		Y = 709;
		
		count = 99;	
		totalM = 99;
		
		smileyX = 582;
		
		smileyCenterX = smileyX + 20;
		
		flaggerX = 482;
		
		flaggerCenterX = flaggerX+21;
		
		timeX = 1122;
		
		vicMesX = 450;
		vicMesY = -50;
		
		startDate = new Date();

		listener.hardPressed(X, Y, count);
	}
	
	public void resetAll(){
			
	//		resetter = true;
			

			
			vicMesY = -50;
			 
//			String vicMes = "Nothing yet!";
			
			happiness = true;
			victory = false;
			defeat = false;
			flagger = false;
	//		count = totalMines();
			
			
			startTimer();
			
		}
	
	interface LevelClickListener{
		public void easyPressed(int x, int y, int count);
		public void mediumPressed(int x, int y, int count);
		public void hardPressed(int x, int y, int count);
	}
}
