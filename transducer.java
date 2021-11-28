package Component.RobovacSimulation;

import java.util.HashMap;
import java.util.Map;

import GenCol.Queue;
import GenCol.entity;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class transducer extends ViewableAtomic {
	
	double clock =0;
	Queue<String> operations = new Queue<String>();
	Map<String, Double> b1, b2;
	
	public transducer() {
		this("transducer", 2600, 2600);
	}
	
	public transducer(String name, double b1Cap, double b2Cap) {
		super(name);
		
		addOutport("Out");
		addInport("In");

		b1 = new HashMap<String, Double>();
		b1.put("level", (double) 100);
		b1.put("mAh", b1Cap);
		b1.put("efficiency", (double) 90);
		b1.put("Consumption", 0.0);
		
	}

	@Override
	public void initialize() {
		super.initialize();
		sigma = INFINITY;
		phase = "Passive";
	}
	
	@Override
	public void deltint() {
		// TODO Auto-generated method stub
		super.deltint();
		clock = clock + sigma;
		passivate();
		show_state();
	
	}
	

	@Override
	public void deltext(double e, message x) {
		Continue(e);
		entity val;
		for(int i=0; i<x.getLength(); i++) {
			if(messageOnPort(x,"In",i)) {
				val = x.getValOnPort("In",i);
				operations.add(val.getName());
			}	
		}
		
	show_state();
	}
		
	
	public message out() {
		message m = new message();
//		content con1 = makeContent("battery1", new entity(" "+batteryDissipation(battery1)));
//		content con2 = makeContent("battery2",new entity(" "+batteryDissipation(battery2)));
//		m.add(con1);
//		m.add(con2);
		return m;
	}
	
	private void show_state() {
		// TODO Auto-generated method stub
		System.out.println("Current battery Level for battery 1: " + b1.get("level"));
		System.out.println("Current battery Level for battery 2: " + b2.get("level"));
	}
	

}
