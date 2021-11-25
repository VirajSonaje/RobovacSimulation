package Component.RobovacSimulation;

import GenCol.Pair;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class Wheel extends ViewableAtomic {

	public Wheel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wheel(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("In");
		addOutport("Consumption");
		
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
			holdIn(task.key, task.value);
		}
		
	}

	
	@Override
	public message out() {
		// TODO Auto-generated method stub
		
		/// write code for power consumption *******
		
		return super.out();
	}

	
	
}
