/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dpcasomgender;

 
import java.util.ArrayList;
import java.util.List;

public class TrainingSet 
{
    List<FaceImage> lstfaces;
    List<String> listnamafiles;
    List<String> lsttarget;

    public TrainingSet() 
    {
        lstfaces = new ArrayList<FaceImage>();  
        lsttarget = new ArrayList<String>();
        listnamafiles = new ArrayList<String>();
    }
    
    public void addFaceImage(FaceImage hw,String nmfile, String target)
    {
        lstfaces.add(hw);
        hw.SetName(target);
        lsttarget.add(target);
        listnamafiles.add(nmfile);
    }
    
    public FaceImage getFaceImage(int idx)
    {
        return lstfaces.get(idx);
    }
    
    public String getFileNames(int idx)
    {
        return listnamafiles.get(idx);
    }
    
    public int getTotalFaces()
    {
        return lstfaces.size();
    } 
}
