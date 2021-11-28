package Component.RobovacSimulation;

import GenCol.doubleEnt;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class ControlUnit extends ViewableAtomic{

	double consumptionMetric;
	
	public ControlUnit() {
		this("ECU", 50);
		// TODO Auto-generated constructor stub
	}

	public ControlUnit(String name, double consumption) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("In");
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
	}

	@Override
	public void deltext(double e, message x) {
		// TODO Auto-generated method stub
		super.deltext(e, x);
		Continue(e);
		for(int i=0;i<x.getLength();i++) {
			
		}
		
		
	}

	@Override
	public void deltcon(double e, message x) {
		// TODO Auto-generated method stub
		super.deltcon(e, x);
	}

	@Override
	public message out() {
		// TODO Auto-generated method stub
		message m = new message();
		content con = makeContent("Consumption", new doubleEnt(consumptionMetric/3600));
		m.add(con);
		return m;
	}
	
	
	

}
