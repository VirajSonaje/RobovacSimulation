package Component.RobovacSimulation;

import java.awt.Dimension;
import java.awt.Point;

import GenCol.Pair;
import GenCol.entity;
import view.modeling.ViewableAtomic;
import view.modeling.ViewableComponent;
import view.modeling.ViewableDigraph;

public class Robovac extends ViewableDigraph{

	public Robovac() {
		this("Robovac");
	}
	
	public Robovac(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
		ViewableAtomic lidar = new Lidar("LiDAR", 625);// 5
		ViewableAtomic IR = new DustGenerator("IR", 20);//mA 3
		ViewableAtomic CU = new ControlUnit("ECU", 50);//mA 1
		ViewableAtomic suction = new SuctionPump("Suction", 7000);// 10
		ViewableAtomic wheeels = new Wheel("Wheels", 900); // 2 sec 
		ViewableAtomic tx = new transducer("Transducer", 2600, 2600, 200);

		add(lidar);
		add(IR);
		add(CU);
		add(wheeels);
		add(suction);
		add(tx);
		
		addInport("Start");
		addOutport("Result");
		
	    addTestInput("Start",new Pair<Integer, Integer>(0, 0));
	    addTestInput("Stop",new entity("stop"));
	    
	    addCoupling(this, "Start", lidar, "Start");
	    addCoupling(this, "Start", lidar, "Stop");
	    //addCoupling(this, "Start", IR, "Start");
	    //addCoupling(this, "Start", IR, "Stop");
	    addCoupling(lidar, "Out", CU, "LiDARin");
	    addCoupling(IR, "Out", CU, "IRin");
	    addCoupling(CU, "Clean", suction, "In");
	    addCoupling(CU, "Move", wheeels, "In");
	    addCoupling(CU, "askIR", IR, "Start");
	    addCoupling(lidar, "Consumption", tx, "In");
	    addCoupling(IR, "Consumption", tx, "In");
	    addCoupling(CU, "Consumption", tx, "In");
	    addCoupling(suction, "Consumption", tx, "In");
	    addCoupling(suction, "Out", CU, "LiDARin");
	    addCoupling(wheeels, "Consumption", tx, "In");
	    addCoupling(wheeels, "Out", CU, "IRin");
	    addCoupling(tx, "Out", this, "Result");
		
	}

	@Override
	public void layoutForSimView() {
		// TODO Auto-generated method stub
		//super.layoutForSimView();
		 preferredSize = new Dimension(700, 400);
	        ((ViewableComponent)withName("LiDAR")).setPreferredLocation(new Point(5, 50));
	        ((ViewableComponent)withName("IR")).setPreferredLocation(new Point(5, 280));
	        ((ViewableComponent)withName("ECU")).setPreferredLocation(new Point(100, 170));
	        ((ViewableComponent)withName("Wheels")).setPreferredLocation(new Point(330, 50));
	        ((ViewableComponent)withName("Transducer")).setPreferredLocation(new Point(360, 170));
	        ((ViewableComponent)withName("Suction")).setPreferredLocation(new Point(330, 280));
		
	}

	
	
}
