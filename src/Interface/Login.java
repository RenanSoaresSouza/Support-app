package Interface;

import javax.swing.*;
import java.awt.*;

class Template extends JFrame {
    protected JPanel panel;

     protected Template(){
        setTitle("Template");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel();
        panel.setBackground(new Color(70, 70, 70));
        panel.setLayout(null);

        setContentPane(panel);
    }
}

public class Login extends Template {
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JLabel Error;

    public Login() {
        super();
        setTitle("Login");
        paineldeLogin();
    }

    private void paineldeLogin() {
        JPanel painelLogin = new JPanel();
        painelLogin.setBounds(170, 100, 260, 180);
        painelLogin.setBackground(new Color(170, 170, 170));
        painelLogin.setLayout(null);

        JLabel login = new JLabel("login");
        login.setBounds(110, 10, 50, 20);
        login.setFont(new Font("Arial", Font.PLAIN, 12));
        painelLogin.add(login);

        JLabel usuario = new JLabel("usuário");
        usuario.setBounds(105, 45, 60, 20);
        usuario.setFont(new Font("Arial", Font.PLAIN, 11));
        painelLogin.add(usuario);

        campoLogin = new JTextField();
        campoLogin.setBounds(60, 70, 140, 25);
        campoLogin.setFont(new Font("Arial", Font.PLAIN, 12));
        painelLogin.add(campoLogin);

        JLabel labelSenha = new JLabel("senha");
        labelSenha.setBounds(110, 105, 50, 20);
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 11));
        painelLogin.add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(60, 130, 140, 25);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 12));
        painelLogin.add(campoSenha);

        Error = new JLabel("Erro ao fazer login");
        Error.setBounds(65, 160, 200, 20);
        Error.setFont(new Font("Arial", Font.BOLD, 11));
        Error.setForeground(Color.RED);
        Error.setVisible(false);
        painelLogin.add(Error);

        campoSenha.addActionListener( e -> realizarLogin());

        panel.add(painelLogin);
    }

    private void realizarLogin(){
        String usuario = campoLogin.getText();
        //converter para string para facilitar a validação da senha
        String senha = new String(campoSenha.getPassword());

        if(usuario.isEmpty() || senha.isEmpty()) {
            Error.setText("Campos não preenchidos!");
            Error.setVisible(true);
        } else{
            Error.setVisible(false);
            JOptionPane.showMessageDialog(this,"Aguardando validacao...","Aguarde",JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
