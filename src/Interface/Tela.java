package Interface;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class Tela extends Template {
    public Tela() {
       super();

       panel.setLayout(new GridLayout(7,2,10,10));

       panel.add(new JLabel("Nome"));
       JTextField campoNome = new JTextField();
       panel.add(campoNome);

       panel.add(new JLabel("Idade"));
       JTextField campoIdade = new JTextField();
       panel.add(campoIdade);

       panel.add(new JLabel("CPF"));
       MaskFormatter mascaraCpf = null;
       try {
           mascaraCpf = new MaskFormatter("###.###.###-##");
           mascaraCpf.setPlaceholderCharacter('_');
        } catch (ParseException e) {
           System.err.println("Erro na formatacao: "+e.getMessage());
           System.exit(-1);
        }
        JFormattedTextField campoCpf = new JFormattedTextField(mascaraCpf);
        campoCpf.setHorizontalAlignment(JTextField.CENTER);
        panel.add(campoCpf);

        panel.add(new JLabel("Titulo"));
        JTextField campoTitulo = new JTextField();
        panel.add(campoTitulo);

        panel.add(new JLabel("Descrição"));
        JTextField campoDescricao = new JTextField();
        panel.add(campoDescricao);

        panel.add(new JLabel("warn"));
        JTextField campoWarn = new JTextField();
        panel.add(campoWarn);

        JButton Envio = new JButton("Enviar Pedido");
        panel.add(new JLabel(""));
        panel.add(Envio);
        add(panel);
    }
}
