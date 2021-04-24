package debug;

import java.awt.Color;
import java.util.ArrayList;

import ecs.Transform;
import rendering.LineModel;
import rendering.ModelTransformPair;
import rendering.RectModel;
import utils.Pair;

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

	public static void drawRect(float x, float y, float sx, float sy, int z, Color color) {
		if(!rendering) {
			return;
		}
		
		RectModel model = new RectModel(color);
		ModelTransformPair pair = new ModelTransformPair(model, new Transform(x, y, sx, sy, z));
		models.add(new Pair<Integer, ModelTransformPair>(-1, pair));
	}
}
