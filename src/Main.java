import Interface.*;
import org.json.JSONObject;
import assets.*;

import javax.swing.*;

public class Main {
    public static void main(String[] argv){
           /*API conex = new API();
           
        try {
            conex.get();
        } catch (Exception e){
            System.out.println(e);
        } finally {
           JSONObject resp = new JSONObject(conex.resp);
           
        }
        try {
           
        } catch (Exception e){
            System.out.println(e);
        } finally {
           
           
        }
*/
        SwingUtilities.invokeLater(() -> {
            Tela tela = new Tela();
            tela.setVisible(true);
        });
    }
    
}