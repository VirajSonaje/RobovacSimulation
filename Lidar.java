package Component.RobovacSimulation;

import GenCol.doubleEnt;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class Lidar extends ViewableAtomic {
	
	double consumptionMetric;

	public Lidar() {
		this("LiDAR",20);
		// TODO Auto-generated constructor stub
	}

	public Lidar(String name, double consumption) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("In");
		addOutport("Out");
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
	}
	
	@Override
	public message out() {
		// TODO Auto-generated method stub
		message m = new message();
		content con = makeContent("Consumption", new doubleEnt(consumptionMetric*(5/3600)));
		m.add(con);
		
		return m;
	}
	
	
	
}
