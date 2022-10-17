package editor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import editor.node.SpriteNode;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class EditorWindow extends JFrame {

	private JPanel contentPane;
	private static final int windowWidth = 1600, windowHeight = 1100;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorWindow frame = new EditorWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public EditorWindow() throws IOException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, windowWidth, windowHeight);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Node");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sprite");
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int levelPanelWidth = 1500;
		int levelPanelHeight = 1000;
		LevelPanel levelPanel = new LevelPanel();
		levelPanel.setBackground(Color.LIGHT_GRAY);
		levelPanel.setBounds(29, 22, levelPanelWidth, levelPanelHeight);
		levelPanel.addNode(new SpriteNode(200, 200, 0, 200, 350, "sprites/phone_empty.png"));
		contentPane.add(levelPanel);
	}
}
