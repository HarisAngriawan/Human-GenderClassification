
package som;

import java.io.Serializable;

public class SOMLattice implements Serializable{
	
    private int width, height;
    private SOMNode[][] matrix;
	
    public SOMLattice(int w, int h,int nfiturs) 
    {
	width = w;
	height = h;
	matrix = new SOMNode[width][height];
	float xstep = .5f / (float)width;
	float ystep = .5f / (float)height;
	for (int x=0; x<w; x++) {
            for (int y=0; y<h; y++) {
		matrix[x][y] = new SOMNode(nfiturs);
		matrix[x][y].setX(x);
		matrix[x][y].setY(y);
            }
	}
    }
	
    public SOMNode getNode(int x, int y) {
	return matrix[x][y];
    }
	
    public int getWidth() {
	return width;
    }
	
    public int getHeight() {
	return height;
    }

    public SOMNode getBMU(SOMVector inputVector) 
    {
	SOMNode bmu = matrix[0][0];
	double bestDist = inputVector.euclideanDist(bmu.getVector());
	double curDist;
	
	for (int x=0; x<width; x++) {
            for (int y=0; y<height; y++) {
		curDist = inputVector.euclideanDist(matrix[x][y].getVector());
                if (curDist < bestDist) 
                {	
                    bmu = matrix[x][y];
                    bestDist = curDist;
                }
            }
	}
	
	return bmu;
    }
	
}
