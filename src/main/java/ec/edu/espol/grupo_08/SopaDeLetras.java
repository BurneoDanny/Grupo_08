
package ec.edu.espol.grupo_08;

import ec.edu.espol.model.Celda;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class SopaDeLetras extends javax.swing.JFrame {

    
    private Celda celdas[][];
    private final Stack<Celda> firstCeldaClicked = new Stack<>();
    private String filas;
    private String columnas;
    //private Stack<Celda> celdasRecorridas = new Stack<>();
    /**
     * Creates new form SopaDeLetras
     */
    public SopaDeLetras() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        cargar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSopa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 400));

        panelSopa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelSopa.setLayout(new java.awt.GridLayout(6, 6));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SOPA DE LETRAS");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addComponent(jLabel1)
                .addContainerGap(142, Short.MAX_VALUE))
            .addComponent(panelSopa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelSopa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void cargar()
    {
        filas = JOptionPane.showInputDialog("Ingrese las filas de la sopa de letras");
        columnas = JOptionPane.showInputDialog("Ingrese las columnas de la sopa de letras");
        setMatrix(filas,columnas);
        llenarDeLetras();
    }
    
    
    private void setMatrix(String filas, String columnas){       
        int filas1 = Integer.parseInt(filas);
        int columnas1 = Integer.parseInt(columnas);
        panelSopa.setLayout(new GridLayout(filas1, columnas1, 1, 1));
        celdas= new Celda[filas1][columnas1];
        for (int i = 0; i < filas1; i++) 
        {
            for (int j = 0; j < columnas1; j++) {
                JLabel letra = new JLabel("",SwingConstants.CENTER);
                letra.setName("");
                letra.setOpaque(true);         
                letra.setFont(new Font("Calibri", Font.PLAIN, 14)); 
                letra.setForeground(Color.BLACK); 
                Celda celda = new Celda(i, j,letra, celdas); 
                celdas[i][j] = celda;
                celda.setText(letra.getText()); 

                celda.addMouseListener(new MouseAdapter(){           
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        clickLetra(e, celda);
                    }
                    
                    
                }); 
                panelSopa.add(celdas[i][j]);
            }
        }
        for (int m = 0; m < filas1; m++) {
            for (int n = 0; n < columnas1; n++) {
                celdas[m][n].setNeighbours();
            }
        }
    }
    
    private void clickLetra(MouseEvent e, Celda celda){   
        for (int i=0;i<celdas.length;i++){
            for(int j=0;j<celdas[0].length;j++){ 
                firstCeldaClicked.push(celdas[i][j]);
            }
        }
        while(!firstCeldaClicked.isEmpty() && !firstCeldaClicked.peek().getBackground().equals(Color.red)){   
            firstCeldaClicked.pop(); 
        }
        if(firstCeldaClicked.isEmpty()){
            e.getComponent().setBackground(Color.red); 
        }
        
        else{
            int filaPrimeraCelda = firstCeldaClicked.peek().getRow();
            int columnaPrimeraCelda = firstCeldaClicked.peek().getColumn();
            int Segundafila = celda.getRow();
            int SegundaColumna = celda.getColumn();
            int restaEntreCeldasFila = filaPrimeraCelda-Segundafila;
            int restaEntreColumnasColumna = columnaPrimeraCelda-SegundaColumna;  
            Stack<Celda> finalWord = new Stack<>();
            finalWord.push(celda); 
            if(restaEntreCeldasFila == 0 || restaEntreColumnasColumna == 0){
                if(restaEntreCeldasFila == 0){ // se movio entre columnas          
                    Stack<Celda> s = new Stack<>();
                    Color color = celdas[filaPrimeraCelda][columnaPrimeraCelda].getBackground();
                    if (!celda.getBackground().equals(color)) {
                        s.push(celda); // push de 2da celda
                    }
                    while (!s.isEmpty()) {                      
                        Celda currentCelda = s.pop(); 
                        if (!currentCelda.getBackground().equals(color)) {
                            currentCelda.setBackground(color);                      
                        }    
                        if(restaEntreColumnasColumna<0){
                            if (currentCelda.left != null && !currentCelda.left.getBackground().equals(color)) {
                                s.push(currentCelda.left); 
                                finalWord.push(currentCelda.left);
                                
                            }                         
                        }
                        else{
                            if (currentCelda.right != null && !currentCelda.right.getBackground().equals(color)) {
                                s.push(currentCelda.right);
                                finalWord.push(currentCelda.right);
                            }
                        }     
                    }
                }
                else{ // se movio entre filas         
                    Stack<Celda> s = new Stack<>();
                    Color color = celdas[filaPrimeraCelda][columnaPrimeraCelda].getBackground();
                    if (!celda.getBackground().equals(color)) {
                        s.push(celda); // push de 2da celda
                    }
                    while (!s.isEmpty()) {                      
                        Celda currentCelda = s.pop(); 
                        if (!currentCelda.getBackground().equals(color)) {
                            currentCelda.setBackground(color);                      
                        }    
                        if(restaEntreCeldasFila<0){                      
                            if (currentCelda.up != null && !currentCelda.up.getBackground().equals(color)) {
                                s.push(currentCelda.up); 
                                finalWord.push(currentCelda.up);
                            }                         
                        }
                        else{
                            if (currentCelda.down != null && !currentCelda.down.getBackground().equals(color)) {
                                s.push(currentCelda.down); 
                                finalWord.push(currentCelda.down);
                            }
                        }     
                    }
                }
                finalWord.push(celdas[filaPrimeraCelda][columnaPrimeraCelda]);
                ComprobarPalabra(finalWord);
            }
            else if(Math.abs(restaEntreCeldasFila) == Math.abs(restaEntreColumnasColumna)){
                Stack<Celda> s = new Stack<>();
                Color color = celdas[filaPrimeraCelda][columnaPrimeraCelda].getBackground();
                if (!celda.getBackground().equals(color)) {
                    s.push(celda); 
                }
                while (!s.isEmpty()) {
                    Celda currentCelda = s.pop(); 
                    if (!currentCelda.getBackground().equals(color)) {
                        currentCelda.setBackground(color);                      
                    } 
                    if(restaEntreCeldasFila < 0 && restaEntreColumnasColumna < 0){
                        if (currentCelda.up.left != null && !currentCelda.up.left.getBackground().equals(color)) {
                            s.push(currentCelda.up.left);
                            finalWord.push(currentCelda.up.left);
                        } 
                    }
                    else if(restaEntreCeldasFila > 0 && restaEntreColumnasColumna < 0){
                        if (currentCelda.down.left != null && !currentCelda.down.left.getBackground().equals(color)) {
                            s.push(currentCelda.down.left); 
                            finalWord.push(currentCelda.down.left);
                        } 
                    }
                    else if(restaEntreCeldasFila < 0 && restaEntreColumnasColumna > 0){
                        if (currentCelda.up.right != null && !currentCelda.up.right.getBackground().equals(color)) {
                            s.push(currentCelda.up.right);
                            finalWord.push(currentCelda.up.right);
                        } 
                    }
                    else{
                        if (currentCelda.down.right != null && !currentCelda.down.right.getBackground().equals(color)) {
                            s.push(currentCelda.down.right); 
                            finalWord.push(currentCelda.down.right);
                        }  
                    }         
                }
                finalWord.push(celdas[filaPrimeraCelda][columnaPrimeraCelda]);
                ComprobarPalabra(finalWord);
            }         
        }      
    }
    
    private void ComprobarPalabra(Stack<Celda> palabra){
        while(!palabra.isEmpty()){
            System.out.println(palabra.pop().getText());
        }
        // DESPUES DE COMPROBAR LA PALABRA DEBERIA SUCEDER LO SIGUIENTE
        /*
        for (int m = 0; m < Integer.parseInt(filas); m++) {
            for (int n = 0; n < Integer.parseInt(columnas); n++) {
                celdas[m][n].setBackground(new Color(255, 255, 255));
            }
        }  
        */
    }
    
    public void llenarDeLetras() {
        //este arreglo ayuda a poner las letras del abecedario
        String abc[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","??","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Random random = new Random();
        for (int i = 0; i < Integer.parseInt(filas); i++) {
            for (int j = 0; j < Integer.parseInt(columnas); j++) {
                if (celdas[i][j].getText().equals("")) { //si la casilla esta vacia pongale una letra del arreglo abc
                    String aleatorio = abc[(int)(random.nextDouble()*abc.length-1)];
                    celdas[i][j].setText(aleatorio);
                    celdas[i][j].setLetra(new JLabel (aleatorio));
                }
            }
        }
    }
    
    
  
    
   
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SopaDeLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SopaDeLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SopaDeLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SopaDeLetras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SopaDeLetras().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelSopa;
    // End of variables declaration//GEN-END:variables
}
