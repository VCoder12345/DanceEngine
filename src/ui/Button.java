package ui;

import java.awt.Color;
import java.util.function.Consumer;

import ecs.EComponent;
import ecs.Entity;

public class Button extends EComponent {
	public Consumer<Entity> onBtnClick;
	public Color normalColor, hoverColor;

	public Button(Consumer<Entity> onBtnClick, Color normalColor, Color hoverColor) {
		super();
		this.onBtnClick = onBtnClick;
		this.normalColor = normalColor;
		this.hoverColor = hoverColor;
	}
	
	
}
