import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GUI extends JFrame implements Panel.LevelClickListener {

	public boolean resetter = false;
	
	public boolean eazy = false;
	public boolean medium = false;
	public boolean hard = false;
	
	int neighs = 0;
	
	
	public int mx = -100;
	public int my = -100;
	public int X = 366;
	public int Y = 430;
	
	private Panel panel = new Panel();
	private Board board = new Board();
	

	public int count = 9;

	public int totalM =  9;

	int frequency = 20;
	
	Random rand = new Random();
	
	int i3 = 9;
	int j3 = 9;
	
	int [][] mines= new int[i3][j3];
	int [][] neightbours = new int[i3][j3];
	boolean [][] revealed = new boolean[i3][j3];
	boolean [][] flagged = new boolean[i3][j3];
	
	
	
	public void reintiArr(){
		 mines= new int[i3][j3];
		 neightbours = new int[i3][j3];
		 revealed = new boolean[i3][j3];
		 flagged = new boolean[i3][j3];
	}
	
	public void setMines(){
		for(int i = 0; i < i3; i++){
			for(int j = 0; j < j3; j++){
				if(rand.nextInt(100) < frequency){
					if(totalMines() < count){
						mines[i][j] = 1;
					}
					}else{
						mines[i][j] = 0;
					}

			revealed[i][j] = false;
		}
	}
	}
	public void setNeibors(){
		for(int i = 0; i < i3; i++){
			for(int j = 0; j < j3; j++){
				neighs = 0;
				for(int m = 0; m < i3; m++){
					for(int n = 0; n < j3; n++){
						if(!( m == i && n == j )){
							if(isN(i,j,m,n)==true)
								neighs++;
						}
					}
				}
				neightbours[i][j] = neighs;
			}
		}
	}
	
	public GUI(){
		this.setTitle("Minesweeper");
		this.setSize(X, Y);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		
		setMines();
		setNeibors();
		
//		Board board = new Board();
		MyJPanel jPanel = new MyJPanel();
		
		jPanel.getGraphics();
		
		this.setContentPane(jPanel);
		
		Move move = new Move();
		this.addMouseMotionListener(move);
		
		Click click = new Click();
		this.addMouseListener(click);
		panel.setClickListener(this);
	}
	
	
	public class MyJPanel extends JPanel {
		
		public void paintComponent(Graphics g){
			board.draw(g);
			panel.draw(g);
		}
	}

	
	public class Move implements MouseMotionListener{
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
//			
//			System.out.println("The mouse was moved");
//			System.out.println("______________________________________________");
//			System.out.println("X : " + mx +"\n"+ "Y : " + my);
//			System.out.println("______________________________________________");
			mx = e.getX();
			my = e.getY();
			
		}
	}
	
	
		public int randBoxX(){
//			for(int i = 0; i < i3; i++){
//				for(int j = 0; j < j3; j++){
//					if(rand.nextInt(i3*j3) < i3){
//						return i;	
//					}
//				}
//			}
//			return -1;
			return rand.nextInt(i3);
		}
		
		public int randBoxY(){
//			for(int i = 0; i < i3; i++){
//				for(int j = 0; j < j3; j++){
//					if(rand.nextInt(i3*j3) < j3){
//						return j;	
//					}
//				}
//			}
//			return -1;
			return rand.nextInt(j3);
		}
	
		
	public class Click implements MouseListener{
		
		public void startAlgorithm(int firstX, int firstY, Boolean isFirstClick) {
			
			if (isFirstClick) {
				if (neightbours[firstX][firstY] != 0) {
					// TODO open next random
				} else {
					openAllAround(firstX, firstY);
					checkOpened();
				}
			} else {
				for (int i = 0; i <= i3; i++) {
					for (int j = 0; j <= j3; j++) {
					
						
						
					}
				}
			}
			
		}
		
		private void checkOpened() {
			for (int i = 0; i <= i3; i++) {
				for (int j = 0; j <= j3; j++) {
				
					if (revealed[i][j] == true) {
						
						if (neightbours[i][j] == 0) {
							openAllAround(i, j);
						}
					}
					
				}
			}
		}
		
		public void openAllAround(int x, int y) {
			if(x>0 && y<(j3-1))revealed[x-1][y+1] = true;
			if(x>0 && y>0)revealed[x-1][y-1] = true;
			if(x>0 )revealed[x-1][y] = true;
			if(x<(i3-1) && y<(j3-1))revealed[x-1][y+1] = true;
			if(x<(i3-1) && y>0)revealed[x-1][y-1] = true;
			if(x<(i3-1) )revealed[x-1][y] = true;
			if(y>0)revealed[x][y-1] = true;
			if(y<(j3-1) )revealed[x][y+1] = true;			
		}
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
		
			mx = e.getX();
			my = e.getY();  
			
			if(AutoGame()==true){
					int randX = randBoxX();
					int randY = randBoxY();
					board.autoFind();
			}
			
			int inBoxX = board.inBoxX(mx, my);
			int inBoxY = board.inBoxY(mx, my);

			board.cellSelected(inBoxX, inBoxY);
			
			if(board.isGameOver){
				panel.stopTimer(false);
				panel.happiness= false;
			}
			
			if(board.isVictory){
				panel.stopTimer(true);
				panel.happiness = true;
			}
			
			if(panel.inSmiley(mx, my)== true){
				board.isGameOver = false;
				board.isVictory = false;
				panel.resetAll();
				board.reset(panel.count);
			}
			
			
			if(panel.inFlagger(mx, my) == true){
				
				board.isFlagMode = !board.isFlagMode;
				panel.flagger = board.isFlagMode;
			}
			
			if(panel.ineasy(mx,my) == true){
				panel.easyLevel();
				System.out.println("In EASY");
			}
			if(panel.inMedium(mx,my) == true){
				panel.mediumLevel();
				System.out.println("In MIDLLE");
			}
			if(panel.inHard(mx,my) == true){
				panel.hardLevel();
				System.out.println("In HARD");
			}
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
	
	
	public void checkVictoryStatus(){
			
	}
	
	public int totalMines(){
		int total = 0;
		for(int i = 0; i < i3; i++){
			for(int j = 0; j < j3; j++){
				if(mines[i][j] == 1){
					total++;	
				}
			}
		}
		return total;
	}
	
	public int totalBoxesRevealed(){
		int total = 0;
		for(int i = 0; i < i3; i++){
			for(int j = 0; j < j3; j++){
				if(revealed[i][j] == true){
					total++;
				}
			}
		}
		return total;
	}
	
	
	public boolean AutoGame(){
		if(mx >= 2 && mx <160 && my >= 27  && my < 43 ){
			return true;
		}
		return false;
	}
	
	public boolean isN(int mX, int mY, int cX, int cY  ){
		
		if( mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && mines[cX][cY]==1){
			return true;    
		}
		
		return false; 
	}

	@Override
	public void easyPressed(int x, int y, int count) {
		this.setSize(x, y);
		resetPanel();
		board.setEasy(count);
	}

	@Override
	public void mediumPressed(int x, int y, int count) {
		this.setSize(x, y);
		resetPanel();
		board.setMedium(count);
	}

	@Override
	public void hardPressed(int x, int y, int count) {
		this.setSize(x, y);
		resetPanel();
		board.setHard(count);
	}
	
	public void resetPanel(){
		board.isGameOver = false;
		board.isVictory = false;
		panel.stopTimer(true);
		panel.happiness = true;
		panel.resetAll();
	}

}
