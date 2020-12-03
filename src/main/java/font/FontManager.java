package font;

import java.awt.Font;
import java.util.HashMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class FontManager {
	private HashMap<FontType, TrueTypeFont> list = new HashMap<FontType, TrueTypeFont>();
	private static FontManager instance;
	
	public static FontManager getInstance() {
		return instance == null ? instance = new FontManager() : instance;
	}
	
	private FontManager() {
		this.list.put(FontType.Default, this.createFont(Font.BOLD, 18));
		this.list.put(FontType.Title, this.createFont(Font.BOLD, 22));
		this.list.put(FontType.Medium, this.createFont(Font.BOLD, 15));
		this.list.put(FontType.Small, this.createFont(Font.BOLD, 13));
	}

	private TrueTypeFont createFont(int transform, int width) {
		Font ft = new Font("Century Gothic", transform, width);
		return new TrueTypeFont(ft, true);
	}
	
	public void setFont(FontType ft, Graphics g) {
		g.setFont(this.list.get(ft));
		g.setAntiAlias(true);
	}
	
	public void resetDefaultFont(Graphics g) {
		this.setFont(FontType.Default, g);
	}
}
