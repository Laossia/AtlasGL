package org.atlasgl.coreapi.render.scene;

import java.util.ArrayList;

public class DrawableManager {
	
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	
	public void addDrawable(Drawable d) {
		
		drawables.add(d);
		
	}
	
	public void removeDrawable(Drawable d) {
		
		drawables.remove(d);
		
	}
	
	public void renderDrawables() {
		
		drawables.forEach((d) -> d.render());
		
	}

}
