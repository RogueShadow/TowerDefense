package rogueshadow.towerdefense;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class EntityManager {
	TowerDefense game;
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Entity> addList = new ArrayList<Entity>();
	public ArrayList<Entity> removeList = new ArrayList<Entity>();
	
	public EntityManager(TowerDefense game){
		super();
		this.game = game;
	}
	
	public TowerDefense getGame(){
		return game;
	}
	public Creep getNearestCreep(float x, float y, float range){
		float closest = range*range;
		Vector2f vdist;
		Creep creep = new Creep(new Path());
		for (Entity c: entities){
			if (c instanceof Creep){
				((Creep) c).debug = "";
				vdist = new Vector2f(c.getPosition().getX() - x, c.getPosition().getY() - y);
				if (vdist.lengthSquared() < closest){
					closest = vdist.lengthSquared();
					creep = (Creep) c;
				}
			}
		}
		creep.debug = "Closest";
		return creep;
	}
	public Creep getStrongestCreep(float x, float y, float range){
		float closest = range*range;
		float strongest = 0;
		Vector2f vdist;
		Creep creep = new Creep(new Path());
		for (Entity c: entities){
			if (c instanceof Creep){
				((Creep) c).debug = "";
				vdist = new Vector2f(c.getPosition().getX() - x, c.getPosition().getY() - y);
				if (vdist.lengthSquared() < closest){
					if (((Creep) c).getHealth() > strongest){
						strongest = ((Creep) c).getHealth();
						creep = (Creep) c;
					}
				}
			}
		}
		creep.debug = "Strongest in Range";
		return creep;
	}
	
	public void update(int delta){

		for (Entity e: entities){
			e.update(this, delta);
		}
		
		entities.addAll(addList);
		entities.removeAll(removeList);
		addList.clear();
		removeList.clear();
			
	}
	
	public void render(Graphics g){
		for (Entity e: entities){
			e.render(g);
		}
	}
	
	public void add(Entity e){
		addList.add(e);
	}
	
	public void remove(Entity e){
		removeList.add(e);
	}
	
}
