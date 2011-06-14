package rogueshadow.towerdefense;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class TowerDefense extends BasicGame {
	public static final int PATH_EDIT = 0;
	public static final int CREEP_CRAWL = 1;
	public int mode = PATH_EDIT;
	public Path path = new Path();
	public boolean renderPath = true;
	private int isDraggingNodeId = -1;
	public EntityManager manager = new EntityManager(this);

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		AppGameContainer container = new AppGameContainer(new TowerDefense("TowerDefense"), 1024,768,false);
		container.setTargetFrameRate(60);
		container.setVSync(true);
		container.start();
	}
	
	public TowerDefense(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	public Path getPath(){
		return path;
	}
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		manager.render(g);
		if (renderPath)path.render(g);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		Input in = container.getInput();
		manager.update(delta);
		if (mode == PATH_EDIT) {
			if (in.isKeyPressed(Input.KEY_Z)){
				manager.add(new Creep(path));
			}
			if (in.isKeyPressed(Input.KEY_X)){
				renderPath = (renderPath) ? false:true;
			}
			if (in.isMousePressed(0)) {
				isDraggingNodeId = path.getNode(in.getMouseX(), in.getMouseY());
				if (isDraggingNodeId == -1) {
					path.addNode(in.getMouseX(), in.getMouseY());
					isDraggingNodeId = path.getNode(in.getMouseX(),
							in.getMouseY());
				}
			}
			if (!in.isMouseButtonDown(0)) {
				isDraggingNodeId = -1;
			} else {
				Vector2f n = path.getNode(isDraggingNodeId);
				n.set(in.getMouseX(), in.getMouseY());
			}
			if (in.isMousePressed(1)) {
				isDraggingNodeId = path.getNode(in.getMouseX(), in.getMouseY());
				if (isDraggingNodeId != -1) {
					path.removeNode(isDraggingNodeId);
				}
			}
			if (in.isMouseButtonDown(2)){
				Creep creep = (Creep) manager.getStrongestCreep(in.getMouseX(), in.getMouseY(),200f);
				creep.hit(1, 0);
			}
		}
		
		// TODO Auto-generated method stub
		
	}

}
