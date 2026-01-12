package Interface;

import javax.swing.*;
import java.awt.*;


public class Custom {

}


class BotaoArredondado extends JButton {
    public BotaoArredondado(String texto) {
        super(texto);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        super.paintComponent(g);
        g2.dispose();
    }
}

class PainelArredondado extends JPanel {
    private boolean desenharFundo = true;

    public PainelArredondado() {
        setOpaque(false);
    }

    public void setDesenharFundo(boolean desenhar) {
        this.desenharFundo = desenhar;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (desenharFundo) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // CORES PARA 2026 (Azul Céu para Roxo Médio)
            Color azulClaro = new Color(135, 206, 250); // Sky Blue
            Color roxoNormal = new Color(180, 100, 255); // Roxo vibrante, não muito escuro

            // Cria o degradê vertical (Cima para Baixo)
            GradientPaint gp = new GradientPaint(0, 0, azulClaro, 0, getHeight(), roxoNormal);

            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            g2.dispose();
        }
        super.paintComponent(g);
    }
}


class CampoArredondado extends JTextField {
    public CampoArredondado() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); // Margem interna do texto
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE); // Fundo branco da caixinha
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Curvatura 15
        super.paintComponent(g);
        g2.dispose();
    }
}

class SenhaArredondada extends JPasswordField {
    public SenhaArredondada() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g);
        g2.dispose();
    }
}


class TextAreaArredondada extends JScrollPane {
    private JTextArea textArea;

    public TextAreaArredondada(int rows, int cols) {
        textArea = new JTextArea(rows, cols);
        textArea.setOpaque(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        setViewportView(textArea);
        setOpaque(false);
        getViewport().setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g);
        g2.dispose();
    }
}

class TelaCarregamento extends JDialog {
    public TelaCarregamento(JFrame pai, String msg) {
        super(pai, true);
        setUndecorated(true);
        setSize(300, 100);
        setLocationRelativeTo(pai);
        setBackground(new Color(0, 0, 0, 0)); // Mantém transparência para o arredondamento

        // Painel com cor sólida escura para destacar do fundo rosa/roxo
        PainelArredondado painel = new PainelArredondado() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fundo Grafite Escuro para dar contraste total
                g2.setColor(new Color(40, 40, 45));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // Borda fina em Azul Claríssimo para brilhar
                g2.setColor(new Color(200, 230, 255));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 30, 30);

                g2.dispose();
            }
        };
        painel.setLayout(null);

        // Texto em Azul Claríssimo
        JLabel l = new JLabel(msg, SwingConstants.CENTER);
        l.setBounds(0, 25, 300, 20);
        l.setFont(new Font("Arial", Font.BOLD, 14));
        l.setForeground(new Color(200, 230, 255));
        painel.add(l);

        // Barra de progresso animada
        JProgressBar barra = new JProgressBar();
        barra.setIndeterminate(true);
        barra.setBounds(50, 60, 200, 6);
        barra.setBorderPainted(false);
        barra.setBackground(new Color(60, 60, 65)); // Fundo da barra um pouco mais claro que o painel
        barra.setForeground(new Color(180, 100, 255)); // Roxo vibrante na animação
        painel.add(barra);

        setContentPane(painel);
    }
}


