
package servidor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author Jesus Gonzalez
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MarcoServidor mimarco=new MarcoServidor();
		
	mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}


class MarcoServidor extends JFrame implements Runnable{
	String desencriptar="";
        int blo=0;
        private	JTextArea Area_recibi;
        private	JTextArea Area_Desencri;
        private JButton miboton;
         String  puerto=null;
	public MarcoServidor()
        {
		puerto=JOptionPane.showInputDialog(null, "Introduce tu Puerto");
                if(puerto==null){JOptionPane.showMessageDialog(null, "Tiene que ingresar el puerto"); System.exit(1); }
		setBounds(500,100,500,450);				
	        
		
		JPanel milamina= new JPanel();
		milamina.setLayout(null);
		
                JLabel texto=new JLabel("MENSAJES RECIBIDOS");
                texto.setBounds(0, 5, 200, 15);
		texto.setBackground(Color.WHITE);
                texto.setForeground(Color.WHITE);
		milamina.add(texto);
                
		miboton=new JButton(new ImageIcon(getClass().getResource("/imagenes/desencriptar.png")));
                miboton.setBounds(300,200,200,100);
		milamina.add(miboton);
                Mostrar_Desencriptadas evento=new Mostrar_Desencriptadas();
                miboton.addActionListener(evento);
                
                
                
                Area_recibi=new JTextArea();
                Area_recibi.setBounds(0,25,300, 200);
		milamina.add(Area_recibi);
                
                
                JLabel texto2=new JLabel("MENSAJES DESENCRIPTADOS");
                texto2.setBounds(0, 165, 200, 150);
		texto2.setBackground(Color.WHITE);
                texto2.setForeground(Color.WHITE);
		milamina.add(texto2);
                
                Area_Desencri=new JTextArea();
                Area_Desencri.setBounds(0,260,300, 200);
		milamina.add(Area_Desencri);
             
                milamina.setBackground(Color.BLACK); 
		add(milamina);
		
                
          
                
		setVisible(true);
                Thread mihilo=new Thread(this);
                mihilo.start();
                
		
	}
	
	

    @Override
    public void run() {
            try {
              
                ServerSocket servidor=new ServerSocket(Integer.parseInt(puerto));
                while(true)
                {
                    
                    Socket mysocket =servidor.accept();
                
                    DataInputStream flujo_entrada=new DataInputStream(mysocket.getInputStream());
                    String mensaje_texto=flujo_entrada.readUTF();
                    
                   
                    int aux=0;
                    char c;
                    StringBuffer recibir=new StringBuffer();
                    
                    for (int x=0;x<mensaje_texto.length();x++)
                    {
                        aux=mensaje_texto.codePointAt(x)-1;
                        c = (char)aux;
                        recibir.append(c);
                    }
                    
                    desencriptar=recibir.toString();
                    

                    blo=0;

                    Area_recibi.append("\n"+mensaje_texto);
                    mysocket.close();
            
                }
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
                
                JOptionPane.showMessageDialog(null, "ERROR EN LA CONEXION Servidor"); 
            }
    
    }
    
    
    
    
    
    private class Mostrar_Desencriptadas implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
     
            if(desencriptar==desencriptar )
            {
                if(blo==0)
                {     
                    Area_Desencri.append("\n"+desencriptar);
                    blo=1;
                }
            }
        }
    }
    
}
