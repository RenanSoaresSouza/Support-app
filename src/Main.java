import Interface.*;
import org.json.JSONObject;
import assets.*;

import javax.swing.*;

public class Main {
    public static void main(String[] argv){


        SwingUtilities.invokeLater(() -> {
            Tela tela = new Tela();
            tela.setVisible(true);
        });
    }
    
}