package editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import editor.help.Helper;
import editor.node.Node;

public class LevelPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
	private PriorityQueue<Node> nodes = new PriorityQueue<>(new Comparator<Node>() {

		@Override
		public int compare(Node o1, Node o2) {
			if(o1.z < o2.z) {
				return -1;
			}else if(o1.z > o2.z) {
				return 1;
			}
			
			return 0;
		}
	});
	private Node selected = null;
	private int gripOffsetx, gripOffsety;
	private BufferedImage verticalResizeImg, horizontalResizeImg;
	private int verArrowW = 50;
	private int verArrowH = 30;
	private int resize = -1;
	
	public LevelPanel() throws IOException {
		this.verticalResizeImg = ImageIO.read(new File("sprites/vertical-flip.png"));
		this.horizontalResizeImg = ImageIO.read(new File("sprites/horizontal-flip.png"));
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}
	
	public void addNode(Node node) {
		nodes.add(node);
		repaint();
	}
	
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for(Node node : nodes) {
			node.draw(g2d);	
		}
		
		drawSelection(g2d);
	}
	
	private void drawSelection(Graphics2D g2d) {
		if(selected == null) return;
		int strokeSize = 2;
		float[] dash1 = { 3f, 0f, 3f };

		BasicStroke dashStroke = new BasicStroke(strokeSize, 
		        BasicStroke.CAP_BUTT, 
		        BasicStroke.JOIN_ROUND, 
		        1.0f, 
		        dash1,
		        2f);
		g2d.setStroke(dashStroke);
		g2d.setColor(Color.black);
		int selectionX =selected.x;
		int selectionY = selected.y;
		int selectionW = selected.w;
		int selectionH = selected.h;
		g2d.drawRect(selectionX, selectionY, selectionW, selectionH);
		
		Point verAr1Pos = getVerAr1Pos();
		g2d.drawImage(verticalResizeImg, verAr1Pos.x, verAr1Pos.y, verArrowW, verArrowH, null);
		Point verAr2Pos = getVerAr2Pos();
		g2d.drawImage(verticalResizeImg, verAr2Pos.x, verAr2Pos.y, verArrowW, verArrowH, null);
		
		int horArrowW = verArrowH;
		int horArrowH = verArrowW;
		Point horAr1Pos = getHorAr1Pos();
		g2d.drawImage(horizontalResizeImg, horAr1Pos.x, horAr1Pos.y, horArrowW, horArrowH, null);
		Point horAr2Pos = getHorAr2Pos();
		g2d.drawImage(horizontalResizeImg, horAr2Pos.x, horAr2Pos.y, horArrowW, horArrowH, null);
	}
	
	private Point getHorAr1Pos() {
		return new Point(selected.x - verArrowH, selected.y + selected.h / 2 - verArrowW / 2);
	}
	
	private Point getHorAr2Pos() {
		return new Point(selected.x + selected.w, selected.y + selected.h / 2 - verArrowH / 2);
	}
	
	private Point getVerAr1Pos() {
		return new Point(selected.x + selected.w/ 2 - verArrowW / 2, selected.y - verArrowH);
	}
	
	private Point getVerAr2Pos() {
		return new Point(selected.x + selected.w / 2 - verArrowW / 2, selected.y + selected.h);
	}
	

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(selected == null) return;
		int mx = e.getX();
		int my = e.getY();
		
		if(resize < 0) {
			selected.x = mx - gripOffsetx;
			selected.y = my - gripOffsety;
			
		}else {
			switch(resize) {
			case 0:
				int oldPos = selected.x;
				selected.x = mx - gripOffsetx;
				selected.w += oldPos - selected.x;
				break;
			case 1:
				int maxX = mx + selected.w - gripOffsetx;
				selected.w = maxX - selected.x;
				break;
			case 2:
				oldPos = selected.y;
				selected.y = my - gripOffsety;
				selected.h += oldPos - selected.y;
				break;
			case 3:
				break;
			}
			
		}
		
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		resize = -1;
		
		if(selected != null) {
			Point horAr1Pos = getHorAr1Pos();
			Point horAr2Pos = getHorAr2Pos();
			Point verAr1Pos = getVerAr1Pos();
			Point verAr2Pos = getVerAr2Pos();
			
			if(Helper.intersects(mx, my, horAr1Pos, verArrowH, verArrowW)) {
				//resize left
				resize = 0;
				gripOffsetx = mx - selected.x;
				gripOffsety = my - selected.y;
			}else if(Helper.intersects(mx, my, horAr2Pos, verArrowH, verArrowW)) {
				//resize right
				resize = 1;
				gripOffsetx = mx - selected.x;
				gripOffsety = my - selected.y;
			}else if(Helper.intersects(mx, my, verAr1Pos, verArrowW, verArrowH)) {
				//resize up
				resize = 2;
				gripOffsetx = mx - selected.x;
				gripOffsety = my - selected.y;
			}else if(Helper.intersects(mx, my, verAr2Pos, verArrowW, verArrowH)) {
				//resize down
				resize = 3;
				gripOffsetx = mx - selected.x;
				gripOffsety = my - selected.y;
			}
		}
		
		if(resize < 0)
			selected = null;
			for(Node node : nodes) {
				if(node.isInside(mx, my)) {
					selected = node;
					gripOffsetx = mx - node.x;
					gripOffsety = my - node.y;
					break;
				}
			

		}

		repaint();
	}
	
	

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
