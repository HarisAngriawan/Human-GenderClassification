package som;

import java.io.Serializable;
import java.util.Vector;

 
public class SOMVector extends java.util.Vector implements Serializable {
    private String label;
    
    public SOMVector(String label) 
    {
        this.label = label;
    }
        
    public void setLabel(String label)
    {
        this.label = label;
    }
      
    public String getLabel()
    {
        return label;
    }
 
    public double euclideanDist(SOMVector v2) {
	if (v2.size() != size())
            return -999;
		
	double summation = 0, temp;
	for (int x=0; x<size(); x++) {
            temp = ((Double)elementAt(x)).doubleValue() - ((Double)v2.elementAt(x)).doubleValue();
            temp *= temp;
            summation += temp;
	}
		
	return summation;
	}
	
}
