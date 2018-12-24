package org.atlasgl.coreapi.render.scene;

import java.util.HashMap;

public class Scene {
	
	private Background background;
	
	private DrawableManager backgroundLayer = new DrawableManager();
	private HashMap<Integer, DrawableManager> layers = new HashMap<Integer, DrawableManager>();
	private DrawableManager foregroundLayer = new DrawableManager();
	
	public Scene(int layerCount) {
		
		for(int i = 0; i < layerCount; i++) {
			
			layers.put(i, new DrawableManager());
			
		}
		
	}
	
	public void setBackground(Background b) {
		
		background = b;
		
	}
	
	public void render() {
		
		if(background != null) background.render();
		
		backgroundLayer.renderDrawables();
		
		for(int i = 0; i < layers.size(); i++) {
			
			if(layers.containsKey(i)) {
				
				DrawableManager dm = layers.get(i);
				if(dm != null) dm.renderDrawables();
				
			}
			
		}
		
		foregroundLayer.renderDrawables();
		
	}

}
