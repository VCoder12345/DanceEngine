package ui;


import ecs.ESystem;
import ecs.Entity;
import ecs.Transform;
import event.EventSystem;
import input.Input;
import input.MouseReleasedEvent;
import rendering.ColorModel;
import rendering.RectModel;
import rendering.Renderer;
import utils.Vector2;

public class ButtonSystem extends ESystem {
	

	@Override
	public void start() {
		super.start();
		EventSystem.addListener(MouseReleasedEvent.class, this::onMouseClicked);
	}
	
	

	@Override
	public void update(float dt) {
		for (Entity e : getEntitiesWithTypes(Button.class, Renderer.class, Transform.class)) {
			Button btn = e.getComponentByType(Button.class);
			Renderer r = e.getComponentByType(Renderer.class);
			Transform t = e.getComponentByType(Transform.class);
			
			ColorModel model = (ColorModel) r.model;
			
			Vector2 mPos = Input.getMousePos();
			if(mPos.x > t.position.x && mPos.x < t.position.x + t.size.x
					&& mPos.y > t.position.y && mPos.y < t.position.y + t.size.y) {
				model.color = btn.hoverColor;
			}else {
				model.color = btn.normalColor;
			}
		}
	}

	
	public void onMouseClicked(MouseReleasedEvent ev) {
		for(Entity e : getEntitiesWithTypes(Button.class, Transform.class)) {
			Button btn = e.getComponentByType(Button.class);
			Transform t = e.getComponentByType(Transform.class);
			Vector2 mPos = Input.getMousePos();
			if(mPos.x > t.position.x && mPos.x < t.position.x + t.size.x
					&& mPos.y > t.position.y && mPos.y < t.position.y + t.size.y) {
				System.out.println("click");
				btn.onBtnClick.accept(e);
			}
		}
	}

}
