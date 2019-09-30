/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pca2d;

 
public class MyVector {
    
    private int ukuran;
    private double [] vektor;

    public MyVector(int ukuran) 
    {
       this.ukuran = ukuran;
       vektor = new double[ukuran];
    }

    public MyVector(  double[] vektor) {
        this.ukuran = vektor.length;
        this.vektor = vektor;
    } 
        
    public double [] getVector()
    {
        double[] v = new double[vektor.length];
        for(int i=0;i<vektor.length;i++)
        {
            v[i] = vektor[i];
        }
        return v;
    }

    public double getNilai(int indeks){
        return this.vektor[indeks];
    }

    public void setNilai(int indeks, double nilai){
        this.vektor[indeks] = nilai;
    }

    private void setVektor(double [] vektor){
        this.vektor = vektor;
    }
   

    public int getUkuran(){
        return this.ukuran;
    }     
  
    public double getRataRata(){
        double jumlah = 0;
        for (int i = 0; i < ukuran; i++){
            jumlah = jumlah + this.vektor[i];
        }
        return jumlah/ukuran;
    }
    
    public void sesuaikan(){
        double ratarata = getRataRata();
        for (int i = 0; i < vektor.length; i++){
            vektor[i] = vektor[i] - ratarata;
        }
    }
    
    public void sesuaikanBalik(){
        double ratarata = getRataRata();
        for (int i = 0; i < vektor.length; i++){
            vektor[i] = vektor[i] + ratarata;
        }
    }
    
     public void printData(){
        for (int i = 0; i < ukuran; i++){
            System.out.println(vektor[i]);
        }
    }
     
}
