package Component.RobovacSimulation;

import view.modeling.ViewableAtomic;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.parser.Entity;

import GenCol.doubleEnt;
import GenCol.entity;


import model.modeling.content;
import model.modeling.message;

public class transducer extends ViewableAtomic {
	public transducer() {
		this("transducer");
	}
	
	public transducer(String  name) {
		super(name);
		addOutport("Battery1");
		addOutport("Battery2");
		addInport("Start");
		addInport("Stop");
		addInport("b1");
		addInport("b2");
		
		bat1 = new HashMap();
		bat2 = new HashMap();
		
		addTestInput("Start", new entity("start"));
		addTestInput("Stop", new entity("stop"));
		addTestInput("Battery1", new entity("val"));
		addTestInput("Battery2", new entity("val"));
		initialize();
		
		setBackgroundColor(Color.pink);
		
	}
	
	@Override
	public void initialize() {
		super.initialize();
		sigma = INFINITY;
		phase = "Passive";
	}
	
	@Override
	public void delint() {
		clock = clock + sigma;
		passivate();
		show_state();
	}
	
	@Override
	public void delext(double e, battery b) {
		Continue(e);
		Entity val;
		if(messageOnPort(b,"b1",1)) {
			val = b.getValOnPort("b1",1);
			bat1.put(val.getName(), new doubleEnt(clock));
		}
		if(messageOnPort(b,"b2",1)) {
			val = b.getValOnPort("b2",1);
			bat2.put(val.getName(), new doubleEnt(clock));
		}
	show_state();
	}
		
	
	public battery out(battery) {
		battery b = new battery();
		content con1 = makeContent("battery1", new entity(" "+batteryDissipation(battery1)));
		content con2 = makeContent("battery2",new entity(" "+batteryDissipation(battery2)));
		b.add(con1);
		b.add(con2);
		return b;
	}
	
	public double batteryDissipation(battery) {
		double bt1 = 100;
		double bt2 = 100;
		if(!b1.isEmpty()) {
			bt1 = bt1 - (a*bt1);//a is the dissipation percent from a bot component
			return bt1;
		}
		if(!b2.isEmpty()) {
			bt2 = bt2 - (a*bt2);//a is the dissipation percent from a bot component
			return bt2;	
		}
	}
}
