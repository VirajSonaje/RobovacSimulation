package Component.RobovacSimulation;

import GenCol.Queue;
import GenCol.entity;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class transducer extends ViewableAtomic {
	
	double clock =0;
	Queue<String> operations = new Queue<String>();
	Battery b1, b2;
	
	public transducer() {
		this("transducer");
	}
	
	public transducer(String  name) {
		super(name);
		addOutport("Battery1");
		addOutport("Battery2");
		addInport("Start");
		addInport("Stop");
		addInport("In");
		
		addTestInput("Start", new entity("start"));
		addTestInput("Stop", new entity("stop"));
		addTestInput("Battery1", new entity("val"));
		addTestInput("Battery2", new entity("val"));
		
		b1 = new Battery("Li-ion");
		b2 = new Battery("NiCa");

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
		System.out.println("Current battery Level for "+ b1.getBatteryType() +": " + b1.getBatteryLevel());
		System.out.println("Current battery Level for "+ b2.getBatteryType() +": " + b2.getBatteryLevel());
	}
	

}
