package utils;

public abstract class Grid<TCell> {
	public int anzx, anzy;
	public Vector2 cellSize;
	public TCell[][] cells;
	
	public Grid(int anzx, int anzy, Vector2 tileSize) {
		super();
		this.anzx = anzx;
		this.anzy = anzy;
		this.cellSize = tileSize;
		create();
	}
	
	protected abstract void create();
	
	public int indexXFromPos(float posx) {
		int x = (int) (posx / cellSize.x);
		
		//is it out of bounds
		if(x < 0)
			x = 0;
		else if(x >= anzx)
			x = anzx - 1;
		
		return x;
	}
	
	public int indexYFromPos(float posy) {
		int y =  (int) (posy / cellSize.y);
		
		//is it out of bounds
		if(y < 0)
			y = 0;
		else if(y >= anzy)
			y = anzy - 1;
		
		return y;
	}
	
	public TCell cellFromPos(Vector2 pos) {
		int x = indexXFromPos(pos.x);
		int y = indexYFromPos(pos.y);
		return cells[x][y];
	}
	
}
