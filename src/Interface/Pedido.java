package Interface;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import org.jcp.xml.dsig.internal.dom.ApacheNodeSetData;

import assets.APIData;
import assets.Exceptions.ConectException;
import assets.Exceptions.DataException;

import java.awt.*;
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
    	interfacePedido.setBounds(25,25, 550, 350);
		interfacePedido.setLayout(null);
		
		JPanel gridPedidos = new JPanel();
		gridPedidos.setLayout(new GridLayout(3,2,10,10));
		gridPedidos.setOpaque(false);
		gridPedidos.setBounds(50, 50, 450, 200);
		
        gridPedidos.add(new JLabel("Titulo"));
        tipoPrioridade = new JComboBox<>(Prioridades.values());
        tipoPrioridade.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        tipoPrioridade.setBackground(Color.WHITE);
        tipoPrioridade.setEditable(false);
        
        gridPedidos.add(tipoPrioridade);

        gridPedidos.add(new JLabel("Descrição"));
        campoDescricao = new JTextArea(5, 20); 
        campoDescricao.setLineWrap(true);
        campoDescricao.setWrapStyleWord(true); 
        campoDescricao.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        gridPedidos.add(scrollDescricao);
  
        JButton Envio = new JButton("Enviar Pedido");
        
        Envio.addActionListener(e ->{
        	realizarPedido();
        });
        gridPedidos.add(new JLabel(""));
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
            api.add(this.user,18,nivelUrgencia.toString(), descricao,nivelUrgencia.getUrgencia());
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
