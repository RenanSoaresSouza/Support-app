import Interface.*;
import org.json.JSONObject;
import assets.*;

import javax.swing.*;

public class Main {
    public static void main(String[] argv){

        SwingUtilities.invokeLater(() -> {
            Tela_Cliente tela = new Tela_Cliente();
            tela.setVisible(true);
        });
    }
    
}