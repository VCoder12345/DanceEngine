package com.danceEngine.debug;

import java.awt.Color;
import java.util.ArrayList;

import com.danceEngine.ecs.Transform;
import com.danceEngine.rendering.ArcModel;
import com.danceEngine.rendering.LineModel;
import com.danceEngine.rendering.ModelTransformPair;
import com.danceEngine.rendering.OvalModel;
import com.danceEngine.rendering.RectModel;
import com.danceEngine.utils.Pair;
import com.danceEngine.utils.Vector2;

public class Debug {
	private static ArrayList<Pair<Integer, ModelTransformPair>> models = new ArrayList<>();
	public static boolean rendering = true;
	
	public static void drawLine(float x1, float y1, float x2, float y2, int z, Color color) {
		if(!rendering) {
			return;
		}
		LineModel model = new LineModel(color, x2, y2);
		ModelTransformPair pair = new ModelTransformPair(model, new Transform(x1, y1, z));
		models.add(new Pair<Integer, ModelTransformPair>(-1, pair));
	}
	
	public static void drawCircle(Vector2 center, float radius, int z, Color color) {
		if(!rendering) {
			return;
		}
		
		OvalModel model = new OvalModel(color);
		float diameter = 2 * radius;
		ModelTransformPair pair = new ModelTransformPair(model, new Transform(center.x - radius, center.y - radius, diameter, diameter, z));
		models.add(new Pair<Integer, ModelTransformPair>(-1, pair));
	}
	
	public static void drawArc(Vector2 center, float radius, float startAngle, float arcAngle, int z, Color color) {
		if(!rendering) {
			return;
		}
		
		
		ArcModel model = new ArcModel(startAngle, arcAngle, color);
		float diameter = 2 * radius;
		ModelTransformPair pair = new ModelTransformPair(model, new Transform(center.x - radius, center.y - radius, diameter, diameter, z));
		models.add(new Pair<Integer, ModelTransformPair>(-1, pair));
	}
	
	public static void drawLine(Vector2 start, Vector2 end, int z, Color color) {
		drawLine(start.x, start.y, end.x, end.y, z, color);
	}
	
	public static ArrayList<ModelTransformPair> getModels() {
		if(!rendering) {
			return new ArrayList<>();
		}
		ArrayList<ModelTransformPair> nModels = new ArrayList<>();
		for(int i = models.size() - 1; i >= 0; --i) {
			var pair = models.get(i);
			if(pair.value1 == 0) {
				models.remove(i);
			}else {
				nModels.add(pair.value2);
				if(pair.value1 < 0) {
					pair.value1 = 0;
				}
			}
		}
		
		return nModels;
	}

	public static void drawRect(float x, float y, float sx, float sy, int z, Color color, boolean fill) {
		if(!rendering) {
			return;
		}
		
		RectModel model = new RectModel(color);
		model.fill = fill;
		ModelTransformPair pair = new ModelTransformPair(model, new Transform(x, y, sx, sy, z));
		models.add(new Pair<Integer, ModelTransformPair>(-1, pair));
	}

	public static void drawRect(Vector2 position, Vector2 size, int z, Color color, boolean fill) {
		drawRect(position.x, position.y, size.x, size.y, z, color, fill);
	}
	
	public static void drawRect(Vector2 position, Vector2 size, int z, Color color) {
		drawRect(position.x, position.y, size.x, size.y, z, color, true);
	}
	
	public static void drawRect(float x, float y, float sx, float sy, int z, Color color) {
		drawRect(x, y, sx, sy, z, color, true);
	}
}
