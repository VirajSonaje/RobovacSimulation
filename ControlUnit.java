package Component.RobovacSimulation;

import model.modeling.message;
import view.modeling.ViewableAtomic;

public class ControlUnit extends ViewableAtomic{

	public ControlUnit() {
		this("ECU");
		// TODO Auto-generated constructor stub
	}

	public ControlUnit(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("In");
		addOutport("Clean");
		addOutport("Move");
		addOutport("Consumption");
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
	}

	@Override
	public void deltcon(double e, message x) {
		// TODO Auto-generated method stub
		super.deltcon(e, x);
	}

	@Override
	public message out() {
		// TODO Auto-generated method stub
		return super.out();
	}
	
	
	

}
