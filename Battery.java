package Component.RobovacSimulation;

import java.util.Map;

public class Battery {

	private double batteryLevel = 100;
	private String batteryType;
	private double consumption = 0;
	private static Map<String, Double> dissipationMetrics;
	
	public Battery(String batteryType) {
		super();
		this.batteryType = batteryType;
		
		if(dissipationMetrics.isEmpty()) {
			initializeMetrics();
		}
		
	}
	
	public void initializeMetrics() {
		
		
		
	}
	
	public void consumeBattery() {
		
		
		
	}
//	public double batteryDissipation(battery) {
//	double bt1 = 100;
//	double bt2 = 100;
//	if(!b1.isEmpty()) {
//		bt1 = bt1 - (a*bt1);//a is the dissipation percent from a bot component
//		return bt1;
//	}
//	if(!b2.isEmpty()) {
//		bt2 = bt2 - (a*bt2);//a is the dissipation percent from a bot component
//		return bt2;	
//	}
//}
	
	
	// create toString() in the end
	
}
