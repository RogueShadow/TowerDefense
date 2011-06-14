package rogueshadow.towerdefense;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Path {
	public ArrayList<Vector2f> nodes = new ArrayList<Vector2f>();
	int currentNode = 0;
	
	int nodeSize = 16;
	
	public Path(){
		super();
	}
	public Path(ArrayList<Vector2f> nodes){
		this.nodes = nodes;
	}
	
	public Path copyPath(){
		return new Path(nodes);
	}
	public Path copyNodes(){
		ArrayList<Vector2f> copy = new ArrayList<Vector2f>();
		for (Vector2f node: nodes){
			copy.add(node.copy());
		}
		return new Path(copy);
	}
	
	public void addNode(float x, float y){
		nodes.add(new Vector2f(x,y));
	}
	
	public void removeNode(Vector2f node){
		nodes.remove(node);
	}
	public void removeNode(int index){
		nodes.remove(index);
	}
	
	// returns -1 for node not found.
	public int getNode(float x, float y){
		int node = -1;
		Vector2f m = new Vector2f(x,y);
		for (Vector2f v: nodes){	
			if (v.distance(m) < nodeSize/2){
				return nodes.indexOf(v);
			}
		}
		
		return node;
	}
	
	public int getLength(){
		return nodes.size();
	}
	
	public Vector2f getNode(int index){
		if (nodes.size() == 0)return new Vector2f(0,0);
		if (index > nodes.size()){
			index = 0;
		}
		if (index < 0)return new Vector2f(0,0);
		if (nodes.size() <=0 ){
			return new Vector2f(0,0);
		}
		return nodes.get(index);
	}
	
	public Vector2f next(){
		if (nodes.size() == 0)return new Vector2f(0,0);
		if (currentNode > nodes.size()){
			currentNode = 0;
		}
		return getNode(currentNode++);
	}
	public boolean hasNext(){
		return (currentNode < nodes.size());
	}
	
	public void reset(){
		currentNode = 0;
	}
	
	public void render(Graphics g){
		if (nodes.size() < 1)return;
		
		Vector2f lastV = nodes.get(0);
		for (Vector2f v: nodes){
			g.setColor(Color.blue);
			g.drawLine(lastV.x, lastV.y, v.x, v.y);
			g.pushTransform();
			g.translate(v.x, v.y);
			g.drawOval(-nodeSize/2, -nodeSize/2, nodeSize, nodeSize);
			g.translate(-8, -26);
			g.setColor(Color.green);
			g.drawString(Integer.toString(nodes.indexOf(v)), 0, 0);
			lastV = v;
			g.popTransform();
			g.setColor(Color.white);
		}
		
	}

}
