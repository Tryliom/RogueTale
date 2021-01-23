package ch.cpnv.roguetale.gui.list;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GuiScrollableList {
	private int x;
	private int y;
	private int width;
	private int height;	
	private ArrayList<Slot> slotList = new ArrayList<Slot>();
	private int currentElement;
	private int maxElement;
	private Image arrowTop;
	private Image arrowBottom;
	private static final String IMAGE_PATH = "ch\\cpnv\\roguetale\\images\\ui\\icon\\arrow-top.png";
	
	public GuiScrollableList(int x, int y, int width, int height, ArrayList<Slot> slotList) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.slotList = slotList;
		this.currentElement = 0;
		this.maxElement = this.height / 50;
		try {
			this.arrowTop = new Image(IMAGE_PATH);
			this.arrowBottom = new Image(IMAGE_PATH);
			this.arrowBottom.rotate(180);
		} catch (Exception e) {
			System.out.println("Error while adding arrow top and bottom to GuiScrollableList");
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (int i = 0; i < this.slotList.size(); i++) {
			Slot slt = this.slotList.get(i);
			
			if (this.canBeDisplay(i)) {
				slt.render(gc, g, this.y + (i - this.currentElement) * 50);
			}
		}
		
		for (int i = 0; i < this.slotList.size(); i++) {
			Slot slt = this.slotList.get(i);
			
			if (this.canBeDisplay(i)) {
				slt.renderTooltip(g);
			}
		}
		
		if (this.currentElement != 0)
			this.arrowTop.draw(this.x + this.width - this.arrowTop.getWidth(), this.y);
		if (this.currentElement + this.maxElement < this.slotList.size() - 1)
			this.arrowBottom.draw(this.x + this.width - this.arrowBottom.getWidth(), this.y + this.height);
	}
	
	

	private boolean canBeDisplay(int i) {
		return i >= this.currentElement && i <= (this.maxElement + this.currentElement);
	}

	public void update(GameContainer gc, int delta, Vector2f origin) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void keyReleased(int key, char c, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void keyPressed(int key, char c, GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

	}

	public void mousePressed(int button, int x, int y) throws SlickException {
		for (Slot slt : slotList) {
			slt.mousePressed(button, x, y);
		}

	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		for (Slot slt : slotList) {
			slt.mouseMoved(oldx, oldy, newx, newy);
		}
	}

	public void mouseReleased(int button, int x, int y) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	public void mouseWheelMoved(int score) {
		if (score < 0) {
			if (this.currentElement < this.slotList.size() - this.maxElement - 1)
				this.currentElement++;
		} else {
			if (this.currentElement > 0)
				this.currentElement--;
		}
		
	}
	
	public boolean isHovering(int x, int y) {
		return this.x < x && this.x+this.width > x && this.y < y && this.y+this.height > y;
	}
	
}
