package Interface;

import javax.swing.*;
import java.awt.*;

public class ExibirErros {
	public static void exibir(String tipoErro){
	    JOptionPane.showMessageDialog(
	        null,
	        "Erro: " + tipoErro,
	        "Erro",
	        JOptionPane.ERROR_MESSAGE
	    );
	}
}