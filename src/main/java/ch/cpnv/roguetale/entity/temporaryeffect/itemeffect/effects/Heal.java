package ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.effects;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import ch.cpnv.roguetale.entity.temporaryeffect.itemeffect.ItemEffect;

public class Heal extends ItemEffect {
	private static final String DEFAULT_PATH = "ch\\cpnv\\roguetale\\images\\effects\\heal.png";

	public Heal(Vector2f position) throws SlickException {
		super(new SpriteSheet(new Image(DEFAULT_PATH), 128, 128, 0), position, 800);
		initAnimation();
	}
	
	private void initAnimation() {
		Image[] textures = new Image[16];
		for (int w = 0;w<4;w++) {
			for (int h = 0;h<4;h++) {
				textures[w*4 + h] = this.spritesheet.getSprite(w, h);
			}
		}
		
		this.animation = new Animation(textures, 50);
		this.animation.setLooping(false);
	}

}
