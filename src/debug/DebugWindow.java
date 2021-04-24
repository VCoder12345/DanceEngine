package debug;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class DebugWindow extends JFrame {
	public DebugWindow(int width, int height) {
		setTitle("DebugWindow");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    
	    int screen = 0;
	    
	    int swidth = gd[screen].getDefaultConfiguration().getBounds().width;
        int sheight = gd[screen].getDefaultConfiguration().getBounds().height;
        setLocation(
            ((swidth / 2) - (getSize().width / 2)) + gd[screen].getDefaultConfiguration().getBounds().x, 
            ((sheight / 2) - (getSize().height / 2)) + gd[screen].getDefaultConfiguration().getBounds().y
        );
        setLayout(null);
        
        JCheckBox dbRenderingBox = new JCheckBox("debug-rendering");
        dbRenderingBox.setBounds(200, 200, 150, 50);
        dbRenderingBox.setSelected(false);
        Debug.rendering = dbRenderingBox.isSelected();
        dbRenderingBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				Debug.rendering = dbRenderingBox.isSelected();
			}
        	
        });
        add(dbRenderingBox);
        
		setVisible(true);
	}
}
