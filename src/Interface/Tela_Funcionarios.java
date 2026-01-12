package Interface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import assets.APIData;
import assets.Exceptions.ConectException;
import assets.Exceptions.DataException;

public class Tela_Funcionarios extends Template{
	APIData api = new APIData();
	private JSONArray data;
	private String user;
	private PainelArredondado area_de_pedidos = new PainelArredondado();
	private JScrollPane scroll = new JScrollPane();
	
	public Tela_Funcionarios(String u) {
		super();
		this.user = u;
		setTitle("Tela Funcionario");
		painelFuncionario();
	}
	
	public void painelFuncionario() {
		PainelArredondado paineLFuncionario = new PainelArredondado();
        paineLFuncionario.setBounds(25,25, 550, 310);
        paineLFuncionario.setBackground(new Color(255, 230, 255));
		paineLFuncionario.setLayout(null);
		
		JLabel funcionario = new JLabel("User: "+user);
		funcionario.setBounds(50,30,140,60);
		funcionario.setFont(new Font("Arial",Font.BOLD,12));
		funcionario.setForeground(Color.BLACK);
		funcionario.setBackground(null);
		paineLFuncionario.add(funcionario);
		
		JPanel separador = new JPanel();
		separador.setBounds(25,100,500,1);
		separador.setBackground(new Color(200,200,200));
		paineLFuncionario.add(separador);
		
		area_de_pedidos = new PainelArredondado();
		area_de_pedidos.setDesenharFundo(false);
		area_de_pedidos.setLayout(new BoxLayout(area_de_pedidos,BoxLayout.Y_AXIS));
		
		JScrollPane scroll = new JScrollPane(area_de_pedidos);
		scroll.setBorder(null);
		scroll.setBounds(25,110,500,200);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		
		paineLFuncionario.add(scroll);

		try {
			api.get();
		} catch (DataException e){
			ExibirErros.exibir(e.getLocalizedMessage());
		} catch (ConectException e){
			ExibirErros.exibir(e.getLocalizedMessage());
		} catch (Exception e){
			ExibirErros.exibir(e.getLocalizedMessage());
		} finally{
			this.data = new JSONArray(api.resp.getJSONObject("record").getJSONArray("data"));
			System.out.println(data);
		}
		
		atualizarPainel();
		
		panel.add(paineLFuncionario);
	}
	
	private JPanel criarPedidoExpansivel(int id) {
		JSONObject info = new JSONObject(this.data.get(id).toString());
		JPanel pedidoCompleto = new JPanel();
		pedidoCompleto.setLayout(new BoxLayout(pedidoCompleto, BoxLayout.Y_AXIS));
		pedidoCompleto.setMaximumSize(new Dimension(480, 1000));
		pedidoCompleto.setBackground(Color.WHITE);
		
		JPanel painelCabecalho = new JPanel();
		painelCabecalho.setPreferredSize(new Dimension(480, 60));
		painelCabecalho.setMaximumSize(new Dimension(480, 60));
		painelCabecalho.setBackground(Color.WHITE);
		painelCabecalho.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
		painelCabecalho.setLayout(new BorderLayout());
		
		JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 20));
		painelInfo.setBackground(Color.WHITE);
		painelInfo.add(new JLabel("Pedido id" + info.getInt("id")));
		painelInfo.add(new JLabel("Titulo: "+ info.getString("titulo")));
		JCheckBox pedidoRealizado = new JCheckBox("Realizado");
		
		pedidoRealizado.addActionListener(e -> {
			int PedidoId = info.getInt("id");
				try {
					api.delete(PedidoId);
					api.get();
				} catch (DataException x){
					ExibirErros.exibir(x.getLocalizedMessage());
				} catch (ConectException x){
					ExibirErros.exibir(x.getLocalizedMessage());
				} catch (Exception x){
					ExibirErros.exibir(x.getLocalizedMessage());
				} finally{
					this.data = new JSONArray(api.resp.getJSONObject("record").getJSONArray("data"));
					System.out.println(data);
					atualizarPainel();
		}
			
		});
		painelInfo.add(pedidoRealizado);
		
		JButton btnSeta = new JButton("▼");
		btnSeta.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSeta.setFocusPainted(false);
		btnSeta.setBorderPainted(false);
		btnSeta.setContentAreaFilled(false);
		btnSeta.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSeta.setMargin(new Insets(0, 10, 0, 10));
		
		JPanel painelDescricao = new JPanel();
		painelDescricao.setLayout(new BorderLayout());
		painelDescricao.setBackground(new Color(250, 250, 250));
		painelDescricao.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(220, 220, 220)),
			BorderFactory.createEmptyBorder(10, 15, 10, 15)
		));
		painelDescricao.setVisible(false);
		
		JTextArea txtDescricao = new JTextArea(info.getString("nome")+" : "+info.getString("data")+ ":\n" +
			info.getString("descricao"));
		txtDescricao.setEditable(false);
		txtDescricao.setBackground(new Color(250, 250, 250));
		txtDescricao.setFont(new Font("Arial", Font.PLAIN, 11));
		txtDescricao.setLineWrap(true);
		txtDescricao.setWrapStyleWord(true);
		painelDescricao.add(txtDescricao, BorderLayout.CENTER);
		
		btnSeta.addActionListener(new ActionListener() {
			private boolean expandido = false;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				expandido = !expandido;
				painelDescricao.setVisible(expandido);
				btnSeta.setText(expandido ? "▲" : "▼");
				pedidoCompleto.revalidate();
				pedidoCompleto.repaint();
			}
		});
		
		painelCabecalho.add(painelInfo, BorderLayout.CENTER);
		painelCabecalho.add(btnSeta, BorderLayout.EAST);
		
		pedidoCompleto.add(painelCabecalho);
		pedidoCompleto.add(painelDescricao);
		
		return pedidoCompleto;
		}
	
	private void atualizarPainel() {
		area_de_pedidos.removeAll();

		for(int i = 0; i < this.data.length();i++) {
			JPanel pedidoCompleto = criarPedidoExpansivel(i);
            area_de_pedidos.add(pedidoCompleto);
            area_de_pedidos.add(Box.createVerticalStrut(10));
		}
		
		area_de_pedidos.revalidate(); //reseta os componentes
		area_de_pedidos.repaint(); //redesenha na tela
	}
}
