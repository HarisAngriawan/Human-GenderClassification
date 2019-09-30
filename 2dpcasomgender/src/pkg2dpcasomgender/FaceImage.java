/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dpcasomgender;

import java.awt.Color;


public class FaceImage
{
    private int widthOri;
    private int heightOri;
    private int pixelasli[][];
    private int pixeloutput[][];
    private double realpixelasli[][];
    private double imgpixelasli[][];
    private String name;
  
    public FaceImage(){
    }

    public FaceImage(int widthOri, int heightOri) 
    {
        this.widthOri = widthOri;
        this.heightOri = heightOri;
        pixelasli = new int[heightOri][widthOri];
        pixeloutput = new int[heightOri][widthOri];
    }
    
    public void SetName(String name)
    {
        this.name = name;
    }
  
    public String getName()
    {
        return name;
    }
  

    public void setPixelAsli(int[][] pixelasli) 
    {
        this.pixelasli = pixelasli;
    }

    public int[][] getPixelAsli() 
    {
        return pixelasli;
    }

    public int getHeight() 
    {
        return heightOri;
    }

    public int getWidth()
    {
        return widthOri;
    }
    
    public void setPixelOutput(int h,int w,int output)
    {
        pixeloutput[h][w]=output;
    }
    
    public int getPixelOutput(int h,int w)
    {
        return  pixeloutput[h][w];
    }

    public void setPixel(int h,int w,int pixel) {
        this.pixelasli[h][w] = pixel;
    }

    public int getPixel(int h,int w) 
    {
        return pixelasli[h][w];
    }
    
    void setPixelReal(int h,int w,double realpixel)
    {
        realpixelasli[h][w] = realpixel;
    }
    
    double getPixelReal(int h,int w){
        return realpixelasli[h][w];
    }
    
    public int[] OneDimensionalPixel2(){
        int[] onedpixel = new int[pixelasli.length*pixelasli[0].length];
        int i=0;
        for(int baris=0;baris<pixelasli.length;baris++){
            for(int kolom=0;kolom<pixelasli[0].length;kolom++){
                int gray = pixelasli[baris][kolom];                  
                onedpixel[i] =gray;
                i++;
            }
        }
        return onedpixel;
    }
      
    public int[] toOneDimensionalPixelOutput()
    {
        int[] onedpixel = new int[pixeloutput.length*pixeloutput[0].length];
        int i=0;
        for(int baris=0;baris<pixeloutput.length;baris++){
            for(int kolom=0;kolom<pixeloutput[0].length;kolom++){
                int gray = pixeloutput[baris][kolom];  
                Color c = new Color(gray, gray, gray,0 );
                onedpixel[i] =c.getRGB();
                i++;
            }
        }
        return onedpixel;
    }
  
    public double[] OneDimensionalPixel()
    {
        double[] onedpixel = new double[pixelasli.length*pixelasli[0].length];
        int i=0;
        for(int baris=0;baris<pixeloutput.length;baris++)
        {
            for(int kolom=0;kolom<pixeloutput[0].length;kolom++)
            {
                double gray = pixeloutput[baris][kolom];                  
                onedpixel[i] =gray;
                i++;
            }
       }
       return onedpixel;
   }
     
 }
