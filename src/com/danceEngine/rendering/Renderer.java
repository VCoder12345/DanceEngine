package com.danceEngine.rendering;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.danceEngine.ecs.EComponent;

public class Renderer extends EComponent {
	public Model model;

	public Renderer(Model model) {
		super();
		this.model = model;
	}
	
	
	public static Renderer rectRenderer(Color color) {
		return new Renderer(new RectModel(color, true));
	}
	
	public static Renderer rectRenderer(Color color, boolean fill) {
		return new Renderer(new RectModel(color, fill));
	}
	
	public static Renderer spriteRenderer(BufferedImage sprite) {
		return new Renderer(new SpriteModel(sprite));
	}
	
}
