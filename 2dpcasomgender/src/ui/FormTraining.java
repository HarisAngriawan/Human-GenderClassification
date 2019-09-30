package ui;

 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import pkg2dpcasomgender.FaceImage;
import pkg2dpcasomgender.FaceImageViewer;
import pkg2dpcasomgender.FeatureExtractor;
import pkg2dpcasomgender.PixelsReader;
import pkg2dpcasomgender.SomLearner;
import pkg2dpcasomgender.TrainingSet;
import utils.ExtensionFileFilter;
import utils.Util;

public class FormTraining extends javax.swing.JFrame
{

    JFileChooser flchooser = new JFileChooser();
    TrainingSet tsfaces;
    SomLearner somlearner ;
    
    public FormTraining() 
    {
        initComponents();
        Util.TengahWindow(this);
        setResizable(false);
        setTitle("Training");
        
        tsfaces = new TrainingSet();
        showFilesImages();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxtIterasiMaksimum = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTxtLearningRate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTxtLatWidth = new javax.swing.JTextField();
        jTxtLatHeight = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblTrainData = new javax.swing.JTable();
        jBtnLoadTrainingSet = new javax.swing.JButton();
        jBtnSmpanBobot = new javax.swing.JButton();
        jBtnTrain = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Face Image"));
        jPanel2.setPreferredSize(new java.awt.Dimension(143, 218));

        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Train Parameter"));
        jPanel3.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N

        jLabel1.setText("Iterasi Maksimum :");

        jtxtIterasiMaksimum.setText("10000");
        jtxtIterasiMaksimum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtIterasiMaksimumActionPerformed(evt);
            }
        });

        jLabel3.setText("Learning Rate :");

        jTxtLearningRate.setText("0.6");

        jLabel4.setText("Map Label (pxl) :");

        jTxtLatWidth.setText("40");

        jTxtLatHeight.setText("40");

        jLabel2.setText("x");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTxtLatWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTxtLatHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(80, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtxtIterasiMaksimum, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtLearningRate, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(21, 21, 21))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtIterasiMaksimum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTxtLearningRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtLatWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtLatHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        jTblTrainData.setModel(new javax.swing.table.DefaultTableModel(
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
        jTblTrainData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblTrainDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblTrainData);

        jBtnLoadTrainingSet.setBackground(new java.awt.Color(0, 153, 153));
        jBtnLoadTrainingSet.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jBtnLoadTrainingSet.setText("Load Images");
        jBtnLoadTrainingSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLoadTrainingSetActionPerformed(evt);
            }
        });

        jBtnSmpanBobot.setBackground(new java.awt.Color(0, 153, 153));
        jBtnSmpanBobot.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jBtnSmpanBobot.setText("Simpan Bobot");
        jBtnSmpanBobot.setEnabled(false);
        jBtnSmpanBobot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSmpanBobotActionPerformed(evt);
            }
        });

        jBtnTrain.setBackground(new java.awt.Color(0, 153, 153));
        jBtnTrain.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 12)); // NOI18N
        jBtnTrain.setText("Training");
        jBtnTrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnTrainActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Cooper Black", 0, 24)); // NOI18N
        jLabel6.setText("TRAINING");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(374, 374, 374))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnTrain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                            .addComponent(jBtnLoadTrainingSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBtnSmpanBobot, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 31, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnSmpanBobot, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBtnLoadTrainingSet)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnTrain, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3.getAccessibleContext().setAccessibleName("Training Parameter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnTrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnTrainActionPerformed
        // TODO add your handling code here:
        int imax = Integer.parseInt(jtxtIterasiMaksimum.getText());
        double learnrate = Double.parseDouble(jTxtLearningRate.getText());
        int latWidth = Integer.parseInt(jTxtLatWidth.getText());
        int latHeight = Integer.parseInt(jTxtLatHeight.getText());
        if (tsfaces.getTotalFaces()>1) 
        {           
                       
          somlearner = new SomLearner(tsfaces,imax,learnrate,latWidth,latHeight);
          somlearner.start();
          jBtnSmpanBobot.setEnabled(true);
          
        }else  {
            showMessage("Data Anda Belum Cukup");
        }
    
    }//GEN-LAST:event_jBtnTrainActionPerformed

    public void showMessage(String pesan)
    {
        JOptionPane.showMessageDialog(this, pesan,"Peringatan",JOptionPane.WARNING_MESSAGE);
    }
    
    private void showFilesImages()
    {
        String[] header = new String[]{"No","Image FileName","Label"};
        String[][] data = new String[tsfaces.getTotalFaces()][header.length];
        for(int i=0;i<tsfaces.getTotalFaces();i++)
        {     
            String ffname = tsfaces.getFaceImage(i).getName();
           
            data[i][0] = String.valueOf(i+1);
            data[i][1] = tsfaces.getFileNames(i);
            data[i][2] = ffname;
        
        }
        jTblTrainData.setModel(new DefaultTableModel(data, header));
}
    
   private int ShowDialogOpenImage()
   {    
    FileFilter filter1 = new ExtensionFileFilter("Bitmap and JPEG Files", new String[] { "BMP","JPG"});
    flchooser.setFileFilter(filter1);
    flchooser.setMultiSelectionEnabled(true);
    int tanggapan = flchooser.showOpenDialog(this);   
    return tanggapan;
   }
      
private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
// TODO add your handling code here:
  
}//GEN-LAST:event_jLabel5MouseClicked

private void jTblTrainDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblTrainDataMouseClicked
// TODO add your handling code here:
    int baris = jTblTrainData.rowAtPoint(evt.getPoint());
    FaceImage faceimage = tsfaces.getFaceImage(baris);
    
    FeatureExtractor fe =new FeatureExtractor();
    fe.extract(faceimage);
   
    FaceImageViewer fiv = new FaceImageViewer();
    fiv.setImage(faceimage);
    fiv.setViewer(jLabel5);
    fiv.viewImageDefault();
}//GEN-LAST:event_jTblTrainDataMouseClicked

private void jBtnLoadTrainingSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLoadTrainingSetActionPerformed
// TODO add your handling code here:
    
    int tanggapan = ShowDialogOpenImage(); 
    if(tanggapan==JFileChooser.APPROVE_OPTION)
    {
        BufferedImage img=null;
        try{
            String name = JOptionPane.showInputDialog("Masukkan Nama Label:");
            if (name==null) 
            {
                showMessage("File yang terpilih dibatalkan.");
            }
            else 
            {
                for(int i=0;i<flchooser.getSelectedFiles().length;i++) 
                {
                    img = ImageIO.read(flchooser.getSelectedFiles()[i].getAbsoluteFile());                
                    ImageIcon imgicon = new ImageIcon(img);                   
                    PixelsReader pxlsLoad = new PixelsReader();
                    pxlsLoad.readPixelsFrom(imgicon);
                    FaceImage faceimg = pxlsLoad.getFaceImage();
                    
                    //System.out.println(name);
                    tsfaces.addFaceImage(faceimg, flchooser.getSelectedFiles()[i].getAbsolutePath(), name);
                }
                showFilesImages();
            }           
        }
        catch(IOException ex){
        }  
    }
}//GEN-LAST:event_jBtnLoadTrainingSetActionPerformed

private void jBtnSmpanBobotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSmpanBobotActionPerformed
// TODO add your handling code here:
   
    if (somlearner.isFinished()) 
    {
        //somlearner.printCluster();
        //somlearner.testCluster();
        somlearner.saveWeights();
    }else {
        showMessage("Training Belum Selesai.");
    }
    
}//GEN-LAST:event_jBtnSmpanBobotActionPerformed

    private void jtxtIterasiMaksimumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtIterasiMaksimumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtIterasiMaksimumActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTraining.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FormTraining().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnLoadTrainingSet;
    private javax.swing.JButton jBtnSmpanBobot;
    private javax.swing.JButton jBtnTrain;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblTrainData;
    private javax.swing.JTextField jTxtLatHeight;
    private javax.swing.JTextField jTxtLatWidth;
    private javax.swing.JTextField jTxtLearningRate;
    private javax.swing.JTextField jtxtIterasiMaksimum;
    // End of variables declaration//GEN-END:variables
}
