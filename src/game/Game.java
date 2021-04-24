package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ecs.EcsManager;
import event.EventSystem;
import input.Input;
import physics.PhysicsInfo;
import scene.Scene;

public class Game implements Runnable {
	private final float scale;
	public static final int width = 500;
	public static final int height = width / 16 * 9;
	private final int rWidth, rHeight;
	private final int gameImgWidth, gameImgHeight, gameImgX, gameImgY;
	private static final Color backgroundColor = Color.gray;
	private static final String title = "DanceTeacher";
	
	//config-stuff
	private boolean fpsDisplay = true;
	
	//window/render stuff
	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy buffer;
	private BufferedImage gameImage;
	
	//scene-stuff
	private static ArrayList<Scene> scenes = new ArrayList<Scene>();
	private static Scene currentScene;
	private static int currentSceneIndex;
	private static boolean started = false;
	
	
	public Game() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension scrDim = tk.getScreenSize();
		
		this.rWidth = scrDim.width;
		this.rHeight = scrDim.height;
		float wscale = (float)rWidth / (float)width;
		float hscale = (float)rHeight / (float)height;
		this.scale = Math.min(wscale, hscale);
		this.gameImgWidth = (int) (width * scale);
		this.gameImgHeight = (int) (height * scale);
		this.gameImgX = (rWidth - gameImgWidth) / 2;
		this.gameImgY = (rHeight - gameImgHeight) / 2;
		 
		
		frame = new JFrame();
		frame.setTitle(title);
		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		//frame.setLayout(null);
		
		canvas = new Canvas();
		canvas.setSize(rWidth, rHeight);
		canvas.setIgnoreRepaint(true);
		frame.add(canvas);
		
		
		frame.pack();
		
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		buffer = canvas.getBufferStrategy();
		
		gameImage = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration()
				.createCompatibleImage(width, height);
		
		
		//init input
		Input input = new Input();
		frame.addKeyListener(input);
		canvas.addKeyListener(input);
	}

	@Override
	public void run() {
		init();
		
		Graphics2D g2d = null;
		Graphics graphics = null;
		
		int frames = 0;
		long totalTime = 0;
		long curTime = System.nanoTime();
		long prevTime = System.nanoTime();
		long secondInNano = 1000000000;
		final long TARGET_FPS = 60;
		final double OPTIMAL_TIME = secondInNano / TARGET_FPS;
		double delta = 0;
		double accumulator = 0;
		final float dt = 0.01f;
		long frameTime;
		
		while(true) {
			try {
				curTime = System.nanoTime();
				frameTime = (curTime - prevTime);
				delta =  frameTime / OPTIMAL_TIME;
				prevTime = curTime;
				//deltaTime = (float)delta / (float)OPTIMAL_TIME;
				accumulator += delta;
				totalTime += frameTime;
				
				if(totalTime > secondInNano) {
					totalTime -= secondInNano;
					if(fpsDisplay)
						System.out.println("FPS: " + frames);
					frames = 0;
				}
				
				while(accumulator >= dt) {
					update(dt);
					accumulator -= dt;
				}
				
				/*if(deltaTime > 0.15f)
					deltaTime = 0.15f;*/
				
				g2d = gameImage.createGraphics();
				
				
				render(g2d);
				frames++;
				
				graphics = buffer.getDrawGraphics();
				graphics.setColor(Color.black);
				graphics.fillRect(0, 0, rWidth, rHeight);
				
				graphics.drawImage(gameImage, gameImgX, gameImgY, gameImgWidth, gameImgHeight, null);
				
				if(!buffer.contentsLost())
					buffer.show();
			}finally {
				if(graphics != null)
					graphics.dispose();
				if(g2d != null)
					g2d.dispose();
			}
		}
	}
	
	public void startGame() {
		Thread thread = new Thread(this);
		thread.start();
	}
	
	private static void init() {
		start();
		started = true;
	}
	
	private static void start() {
		currentScene.prepare();
		currentScene.start();
	}
	
	private void update(float dt) {
		currentScene.update(dt);
		currentScene.runActions(dt);
		EventSystem.execute();
	}
	
	
	private void render(Graphics2D g2d) {
		//clear background
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, width, height);
		
		//scene-rendering
		currentScene.render(g2d);
	}
	
	public static void addScene(Scene scene) {
		scenes.add(scene);
	}
	
	public static void loadScene(int i) {
		currentSceneIndex = i;
		currentScene = scenes.get(i);
		if(started) {
			reset();
		}
	}
	
	private static void reset() {
		currentScene.reset();
		EventSystem.reset();
		start();
	}
	
	
	public static Scene getCurrentScene() {
		return currentScene;
	}
	
	public static EcsManager getEcsManager() {
		return currentScene.ecsManager;
	}
	
	public static PhysicsInfo getPhysicsInfo() {
		return currentScene.physicsInfo;
	}

	public static void reloadScene() {
		loadScene(currentSceneIndex);
	}
	
	public static int getCurrentSceneIndex() {
		return currentSceneIndex;
	}

}
