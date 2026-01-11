import Interface.*;
import assets.*;

import org.json.JSONObject;

import javax.swing.*;

public class Main {
    public static void main(String[] argv){

        SwingUtilities.invokeLater(() -> {
            EscolhaLogin tela = new EscolhaLogin();
            tela.setVisible(true);
        });
    }
    
}