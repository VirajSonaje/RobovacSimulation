package Component.RobovacSimulation;

import model.modeling.message;
import view.modeling.ViewableAtomic;

public class Lidar extends ViewableAtomic {

	public Lidar() {
		this("LiDAR");
		// TODO Auto-generated constructor stub
	}

	public Lidar(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("In");
		addOutport("Out");
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
	public message out() {
		// TODO Auto-generated method stub
		return super.out();
	}
	
	
	
}
