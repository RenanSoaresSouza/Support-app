import Interface.*;
import org.json.JSONObject;
import assets.*;

import javax.swing.*;

public class Main {
    public static void main(String[] argv){

        SwingUtilities.invokeLater(() -> {
            Tela_Funcionarios tela = new Tela_Funcionarios();
            tela.setVisible(true);
        });
    }
    
}