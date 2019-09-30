/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2dpcasomgender;
 
 
import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import javax.swing.ImageIcon;


public class PixelsReader 
{
    private FaceImage img;
    public PixelsReader() 
    {
        img = new FaceImage();
    }
    
  
    
    public void readPixelsFrom(ImageIcon imgic)
    {
        img = new FaceImage(imgic.getIconWidth(), imgic.getIconHeight()); 
        PixelGrabber pxlgrabber = new PixelGrabber(imgic.getImage(),0,0,img.getWidth(), img.getHeight(),false);
        pxlgrabber.startGrabbing();
        int pixels[];
        int pixelasli[][];
       
        try{
            if(pxlgrabber.grabPixels())
            {
                pixels = (int[])pxlgrabber.getPixels();
                       
                BufferedImage image = new BufferedImage(imgic.getIconWidth() , imgic.getIconHeight() , BufferedImage.TYPE_INT_RGB);
                image.setRGB(0, 0, imgic.getIconWidth() , imgic.getIconHeight() ,pixels, 0, imgic.getIconWidth());  
            
                ImageIcon imgIcon = new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_DEFAULT));          
                img = new FaceImage(imgIcon.getIconWidth(), imgIcon.getIconHeight());             
            
                pxlgrabber = new PixelGrabber(imgIcon.getImage(),0,0,img.getWidth(), img.getHeight(),false);      
                pxlgrabber.startGrabbing();           
            
                if(pxlgrabber.grabPixels())
                {
                    pixels = (int[])pxlgrabber.getPixels();            
                    pixelasli  = new int [img.getHeight()][img.getWidth()];
                    int wpx = 0;
                    int hpx = 0;
                    for(int i =0;i<pixels.length;i++)
                    {    
                        int pixel = pixels[i]; 
                        Color c = new Color(pixel);
                        int merah = c.getRed();
                        int hijau = c.getGreen();
                        int biru = c.getBlue();
                        int gray = (merah+hijau+biru)/3 ;           
                                  
                        pixelasli[hpx][wpx] = pixel; 
                        img.setPixelOutput(hpx, wpx, gray);
                        wpx++;
                    
                        if (wpx==img.getWidth())
                        {
                            wpx=0;
                            hpx++;
                        }
                    }          
                    img.setPixelAsli(pixelasli);
                }
            }
        }
        catch(InterruptedException ex){}
    }
    
    public FaceImage getFaceImage(){
        return img;
    }   
    
}
