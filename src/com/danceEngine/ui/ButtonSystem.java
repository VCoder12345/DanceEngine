package com.danceEngine.ui;


import com.danceEngine.ecs.ESystem;
import com.danceEngine.ecs.Entity;
import com.danceEngine.ecs.Transform;
import com.danceEngine.event.EventSystem;
import com.danceEngine.input.Input;
import com.danceEngine.input.MouseReleasedEvent;
import com.danceEngine.rendering.Renderer;
import com.danceEngine.utils.Vector2;

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
			
			ButtonModel model = (ButtonModel) r.model;
			
			Vector2 mPos = Input.getMousePosInWindow();
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
			Vector2 mPos = Input.getMousePosInWindow();
			if(mPos.x > t.position.x && mPos.x < t.position.x + t.size.x
					&& mPos.y > t.position.y && mPos.y < t.position.y + t.size.y) {
				btn.onBtnClick.accept(e);
			}
		}
	}

}
