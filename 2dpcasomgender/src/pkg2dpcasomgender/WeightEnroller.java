/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dpcasomgender;
 
 
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import som.SOMLattice;

public class WeightEnroller
{
    public void WeightIOController(){      
    }
    
    private static void createDir(String dir)
    {
        File filePath = new File(dir);
        filePath.mkdirs();
    }
    
    private static void deleteDir(String dir){
        File filePath = new File(dir);
        System.out.println(filePath.delete());
    }
    
    public void saveWeight(String dir,String fileName, SOMLattice somlattice) throws IOException
    {
        createDir(dir);
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(dir + "/" + fileName + ".haris"));
            objectOutputStream.writeObject(somlattice);
        } catch (IOException e) {
            System.out.println("Could not write to file: " + fileName+"\n"+e);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Could not write to file: " + fileName);
            }
        } 
    }
       
    public double[][] readWeights(String dir, String fileName) throws FileNotFoundException, IOException
    {
        DataInputStream dist1 = new DataInputStream(new BufferedInputStream(new FileInputStream(dir + "/" + fileName + ".txt")));
        int maxw = Integer.parseInt(dist1.readLine());
        double[][] w  = new double[maxw][];
        for(int i=0;i<w.length;i++)
        {
            String data = dist1.readLine();
            String arrdata[] = data.split(";");
            w[i] = new double[arrdata.length];
            for(int j=0;j<arrdata.length;j++){
                w[i][j]= Double.parseDouble(arrdata[j]);
            }
        }
        return w;
    }
    
}
