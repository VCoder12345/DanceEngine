package com.danceEngine.game;

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
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.danceEngine.ecs.DataLocator;
import com.danceEngine.ecs.EcsManager;
import com.danceEngine.event.EventSystem;
import com.danceEngine.input.Input;
import com.danceEngine.scene.Scene;
import com.danceEngine.time.Time;

public class Game implements Runnable {
	public static float scale;
	public static int width = 600;
	public static int height = width / 16 * 9;
	public static int gameImgX, gameImgY;
	private int rWidth, rHeight;
	private int gameImgWidth, gameImgHeight;
	private static Color backgroundColor = Color.gray;
	private static final String title = "DanceTeacher";
	
	//config-stuff
	public static boolean fpsDisplay = true;
	public static boolean renderGame = true;
	
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
	private static Stack<Integer> sceneStack = new Stack<>();
	private static boolean loadedNewScene = true;
	

	
	public Game() {
		this(width, Toolkit.getDefaultToolkit().getScreenSize(), true);
	}
	
	public Game(int nwidth) {
		this(nwidth, Toolkit.getDefaultToolkit().getScreenSize(), true);
	}
	
	
	public Game(int scrWidth, int scrHeight) {
		this(width, new Dimension(scrWidth, scrHeight), false);
	}
	
	public Game(int width, Dimension scrDim, boolean undecorated) {
		if(!renderGame)
			return;
		height = width / 16 * 9;
		this.rWidth = scrDim.width;
		this.rHeight = scrDim.height;
		float wscale = (float)rWidth / (float)width;
		float hscale = (float)rHeight / (float)height;
		Game.scale = Math.min(wscale, hscale);
		this.gameImgWidth = (int) (width * scale);
		this.gameImgHeight = (int) (height * scale);
		this.gameImgX = (rWidth - gameImgWidth) / 2;
		this.gameImgY = (rHeight - gameImgHeight) / 2;
		 
		
		frame = new JFrame();
		frame.setTitle(title);
		frame.setIgnoreRepaint(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(undecorated);
		//frame.setLayout(null);
		
		canvas = new Canvas();
		canvas.setSize(rWidth, rHeight);
		canvas.setIgnoreRepaint(true);
		frame.add(canvas);
		
		
		frame.pack();
		
		frame.setVisible(renderGame);
		
		canvas.createBufferStrategy(2);
		buffer = canvas.getBufferStrategy();
		
		gameImage = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration()
				.createCompatibleImage(width, height);
		
		
		//init input
		Input input = new Input();
		frame.addKeyListener(input);
		frame.addMouseListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseWheelListener(input);
		
		canvas.addKeyListener(input);
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);
		canvas.addMouseWheelListener(input);
		Input.init();
	}
	
	public static void setWidth(int width) {
		Game.width = width;
		Game.height = width / 16 * 9;
	}
	

	@Override
	public synchronized void run() {
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
					Time.deltaTime = dt;
					update(dt);
					accumulator -= dt;
				}
				
				/*if(deltaTime > 0.15f)
					deltaTime = 0.15f;*/
				
				if(renderGame) {
					g2d = gameImage.createGraphics();
					
					
					render(g2d);
					
					graphics = buffer.getDrawGraphics();
					graphics.setColor(Color.black);
					graphics.fillRect(0, 0, rWidth, rHeight);
					
					graphics.drawImage(gameImage, gameImgX, gameImgY, gameImgWidth, gameImgHeight, null);
					
					if(!buffer.contentsLost())
						buffer.show();
				}
				frames++;
			}finally {
				if(graphics != null)
					graphics.dispose();
				if(g2d != null)
					g2d.dispose();
			}
		}
	}
	
	public static void setBackgroundColor(Color color) {
		backgroundColor = color;
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
		DataLocator.init();
		if(!currentScene.isPersistent() || !currentScene.wasStartedBefore())
			currentScene.prepare();
		currentScene.start();
	}
	
	private void update(float dt) {
		loadedNewScene = true;
		currentScene.update(dt);
		currentScene.runActions(dt);
		EventSystem.execute();
		currentScene.afterEvents(dt);
		Input.update();
	}
	
	
	private void render(Graphics2D g2d) {
		//clear background
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, width, height);
		
		//scene-rendering
		currentScene.render(g2d);
	}
	
	public static int addScene(Scene scene) {
		scenes.add(scene);
		return scenes.size() - 1;
	}
	
	public static int addLoadScene(Scene scene) {
		int i = addScene(scene);
		loadScene(i);
		return i;
	}
	
	public static int addLoadSceneAdditive(Scene scene) {
		int i = addScene(scene);
		loadSceneAdditive(i);
		return i;
	}
	
	public static void loadSceneAdditive(int i) {
		if(!loadedNewScene) return;
		setCurrentScene(i);
		if(started) {
			reset();
		}
		sceneStack.add(currentSceneIndex);
	}
	
	private static void setCurrentScene(int i) {
		if(currentScene != null && !currentScene.keepInCache) {
			scenes.remove(currentSceneIndex);
		}
		currentSceneIndex = i;
		currentScene = scenes.get(i);
		loadedNewScene = false;
	}
	
	public static void popCurrentScene() {
		if(!loadedNewScene) return;
		sceneStack.pop();
		setCurrentScene(sceneStack.peek());
		EventSystem.reset();
		currentScene.start();
	}
	
	public static void loadScene(int i) {
		if(!loadedNewScene) return;
		if(!sceneStack.empty()) sceneStack.pop();
		loadSceneAdditive(i);
	}
	
	private static void reset() {
		if(!currentScene.isPersistent())
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

	public static void reloadScene() {
		loadScene(currentSceneIndex);
	}
	
	public static int getCurrentSceneIndex() {
		return currentSceneIndex;
	}
	
	public static int numScenes() {
		return scenes.size();
	}

}
