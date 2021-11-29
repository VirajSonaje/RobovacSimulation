package Component.RobovacSimulation;

import model.modeling.message;
import view.modeling.ViewableAtomic;
import view.modeling.ViewableDigraph;

public class GridGenerator extends ViewableAtomic{

	public GridGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GridGenerator(String name) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("In");
		addOutport("Out");
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
