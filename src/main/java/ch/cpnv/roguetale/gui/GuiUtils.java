package ch.cpnv.roguetale.gui;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import ch.cpnv.roguetale.main.Main;

public class GuiUtils {

	public static void renderDefaultBackground(Graphics g) {
		Rectangle rect = new Rectangle(0, 0, Main.BASE_WIDTH, Main.BASE_HEIGHT);
		Color border = new Color(61,64,62);
		Color fill = new Color(55,56,55);
		
		GuiUtils.renderBox(rect, fill, border, 15, g);
	}
	
	public static ArrayList<String> formatDisplayText(String text, int maxSpace, Graphics g) {
		ArrayList<String> list = new ArrayList<String>();
		String description = "";
		text = text.replaceAll("\n", "`");
		
		for (int i = 0;i<text.length();i++) {
			// Cut text only at whitespace or \n
			if ((g.getFont().getWidth(description) > maxSpace && Character.isWhitespace(text.charAt(i))) 
					|| text.charAt(i) == '`') {
				list.add(description);
				description = "";
			} else			
				description += text.charAt(i);

		}
		if (description != "")
			list.add(description);
		
		return list;
	}
	
	public static void renderBox(Rectangle rect, Color fill, Color border, int width, Graphics g) {
		Color old = g.getColor();
		
		g.setColor(fill);
		g.fill(rect);
		g.setColor(border);
		g.setLineWidth(width);
		g.draw(rect);
		
		g.setColor(old);
		g.resetLineWidth();
	}
	
	public static void renderBar(Rectangle rect, Color fill, Color border, Color barProgress, int borderWidth, float percent, Graphics g) {
		Color old = g.getColor();
		Rectangle progress = new Rectangle(rect.getX(), rect.getY(), rect.getWidth() * percent, rect.getHeight());
		
		g.setColor(fill);
		g.fill(rect);
		
		g.setColor(barProgress);
		g.fill(progress);
		
		g.setColor(border);
		g.setLineWidth(borderWidth);
		g.draw(rect);
		
		g.setColor(old);
		g.resetLineWidth();
	}


}
