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
        JPanel telaEscolha = new JPanel();
        telaEscolha.setBounds(170, 100, 260, 180);
        telaEscolha.setBackground(new Color(170,170,170));
        telaEscolha.setLayout(null);

        JButton btCliente = new JButton("Cliente");
        btCliente.setBounds(60,25,140,50);
        btCliente.setFont(new Font("Arial",Font.PLAIN,11));
        btCliente.setFocusPainted(false);
        btCliente.setBorderPainted(false);

        btCliente.addActionListener(e ->{
            Login loginUsuario = new Login("Usuario");
            loginUsuario.setVisible(true);
            dispose(); //fecha o EscolhaLogin
        });
        telaEscolha.add(btCliente);

        JButton btFuncionario = new JButton("Funcionario");
        btFuncionario.setBounds(60,115,140,50);
        btFuncionario.setFont(new Font("Arial",Font.PLAIN,11));
        btFuncionario.setFocusPainted(false);
        btFuncionario.setBorderPainted(false);

        btFuncionario.addActionListener(e ->{
            Login loginUsuario = new Login("Funcionario");
            loginUsuario.setVisible(true);
            dispose(); //fecha o EscolhaLogin
        });

        telaEscolha.add(btFuncionario);

        panel.add(telaEscolha);
    }
}
