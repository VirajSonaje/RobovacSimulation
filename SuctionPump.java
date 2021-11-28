package Component.RobovacSimulation;

import GenCol.doubleEnt;
import GenCol.entity;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class SuctionPump extends ViewableAtomic{

	double consumptionMetric;
	
	public SuctionPump() {
		this("SuctionPump",20);
		// TODO Auto-generated constructor stub
	}

	public SuctionPump(String name, double consumption) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("In");
		addOutport("Out");
		addOutport("Consumption");
		
		addTestInput("In", new entity("1"));
		
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
		
		for(int i=0;i<x.getLength();i++) {
			holdIn("Active", 10);
		}
		
	}

	@Override
	public message out() {
		// TODO Auto-generated method stub
		
		message m = new message();
		content con = makeContent("Out", new entity("cleaned"));
		m.add(con);
		con = makeContent("Consumption", new doubleEnt(consumptionMetric*(10)));
		m.add(con);
		return m;
	}
	
	

}
