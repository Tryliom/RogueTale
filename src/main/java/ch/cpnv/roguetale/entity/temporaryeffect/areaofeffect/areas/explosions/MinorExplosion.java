package ch.cpnv.roguetale.entity.temporaryeffect.areaofeffect.areas.explosions;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

import ch.cpnv.roguetale.entity.temporaryeffect.areaofeffect.areas.Explosion;

public class MinorExplosion extends Explosion {
	private static final String PATH_DEFAULT_EXPLOSION = "ch\\cpnv\\roguetale\\images\\areas\\explosions\\explosion1\\1.png";
	
	public MinorExplosion(Vector2f position, int damage) throws SlickException {
		super(new SpriteSheet(PATH_DEFAULT_EXPLOSION, 96, 91), position, damage, new Circle(position.getX(), position.getY(), 50));
		this.initAnimation();
	}
	
	private void initAnimation() throws SlickException {
		Image[] textures;
		textures = new Image[9];
		for (int i=1;i<10;i++)
			textures[i-1] = new Image("ch\\cpnv\\roguetale\\images\\areas\\explosions\\explosion1\\"+i+".png");
		this.animation = new Animation(textures, 50);
		this.animation.setLooping(false);
	}

}
