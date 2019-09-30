/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pca2d;

 
import pkg2dpcasomgender.FaceImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class PCA
{
    private FaceImage imblock[];
    private MyVector mVector[],mVectorAdjusted[];
    private Matrix mxImgAsli,mxImgAdjusted,mxCov,mxEigenGambar; 
    private Matrix eigenvalue,resultPCA;
    private eigenJacobi nilaiEigen;
    private int colblockmax;
    private int rowblockmax;
    private int blocksize;
    

    public PCA(){   
    }
    
    private void CreateImageBlock(FaceImage im,int w)
    {
        int k = im.getHeight()/w;
        int l = im.getWidth()/w;
        imblock = new FaceImage[k*l];
        rowblockmax=k;
        colblockmax=l;
        blocksize = w;
        
        int blockheight=w;
        int blockwidth=w;

        int startHeight=0;
        int endHeight=0;

        int startWidth=0;
        int endWidth=0;

        int jblok=0;

        for(int i=0; i<k; i++)
        {
            startHeight=blockheight*i;
            endHeight=blockheight*(i+1);
            for(int j=0; j<l; j++)
            {
                startWidth=blockwidth*j;
                endWidth=blockwidth*(j+1);
                jblok++;
             
                FaceImage imb = new FaceImage(blockwidth,blockheight);
                for(int row=startHeight;row<endHeight;row++)
                {
                    for(int col=startWidth;col<endWidth;col++)
                    {
                       int pixel = im.getPixelOutput(row,col);
                       imb.setPixelOutput(row-startHeight,col-startWidth,pixel);
                    }
                }             
               
                imblock[jblok-1]=imb;
            }
        }
    }
    
     
    
    private  void turnBlocksToVector()
    {
        mVector = new MyVector[imblock.length];
        for(int i=0;i<imblock.length;i++)
        {
            mVector[i] = new MyVector(imblock[i].OneDimensionalPixel());                        
        }    
    }
    
    private void createMatrix()
    {
        mxImgAsli = new Matrix(blocksize*blocksize,imblock.length);
        mxImgAdjusted = new Matrix(blocksize*blocksize,imblock.length);
        mxEigenGambar = new Matrix(blocksize*blocksize,imblock.length);
        for(int i=0;i<imblock.length;i++)
        {
            mxImgAsli.setNilaiBaris(mVector[i].getVector(), i);
            mxImgAdjusted.setNilaiBaris(mVectorAdjusted[i].getVector(), i);
        }
    }
    
    private void countCoVariance()
    {
        mxCov = mxImgAdjusted.kaliMatriks(mxImgAdjusted.transpose());
        mxCov = mxCov.kali((double) 1 / (imblock.length));
    }  
    
    private void adjustData()
    {
        mVectorAdjusted = new MyVector[mVector.length];
        for(int i=0;i<mVector.length;i++)
        {
          double rata2 = mVector[i].getRataRata();
          mVectorAdjusted[i] = new MyVector(mVector[i].getVector());
          for(int j=0;j<mVectorAdjusted[i].getUkuran();j++){
          mVectorAdjusted[i].setNilai(j,mVectorAdjusted[i].getNilai(j) - rata2);      
            }          
        }
    }      
    
    public void runPCA(FaceImage im)
    {
      CreateImageBlock(im,(int)Math.sqrt(im.getHeight()));
      turnBlocksToVector();
      adjustData();
      createMatrix();
      countCoVariance();    
      
      nilaiEigen = new eigenJacobi(mxCov);
      eigenvalue = nilaiEigen.getMyVectorEigenMenaik();
      mxEigenGambar.copy(eigenvalue.transpose().kaliMatriks(mxImgAdjusted.transpose()));   
      resultPCA = mxEigenGambar.transpose();          
        
        /*
        System.out.println("================== PCA Result ====================");
        resultPCA.printData();
                  
        System.out.println(resultPCA.getJumlahBaris());
        System.out.println(resultPCA.getJumlahKolom());
        */
    }
    
    public Matrix getResult() {
        return resultPCA;
    }      
    
}
