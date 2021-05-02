package event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class EventSystem {
	private static HashMap<Class, ArrayList<Event>> queue = new HashMap<>();
	private static HashMap<Class, ArrayList<Listener>> listeners = new HashMap<>();
	
	public static void reset() {
		queue.clear();
		listeners.clear();
	}
	
	public static void submit(Event ev) {
		
		
		if(!queue.containsKey(ev.getClass())) {
			queue.put(ev.getClass(), new ArrayList<>());
		}
		queue.get(ev.getClass()).add(ev);
	}
	
	
	public static <T> void addListener(Listener listener, Class<T> type) {
		if(!listeners.containsKey(type)) {
			listeners.put(type, new ArrayList<>());
		}
		listeners.get(type).add(listener);
	}
	
	public static <T> void addListener(Class<T> type, Consumer<T> function) {
		addListener(new Listener(function), type);
	}
	
	public static <T extends EntityEvent> void addEntityTypeListener(Class<T> evType, Consumer<T> function, Class...entityTypes) {
		addListener(new EntityTypeListener(function, entityTypes), evType);
	}
	
	public static <T extends EntityEvent> void addEntityTagListener(Class<T> evType, Consumer<T> function, int tag) {
		addListener(new EntityTagListener(function, tag), evType);
	}

	
	public static void execute() {
		var oldQueue = new HashMap<>(queue);
		queue.clear();
		for(Entry<Class, ArrayList<Event>> entry : oldQueue.entrySet()) {
			Class type = entry.getKey();
			if(!listeners.containsKey(type))
				continue;
			
			for(Event ev : entry.getValue()) {
				for(Listener listener : listeners.get(type)) {
					System.out.println(ev);
					listener.call(ev);
				}
			}
		}
		
	}
}
