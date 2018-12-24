package org.atlasgl.coreapi.loaders;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontLoader {

	/**
	 * Loads a font. Only true type font (.ttf) types allowed
	 * 
	 * TODO: 
	 * 		- Add checks to make sure it is a .tff file
	 * 		- Load the font into memory so we don't have to load it every time (caching system)
	 * 
	 * @param path - path to the font (this/is/anexample/font.ttf)
	 * @param fontSize - the size of the font (20, 30)
	 * @return - null if all else fails
	 */
	public static Font loadFont(String path, float fontSize) {
		
		try {
			
			System.out.println(String.format("\u001B[33m" + "[INFO][AtlasGL][FontLoader] Loading font %s ...succeded!", path));
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, fontSize);
			
		} catch (FontFormatException | IOException e) {
			
			System.err.println(String.format("\u001B[31m" + "[ERROR][AtlasGL][FontLoader] Loading font %s ...failed!", path));
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	

}
