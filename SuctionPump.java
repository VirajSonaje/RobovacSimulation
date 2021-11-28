package Component.RobovacSimulation;

import GenCol.doubleEnt;
import GenCol.entity;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class SuctionPump extends ViewableAtomic{

	public SuctionPump() {
		this("SuctionPump");
		// TODO Auto-generated constructor stub
	}

	public SuctionPump(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("In");
		addOutport("Out");
		addOutport("Consumption");
		
		addTestInput("In", new entity("1"));
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
			holdIn("Active", 5); // need to see how much time.
			//calculaiton on power consumption.
		}
		
	}

	@Override
	public message out() {
		// TODO Auto-generated method stub
		
		message m = new message();
		content con = makeContent("Out", new entity("cleaned"));
		m.add(con);
		con = makeContent("Consumption", new doubleEnt(5)); // need to calculate consumption
		m.add(con);
		return m;
	}
	
	

}
