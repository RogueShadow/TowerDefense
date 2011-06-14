package rogueshadow.towerdefense;

import java.text.DecimalFormat;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Creep extends AbstractEntity implements Entity {
	public Path path;
	public String debug = "";
	public Vector2f destination;
	public Vector2f offset;
	public float speed = 0.1f;
	public float maxSpeed = 0.1f;
	public float rotation = 0;
	public boolean finished = false;
	public int size = 40;
	public int health;
	public int maxHealth;
	DecimalFormat f = new DecimalFormat("000.00");

	public Creep(Path path){
		super(path.getNode(0).copy());
		this.path = path.copyPath();
		this.destination = position.copy();
		maxHealth = (int)(Math.random()*50f)+1;
		offset = new Vector2f(0f,2-(float)Math.random()*4).normalise().scale((float)Math.random()*70f);
		health = maxHealth;
	}
	public Creep(){
		super();
	}
	public int getHealth(){
		return health;
	}
	public void hit(float damage, int type){
		if (type == 0){
			health -= damage;
			if (health <= 0){
				health = 0;
				finished = true;
			}
			if (health > maxHealth){
				health = maxHealth;
			}
		}else if (type == 1){
			speed -= damage;
			if (speed < 0)speed = 0;
		}
	}


	public void update(EntityManager manager, int delta){
		if (isFinished()){
			manager.remove(this);
		}
		Vector2f v = new Vector2f(destination.x - getPosition().x, destination.y - getPosition().y);
		float dist;
		for (int i = 0; i < delta; i++){
			v = new Vector2f(destination.x - getPosition().x, destination.y - getPosition().y);
			dist = v.length();
			v.normalise().scale(speed);
			
			if (dist > v.length()){
				getPosition().add(v);
			}else{
				getPosition().add(v.normalise().scale(dist));
				if (path.hasNext()){
					destination = path.next();
					v = new Vector2f(destination.x - getPosition().x, destination.y - getPosition().y);
					dist = v.length();
					v.normalise().scale(speed);
				}else {
					path.reset();
					setPosition(path.getNode(0).copy());
					destination = getPosition().copy();
				}
			}
		}
		
			rotation += (rotation - (float)v.getTheta() < 0) ? 2:-2;
		
	}
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.pushTransform();
		g.setColor(Color.yellow);
		g.translate(getX(),getY());
		g.drawString(debug, -15, -85);
		g.setColor(Color.red);
		g.fillRect(-20, -size, 60, 2);
		g.setColor(Color.green);
		g.fillRect(-20, -size, 60f*((float)health/(float)maxHealth), 2);
		g.rotate(0, 0, rotation);
		g.drawOval(-size/2, -size/2, size, size);
		g.drawLine(0, 0,-30, 0);
		if (isFinished())g.drawString("Finished!", 0, 0);
		g.popTransform();
		g.setColor(Color.white);
	}
	private boolean isFinished() {
		return finished;
	}
	
	public float getX(){
		return getPosition().copy().add(offset.copy().add(rotation)).getX();
	}
	public float getY(){
		return getPosition().copy().add(offset.copy().add(rotation)).getY();
	}

}
