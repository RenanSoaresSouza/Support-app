package Interface;

import javax.swing.*;

import assets.APICad;
import assets.Exceptions.ConectException;
import assets.Exceptions.LoginException;

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
        panel.setBackground(new Color(255, 230, 255));
        panel.setLayout(null);

        setContentPane(panel);
    }
}

public class Login extends Template {
    private APICad api = new APICad();
    protected JTextField campoLogin;
    protected JPasswordField campoSenha;
    protected JLabel Error;
    protected String tipo;

    public Login(String tipo) {
        super();
        setTitle("Login");
        this.tipo = tipo;
        paineldeLogin(this.tipo);
    }

    protected void paineldeLogin(String tipo) {
        PainelArredondado painelLogin = new PainelArredondado();
        painelLogin.setBounds(170, 60, 260, 220);
        painelLogin.setBackground(new Color(240, 240, 240));
        painelLogin.setLayout(null);

        JLabel login = new JLabel("Login");
        login.setBounds(110, 10, 50, 20);
        login.setFont(new Font("Arial", Font.PLAIN, 19));
        login.setForeground(Color.WHITE);
        painelLogin.add(login);

        JLabel usuario = new JLabel(tipo);
        usuario.setBounds(60, 40, 140, 20);
        usuario.setFont(new Font("Arial", Font.PLAIN, 11));
        usuario.setForeground(Color.WHITE);
        painelLogin.add(usuario);

        campoLogin = new CampoArredondado();
        campoLogin.setBounds(60, 70, 140, 30);
        campoLogin.setFont(new Font("Arial", Font.PLAIN, 10));

        painelLogin.add(campoLogin);

        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setBounds(60, 105, 140, 20);
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 11));
        labelSenha.setForeground(Color.WHITE);
        painelLogin.add(labelSenha);

        campoSenha = new SenhaArredondada();
        campoSenha.setBounds(60, 130, 140, 30);
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 12));

        painelLogin.add(campoSenha);

        JButton ncadastro = new JButton("Clique aqui para criar uma conta");
        ncadastro.setBackground(new Color(170,170,170));
        ncadastro.setForeground(Color.BLACK);
        ncadastro.setBounds(40,180,180,20);
        ncadastro.setFont(new Font("Arial",Font.BOLD,8));
        ncadastro.setForeground(new Color(240, 240, 240));     // Cor azul de link
        ncadastro.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Faz aparecer a "mãozinha" do mouse
        ncadastro.setContentAreaFilled(false);               // Remove o fundo cinza de botão
        ncadastro.setFocusPainted(false);
        ncadastro.setBorderPainted(false);
        ncadastro.setVisible(false);

        if(tipo.equals("Usuario")){
            ncadastro.setVisible(true);
            ncadastro.addActionListener(e -> {
                Cadastro novoCadastro = new Cadastro();
                novoCadastro.setVisible(true);
                disable();
            });
        }

        painelLogin.add(ncadastro);

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
            TelaCarregamento aguarde = new TelaCarregamento(this, "Validando Acesso...");

            Timer timer = new Timer(1370, e -> {
                aguarde.dispose();
            });
            timer.setRepeats(false);
            timer.start();

            aguarde.setVisible(true);
            
            if (tipo == "Usuario"){
                try {
                    api.Login(usuario,senha,"U");
                    Tela_Cliente TelaUser =new Tela_Cliente(usuario);
                    TelaUser.setVisible(true);
                    dispose();
                }catch (ConectException e){
                	ExibirErros.exibir("Erro de Conexao");
                }catch(LoginException e) {
                	ExibirErros.exibir(e.getLocalizedMessage());
                }catch(Exception e) {
                    System.out.println(e);
                	ExibirErros.exibir("Erro desconhecido");
                }
            } else {
                try {
                    api.Login(usuario,senha,"F");
                    Tela_Funcionarios TelaFun = new Tela_Funcionarios(usuario);
                    TelaFun.setVisible(true);
                    dispose(); //fecha o Tela_Cliente
                }catch (ConectException e){
                	ExibirErros.exibir("Erro de Conexao");
                }catch(LoginException e) {
                	ExibirErros.exibir(e.getLocalizedMessage());
                }catch(Exception e) {
                    System.out.println(e);
                	ExibirErros.exibir("Erro desconhecido");
                } 
            }
        }

    }
}

