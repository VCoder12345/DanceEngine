package tiled;

public class Layer {
	public int width, height;
	public int[][] tileIndices;
	
	public Layer(int width, int height, int[][] tileIndices) {
		super();
		this.width = width;
		this.height = height;
		this.tileIndices = tileIndices;
	}
	
	
}
