import Interface.*;
import org.json.JSONObject;
import assets.*;

import javax.swing.*;

public class Main {
    public static void main(String[] argv){


        SwingUtilities.invokeLater(() -> {
            EscolhaLogin tela = new EscolhaLogin();
            tela.setVisible(true);
        });
    }
    
}