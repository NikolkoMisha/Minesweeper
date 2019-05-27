
public class Cell {
	public boolean isOpened = false;
	public boolean isEmpty = true;
	public boolean isBomb = false;
	public boolean isFlaged = false;
	public int number = -1;
	
	public int x = 0;
	public int y = 0;
	
	public Cell(int x, int y) {
		super();

		this.x = x;
		this.y = y;
	}

	public void open(){
		this.isOpened = true;
	}
}
