package codicefiscale;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author 71015282
 */
public class GeneraCod {
    public String genera() throws FileNotFoundException, IOException{
        String codFisc = "";
        String sesso = "";
        
        Scanner s = new Scanner(System.in);
        
        System.out.println("Inserisci il tuo cognome: ");
        Cognome c = new Cognome(s.nextLine().replaceAll(" ", ""));       
        
        System.out.println("Inserisci il tuo nome: ");
        Nome n = new Nome(s.nextLine().replaceAll(" ", ""));
        
        do{
        System.out.println("Inserisci il tuo sesso:   [M/F]");
        sesso = s.nextLine();
        }while(!sesso.equalsIgnoreCase("M") && !sesso.equalsIgnoreCase("F"));
        
        System.out.println("Inserisci il tuo anno di nascita: ");
        String anno = s.nextLine();
        
        System.out.println("Inserisci il tuo mese di nascita:   [in lettere (es. Gennaio)]");
        String mese = s.nextLine();
        
        System.out.println("Inserisci il tuo giorno di nascita: ");
        String giorno = s.nextLine();
        
        System.out.println("Inserisci il tuo comune di nascita");
        String comune = s.nextLine();
        
        
      
        
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
        sesso=sesso.toUpperCase();
        sesso=sesso.trim();
        if(sesso.charAt(0)=='F'){
        	int w=Integer.valueOf(giorno).intValue();
        	w+=40;
        	giorno=String.valueOf(w).toString();
        	codFisc+=giorno;
        }
        else {
        	if(giorno.length()==1)codFisc= codFisc+"0"+giorno;
        	else codFisc+=giorno;
        }
        
        
        codFisc+=Comune(comune);
        
        codFisc+=codiceControllo(codFisc);
              
            System.out.println(codFisc);
       
        return codFisc;
    }
   
    public static String Comune(String comune) throws FileNotFoundException, IOException{
        String codice = "-1";
        try{
        Scanner reader = new Scanner(new FileReader("codici_catastali.txt"));
        reader.useDelimiter(" -- ");
        comune = comune.toUpperCase();
        while(reader.hasNext()){
            String line = reader.next();
            //System.out.println(line);
            if(line.contains(comune.trim())){
                codice = reader.next();
                break;
            }
        }
        }
        catch(FileNotFoundException e){}
        if(codice.equals("-1")){System.out.println("******  COMUNE NON TROVATO  ******");}
        else codice = codice.substring(0, 4);
        return codice;
    }
    
    
    public static char codiceControllo (String codiceFiscale) {
    	String pari="", dispari="";
    	int sdispari=0,spari=0;
    	for(int i=0; i<15;i++) {
    		if(i%2==0)dispari+=codiceFiscale.charAt(i);
    		else pari+=codiceFiscale.charAt(i);
    	}
    	
    	for(int j=0;j<dispari.length();j++) {
    		char t= dispari.charAt(j);
    		switch(t) {
    		case '0': 
    			sdispari+=1;
    			break;
    		case '1': 
    			sdispari+=0;
    			break;
    		case '2': 
    			sdispari+=5;
    			break;
    		case '3': 
    			sdispari+=7;
    			break;
    		case '4': 
    			sdispari+=9;
    			break;
    		case '5': 
    			sdispari+=13;
    			break;
    		case '6': 
    			sdispari+=15;
    			break;
    		case '7': 
    			sdispari+=17;
    			break;
    		case '8': 
    			sdispari+=19;
    			break;
    		case '9': 
    			sdispari+=21;
    			break;
    		case 'A': 
    			sdispari+=1;
    			break;
    		case 'B': 
    			sdispari+=0;
    			break;
    		case 'C': 
    			sdispari+=5;
    			break;
    		case 'D': 
    			sdispari+=7;
    			break;
    		case 'E': 
    			sdispari+=9;
    			break;
    		case 'F': 
    			sdispari+=13;
    			break;
    		case 'G': 
    			sdispari+=15;
    			break;
    		case 'H': 
    			sdispari+=17;
    			break;
    		case 'I': 
    			sdispari+=19;
    			break;
    		case 'J': 
    			sdispari+=21;
    			break;
    		case 'K': 
    			sdispari+=2;
    			break;
    		case 'L': 
    			sdispari+=4;
    			break;
    		case 'M': 
    			sdispari+=18;
    			break;
    		case 'N': 
    			sdispari+=20;
    			break;
    		case 'O': 
    			sdispari+=11;
    			break;
    		case 'P': 
    			sdispari+=3;
    			break;
    		case 'Q': 
    			sdispari+=6;
    			break;
    		case 'R': 
    			sdispari+=8;
    			break;
    		case 'S': 
    			sdispari+=12;
    			break;
    		case 'T': 
    			sdispari+=14;
    			break;
    		case 'U': 
    			sdispari+=16;
    			break;
    		case 'V': 
    			sdispari+=10;
    			break;
    		case 'W': 
    			sdispari+=22;
    			break;
    		case 'X': 
    			sdispari+=25;
    			break;
    		case 'Y': 
    			sdispari+=24;
    			break;
    		case 'Z': 
    			sdispari+=23;
    			break;
    		} //System.out.println("sdispari: "+sdispari);
    		  		
    	}
    	for(int j=0;j<pari.length();j++) {
    		char r= pari.charAt(j);
    		switch(r) {
    		case '0': 
    			spari+=0;
    			break;
    		case '1': 
    			spari+=1;
    			break;
    		case '2': 
    			spari+=2;
    			break;
    		case '3': 
    			spari+=3;
    			break;
    		case '4': 
    			spari+=4;
    			break;
    		case '5': 
    			spari+=5;
    			break;
    		case '6': 
    			spari+=6;
    			break;
    		case '7': 
    			spari+=7;
    			break;
    		case '8': 
    			spari+=8;
    			break;
    		case '9': 
    			spari+=9;
    			break;
    		case 'A': 
    			spari+=0;
    			break;
    		case 'B': 
    			spari+=1;
    			break;
    		case 'C': 
    			spari+=2;
    			break;
    		case 'D': 
    			spari+=3;
    			break;
    		case 'E': 
    			spari+=4;
    			break;
    		case 'F': 
    			spari+=5;
    			break;
    		case 'G': 
    			spari+=6;
    			break;
    		case 'H': 
    			spari+=7;
    			break;
    		case 'I': 
    			spari+=8;
    			break;
    		case 'J': 
    			spari+=9;
    			break;
    		case 'K': 
    			spari+=10;
    			break;
    		case 'L': 
    			spari+=11;
    			break;
    		case 'M': 
    			spari+=12;
    			break;
    		case 'N': 
    			spari+=13;
    			break;
    		case 'O': 
    			spari+=14;
    			break;
    		case 'P': 
    			spari+=15;
    			break;
    		case 'Q': 
    			spari+=16;
    			break;
    		case 'R': 
    			spari+=17;
    			break;
    		case 'S': 
    			spari+=18;
    			break;
    		case 'T': 
    			spari+=19;
    			break;
    		case 'U': 
    			spari+=20;
    			break;
    		case 'V': 
    			spari+=21;
    			break;
    		case 'W': 
    			spari+=22;
    			break;
    		case 'X': 
    			spari+=23;
    			break;
    		case 'Y': 
    			spari+=24;
    			break;
    		case 'Z': 
    			spari+=25;
    			break;
    		} //System.out.println("spari: "+spari);
    		
    	
    } /*
    	System.out.println("pari: "+pari);
    	System.out.println("dispari: "+dispari);
    	System.out.println(spari); 
    	System.out.println(sdispari);
    	System.out.println("somma tot: "+(spari+sdispari)); 
    	System.out.println("resto: "+(spari + sdispari)%26); 
    	*/
    	int resto = (spari + sdispari)%26;
    	
    	switch(resto) {
    	case 0:
    		return 'A';
    	case 1:
    		return 'B';
    	case 2:
    		return 'C';
    	case 3:
    		return 'D';
    	case 4:
    		return 'E';
    	case 5:
    		return 'F';
    	case 6:
    		return 'G';
    	case 7:
    		return 'H';
    	case 8:
    		return 'I';
    	case 9:
    		return 'J';
    	case 10:
    		return 'K';
    	case 11:
    		return 'L';
    	case 12:
    		return 'M';
    	case 13:
    		return 'N';
    	case 14:
    		return 'O';
    	case 15:
    		return 'P';
    	case 16:
    		return 'Q';
    	case 17:
    		return 'R';
    	case 18:
    		return 'S';
    	case 19:
    		return 'T';
    	case 20:
    		return 'U';
    	case 21:
    		return 'V';
    	case 22:
    		return 'W';
    	case 23:
    		return 'X';
    	case 24:
    		return 'Y';
    	case 25:
    		return 'Z';
    		
    	}   	
    	return 0;
    }  
    }
