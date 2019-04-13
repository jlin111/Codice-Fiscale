package codicefiscale;

/**
 *
 * @author Jay Lean
 */
public class Cognome {
    
    String vocali ="", consonanti="";
    public Cognome(){}
    
    public Cognome(String c){
    	
        String a = c.toUpperCase();
        	a=a.trim();
        for (int i=0; i<a.length(); i++){
        	
            if(a.charAt(i)=='A' || a.charAt(i)=='E' || a.charAt(i)=='I' || a.charAt(i)=='O' || a.charAt(i)=='U'){
                vocali += a.charAt(i);
                
            }
             
            else {
                consonanti += a.charAt(i);
            }    
        }
               
    }
    public int lun() {
    	return vocali.length()+consonanti.length();     
    }

}
