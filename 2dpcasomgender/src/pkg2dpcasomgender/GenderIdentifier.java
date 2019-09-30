/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dpcasomgender;

 
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Vector;
import som.SOMLattice;
import som.SOMNode;
import som.SOMVector;

 
public class GenderIdentifier 
{  
    TrainingSet tsampleuji;
    private Vector<SOMVector> testVectors;
    private SOMLattice lattice;  
        
    public GenderIdentifier(TrainingSet tsampleuji) 
    {
        this.tsampleuji = tsampleuji;
        testVectors = new Vector<SOMVector>();
    }  
    
    private void extract()
    {
        double[][] inputs = new double[tsampleuji.getTotalFaces() ][];
        for(int i=0;i<tsampleuji.getTotalFaces();i++)
        {
            FaceImage faceimg = tsampleuji.getFaceImage(i);
            FeatureExtractor fe = new FeatureExtractor();
            fe.extract(faceimg);  
            inputs[i] = fe.getFeature();
        }
        
        SOMVector tempVec;
       
        for(int i=0;i<inputs.length;i++)
        {
            //System.out.print("Data -" + i);
            tempVec = new SOMVector( tsampleuji.getFaceImage(i).getName()); 
            for(int j=0;j<inputs[i].length;j++)
            {
              //System.out.print("\t" + inputs[i][j]);
              tempVec.addElement(new Double(inputs[i][j]));
            }
            testVectors.addElement(tempVec);
            System.out.println();      
        }   
    }
    
    private SOMLattice readTrainedLattice(File fsave) 
    {
  
        SOMLattice somlattice = null;
        File f = fsave;
       
        if (f.exists())
        {
            System.out.println("Trying to read the existing Weights");           
            try{
                InputStream file = new FileInputStream(f.getAbsolutePath());
                InputStream buffer = new BufferedInputStream(file);               
                ObjectInputStream oi = new ObjectInputStream(buffer);
                somlattice = (SOMLattice)oi.readObject();
                
            }
            catch (ClassNotFoundException ex)
            {
                System.out.println("Something went wrong --- ClassNotFoundError: \n" + ex.getMessage());
            }
            catch(IOException ex)
            {
                System.out.println("Something went wrong --- IOException: \n" + ex.getMessage());
            }
        }
        return  somlattice;
    }
    
    public void classification()
    { 
        extract();
        SOMLattice lattice = readTrainedLattice( new File("bobot/som.haris"));
        for(int i=0;i<testVectors.size();i++)
        {
            SOMNode bmu = lattice.getBMU(testVectors.get(i));
            tsampleuji.getFaceImage(i).SetName(bmu.getCluster()); 
            //System.out.println("Gambar-" + String.valueOf(i+1) + ",Cluster:" + bmu.getCluster());
        }    
    }
    
}