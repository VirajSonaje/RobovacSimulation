package Component.RobovacSimulation;

import GenCol.Pair;
import GenCol.doubleEnt;
import GenCol.entity;
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
		addOutport("Out");
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
		//super.deltext(e, x);
		Continue(e);
		for(int i=0;i<x.getLength();i++) {
			entity task = x.getValOnPort("In", i);
			holdIn(task.getName(), 2);
		}
		
	}

	
	@Override
	public message out() {
		// TODO Auto-generated method stub
		
		message m = new message();
		content con = makeContent("Consumption", new doubleEnt(consumptionMetric*2));
		m.add(con);
		con = makeContent("Out", new entity("reached"));
		m.add(con);
		return m;
	}

	
	
}
