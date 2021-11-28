package Component.RobovacSimulation;

import GenCol.Pair;
import GenCol.doubleEnt;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class Wheel extends ViewableAtomic {

	double consumptionMetric;
	
	public Wheel() {
		this("wheels",30);
		// TODO Auto-generated constructor stub
	}

	public Wheel(String name, double consumption) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("In");
		addOutport("Consumption");
		
		this.consumptionMetric = consumption;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		super.initialize();
		
		phase = "Passive";
		sigma = INFINITY;
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
			Pair<String, Double> task = (Pair<String, Double>)x.getValOnPort("In", i);
			holdIn(task.key, 2);
		}
		
	}

	
	@Override
	public message out() {
		// TODO Auto-generated method stub
		
		message m = new message();
		content con = makeContent("Out", new doubleEnt(consumptionMetric*(sigma/3600)));
		m.add(con);
		
		return m;
	}

	
	
}
