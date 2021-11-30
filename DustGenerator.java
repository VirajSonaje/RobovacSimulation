package Component.RobovacSimulation;

import java.util.Random;

import GenCol.doubleEnt;
import GenCol.entity;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class DustGenerator extends ViewableAtomic {
	
	int arrTime; 
	boolean dust = true;
	Random rd = new Random();
	double consumptionMetric;
	
	public DustGenerator() {
		this("DustGenerator",10);
		// TODO Auto-generated constructor stub
	}

	public DustGenerator(String name, double consumption) {
		super(name);
		// TODO Auto-generated constructor stub
		arrTime = 3;
		addOutport("Out");
		addInport("Start");
		addOutport("Consumption");
		
		addTestInput("Start", new entity("start"));
		
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
		//super.deltint();
//		if(phaseIs("Active")) {
//			holdIn("Active", arrTime);
//			dust = rd.nextBoolean();
//		}
		holdIn("Passive", INFINITY);
	}

	@Override
	public void deltext(double e, message x) {
		// TODO Auto-generated method stub
		//super.deltext(e, x);
		
		if(phaseIs("Passive")) {
			for(int i=0; i<x.getLength(); i++) {
				if(messageOnPort(x, "Start", i)) {
					holdIn("Sensing", arrTime);
					dust = rd.nextBoolean();
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
		con = makeContent("Consumption", new doubleEnt(consumptionMetric*(3)));
		m.add(con);	
		
		
		return m;
	}

	
}
