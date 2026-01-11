package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EscolhaLogin extends Template{
    public EscolhaLogin(){
        super();
        setTitle("Escolha");
        paineldeEscolha();
    }

    public void paineldeEscolha(){

        PainelArredondado telaEscolha = new PainelArredondado();
        telaEscolha.setBounds(170, 100, 260, 180);
        telaEscolha.setBackground(new Color(255, 230, 255));
        telaEscolha.setDesenharFundo(false);
        telaEscolha.setLayout(null);

        BotaoArredondado btCliente = new BotaoArredondado("Cliente");
        btCliente.setBounds(40,5,180,65);
        btCliente.setFont(new Font("Arial",Font.PLAIN,14));
        btCliente.setForeground(Color.WHITE);
        btCliente.setBackground(new Color(130, 80, 220));
        btCliente.setFocusPainted(false);
        btCliente.setBorderPainted(false);
        btCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btCliente.setBackground(new Color(93, 23, 139)); // Cor quando o mouse entra
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btCliente.setBackground(new Color(130, 80, 220)); // Cor original quando o mouse sai
            }
        });


        btCliente.addActionListener(e ->{
            Login loginUsuario = new Login("Usuario");
            loginUsuario.setVisible(true);
            dispose(); //fecha o EscolhaLogin
        });
        telaEscolha.add(btCliente);

        BotaoArredondado btFuncionario = new BotaoArredondado("Funcionario");
        btFuncionario.setBounds(40, 105, 180, 65);
        btFuncionario.setFont(new Font("Arial",Font.PLAIN,14));
        btFuncionario.setForeground(Color.WHITE);
        btFuncionario.setBackground(new Color(80, 150, 250));
        btFuncionario.setFocusPainted(false);
        btFuncionario.setBorderPainted(false);
        btFuncionario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btFuncionario.setBackground(new Color(65, 105, 225)); // Cor quando o mouse entra
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btFuncionario.setBackground(new Color(80, 150, 250)); // Cor original quando o mouse sai
            }
        });


        btFuncionario.addActionListener(e ->{
            Login loginUsuario = new Login("Funcionario");
            loginUsuario.setVisible(true);
            dispose(); //fecha o EscolhaLogin
        });

        telaEscolha.add(btFuncionario);


        panel.add(telaEscolha);
    }
}
