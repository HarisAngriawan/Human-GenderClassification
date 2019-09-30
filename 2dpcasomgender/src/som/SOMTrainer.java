package som;

import java.util.Vector;
import javax.swing.JOptionPane;


public class SOMTrainer implements Runnable 
{
    private double START_LEARNING_RATE = 0;
    private int NUM_ITERATIONS = 0;
    private double LATTICE_RADIUS;
    private double TIME_CONSTANT;
 
    private SOMLattice lattice;
    private Vector<SOMVector> inputs;
    private static boolean running;
    private Thread runner;
	
    
    public SOMTrainer(int maxiteration,double learningrate){
	running = false;
        NUM_ITERATIONS = maxiteration;
        START_LEARNING_RATE = learningrate;
    }
	
    private double getNeighborhoodRadius(double iteration){
	return LATTICE_RADIUS * Math.exp(-iteration/TIME_CONSTANT);
    }
	
    private double getDistanceFalloff(double distSq, double radius){
	double radiusSq = radius * radius;
	return Math.exp(-(distSq)/(2 * radiusSq));
    }
			
    public void setTraining(SOMLattice latToTrain, Vector<SOMVector> in){
	lattice = latToTrain;
	inputs = in;
    }
	
    public void start(){
	if (lattice != null){
            runner = new Thread(this);
            runner.setPriority(Thread.MIN_PRIORITY);
            running = true;
            runner.start();
	}
    }
              
    public void printLattice()
    {            
        int width = lattice.getWidth();
        int height = lattice.getHeight();
	  
        System.out.println("================================="
                + "========= SOM MAP ======================================");   
	for (int x=0; x<width; x++){                    
            for (int y=0; y<height; y++){
		SOMNode sn = lattice.getNode(x, y);
                System.out.print(sn.getCluster() + "\t");   
            }
            System.out.println("");   
	}
        System.out.println
        ("==============================================="
                + "========================================");     
    }
        
    @Override
    public void run() {
        int lw = lattice.getWidth();
	int lh = lattice.getHeight();
	int xstart, ystart, xend, yend;
	double dist, dFalloff;
	
	LATTICE_RADIUS = Math.max(lw, lh)/2;
	TIME_CONSTANT = NUM_ITERATIONS / Math.log(LATTICE_RADIUS);
		
	int iteration = 0;
	double nbhRadius;
	SOMNode bmu = null, temp = null;
	SOMVector curInput = null;
	double learningRate = START_LEARNING_RATE;
		
	while (iteration < NUM_ITERATIONS && running) 
        {
            nbhRadius = getNeighborhoodRadius(iteration);
            for (int i=0; i<inputs.size(); i++) {
		curInput =  inputs.elementAt(i);
		bmu = lattice.getBMU(curInput);
                bmu.SetCluster(curInput.getLabel()); 
		xstart = (int)(bmu.getX() - nbhRadius - 1);
		ystart = (int)(bmu.getY() - nbhRadius - 1);
		xend = (int)(xstart + (nbhRadius * 2) + 1);
		yend = (int)(ystart + (nbhRadius * 2) + 1);
		
                if (xend > lw) xend = lw;
		if (xstart < 0) xstart = 0;
		if (yend > lh) yend = lh;
		if (ystart < 0) ystart = 0;
				
		for (int x=xstart; x<xend; x++) {
                    for (int y=ystart; y<yend; y++) {
			temp = lattice.getNode(x,y);
			dist = bmu.distanceTo(temp);
			if (dist <= (nbhRadius * nbhRadius)) {
                            dFalloff = getDistanceFalloff(dist, nbhRadius);
                           temp.adjustWeights(curInput, learningRate, dFalloff);
			}
                    }
		}
            }
	    iteration++;
            learningRate = START_LEARNING_RATE * Math.exp(-
                    (double)iteration/NUM_ITERATIONS);		 
	}
	
        running = false;
        JOptionPane.showMessageDialog(null, "Train Finished!");
    }
        
    public boolean isRunning() {
        return running;
    }

    public void stop() {
	if (runner != null) {
            running = false;
            while (runner.isAlive()) {};
	}
    }
    
}
