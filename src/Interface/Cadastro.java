package Interface;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class Cadastro extends Template{
    private JTextField campoNome;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmacao;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JLabel Error;

    public Cadastro(){
        super();
        panel.setBackground(Color.white);
        paineldeCadastro();
    }

    private void paineldeCadastro(){
        JPanel telaCadastro = new JPanel();
        telaCadastro.setBounds(100,0,400,400);
        telaCadastro.setLayout(null);

        JLabel Cadastre_se = new JLabel("Cadastre-se!");
        Cadastre_se.setBounds(150,5,200,100);
        Cadastre_se.setFont(new Font("Arial", Font.PLAIN,20));
        telaCadastro.add(Cadastre_se);

        JLabel nome = new JLabel("Nome : ");
        nome.setBounds(100,80,60,20);
        nome.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(nome);

        campoNome = new JTextField();
        campoNome.setBounds(160,80,140,25);
        campoNome.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoNome);

        JLabel senha = new JLabel("Senha :");
        senha.setBounds(100,120,60,20);
        senha.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(senha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(160,120,140,25);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoSenha);

        JLabel confirmacao = new JLabel("Confirme sua senha :");
        confirmacao.setBounds(35,160,120,20);
        confirmacao.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(confirmacao);

        campoConfirmacao = new JPasswordField();
        campoConfirmacao.setBounds(160,160,140,25);
        campoConfirmacao.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoConfirmacao);

        JLabel telefone = new JLabel("Telefone(opcional) :");
        telefone.setBounds(40,200,120,20);
        telefone.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(telefone);

        campoTelefone = new JTextField();
        campoTelefone.setBounds(160,200,140,25);
        campoTelefone.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoTelefone);

        JLabel email = new JLabel("Email(opcional) :");
        email.setBounds(55,240,120,20);
        email.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(email);

        campoEmail = new JTextField();
        campoEmail.setBounds(160,240,140,25);
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoEmail);

        Error = new JLabel();
        Error.setBounds(100,280,300,20);
        Error.setFont(new Font("Arial", Font.BOLD,11));
        Error.setForeground(Color.RED);
        Error.setVisible(false);
        telaCadastro.add(Error);

        JButton enviar = new JButton("Cadastrar dados");
        enviar.setBounds(210,340,120,40);
        enviar.setFont(new Font("Arial",Font.PLAIN,11));


        enviar.addActionListener(e -> {
            realizarCadastro();
            }
        );

        telaCadastro.add(enviar);

        panel.add(telaCadastro);
    }

    public void realizarCadastro(){
        String nome = campoNome.getText().trim();
        String senha = new String(campoSenha.getPassword());
        String confirmacao = new String(campoConfirmacao.getPassword());
        String telefone = campoTelefone.getText();
        String email = campoEmail.getText();

        if(nome.isEmpty()){
            Error.setText("Insira um nome!");
            Error.setVisible(true);
            return;
        }
        if(senha.length() < 8){
            Error.setText("Senha muito curta, insira pelo menos 8 caracteres");
            Error.setVisible(true);
            return;
        }
        if(!senha.equals(confirmacao)){
            Error.setText("As senhas não coincidem!");
            Error.setVisible(true);
            return;
        }
        if(telefone.isEmpty()){
            telefone = "";
        }
        if(email.isEmpty()){
            email = "";
        }

        Error.setVisible(false);

        limparDados();
    }

    public void limparDados(){
        campoNome.setText("");
        campoSenha.setText("");
        campoConfirmacao.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
    }

}
