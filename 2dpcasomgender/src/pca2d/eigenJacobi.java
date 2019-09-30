/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pca2d;
 

public class eigenJacobi {
    Matrix matrix;
    Matrix[] matrixU;
    Matrix[] matrixA;
    private int eppoch = 40;
    private int sweep = 0;
    boolean konvergen = false;
    double error = 0.1;
    MyVector eigen, eigenMenaik;
    Matrix MyVectorEigen, MyVectorEigenMenaik;

    public eigenJacobi(Matrix matrix)
    {
        setMatrix(matrix);
        matrixU = new Matrix[1];
        matrixA = new Matrix[1];
  
        rotasi(matrix.getNilai());
        setNilaiEigen();        
        setNilaiMyVectorEigen();
        urutNilaiEigen();
    }

    private int faktorial(int n) {
        if (n <= 1)
        {
            return 1;
        } else {
            return n * faktorial(n - 1);
        }
    }

    public static double[][] minus(double A[][], double B[][], int n) {
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }
   
    public static void abs(double A[][], double B[][], int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = Math.abs(A[i][j]);
            }
        }
    }
    
    public static void maxMatrix(double A[][], int n, int Row[], double Max[]) {
        for (int i = 0; i < n; i++) {
            int k = 0;
            Max[i] = A[k][i];
            Row[i] = k;
            for (int j = 0; j < n; j++) {
                if (A[j][i] > Max[i]) {
                    Max[i] = A[j][i];
                    Row[i] = j;
                }
            }
        k = k + 1;
        }
    }

    public static void maxVector(double A[], int n, int Row[], double Max[]) {
        Max[0] = A[0];
        Row[0] = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] > Max[0]) {
                Max[0] = A[i];
                Row[0] = i;
            }
        }
    }
    
    public static void transpose(double A[][], double B[][], int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = A[j][i];
            }
        }
    }
     
    public static double[][] diag(double A[][], int n) {
        double[][] B = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = 0;
            }
        B[i][i] = A[i][i];
        }
    return B;
    }   
     
    public static double sumDiagElSq(double A[][], int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum = A[i][i] * A[i][i] + sum;
        }
    return sum;
    }
      
    private void printArray(String label,double A[][]){
        System.out.println(label); 
            
        for (int i=0;i<A.length;i++){
            for(int j=0;j<A[i].length;j++){
                System.out.print(A[i][j] + "\t");    
            }        
        System.out.println();
        }
    }
  
    private void rotasi( double[][] A){
        double t, c, s;
        int p, q, icount, state, size = A.length;
        double tol = 1.e-5; // level toleransi konvergen
        int icmax = 100; // jumlah iterasi maksimum

        int[] colRowOfElMax = new int[size], rowOfElMax = new int[1];
        double[][] temp = new double[size][size], D = new double[size][size];
        double[][] V, diagD;

        double[] maxElColRow = new double[size], maxElRow = new double[1];
        double[][] dMinusDiagD = new double[size][size], absDminusDiagD = new double[size][size];
        double[][] rot = new double[2][2], rotT = new double[2][2];

        // mengubah ke matrix identitas
        V = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                V[i][j] = 0;
            }
            V[i][i] = 1.0;
        }

        D = A; // menyalin A ke D
        diagD = diag(D, size);// keluaran DiagD=diagonal dari D
        dMinusDiagD = minus(D, diagD, size); //   D-DiagD
        abs(dMinusDiagD, absDminusDiagD, size);//  abs(D-DiagD)
        maxMatrix(absDminusDiagD, size, colRowOfElMax, maxElColRow);
        maxVector(maxElColRow, size, rowOfElMax, maxElRow);
        q = rowOfElMax[0];
        p = colRowOfElMax[q];
        icount = 0;
        state = 1;

        // Iterasi
        while (state == 1 && icount < icmax) {
            icount = icount + 1;
            if (D[q][q] == D[p][p]) { // memeriksa untuk menCegah t menjadi divergen
                D[q][q] = D[p][p] + 1.e-10;
            }
            t = D[p][q] / (D[q][q] - D[p][p]);
            c = 1 / Math.sqrt(t * t + 1);
            s = c * t;
            rot[0][0] = c;
            rot[0][1] = s;
            rot[1][0] = -s;
            rot[1][1] = c;
            transpose(rot, rotT, 2); 

            for (int i = 0; i < size; i++) {
                temp[p][i] = rotT[0][0] * D[p][i] + rotT[0][1] * D[q][i];
                temp[q][i] = rotT[1][0] * D[p][i] + rotT[1][1] * D[q][i];
                D[p][i] = temp[p][i];
                D[q][i] = temp[q][i];
            }
            
            for (int i = 0; i < size; i++) {
                temp[i][p] = D[i][p] * rot[0][0] + D[i][q] * rot[1][0];
                temp[i][q] = D[i][p] * rot[0][1] + D[i][q] * rot[1][1];
                D[i][p] = temp[i][p];
                D[i][q] = temp[i][q];
            }
      
            for (int i = 0; i < size; i++) {
                temp[i][p] = V[i][p] * rot[0][0] + V[i][q] * rot[1][0];
                temp[i][q] = V[i][p] * rot[0][1] + V[i][q] * rot[1][1];
                V[i][p] = temp[i][p];
                V[i][q] = temp[i][q];
            }

            //menemukan array q, p baru  yang perlu dirubah
            diagD = diag(D, size); // outputs diagD=diagonal of D
            dMinusDiagD = minus(D, diagD, size); // does D-DiagD
            abs(dMinusDiagD, absDminusDiagD, size); // does abs(D-DiagD)
            maxMatrix(absDminusDiagD, size, colRowOfElMax, maxElColRow);
            maxVector(maxElColRow, size, rowOfElMax, maxElRow);
            q = rowOfElMax[0];
            p = colRowOfElMax[q];
            if (Math.abs(D[p][q]) < tol * Math.sqrt(sumDiagElSq(diagD, size)) / size) {
                state = 0;
            }
        }
 
    matrixA[0] = new Matrix(diagD);
    matrixU[0] = new Matrix(V);
    }

    private double getNilaiRotasi(int baris, int kolom, Matrix MatrixX) {
        double nilaiRotasi = 0;
        nilaiRotasi = 0.5 * Math.toDegrees(Math.atan(2 * MatrixX.getNilai(baris, kolom) / (MatrixX.getNilai(baris, baris) - MatrixX.getNilai(kolom, kolom))));
        return nilaiRotasi;
    }

    private void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public Matrix getMatrix() {
        return this.matrix;
    } 

    private void setNilaiEigen() {
        eigen = new MyVector(matrixA[0].getDiagonal());      
    }

    public MyVector getNilaiEIgen() {
        return eigen;
    }

    private void setNilaiMyVectorEigen() {
        MyVectorEigen = new Matrix(matrixU[0].getJumlahBaris(), matrixU[0].getJumlahKolom());
        MyVectorEigen.copy(matrixU[0]);   
    }

    public Matrix getMyVectorEigen(){
        return MyVectorEigen;
    }

    public MyVector getEigenMenaik(){
        return eigenMenaik;
    }

    public Matrix getMyVectorEigenMenaik(){
        return MyVectorEigenMenaik;
    }

    private void urutNilaiEigen() {
        int iPos;
        int iMax;
       
        MyVector eigenTemp = eigen;
        Matrix MyVectorEigenTemp = MyVectorEigen;
       
        for (iPos = 0; iPos < matrix.getJumlahBaris(); iPos++) {
            iMax = iPos;
            for (int i = iPos + 1; i < matrix.getJumlahBaris(); i++) {
                if (eigenTemp.getNilai(i) > eigenTemp.getNilai(iMax)) {
                    iMax = i;
                }
            }          
            if (iMax != iPos) {
                tukar(iMax,iPos,eigenTemp,MyVectorEigenTemp);             
            }
        }

        eigenMenaik = eigenTemp;
        MyVectorEigenMenaik = MyVectorEigenTemp;
        }

    private void tukar(int i, int j , MyVector tukarEigen, Matrix tukarMyVectorEigen){
        double temp;
        temp = tukarEigen.getNilai(i);
        tukarEigen.setNilai(i,tukarEigen.getNilai(j));
        tukarEigen.setNilai(j,temp);

        double [] tempMyVector;
        tempMyVector = tukarMyVectorEigen.getNilaiBaris(i);
        tukarMyVectorEigen.setNilaiBaris(tukarMyVectorEigen.getNilaiBaris(j), i);
        tukarMyVectorEigen.setNilaiBaris(tempMyVector, j);
    }

    private void replaceNilaiEigen(double nilaiEigen, double [] MyVectorEigen, int pos ){
        this.eigenMenaik.setNilai(pos, nilaiEigen);
        this.MyVectorEigenMenaik.setNilaiBaris(MyVectorEigen, pos);
    }
    
}
