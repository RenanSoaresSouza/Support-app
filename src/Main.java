import Interface.*;
import assets.*;

import org.json.JSONObject;

import javax.swing.*;

public class Main {
    public static void main(String[] argv){

        SwingUtilities.invokeLater(() -> {
            Tela_Funcionarios tela = new Tela_Funcionarios();
            tela.setVisible(true);
        });
    }
    
}