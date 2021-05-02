package event;

import java.util.function.Consumer;

public class EntityTypeListener extends Listener {
	public Class[] types;
	
	public EntityTypeListener(Consumer function, Class[] types) {
		super(function);
		this.types = types;
	}

	@Override
	public void call(Event ev) {
		if(ev instanceof EntityEvent) {
			callForEntity((EntityEvent) ev);
		}else {
			super.call(ev);
		}
	}
	
	private void callForEntity(EntityEvent ev) {
		if(ev.entity.hasComponentTypes(types)) {
			function.accept(ev);
		}
	}
	
	

}
