package event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.Consumer;

public class EventSystem {
	private static HashMap<Class, ArrayList<Event>> queue = new HashMap<>();
	private static HashMap<Class, ArrayList<Consumer>> listeners = new HashMap<>();
	
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
	
	public static <T> void addListener(Class<T> type, Consumer<T> listener) {
		if(!listeners.containsKey(type)) {
			listeners.put(type, new ArrayList<>());
		}
			
		listeners.get(type).add(listener);
	}
	
	public static void execute() {
		for(Entry<Class, ArrayList<Event>> entry : queue.entrySet()) {
			Class type = entry.getKey();
			if(!listeners.containsKey(type))
				continue;
			
			for(Event ev : entry.getValue()) {
				for(Consumer consumer : listeners.get(type)) {
					consumer.accept(ev);
				}
			}

		}
		
		queue.clear();
	}
}
