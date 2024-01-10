package com.brad.game.jade;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

	private String name;
	private List<Component> components;
	public Transform transform;
	private int zIndex;

	public GameObject(String name) {
		this.name = name;
		
		this.transform = new Transform();
		this.components = new ArrayList<Component>();
	}
	
	public GameObject(String name, Transform transform, int zIndex) {
		this.name = name;
		this.transform = transform;
		this.zIndex = zIndex;
		
		this.components = new ArrayList<Component>();
	}

	public <T extends Component> T getComponent(Class<T> componentClass) {
		for (Component c : components) {
			if (componentClass.isAssignableFrom(c.getClass())) {
				try {
					return componentClass.cast(c);
				} catch (ClassCastException e) {
					e.printStackTrace();
					assert false : "Error : Casting component.";
				}
			}
		}
		return null;
	}

	public <T extends Component> void removeComponent(Class<T> classComponent) {
		for (int i = 0; i < components.size(); i++) {
			Component c = components.get(i);
			if (classComponent.isAssignableFrom(c.getClass())) {
				components.remove(i);
				return;
			}
		}
	}

	public void addComponent(Component c) {
		this.components.add(c);
		c.gameObject = this; // assign the parent gameObject to the component;
	}
	
	public void update(float dt) {
		for (int i = 0; i < components.size(); i++) {
			components.get(i).update(dt);
		}
	}
	public void start() {
		for (int i = 0; i < components.size(); i++) {
			components.get(i).start();
		}
	}
	
	public int zIndex() {
		return this.zIndex;
	}
}
