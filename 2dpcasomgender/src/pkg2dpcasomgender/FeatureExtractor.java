/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dpcasomgender;

import pca2d.PCA;

 
public class FeatureExtractor {
    PCA pca;

    public FeatureExtractor(){
    }
        
    public void extract(FaceImage m )
    {
       pca = new PCA(); 
       pca.runPCA(m);    
    }
    
    public double[] getFeature()
    {
        int kolom = 4;
        double[] feature = new double[pca.getResult().getJumlahBaris()*kolom];
        int i=0;
        for(int kol=0;kol<kolom;kol++) 
        {
            for(int row=0;row<pca.getResult().getJumlahBaris();row++)
            {
                feature[i] = pca.getResult().getNilai(row, kol);
                i++;
            }
        }
        return feature;
    }   
     
}
