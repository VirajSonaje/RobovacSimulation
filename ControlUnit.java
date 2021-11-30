package Component.RobovacSimulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import GenCol.Bag;
import GenCol.Pair;
import GenCol.doubleEnt;
import GenCol.entity;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class ControlUnit extends ViewableAtomic{

	double consumptionMetric;
	boolean suction =false, move =false, getDirt = false;
	String direction = "";
	int index =1;
	Set<Pair<Integer,Pair<Integer, Integer>>> pathSet;
	Iterator<Pair<Integer, Pair<Integer, Integer>>> giter;
	Pair<Integer,Pair<Integer, Integer>> prev = new Pair<Integer,Pair<Integer, Integer>> (0,new Pair<Integer,Integer>(0, 0));
	
	public ControlUnit() {
		this("ECU", 50);
		// TODO Auto-generated constructor stub
	}

	public ControlUnit(String name, double consumption) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("LiDARin");
		addInport("IRin");
		addOutport("Clean");
		addOutport("Move");
		addOutport("askIR");
		addOutport("Consumption");

		this.consumptionMetric = consumption;
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		
		sigma = INFINITY;
		phase = "Passive";
		
	}

	@Override
	public void deltint() {
		// TODO Auto-generated method stub
		super.deltint();
		//passivate();
		if(move) {
			move = false;
			holdIn("Move", INFINITY);
		}
		

		if(getDirt) {
			getDirt = false;
			holdIn("CheckDirt", INFINITY);
		}
		
		
		if(suction) {
			//suction = false;
			holdIn("Move", INFINITY);
		}
		
	}

	@Override
	public void deltext(double e, message x) {
		// TODO Auto-generated method stub
		super.deltext(e, x);
		Continue(e);
		for(int i=0;i<x.getLength();i++) {
			if(messageOnPort(x, "IRin", i)) {
				String val = x.getValOnPort("IRin", i).getName();
				if(val.startsWith("no")) {
					suction = false;
					move = true;
				}
				else if(val.equals("reached")){
					getDirt = true;
					holdIn("CheckDirt", 1);
				}
				else {
					suction = true;
					holdIn("Clean", 1);
				}
				
			}
			if(messageOnPort(x, "LiDARin", i) || move) {
				if(suction) {
					suction =false;
				}
				else if(!move) {
					//System.out.println(x.getValOnPort("LiDARin", i));
					Bag<Pair<Integer,Pair<Integer, Integer>>> path = (Bag<Pair<Integer,Pair<Integer, Integer>>> )x.getValOnPort("LiDARin", i);	
					//System.out.println(path);
					pathSet = path.bag2Set();
					getDirt =true;
					giter = pathSet.iterator();
				}
				
			
				
				if(giter.hasNext()) {
					move = true;
//					path.bag			
//					List list = new ArrayList(pathSet);
//					Collections.reverse(list);
//					pathSet = new LinkedHashSet(list);
					
					//System.out.println(pathSet);
								
					Pair<Integer,Pair<Integer,Integer>> cur = giter.next();
					
					System.out.println(cur);
					
					holdIn("Move", 1);
					int pri = cur.value.key - prev.value.key;
					int sec = cur.value.value - prev.value.value;
					if(pri == 0 && sec == 1) {
						direction = "East";
					}
					else if(pri == 0 && sec == -1) {
						direction = "West";
					}
					else if(pri == 1 && sec == 0) {
						direction = "South";
					}
					else if(pri == -1 && sec == 0) {
						direction = "North";
					}
					else if(pri == 1 && sec == 1) {
						direction = "SouthEast";
					}
					else if(pri == -1 && sec == 1) {
						direction = "NorthEast";
					}
					else if(pri == -1 && sec == -1) {
						direction = "NorthWest";
					}
					else if(pri == 1 && sec == -1) {
						direction = "SouthWest";
					}
					else {
						move = false;
						holdIn("CheckDirt", 1);
					}
					prev = cur;
					
						
					//}
				}
				
				
			}
			
			
		}
		
		
	}

	@Override
	public void deltcon(double e, message x) {
		// TODO Auto-generated method stub
		deltint();
		deltext(0, x);
	}

	@Override
	public message out() {
		// TODO Auto-generated method stub
		message m = new message();
		content con;
		if(suction) {
			con= makeContent("Clean", new entity("clean"));
			m.add(con);
		}
		if(move) {
			con = makeContent("Move", new entity(direction));
			m.add(con);
		}
		if(getDirt) {
			con = makeContent("askIR", new entity("Dust??"));
			m.add(con);	
		}
		con = makeContent("Consumption", new doubleEnt(consumptionMetric));
		m.add(con);
		return m;
	}
	
	
	

}
