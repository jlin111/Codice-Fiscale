package codicefiscale;

import static codicefiscale.GeneraCod.Comune;
import static codicefiscale.GeneraCod.codiceControllo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author 71015282
 */
public class GeneraGrafica extends JFrame{
    JPanel pannelloPrincipale, pannelloTitolo, pannelloCentrale, pannelloCF, pannelloSesso, pannelloBott; 
    JLabel titolo,cognome,nome,sesso,giorno,mese,anno,comune; 
    JTextField cognomeField, nomeField, giornoField, annoField, comuneField, CFField; 
    JCheckBox sessoFCheck, sessoMCheck;
    JComboBox meseCombo;
    JButton bott,bott1;
    
    String[] mesi = {"", "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
    
    
    public GeneraGrafica(){
          
        titolo = new JLabel("CF Generator"); 
        titolo.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 22)); 
        titolo.setForeground(Color.MAGENTA);
        pannelloSesso = new JPanel();
        pannelloPrincipale = new JPanel(new BorderLayout());
        pannelloPrincipale.setBackground(Color.yellow);
        pannelloTitolo = new JPanel(new FlowLayout()); 
        pannelloTitolo.setBackground(Color.ORANGE);
        pannelloCentrale = new JPanel(new GridLayout(7,2)); 
        pannelloCentrale.setBackground(Color.PINK);        
        pannelloBott = new JPanel(new GridLayout(1,2));
        pannelloCF = new JPanel(new GridLayout(2,1));
        pannelloCF.setBackground(Color.ORANGE);
        
        bott = new JButton("GENERA");
        bott1 = new JButton("PULISCI");
        CFField = new JTextField();
        CFField.setEditable(false);
        
        ButtonGroup group1 = new ButtonGroup();
        group1.add(bott);
        group1.add(bott1);
        pannelloBott.add(bott);
        pannelloBott.add(bott1);
        pannelloCF.add(pannelloBott);
        pannelloCF.add(CFField);
        
        cognome = new JLabel("COGNOME");
        pannelloCentrale.add(cognome);
        cognomeField = new JTextField(); 
        cognomeField.setFont(new Font("Arial", Font.PLAIN, 14));
        cognomeField.setHorizontalAlignment(JTextField.CENTER);
        pannelloCentrale.add(cognomeField); 
        nome = new JLabel("NOME");
        pannelloCentrale.add(nome);
        nomeField = new JTextField(); 
        nomeField.setFont(new Font("Arial", Font.PLAIN, 14));
        nomeField.setHorizontalAlignment(JTextField.CENTER);
        pannelloCentrale.add(nomeField); 
        giorno = new JLabel("GIORNO DI NASCITA");
        pannelloCentrale.add(giorno);
        giornoField = new JTextField(); 
        giornoField.setFont(new Font("Arial", Font.PLAIN, 14));
        giornoField.setHorizontalAlignment(JTextField.CENTER);
        pannelloCentrale.add(giornoField); 
        mese = new JLabel("MESE DI NASCITA");
        pannelloCentrale.add(mese);
        meseCombo = new JComboBox(mesi);
        pannelloCentrale.add(meseCombo); 
        anno = new JLabel("ANNO DI NASCITA");
        pannelloCentrale.add(anno);
        annoField = new JTextField(); 
        annoField.setFont(new Font("Arial", Font.PLAIN, 14));
        annoField.setHorizontalAlignment(JTextField.CENTER);
        pannelloCentrale.add(annoField); 
        sesso = new JLabel("SESSO");
        pannelloCentrale.add(sesso);
        
        ButtonGroup group = new ButtonGroup(); 
        sessoFCheck = new JCheckBox("F");
        sessoMCheck = new JCheckBox("M");
        group.add(sessoFCheck);
        group.add(sessoMCheck); 
        pannelloSesso.add(sessoMCheck);
        pannelloSesso.add(sessoFCheck);
        
        pannelloCentrale.add(pannelloSesso);       
        comune = new JLabel("COMUNE DI NASCITA");
        pannelloCentrale.add(comune);
        comuneField = new JTextField();
        comuneField.setFont(new Font("Arial", Font.PLAIN, 18));
        comuneField.setHorizontalAlignment(JTextField.CENTER);
        pannelloCentrale.add(comuneField); 
               
        pannelloTitolo.add(titolo); 
        pannelloPrincipale.add(pannelloTitolo, BorderLayout.NORTH); 
        pannelloPrincipale.add(pannelloCentrale, BorderLayout.CENTER);
        pannelloPrincipale.add(pannelloCF, BorderLayout.SOUTH);
        add(pannelloPrincipale); 
        
        bott1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cognomeField.setText("");
                nomeField.setText("");
                giornoField.setText("");
                annoField.setText("");
                comuneField.setText("");
                CFField.setText("");
                sessoMCheck.setSelected(false);
                sessoFCheck.setSelected(false);
                
            }
        });
        
        bott.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                 String codFisc = "";
                 String sesso = "";
                  
                
                 Cognome c = new Cognome(cognomeField.getText().replaceAll(" ", "")); 
                
                 Nome n = new Nome(nomeField.getText().replaceAll(" ", ""));   
                 
                 //C'è un problemino su come ritornare il valore del JcheckBox
                 boolean selectedF = sessoFCheck.isSelected();
                 if(selectedF == true)                                  
                  sesso = "F";  
                 else sesso = "M";
                 
                 String anno = annoField.getText();        
                 String mese = (String) meseCombo.getSelectedItem();        
                 String giorno = giornoField.getText();  
                 String comune = comuneField.getText();
                 
                 if(c.consonanti.length()>=3) 
        	codFisc+=c.consonanti.substring(0, 3);
        else if(c.consonanti.length()==2) {
        	codFisc+=c.consonanti.substring(0, 2);
        	if(c.vocali.length()>=1) codFisc+=c.vocali.charAt(0);
        	else codFisc += "X";
        }
        else if(c.consonanti.length()==1) {
        	codFisc+=c.consonanti.substring(0);
        	if(c.vocali.length()>1) codFisc+=c.vocali.substring(0, 1);
        else if(c.vocali.length()==1) codFisc= codFisc + c.vocali.charAt(0) + "X";
        else codFisc += "XX";
        }
        
        
            	if(n.consonanti.length()>3) 
            		codFisc=codFisc+n.consonanti.charAt(0)+n.consonanti.substring(2,4);
            	if(n.consonanti.length()==3)
            		codFisc+=n.consonanti.substring(0, 3);
            	
            	if(n.consonanti.length()==2) {
            		codFisc+=n.consonanti.substring(0,2);
            		if(n.vocali.length()>1)
            			codFisc+=n.vocali.charAt(0);
            		else codFisc+="X";
            	  }
            	         	
                                          
                       
        if(n.consonanti.length()==1) {
    		codFisc+=n.consonanti.charAt(0);
    		if(n.vocali.length()==1)
    			codFisc=codFisc+ n.vocali.charAt(0)+"X";           			
    		else if(n.vocali.length()>1)
    			codFisc+=n.vocali.substring(0,2); 
    		else codFisc+="XX";
    	                             }
        
        codFisc+=anno.substring(2, 4);
        
        String m = mese.toLowerCase();
        switch(m) {
        case "gennaio":
        	codFisc+="A";
        	break;
        case "febbraio":
        	codFisc+="B";
        	break;
        case "marzo":
        	codFisc+="C";
        	break;
        case "aprile":
        	codFisc+="D";
        	break;
        case "maggio" :
        	codFisc+="E";
        	break;
        case "giugno":
        	codFisc+="H";
        	break;
        case "luglio":
        	codFisc+="L";
        	break;
        case "agosto":
        	codFisc+="M";
        	break;
        case "settembre":
        	codFisc+="P";
        	break;
        case "ottobre":
        	codFisc+="R";
        	break;
        case "novembre":
        	codFisc+="S";
        	break;
        case "dicembre":
        	codFisc+="T";
        	break;
	
        }
        
         if(sesso.equals("F")){
        	int w=Integer.valueOf(giorno).intValue();
        	w+=40;
        	giorno=String.valueOf(w).toString();
        	codFisc+=giorno;
        }
        else {
        	if(giorno.length()==1)codFisc= codFisc+"0"+giorno;
        	else codFisc+=giorno;
        }
        
                try {
                    codFisc+=Comune(comune);
                } catch (IOException ex) {
                    Logger.getLogger(GeneraGrafica.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        codFisc+=codiceControllo(codFisc);
              
            System.out.println(codFisc);
        
        CFField.setText(codFisc);
            }
       
        });
    }

   
    
   
    
}
