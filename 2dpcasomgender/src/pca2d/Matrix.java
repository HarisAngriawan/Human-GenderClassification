/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pca2d;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

 
public class Matrix {
    private int kolom;
    private int baris;
    private double nilai[][];

    public Matrix(int baris,int kolom) 
    {
        this.baris = baris;
        this.kolom = kolom;
        nilai = new double[baris][kolom];
    }
    
    public Matrix(Matrix M)
    {
        nilai = new double[M.getJumlahBaris()][M.getJumlahKolom()];
        this.baris = M.getJumlahBaris();
        this.kolom  = M.getJumlahKolom();
        copy(M);
    }
    
    public Matrix(double[][] nilai) 
    {
        this.baris = nilai.length;
        this.kolom = nilai[0].length;
        this.nilai = new double[baris][kolom];

        for (int x = 0; x < this.baris; x++) {
            for (int y = 0; y < this.kolom; y++) 
            {
                this.nilai[x][y] = nilai[x][y];
            }
        } 
    }
    
    public Matrix(int[] nilai, int baris, int kolom) 
    {
        this.baris =baris;
        this.kolom = kolom;
        this.nilai = new double[baris][kolom];
        int i=0;
        for (int x = 0; x < this.baris; x++) {
            for (int y = 0; y < this.kolom; y++) 
            {
                this.nilai[x][y] = nilai[i];
                i++;
            }
        } 
    }
     
    public Matrix(double[] nilai, int baris, int kolom) 
    {
        this.baris =baris;
        this.kolom = kolom;
        this.nilai = new double[baris][kolom];
        int i=0;
        
        for (int x = 0; x < this.baris; x++) {
            for (int y = 0; y < this.kolom; y++){
                this.nilai[x][y] = nilai[i];
                i++;
            }
        } 
    }
    
    public Matrix(String tipe, int baris,int kolom) {
        if (tipe.equals("identitas")) {
            this.baris = baris;            
            this.kolom = kolom;
            nilai = new double[baris][kolom];

            for (int i = 0; i < baris; i++) {
                nilai[i][i] = 1;
            }
        }
    }
    
    public int getJumlahBaris() {
        return baris;
    }

    public void setJumlahBaris(int baris) {
        this.baris = baris;
    }

    public int getJumlahKolom() {
        return kolom;
    }

    public void setJumlahKolom(int kolom) {
        this.kolom = kolom;
    }

    public double[][] getNilai() {
        return this.nilai;
    }

    public double getNilai(int baris, int kolom) {
        return this.nilai[baris][kolom];
    } 
 
    public void setNilai(double nilai, int baris, int kolom) {
        this.nilai[baris][kolom] = nilai;
    }

    public void setNilaiBaris(double[] nilai, int kolom) {
        for (int i = 0; i < this.baris; i++) {
            this.nilai[i][kolom] = nilai[i];
        }
    }
    
    public void setNilaiKolom(double[] nilai, int baris) {
        for (int i = 0; i < this.kolom; i++) {
            this.nilai[baris][i] = nilai[i];
        }
    }

    public double[] getNilaiBaris(int baris) {
        double[] dataBaris = new double[this.baris];
            for (int i = 0; i < this.baris; i++) {
                dataBaris[i] = nilai[i][baris];
            }
        return dataBaris;
    }

    public double[] getNilaiKolom(int kolom) {
        double[] dataBaris = new double[this.kolom];
        for (int i = 0; i < this.kolom; i++) {
            dataBaris[i] = nilai[kolom][i];
        }
        return dataBaris;
    }   

    public double [] getDiagonal(){
        if(baris == kolom){
            double [] nilaiDiagonal = new double[baris];
            for (int i = 0; i < baris; i++){
                nilaiDiagonal[i] = nilai[i][i];
            }
            return nilaiDiagonal;
        }
        else{
            return null;
        }
    }    

    public Matrix transpose() {
        Matrix transpose = new Matrix(kolom, baris);
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                transpose.nilai[j][i] = this.nilai[i][j];
            }
        }
        return transpose;
    }

    public Matrix kaliMatriks(Matrix B) {
        if (B.baris != kolom) {
            throw new IllegalArgumentException("Tidak Memenuhi Persyaratan Perkalian");
        }
        Matrix X = new Matrix(baris, B.kolom);
        double[][] C = X.getNilai();
        double[] Bcolj = new double[kolom];

        for (int j = 0; j < B.kolom; j++) {
            for (int k = 0; k < kolom; k++) {
                Bcolj[k] = B.nilai[k][j];
            }
            for (int i = 0; i < baris; i++) {
                double[] Arowi = nilai[i];
                double s = 0;
                for (int k = 0; k < kolom; k++) {
                    s += Arowi[k] * Bcolj[k];
                }
                C[i][j] = s;
            }
        }
        return X;
    }

    public Matrix kali(double nilaiPerkalian) {
        Matrix hasil = new Matrix(baris, kolom);
        for (int i = 0; i < hasil.baris; i++) {
            for (int j = 0; j < hasil.kolom; j++) {
                hasil.setNilai(nilaiPerkalian * nilai[i][j], i, j);
            }
        }
        return hasil;
    }

    public void copy(Matrix asal) {
        if (nilai[0].length == asal.kolom && nilai.length == asal.getJumlahBaris()) {
            for (int i = 0; i < nilai.length; i++) {
                for (int j = 0; j < nilai[0].length; j++) {
                    nilai[i][j] = asal.getNilai(i, j);
                }
            }
        }
    }   

    public Matrix PotongKolom(int mulai, int akhir)
    {
        Matrix hasil = new Matrix(baris,akhir-mulai);
        System.out.println("Baris : " + baris);
        for (int i = 0; i < baris; i++)
        {
            for (int j = mulai; j < akhir; j++)
            {
                hasil.setNilai(nilai[i][j], i, j-mulai);
            }
        }
        return hasil;
    }

    public Matrix PotongBaris(int mulai, int akhir){
        Matrix hasil = new Matrix(akhir - mulai, kolom);
        int barisPindah = 0;
        for (int i = mulai; i < akhir; i++){
            for (int j = 0; j < kolom; j++){
                hasil.setNilai(nilai[i][j], barisPindah, j);
            }
            barisPindah++;
        }
        return hasil;
    }   
        
    public double[] getData()
    {
        double[] temppixel = new double[kolom*baris];           
        int i=0;
        for(int h=0;h<baris;h++){
            for(int w=0;w<kolom;w++){
               double pix =  nilai[h][w];              
               temppixel[i] = pix;
               i++;
            }
        }
        return temppixel;
    }       
    
      public void printData() {
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                 System.out.print(this.nilai[i][j] + "\t");
            }
            System.out.println();
        }
    }
   
}
