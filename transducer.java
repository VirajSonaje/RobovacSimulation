package Component.RobovacSimulation;

import java.util.HashMap;
import java.util.Map;

import GenCol.Queue;
import GenCol.doubleEnt;
import GenCol.entity;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class transducer extends ViewableAtomic {
	
	double clock =0;
	Map<String, Double> b1, b2;
	double observationTime;
	
	public transducer() {
		this("transducer", 2600, 2600, 20);
	}
	
	public transducer(String name, double b1Cap, double b2Cap, double obsTime) {
		super(name);
		
		addOutport("Out");
		addInport("In");
		
		this.observationTime = obsTime;

		b1 = new HashMap<String, Double>();
		b1.put("level", (double) 100);
		b1.put("mAh", b1Cap*3600);
		b1.put("efficiency", (double) 0.8);
		b1.put("Consumption", 0.0);

		b2 = new HashMap<String, Double>();
		b2.put("level", (double) 100);
		b2.put("mAh", b2Cap*3600);
		b2.put("efficiency", (double) 0.5);
		b2.put("Consumption", 0.0);
		
	}

	@Override
	public void initialize() {
		super.initialize();
		sigma = observationTime;
		phase = "Active";
		
	}
	
	@Override
	public void deltint() {
		// TODO Auto-generated method stub
		//super.deltint();
		clock = clock + sigma;
		passivate();
		//show_state();
	
	}
	

	@Override
	public void deltext(double e, message x) {
		Continue(e);
		clock+=e;
		doubleEnt val;
		for(int i=0; i<x.getLength(); i++) {
			if(messageOnPort(x,"In",i)) {
				val = (doubleEnt)x.getValOnPort("In",i);
				
				b1.put("Consumption", b1.get("Consumption")+ val.getv()/b1.get("efficiency"));
				b1.put("level", ((b1.get("mAh")-b1.get("Consumption"))/b1.get("mAh"))*100);
				
				b2.put("Consumption", b2.get("Consumption")+ val.getv()/b2.get("efficiency"));
				b2.put("level", ((b2.get("mAh")-b2.get("Consumption"))/b2.get("mAh"))*100);
				
			}	
		}
		
	show_state();
	}
		
	
	public message out() {
		message m = new message();
		content con1 = makeContent("Out", new entity("Consumption: "+b1.get("Consumption")));
		content con2 = makeContent("Out",new entity("Consumption: "+b2.get("Consumption")));
		m.add(con1);
		m.add(con2);
		return m;
	}
	
	private void show_state() {
		// TODO Auto-generated method stub
		System.out.println("Current battery Level for battery 1: " + b1.get("level"));
		System.out.println("Current consumption : " + b1.get("Consumption"));
		System.out.println("Current battery Level for battery 2: " + b2.get("level"));
		System.out.println("Current consumption : " + b2.get("Consumption"));
	}
	

}
