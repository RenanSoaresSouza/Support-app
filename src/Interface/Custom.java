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
