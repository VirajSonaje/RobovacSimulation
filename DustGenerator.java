package Component.RobovacSimulation;

import java.util.Random;

import GenCol.entity;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class DustGenerator extends ViewableAtomic {
	
	int arrTime; 
	boolean dust = true;
	Random rd = new Random();
	
	public DustGenerator() {
		this("DustGenerator");
		// TODO Auto-generated constructor stub
	}

	public DustGenerator(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		arrTime = 10;
		addOutport("Out");
		addInport("Stop");
		addInport("Start");
		
		
		addTestInput("Start", new entity("start"));
		addTestInput("Stop", new entity("stop"));
		
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
		if(phaseIs("Active")) {
			holdIn("Active", arrTime);
			dust = rd.nextBoolean();
		}
	}

	@Override
	public void deltext(double e, message x) {
		// TODO Auto-generated method stub
		super.deltext(e, x);
		
		if(phaseIs("Passive")) {
			for(int i=0; i<x.getLength(); i++) {
				if(messageOnPort(x, "Start", i)) {
					holdIn("Active", arrTime);
					dust = rd.nextBoolean();
				}
			}
		}
		else {
			for(int i=0; i<x.getLength(); i++) {
				if(messageOnPort(x, "Stop", i)) {
					holdIn("Passive", INFINITY);
				}
			}	
		}
		
	}

	@Override
	public message out() {
		// TODO Auto-generated method stub
		message m = new message();
		content con;
		if(dust == true) {
			con = makeContent("Out", new entity("dust"));	
		}
		else {
			con = makeContent("Out", new entity("no dust"));	
		}
			
		
		m.add(con);
		return m;
	}

	
}
