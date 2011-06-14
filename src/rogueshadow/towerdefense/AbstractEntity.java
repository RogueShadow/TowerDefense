package rogueshadow.towerdefense;

import org.newdawn.slick.geom.Vector2f;

public abstract class AbstractEntity implements Entity {
	public Vector2f position;
	public Vector2f velocity;
	
	public AbstractEntity(Vector2f position, Vector2f velocity){
		this.position = position;
		this.velocity = velocity;
	}
	public AbstractEntity(Vector2f position){
		this(position, new Vector2f(0,0));
	}
	public AbstractEntity(){
		this(new Vector2f(0,0), new Vector2f(0,0));
	}

	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public Vector2f getVelocity() {
		return velocity;
	}
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	
}
