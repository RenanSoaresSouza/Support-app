package Interface;

import javax.swing.*;

import assets.APICad;
import assets.Exceptions.*;

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
    private APICad api = new APICad();

    public Cadastro(){
        super();
        setTitle("Cadastro");
        panel.setBackground(new Color(255, 230, 255));
        paineldeCadastro();
    }

    private void paineldeCadastro(){
        PainelArredondado telaCadastro = new PainelArredondado();
        telaCadastro.setBounds(50, 10, 500, 340);
        telaCadastro.setLayout(null);

        JLabel Cadastre_se = new JLabel("Cadastre-se!");
        Cadastre_se.setBounds(210,5,200,100);
        Cadastre_se.setFont(new Font("Arial", Font.PLAIN,20));
        Cadastre_se.setForeground(Color.WHITE);
        telaCadastro.add(Cadastre_se);

        JLabel nome = new JLabel("Nome : ");
        nome.setBounds(100,80,60,20);
        nome.setFont(new Font("Arial", Font.PLAIN, 12));
        nome.setForeground(Color.WHITE);
        telaCadastro.add(nome);

        campoNome = new CampoArredondado();
        campoNome.setBounds(160,80,220,25);
        campoNome.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoNome);

        JLabel senha = new JLabel("Senha :");
        senha.setBounds(100,120,60,20);
        senha.setFont(new Font("Arial", Font.PLAIN, 12));
        senha.setForeground(Color.WHITE);
        telaCadastro.add(senha);

        campoSenha = new SenhaArredondada();
        campoSenha.setBounds(160,120,220,25);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoSenha);

        JLabel confirmacao = new JLabel("Confirme sua senha :");
        confirmacao.setBounds(35,160,120,20);
        confirmacao.setFont(new Font("Arial", Font.PLAIN, 12));
        confirmacao.setForeground(Color.WHITE);
        telaCadastro.add(confirmacao);

        campoConfirmacao = new SenhaArredondada();
        campoConfirmacao.setBounds(160,160,220,25);
        campoConfirmacao.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoConfirmacao);

        JLabel telefone = new JLabel("Telefone(opcional) :");
        telefone.setBounds(40,200,120,20);
        telefone.setFont(new Font("Arial", Font.PLAIN, 12));
        telefone.setForeground(Color.WHITE);
        telaCadastro.add(telefone);

        campoTelefone = new CampoArredondado();
        campoTelefone.setBounds(160,200,220,25);
        campoTelefone.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoTelefone);

        JLabel email = new JLabel("Email(opcional) :");
        email.setBounds(55,240,120,20);
        email.setFont(new Font("Arial", Font.PLAIN, 12));
        email.setForeground(Color.WHITE);
        telaCadastro.add(email);

        campoEmail = new CampoArredondado();
        campoEmail.setBounds(160,240,220,25);
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 11));
        telaCadastro.add(campoEmail);

        Error = new JLabel();
        Error.setBounds(110,262,300,20);
        Error.setFont(new Font("Arial", Font.BOLD,11));
        Error.setForeground(Color.RED);
        Error.setVisible(false);
        telaCadastro.add(Error);

        BotaoArredondado enviar = new BotaoArredondado("CADASTRAR");
        enviar.setBounds(210,280,120,40);
        enviar.setFont(new Font("Arial",Font.PLAIN,11));
        enviar.setBackground(Color.WHITE);
        enviar.setForeground(new Color(65, 105, 225));

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
        if(senha.isEmpty()){
            Error.setText("Insira uma senha");
            Error.setVisible(true);
            return;
        }
        if(senha.length() < 8 && senha.length() != 0 ) {
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
            telefone = "Vazio";
        }
        if(email.isEmpty()){
            email = "Vazio";
        }

        Error.setVisible(false);

        limparDados();
        
        try {
            api.Cadastro(nome, senha, confirmacao,"U", email, telefone);;
            EscolhaLogin tela = new EscolhaLogin();
            tela.setVisible(true);
            dispose();
        }catch (ConectException e){
        	ExibirErros.exibir("Erro de Conexao");
        }catch(LoginException e) {
        	ExibirErros.exibir("Usuario e/ou Senhas já cadastrados");
        }catch(Exception e) {
        	ExibirErros.exibir("Erro desconhecido");
        }
    }

    public void limparDados(){
        campoNome.setText("");
        campoSenha.setText("");
        campoConfirmacao.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
    }

}
