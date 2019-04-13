package codicefiscale;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Jay Lean
 */

public class CodiceFiscale {
    public static void main(String[] args) throws IOException  {
        //GeneraCod CF = new GeneraCod();
        //CF.genera();
        
        GeneraGrafica gg = new GeneraGrafica();
        gg.setSize(500, 400);
        gg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gg.setResizable(false);
        gg.setVisible(true);

    }
        
}
  
