package rogueshadow.towerdefense;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public interface Entity {
	
	public void update(EntityManager manager, int delta);
	
	public void render(Graphics g);

	public Vector2f getPosition();
	
	public Vector2f getVelocity();
	
	
}
