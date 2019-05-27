import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Board {
	
	private boolean isFirstSelect = true;
	private int TOP_OFFSET = 40;
	private int xSizeE = 9;
	private int ySizeE = 9;
	
	private int xSizeM = 16;
	private int ySizeM = 16;
	
	private int xSizeH = 30;
	private int ySizeH = 16;
	
	private int xSize;
	private int ySize;
	
	public boolean isVictory = false;
	public boolean isGameOver = false;
	public boolean isFlagMode = false;
	
	String vicMes = "Nothing yet!";
	
	Random rand = new Random();
		
	private Cell[][] cells;

	int spacing = 2 ;
	
	public Board(){
		setEasy(9);
	}
	
	public void reset(int count) {
		initArray(count);
	}
	
	public void setEasy(int count) {
		xSize = xSizeE;
		ySize = ySizeE;
		initArray(count);
	}
	
	public void setMedium(int count) {
		xSize = xSizeM;
		ySize = ySizeM;
		initArray(count);
	}
	
	public void setHard(int count) {
		xSize = xSizeH;
		ySize = ySizeH;
		initArray(count);
	}
		
	private void initArray(int count) {
		isFirstSelect = true;
		cells = new Cell[xSize][ySize];
		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){
				cells[x][y] = new Cell(x,y);		
			}	
		}
		
		addMines(count);
		addNumbers();
	}
	
	private void addMines( int count) {
		
		for (int i = 0; i < count;) {
			int randX = rand.nextInt(xSize);
			int randY = rand.nextInt(ySize);
			
			Cell cell = cells[randX][randY];
			
			if (!cell.isBomb) {
				cell.isBomb = true;
				cell.isEmpty = false;
				i++;
			}
		}
	}
	
	private void addNumbers() {
		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){
				Cell cell = cells[x][y];
				
				int bombsCount =0;
				
				if(x>0  && cells[x-1][y].isBomb)bombsCount++;
				if(x<(xSize-1) && cells[x+1][y].isBomb)bombsCount++;
				if(x>0  &&y>0 && cells[x-1][y-1].isBomb)bombsCount++;
				if(y>0 && y<(ySize-1) && cells[x][y-1].isBomb)bombsCount++;
				if(x<(xSize-1) && y>0 && cells[x+1][y-1].isBomb)bombsCount++;
				if(x>0  &&y<(ySize-1) && cells[x-1][y+1].isBomb)bombsCount++;
				if( y<(ySize-1) &&cells[x][y+1].isBomb)bombsCount++;
				if(x<(xSize-1) && y<(ySize-1) &&cells[x+1][y+1].isBomb )bombsCount++;
				
				if (bombsCount > 0) {
					cell.isEmpty = false;
					cell.number = bombsCount;
				}
			}
		}
	}
	
	public void cellSelected(int x, int y) {
		if (x >= 0 && y >= 0) {
			Cell cell = cells[x][y];
			
			if(isFlagMode){
				if (!cell.isOpened) {
					cell.isFlaged = true;
				}
			}else{
				if (cell.isOpened) return;				
				
				cell.isOpened = !cell.isFlaged;
				if (cell.isBomb) {
					isGameOver = true;
					for(int xx = 0; xx < xSize; xx++){
						for(int yy = 0; yy < ySize; yy++){
							Cell cellItem = cells[xx][yy];
							cellItem.isOpened = true;
							cellItem.isFlaged = false;
						}
					}
				}
				
				
				if (cell.isEmpty) {
					System.out.println("Hello");
					openAllEmpty();
				}
			}
		}
	}
	
	public void draw(Graphics g){
		drawBackground(g);
		drawNet(g);
		drawNumbers(g);
		drawBomb(g);
		drawClosed(g);
		drawFlags(g);
	}
	
	public void drawBackground(Graphics g) {
		g.setPaintMode();
		g.setColor(Color.WHITE);
		g.fillRect(0, TOP_OFFSET, 1280, 1024);
	}
	
	public void drawNet(Graphics g) {
		int sellSide = 40;
		int lineWidth = 0;
		
		for(int x = 0; x < xSize; x++){
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine((sellSide + lineWidth)*x, TOP_OFFSET, (sellSide + lineWidth)*x, (sellSide + lineWidth)*ySize+TOP_OFFSET);
		}
		
		for(int y = 0; y < ySize; y++){
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(0, (sellSide + lineWidth)*y+TOP_OFFSET, (sellSide + lineWidth)*xSize, (sellSide + lineWidth)*y+TOP_OFFSET);
		}	
	}
	
	public void drawBomb(Graphics g) {
		
		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){
				
				Cell cell = cells[x][y];
				if (cell.isBomb) {
					g.setColor(Color.RED);
					g.fillRect(x*40+2, y*40+42, 38, 38);
					g.setColor(Color.BLACK);
					g.fillOval(x*40+13, y*40+40+13, 13, 13);
					g.fillRect(x*40+19, y*40+40+7, 2, 26);
					g.fillRect(x*40+7, y*40+40+19, 26, 2);
				}
			}
		}
	}
	
	public void drawNumbers(Graphics g){
		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){
				Cell cell = cells[x][y];
				
				if (cell.isBomb || cell.isEmpty) 
					continue;
				
					
				drawNumber(g, cell);
			}
		}
	}
	
	public void drawNumber(Graphics g, Cell cell) {
		switch(cell.number){
			case 1:{g.setColor( new Color(3, 16, 112));break;}	
			case 2:{g.setColor(new Color(4, 112, 15));break;}
			case 3:{g.setColor(new Color(211, 193, 0));break;}
			case 4:{g.setColor(new Color(0,0,128));break;}
			case 5:{g.setColor(new Color(255, 0, 0));break;}
			case 6:{g.setColor(new Color(72,255,204));break;}
			case 7:{g.setColor(new Color(0, 0, 0));break;}
			case 8:{g.setColor(new Color(58, 51, 49));break;}			
		}

		g.setFont(new Font("Tahoma", Font.BOLD, 20));
		g.drawString(Integer.toString(cell.number), 0+cell.x*40+14, cell.y*40+40+28);
	}
	
	public void drawFlags(Graphics g) {
		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){
				Cell cell = cells[x][y];
				if (!cell.isFlaged) 
					continue;
				
				g.setColor(new Color(0, 0, 0));
				g.fillRect(x*40+16+3 , y*40+40+7+3, 4, 20);
				g.fillRect(x*40+10+3, y*40+40+25+3, 15, 4);
				g.setColor(new Color(255, 0, 0));
				g.fillRect(x*40+8+3, y*40+40+8+3, 10, 8);
				
				g.setColor(new Color(0, 0, 0));
				g.drawRect(x*40+8+3, y*40+40+8+3, 10, 8);
				g.drawRect(x*40+9+3, y*40+40+8+3, 9, 7);
				g.drawRect(x*40+9+3, y*40+40+9+3, 8, 6);
			}
		}
	}
	
	public void drawClosed(Graphics g) {
		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){
				
				Cell cell = cells[x][y];
				if (!cell.isOpened) {
					g.setColor(Color.GRAY);
					g.fillRect(x*40+2, y*40+42, 38, 38);
				}
			
			}
		}
	}
	
	public int inBoxX(int mx, int my){
		return mx/(40+2);
	}
	
	public int inBoxY(int mx, int my){
		if(my<= TOP_OFFSET+26)return -1;
		return (my - TOP_OFFSET-20 )/(40+2);
	}
	
	public void autoFind() {
		if(isFirstSelect){
			Cell cell = findRandomEmpty();
			cellSelected(cell.x, cell.y);
			isFirstSelect = false;
		}else{
			autoOpen();
		}
	}
	
	public void autoOpen(){
		boolean isRun  = true;
		for(int x = 0; x < xSize &&  isRun; x++){
			for(int y = 0; y < ySize&&  isRun ; y++){
				if (cells[x][y].isOpened && cells[x][y].number > 0){
					int unopenedCount = findUnopenedAroundCount(x, y);
					
					if(unopenedCount == cells[x][y].number){
						openAround(x, y, true);
						isRun = false;
					}
				}
			}
		}
		
		isRun = true;
		
		for(int x = 0; x < xSize &&  isRun; x++){
			for(int y = 0; y < ySize&&  isRun ; y++){
				if (cells[x][y].isOpened && cells[x][y].number > 0){
					int flagsCount = findFlagsCount(x, y);
					int closedCount = closedCount(x, y);
					
					if(flagsCount == cells[x][y].number && flagsCount < closedCount){
						openAround(x, y, false);
					}
				}
			}
		}
		
		openAllEmpty();
	}
	
	public int findFlagsCount(int x, int y){
		int flagsCount = 0;
		
		if(x>0 && cells[x-1][y].isFlaged){
			flagsCount++;
		}
		if(x<(xSize-1) &&cells[x+1][y].isFlaged){
			flagsCount++;
		}
		if(x>0  &&y>0 &&cells[x-1][y-1].isFlaged){
			flagsCount++;
		}
		if(x>0 && y<(ySize-1)&& cells[x-1][y+1].isFlaged){
			flagsCount++;
		}
		if(x<(xSize-1) && y>0  && cells[x+1][y-1].isFlaged){
			flagsCount++;
		}
		if(y<(ySize-1)  && cells[x][y+1].isFlaged){
			flagsCount++;
		}
		if( y>0  && cells[x][y-1].isFlaged){
			flagsCount++;
		}
		if(x<(xSize-1) && y<(ySize-1)  && cells[x+1][y+1].isFlaged){
			flagsCount++;
		}
		
		return flagsCount;
	}
	
	public int closedCount(int x, int y){
		int closedCount = 0;
		
		if(x>0 && !cells[x-1][y].isOpened){
			closedCount++;
		}
		if(x<(xSize-1) &&!cells[x+1][y].isOpened){
			closedCount++;
		}
		if(x>0  &&y>0 &&!cells[x-1][y-1].isOpened){
			closedCount++;
		}
		if(x>0 && y<(ySize-1)&& !cells[x-1][y+1].isOpened){
			closedCount++;
		}
		if(x<(xSize-1) && y>0  && !cells[x+1][y-1].isOpened){
			closedCount++;
		}
		if(y<(ySize-1)  && !cells[x][y+1].isOpened){
			closedCount++;
		}
		if( y>0  && !cells[x][y-1].isOpened){
			closedCount++;
		}
		if(x<(xSize-1) && y<(ySize-1)  && !cells[x+1][y+1].isOpened){
			closedCount++;
		}
		
		return closedCount;
	}
	
	public int findUnopenedAroundCount(int x, int y){
		Cell cell = cells[x][y];
		int unopenedCount = 0;
		
		if(x>0 && !cells[x-1][y].isOpened && !cells[x-1][y].isFlaged){
			unopenedCount++;
		}
		if(x<(xSize-1) && !cells[x+1][y].isOpened && !cells[x+1][y].isFlaged){
			unopenedCount++;
		}
		if(x>0  &&y>0 && !cells[x-1][y-1].isOpened && !cells[x-1][y-1].isFlaged){
			unopenedCount++;
		}
		if(x>0 && y<(ySize-1) && !cells[x-1][y+1].isOpened && !cells[x-1][y+1].isFlaged){
			unopenedCount++;
		}
		if(x<(xSize-1) && y>0  && !cells[x+1][y-1].isOpened && !cells[x+1][y-1].isFlaged){
			unopenedCount++;
		}
		if(y<(ySize-1)  && !cells[x][y+1].isOpened && !cells[x][y+1].isFlaged){
			unopenedCount++;
		}
		if( y>0  && !cells[x][y-1].isOpened && !cells[x][y-1].isFlaged){
			unopenedCount++;
		}
		if(x<(xSize-1) && y<(ySize-1)  && !cells[x+1][y+1].isOpened && !cells[x+1][y+1].isFlaged){
			unopenedCount++;
		}
		
		return unopenedCount;
	}
	
	
	public Cell findRandomEmpty(){
		int x = -1;
		int y = -1;
		for(int xx = 0; xx < xSize && x < 0; xx++){
			for(int yy = 0; yy < ySize && y< 0; yy++){
				if (cells[xx][yy].isEmpty){
					x = xx;
					y = yy;
				}
			}
		}
		
		return cells[x][y];
	}
	
	public void openAllEmpty() {
		boolean isSomethisngNewOpened = false;
		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){
				if (cells[x][y].isOpened && cells[x][y].isEmpty) isSomethisngNewOpened = openAround(x, y, false)||isSomethisngNewOpened;
			}
		}
		
		if(isSomethisngNewOpened)
			openAllEmpty();
	}
	
	public boolean openAround(int x, int y, boolean isSetFlag) {
		boolean isSomethisngNewOpened = false;
		
		if(x>0 ){
			isSomethisngNewOpened = (!cells[x-1][y].isOpened && !cells[x-1][y].isFlaged)||isSomethisngNewOpened;
			if(isSetFlag){
				cells[x-1][y].isFlaged = !cells[x-1][y].isOpened;
			}else{
				cells[x-1][y].isOpened = !cells[x-1][y].isFlaged;
			}
		}
		if(x<(xSize-1)){
			isSomethisngNewOpened = (!cells[x+1][y].isOpened && !cells[x+1][y].isFlaged)||isSomethisngNewOpened;
			
			if(isSetFlag){
				cells[x+1][y].isFlaged = !cells[x+1][y].isOpened;
			}else{
				cells[x+1][y].isOpened = !cells[x+1][y].isFlaged;
			}
		}
		if(x>0  &&y>0){
			isSomethisngNewOpened = (!cells[x-1][y-1].isOpened && !cells[x-1][y-1].isFlaged)||isSomethisngNewOpened;
			
			if(isSetFlag){
				cells[x-1][y-1].isFlaged = !cells[x-1][y-1].isOpened;
			}else{
				cells[x-1][y-1].isOpened = !cells[x-1][y-1].isFlaged;
			}
		}
		if(x>0 && y<(ySize-1)){
			isSomethisngNewOpened = (!cells[x-1][y+1].isOpened && !cells[x-1][y+1].isFlaged)||isSomethisngNewOpened;
			
			if(isSetFlag){
				cells[x-1][y+1].isFlaged = !cells[x-1][y+1].isOpened;
			}else{
				cells[x-1][y+1].isOpened = !cells[x-1][y+1].isFlaged;
			}
		}
		if(x<(xSize-1) && y>0 ){
			isSomethisngNewOpened = (!cells[x+1][y-1].isOpened && !cells[x+1][y-1].isFlaged)||isSomethisngNewOpened;
			if(isSetFlag){
				cells[x+1][y-1].isFlaged = !cells[x+1][y-1].isOpened;
			}else{
				cells[x+1][y-1].isOpened = !cells[x+1][y-1].isFlaged;
			}
		}
		if(y<(ySize-1) ){
			isSomethisngNewOpened = (!cells[x][y+1].isOpened && !cells[x][y+1].isFlaged)||isSomethisngNewOpened;
			if(isSetFlag){
				cells[x][y+1].isFlaged = !cells[x][y+1].isOpened;
			}else{
				cells[x][y+1].isOpened = !cells[x][y+1].isFlaged;
			}
		}
		if( y>0 ){
			isSomethisngNewOpened = (!cells[x][y-1].isOpened && !cells[x][y-1].isFlaged)||isSomethisngNewOpened;
			if(isSetFlag){
				cells[x][y-1].isFlaged = !cells[x][y-1].isOpened;
			}else{
				cells[x][y-1].isOpened = !cells[x][y-1].isFlaged;
			}
		}
		if(x<(xSize-1) && y<(ySize-1) ){
			isSomethisngNewOpened = (!cells[x+1][y+1].isOpened && !cells[x+1][y+1].isFlaged)||isSomethisngNewOpened;
			if(isSetFlag){
				cells[x+1][y+1].isFlaged = !cells[x+1][y+1].isOpened;
			}else{
				cells[x+1][y+1].isOpened = !cells[x+1][y+1].isFlaged;
			}
		}
		
		return isSomethisngNewOpened;
	}
}