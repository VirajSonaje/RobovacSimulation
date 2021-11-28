package Component.RobovacSimulation;

import java.awt.Dimension;
import java.awt.Point;

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
		ViewableAtomic wheeels = new ControlUnit("Wheels", 900); // 2 sec 
		ViewableAtomic tx = new transducer("transducer", 2600, 2600, 20);

		add(lidar);
		add(IR);
		add(CU);
		add(wheeels);
		add(suction);
		add(tx);
		
		addInport("Start");
		addOutport("Result");
		
	    addTestInput("Start",new entity("start"));
	    addTestInput("Stop",new entity("stop"));
	    
	    addCoupling(this, "Start", lidar, "In");
	    addCoupling(this, "Start", IR, "Start");
	    addCoupling(this, "Start", IR, "Stop");
	    addCoupling(lidar, "Out", CU, "In");
	    addCoupling(IR, "Out", CU, "In");
	    addCoupling(CU, "Out", suction, "In");
	    addCoupling(CU, "Out", wheeels, "In");
	    addCoupling(lidar, "Consumption", tx, "In");
	    addCoupling(IR, "Consumption", tx, "In");
	    addCoupling(CU, "Consumption", tx, "In");
	    addCoupling(wheeels, "Consumption", tx, "In");
	    addCoupling(tx, "Out", this, "Result");
		
	}

	@Override
	public void layoutForSimView() {
		// TODO Auto-generated method stub
		//super.layoutForSimView();
		 preferredSize = new Dimension(700, 400);
	        ((ViewableComponent)withName("LiDAR")).setPreferredLocation(new Point(50, 49));
	        ((ViewableComponent)withName("IR")).setPreferredLocation(new Point(50, 70));
	        ((ViewableComponent)withName("ECU")).setPreferredLocation(new Point(63, 128));
	        ((ViewableComponent)withName("Suction")).setPreferredLocation(new Point(170, 49));
	        ((ViewableComponent)withName("Wheels")).setPreferredLocation(new Point(170, 80));
	        ((ViewableComponent)withName("transducer")).setPreferredLocation(new Point(200, 65));
		
	}

	
	
}
