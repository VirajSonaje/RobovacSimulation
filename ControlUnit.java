package Component.RobovacSimulation;

import GenCol.doubleEnt;
import GenCol.entity;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class ControlUnit extends ViewableAtomic{

	double consumptionMetric;
	boolean suction =false, move =false;
	
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
		passivate();
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
				}
				else {
					suction = true;
				}
				
			}
			
			holdIn("Active", 1);
			
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
			con = makeContent("Move", new entity("")); // need to figure out 
		}
		con = makeContent("Consumption", new doubleEnt(consumptionMetric));
		m.add(con);
		return m;
	}
	
	
	

}
