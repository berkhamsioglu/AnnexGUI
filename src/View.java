package AnnexGUI;
import java.awt.*;
import javax.swing.*;
 
public class View extends JFrame implements Runnable{
   /**
	 * 
	 */
   //private static final long serialVersionUID = 1L;
   private final java.awt.Label[] lblLabel;
   private final java.awt.Label[] lblDataValues;
   private float[] fDataValues;

   /** Constructor to setup the GUI components */
   public View() {
      Container cp = getContentPane();
      cp.setLayout(new java.awt.GridLayout(4, 2));
      lblLabel = new java.awt.Label[4];
      lblDataValues = new java.awt.Label[4];
      fDataValues = new float[4];

      lblLabel[0] = new java.awt.Label("OCV   : ");
      lblLabel[1] = new java.awt.Label("Height: ");
      lblLabel[2] = new java.awt.Label("Weight: ");
      lblLabel[3] = new java.awt.Label("STL   : ");
      Font myFont = new Font("Verdana",Font.BOLD,120);//200);
      for(int i = 0;i<4;i++)
      {
        lblLabel[i].setAlignment(java.awt.Label.RIGHT);
        lblLabel[i].setForeground(new java.awt.Color(0, 0, 255));
        lblLabel[i].setFont(myFont);
      }
      
      for(int i = 0;i<4;i++)
      {
        lblDataValues[i] = new java.awt.Label("Pending");
        lblDataValues[i].setAlignment(java.awt.Label.LEFT);
        lblDataValues[i].setForeground(new java.awt.Color(0, 153, 0));
        lblDataValues[i].setFont(myFont);
      }

      for(int i = 0;i<4;i++)
      {
          cp.add(lblLabel[i]);
          cp.add(lblDataValues[i]);
      }
  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Annex GUI");
      Toolkit tk = Toolkit.getDefaultToolkit();  
      int xSize = ((int) tk.getScreenSize().getWidth());  
      int ySize = ((int) tk.getScreenSize().getHeight());  
      setSize(xSize,ySize);
      setVisible(true);
   }
 
   public void setValues(String iValues)
   {
      for(int i = 0;i<4;i++)
      {
          String strValues = "";
          StringBuilder sb = new StringBuilder();
          for(int j = i*6 ;j<(i*6)+6;j++)
          {
              sb.append(iValues.charAt(j));
          }
          strValues = sb.toString();
          lblDataValues[i].setText(strValues);
          fDataValues[i] = Float.valueOf(strValues);
      }
   }
   /** The entry main method */
   public static void main(String[] args) {

   }

@Override
public void run() {
	// TODO Auto-generated method stub
    
}
}