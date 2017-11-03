/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Jesus Gonzalez
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MarcoCliente mimarco=new MarcoCliente();
		
	mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}

class MarcoCliente extends JFrame{
	
	public MarcoCliente(){
		
		setBounds(100,100,280,350);
	        
		LaminaMarcoCliente milamina=new LaminaMarcoCliente();
		
		add(milamina);
		
		setVisible(true);
                
               
                
		}	
	
}

class LaminaMarcoCliente extends JPanel{
        private JTextField campo1;
	private JButton miboton;
	 String IP=null,puerto=null;
	public LaminaMarcoCliente()
        {
                IP=JOptionPane.showInputDialog(null, "Introduce tu IP");
                puerto=JOptionPane.showInputDialog(null, "Introduce tu Puerto");
                if(IP==null||puerto==null){JOptionPane.showMessageDialog(null, "Tiene que ingresar todos los valores"); System.exit(1); }
		JLabel texto=new JLabel("CLIENTE");
		texto.setBackground(Color.WHITE);
                texto.setForeground(Color.WHITE);
		add(texto);
	
		campo1=new JTextField(20);
		add(campo1);		
	
		miboton=new JButton(new ImageIcon(getClass().getResource("/imagenes/encriptar.png")));
                miboton.setSize(150, 50);
                EnviarTexto evento=new EnviarTexto();
                miboton.addActionListener(evento);
		add(miboton);
                
                setBackground(Color.BLACK);
		         
	}
	
	private class EnviarTexto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          
            try {
              
                String encriptar;
                String sCadena;
         

                
                
                Socket misocket=new Socket(IP,Integer.parseInt(puerto));
                
                DataOutputStream flujo_salida=new DataOutputStream(misocket.getOutputStream());
                
                
                    sCadena=campo1.getText();
                    StringBuffer mensaje=new StringBuffer();
                    int aux=0;
                    char c;
                    
                    for (int x=0;x<sCadena.length();x++)
                    {
                         aux=sCadena.codePointAt(x)+1;
                         c = (char)aux;   
                         mensaje.append(c);
                    }
      
                encriptar=mensaje.toString();

                flujo_salida.writeUTF(encriptar);
                
                flujo_salida.close();
                
            } catch (IOException ex) {
//                Logger.getLogger(LaminaMarcoCliente.class.getName()).log(Level.SEVERE, null, ex);
               
                JOptionPane.showMessageDialog(null, "ERROR EN LA CONEXION Cliente");                
            }





        }
            
            
            
        }
	
		
		
		
	
	
}