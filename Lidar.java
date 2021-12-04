package Component.RobovacSimulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import GenCol.Bag;
import GenCol.Pair;
import GenCol.Queue;
import GenCol.doubleEnt;
import GenCol.entity;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class Lidar extends ViewableAtomic {
	
	double consumptionMetric;
	static final int ROW = 5, COL = 5;
	boolean visited[][] = new boolean[ROW][COL];
	int M[][];
	Queue<Pair<Integer, Integer>> path = new Queue<Pair<Integer, Integer>>();
	Bag<Pair<Integer,Pair<Integer, Integer>>> pathf = new Bag<Pair<Integer,Pair<Integer, Integer>>>();

	public Lidar() {
		this("LiDAR",20);
		// TODO Auto-generated constructor stub
	}

	public Lidar(String name, double consumption) {
		super(name);
		// TODO Auto-generated constructor stub
		
		addInport("Start");
		addInport("Stop");
		addOutport("Out");
		addOutport("Consumption");
		
		this.consumptionMetric = consumption;
		
		addTestInput("Start", new entity(""));
		
		generateGrid();
	}
	
	private void generateGrid() {
		// TODO Auto-generated method stub
		
		M = new int[][] {{1, 0, 0, 0, 0 },
						{ 1, 1, 1, 0, 0 },
						{ 1, 0, 1, 0, 0 },
						{ 1, 0, 0, 1, 0 },
						{ 1, 1, 1, 0, 0 } };
		
	}
	
	boolean isSafe(int row, int col)
	{
		// row number is in range, column number is in range
		// and value is 1 and not yet visited
		return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL) && (M[row][col] == 1 && !visited[row][col]);
	}

	// A utility function to do DFS for a 2D boolean matrix.
	// It only considers the 8 neighbors as adjacent vertices
	void DFS(int row, int col)
	{
		// These arrays are used to get row and column numbers
		// of 8 neighbors of a given cell
		int rowNbr[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 };
		int colNbr[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 };

		// Mark this cell as visited
		visited[row][col] = true;
		path.addFirst(new Pair<Integer, Integer>(row, col));
		// Recur for all connected neighbours
		for (int k = 0; k < 8; ++k)
			if (isSafe(row + rowNbr[k], col + colNbr[k]))
				DFS(row + rowNbr[k], col + colNbr[k]);
	}

	// The main function that returns count of islands in a given
	// boolean 2D matrix
	void getPath()
	{
		
		DFS(0,0);
		
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
		passivate();
	}

	@Override
	public void deltext(double e, message x) {
		// TODO Auto-generated method stub
		//super.deltext(e, x);
		if(phaseIs("Passive")) {
			for(int i=0;i<x.getLength();i++) {
				if(messageOnPort(x, "Start", i)) {
					holdIn("Active", 3);
					getPath();
					int l=0;
					Collections.reverse(path);
					for (Pair<Integer, Integer> pair : path) {
						pathf.add(new Pair<Integer, Pair<Integer,Integer>>(l, pair));
						l++;
						
					}
					System.out.println(path);
				}
			}
		}
		else {
			for(int i=0;i<x.getLength();i++) {
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
		content con = makeContent("Consumption", new doubleEnt(consumptionMetric*(5)));
		m.add(con);
		con = makeContent("Out", pathf);
		m.add(con);
		
		return m;
	}
	
	
	
}
