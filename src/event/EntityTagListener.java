package event;

import java.util.function.Consumer;

public class EntityTagListener extends Listener {
	public int tag;
	
	public EntityTagListener(Consumer function, int tag) {
		super(function);
		this.tag = tag;
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
		if(ev.entity.hasTag(tag)) {
			function.accept(ev);
		}
	}
	
	

}
