package Interface;

import javax.swing.*;
import javax.swing.text.MaskFormatter;


import assets.APIData;
import assets.Exceptions.ConectException;
import assets.Exceptions.DataException;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

public class Pedido extends Template {
    private JComboBox<Prioridades> tipoPrioridade = new JComboBox<>(Prioridades.values());
    private JTextArea campoDescricao = new JTextArea();
	private JLabel Error = new JLabel();
    private APIData api = new APIData();
    private String user;
    
    public Pedido(String u) {
       super();
       this.user = u;
       telaPedido();
       
    }
    
    public void telaPedido() {
    	PainelArredondado interfacePedido = new PainelArredondado();
    	interfacePedido.setBounds(40,25, 520, 300);
		interfacePedido.setLayout(null);
		
		JPanel gridPedidos = new JPanel();
		gridPedidos.setLayout(new GridLayout(3,2,10,10));
		gridPedidos.setOpaque(false);
		gridPedidos.setBounds(50, 50, 450, 200);

        JLabel labelTitulo = new JLabel("Título");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 17));
        labelTitulo.setForeground(Color.WHITE);
        gridPedidos.add(labelTitulo);


        gridPedidos.add(tipoPrioridade);

        JLabel lblDesc = new JLabel("Descrição");
        lblDesc.setFont(new Font("Arial", Font.BOLD, 17));
        lblDesc.setForeground(Color.WHITE);
        gridPedidos.add(lblDesc);

        TextAreaArredondada scrollCustom = new TextAreaArredondada(5, 20);
        campoDescricao = scrollCustom.getTextArea();
        gridPedidos.add(scrollCustom);

        BotaoArredondado Envio = new BotaoArredondado("Enviar Pedido");
        Envio.setBackground(Color.WHITE);
        Envio.setForeground(new Color(200, 230, 255));
        Envio.setFont(new Font("Arial", Font.BOLD, 12));
        Envio.setBackground(new Color(25, 25, 112));
        Envio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Envio.setBackground(new Color(93, 23, 139)); // Cor quando o mouse entra
            }
            @Override
            public void mouseExited(MouseEvent e) {
                Envio.setBackground(new Color(25, 25, 112)); // Cor original quando o mouse sai
            }
        });


        Envio.addActionListener(e -> {
            realizarPedido();
        });
        gridPedidos.add(new JLabel("")); // Espaçador para pular uma coluna
        gridPedidos.add(Envio);

        Error = new JLabel();
        Error.setBounds(330, 250, 200, 40);
        Error.setFont(new Font("Arial", Font.BOLD,11));
        Error.setForeground(Color.RED);
        Error.setVisible(false);
        interfacePedido.add(Error);
        
        interfacePedido.add(gridPedidos);
        panel.add(interfacePedido);
    }
    
    public void realizarPedido() {
    	Prioridades nivelUrgencia = (Prioridades) tipoPrioridade.getSelectedItem();
    	String descricao = campoDescricao.getText();
    	

    	if(descricao.isEmpty()) {
    		Error.setText("Descricao vazia");
    		Error.setVisible(true);
    		return;
    	}
    	Error.setVisible(false);
        try {

            api.get();
            api.add(this.user,nivelUrgencia.toString(), descricao,nivelUrgencia.getUrgencia());
            Tela_Cliente TelaUser =new Tela_Cliente(this.user);
            TelaUser.setVisible(true);
            dispose();
            
        } catch (DataException e){
            System.out.println(e.getLocalizedMessage());
        }catch (ConectException e){
            ExibirErros.exibir("Erro de Conexão");
        } catch (Exception e){
            ExibirErros.exibir("Erro Desconhecido");
        }
    	limpaDados();
    	
    }
    public void limpaDados() {
    	campoDescricao.setText("");
    }
}
