package Interface;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.JPopupMenu.Separator;

public class Tela_Cliente extends Template {
	public Tela_Cliente() {
		super();
		setTitle("Tela Cliente");
		painelCliente();
	}
	
	public void painelCliente() {
		PainelArredondado painelCliente = new PainelArredondado();
		painelCliente.setBounds(25,25, 550, 350);
		painelCliente.setLayout(null);
		
		JLabel usuario = new JLabel("Nome do Usuario");
		usuario.setBounds(50,30,140,60);
		usuario.setFont(new Font("Arial",Font.BOLD,12));
		usuario.setForeground(Color.BLACK);
		usuario.setBackground(null);
		painelCliente.add(usuario);
		
		BotaoArredondado novoPedido = new BotaoArredondado("Novo Pedido");
		novoPedido.setBounds(400,25,120,60);
		novoPedido.setFont(new Font("Arial", Font.PLAIN, 12));
        novoPedido.setForeground(Color.WHITE);
        novoPedido.setBackground(new Color(25, 25, 112));
        novoPedido.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                novoPedido.setBackground(new Color(93, 23, 139)); // Cor quando o mouse entra
            }
            @Override
            public void mouseExited(MouseEvent e) {
                novoPedido.setBackground(new Color(25, 25, 112)); // Cor original quando o mouse sai
            }
        });


        novoPedido.addActionListener(e ->{
            Pedido fazerPedido = new Pedido();
            fazerPedido.setVisible(true);
            dispose(); //fecha o Tela_Cliente
        });
		painelCliente.add(novoPedido);
		
		JPanel separador = new JPanel();
		separador.setBounds(25,100,500,1);
		separador.setBackground(new Color(200,200,200));
		painelCliente.add(separador);
		
		PainelArredondado area_de_pedidos = new PainelArredondado();
		area_de_pedidos.setDesenharFundo(false);
		area_de_pedidos.setLayout(new BoxLayout(area_de_pedidos,BoxLayout.Y_AXIS));
		
		JScrollPane scroll = new JScrollPane(area_de_pedidos);
		scroll.setBorder(null);
		scroll.setBounds(25,110,500,200);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		
		painelCliente.add(scroll);
		
		//fazer variavel de qtd de pedidos e puxar da API pra ca
		for(int i = 1;i < 6;i++) {
			JPanel painelPedidos = new JPanel();
			painelPedidos.setPreferredSize(new Dimension(480, 60));
			painelPedidos.setMaximumSize(new Dimension(480, 60));
			painelPedidos.setBackground(Color.WHITE);
			painelPedidos.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
			painelPedidos.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 20)); 
			painelPedidos.add(new JLabel("Pedido n" + i));			
			painelPedidos.add(new JLabel("Titulo Placeholder"));
			painelPedidos.add(new JLabel("Status: Placeholder"));
			painelPedidos.add(new JLabel("Data: xx/xx/xxxx"));
			
			
			area_de_pedidos.add(painelPedidos);
			area_de_pedidos.add(Box.createVerticalStrut(10));
			
		}
		
		panel.add(painelCliente);
	}
	
}
