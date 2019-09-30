package ui;

 
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import pkg2dpcasomgender.GenderIdentifier;
import pkg2dpcasomgender.FaceImage;
import pkg2dpcasomgender.FaceImageViewer;
import pkg2dpcasomgender.FeatureExtractor;
import pkg2dpcasomgender.PixelsReader;
import pkg2dpcasomgender.TrainingSet;
import utils.ExtensionFileFilter;
import utils.Util;

 
public class FormTesting extends javax.swing.JFrame {
 JFileChooser flchooser = new JFileChooser();
 FaceImage fitest;
 TrainingSet sampleuji;

    public FormTesting() {
        initComponents();
        Util.TengahWindow(this);
        setResizable(false);
        sampleuji = new TrainingSet();
        showFilesImages();
    }

     private void showFilesImages()
    {
        String[] header = new String[]{"No","Image FileName","Hasil"};
        String[][] data = new String[sampleuji.getTotalFaces()][header.length];
        for(int i=0;i<sampleuji.getTotalFaces();i++)
        {     
            String ffname = sampleuji.getFaceImage(i).getName();
            
            data[i][0] = String.valueOf(i+1);
            data[i][1] = sampleuji.getFileNames(i);
            data[i][2] = ffname;
        }
        jTblTesting.setModel(new DefaultTableModel(data, header));
}
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jbtnBrowse = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jlblUji = new javax.swing.JLabel();
        jBtnRecognise = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jlblHasil = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblTesting = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(0, 0));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 24)); // NOI18N
        jLabel1.setText("TESTING");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jbtnBrowse.setBackground(new java.awt.Color(0, 153, 153));
        jbtnBrowse.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jbtnBrowse.setText("Load Images");
        jbtnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBrowseActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnBrowse, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 60, 257, 40));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblUji, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlblUji, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, -1, 300));

        jBtnRecognise.setBackground(new java.awt.Color(0, 153, 153));
        jBtnRecognise.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jBtnRecognise.setText("Classification");
        jBtnRecognise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnRecogniseMouseClicked(evt);
            }
        });
        jBtnRecognise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRecogniseActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnRecognise, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 257, 43));

        jLabel2.setFont(new java.awt.Font("Bodoni MT Black", 1, 14)); // NOI18N
        jLabel2.setText("Gender :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

        jlblHasil.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jlblHasil.setText("---------------------------------------");
        getContentPane().add(jlblHasil, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 260, 25));

        jTblTesting.setFont(new java.awt.Font("Sylfaen", 0, 12)); // NOI18N
        jTblTesting.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTblTesting.setGridColor(new java.awt.Color(0, 102, 102));
        jTblTesting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblTestingMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblTesting);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 58, 430, 440));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int ShowDialogOpenImage()
    {
        FileFilter filter1 = new ExtensionFileFilter("Bitmap and JPEG Files", new String[] { "BMP","JPG"});
        flchooser.setFileFilter(filter1);
        flchooser.setMultiSelectionEnabled(true);
        int tanggapan = flchooser.showOpenDialog(this);   
        return tanggapan;
    }
     
    public void showWarningMessage(String pesan)
    {
        JOptionPane.showMessageDialog(this, pesan,"Peringatan",JOptionPane.WARNING_MESSAGE);
    }   
       
    public void showInfoMessage(String pesan)
    {
        JOptionPane.showMessageDialog(this, pesan,"Informasi",JOptionPane.INFORMATION_MESSAGE);
    }
    
    
private void jBtnRecogniseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRecogniseActionPerformed
    if (sampleuji.getTotalFaces() >0) 
    {
      GenderIdentifier er = new GenderIdentifier(sampleuji);
      er.classification();
      showFilesImages();
    }else 
    {
      showWarningMessage("Tidak Ada yang dapat diproses!");
    }
}//GEN-LAST:event_jBtnRecogniseActionPerformed

private void jbtnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBrowseActionPerformed
    int tanggapan = ShowDialogOpenImage(); 
    if(tanggapan==JFileChooser.APPROVE_OPTION)
    {
        BufferedImage img=null;
        try{             
            for(int i=0;i<flchooser.getSelectedFiles().length;i++) 
            {
                img = ImageIO.read(flchooser.getSelectedFiles()[i].getAbsoluteFile());                
                ImageIcon imgicon = new ImageIcon(img); 
                PixelsReader pxlsLoad = new PixelsReader();
                pxlsLoad.readPixelsFrom(imgicon);
                FaceImage faceimg = pxlsLoad.getFaceImage();
                sampleuji.addFaceImage(faceimg, flchooser.getSelectedFiles()[i].getAbsolutePath(), "-Belum Diketahui-");
            }
            
            showFilesImages();         
        }catch(IOException ex)
        {
            
        }  
    }
}//GEN-LAST:event_jbtnBrowseActionPerformed

private void jTblTestingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblTestingMouseClicked
    int baris = jTblTesting.rowAtPoint(evt.getPoint());
    FaceImage faceimage = sampleuji.getFaceImage(baris);
    
    FeatureExtractor fe =new FeatureExtractor();
    fe.extract(faceimage);
   
    FaceImageViewer fiv = new FaceImageViewer();
    fiv.setImage(faceimage);
    jlblHasil.setText(faceimage.getName());
    fiv.setViewer(jlblUji);
    fiv.viewImageDefault();    
}//GEN-LAST:event_jTblTestingMouseClicked

private void jBtnRecogniseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnRecogniseMouseClicked

}//GEN-LAST:event_jBtnRecogniseMouseClicked


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FormTesting().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnRecognise;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblTesting;
    private javax.swing.JButton jbtnBrowse;
    private javax.swing.JLabel jlblHasil;
    private javax.swing.JLabel jlblUji;
    // End of variables declaration//GEN-END:variables
}
