/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dpcasomgender;
 
 
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import som.SOMLattice;
import som.SOMNode;
import som.SOMTrainer;
import som.SOMVector;


public class SomLearner implements  Runnable
{ 
    private int latticeWidth = 40;
    private int latticeHeight = 40;
	
    private SOMTrainer trainer;
    private SOMLattice lattice;
    private Vector<SOMVector> inputVectors;
    
    private int nfiturs;      
    private TrainingSet facetrainingset;
    private boolean finished;     

    public SomLearner(TrainingSet facetrainingset,int maxiterasi,double learingrate,int latWidth,int latHeight ) 
    {
        this.facetrainingset = facetrainingset;
        latticeWidth = latWidth;
        latticeHeight = latHeight;     
        trainer = new SOMTrainer(maxiterasi,learingrate);        
        inputVectors = new Vector<SOMVector>(); 
        finished = false;        
    }
        
    private void extract()
    {
        double[][] inputs = new double[facetrainingset.getTotalFaces() ][];
        for(int i=0;i<facetrainingset.getTotalFaces();i++)
        {
            FaceImage faceimg = facetrainingset.getFaceImage(i);
            FeatureExtractor fe = new FeatureExtractor();
            fe.extract(faceimg);  
            inputs[i] = fe.getFeature();  
        }
        
        nfiturs = inputs[0].length;
        SOMVector tempVec;
      
        for(int i=0;i<inputs.length;i++)
        {
            //System.out.print("Data -" + i);
            tempVec = new SOMVector( facetrainingset.getFaceImage(i).getName()); 
            for(int j=0;j<inputs[i].length;j++)
            {
                //System.out.print("\t" + inputs[i][j]);
                tempVec.addElement(new Double(inputs[i][j]));
            }
            inputVectors.addElement(tempVec);
            //System.out.println();
        }
    }
    
    public void printCluster()
    {
        trainer.printLattice();
    }
    
    public void testCluster()
    {
        for(int i=0;i<inputVectors.size();i++)
        {
            SOMNode bmu = lattice.getBMU(inputVectors.get(i));
            System.out.println("Gambar-" + String.valueOf(i+1) + ",Cluster:" + bmu.getCluster());
        }
    }
    
    public void start(){
        new Thread(this).start();
    }
    
    public void saveWeights()
    {      
        WeightEnroller wio = new WeightEnroller();       
        try {
            wio.saveWeight("bobot", "som",lattice);
        } catch (IOException ex) {
            Logger.getLogger(SomLearner.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Bobot Berhasil Disimpan!","Sukses",JOptionPane.INFORMATION_MESSAGE);      
       
    }
      
    public boolean isFinished()
    {
        return !trainer.isRunning();
    }
    
    public void train()
    {
       trainer.stop();
       extract();
      
       lattice = new SOMLattice(latticeWidth, latticeHeight,nfiturs);
       trainer.setTraining(lattice, inputVectors);       
       trainer.start();             
    }    

    @Override
    public void run() 
    {
       train();   
    }
    
}
