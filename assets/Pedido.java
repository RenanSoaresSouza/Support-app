package assets;

public class Pedido {
    private String nome;
    private int idade;
    private String titulo;
    private String desc;
    public static int quant=0;

    public Pedido(String n, int i,String t,String d){
        this.nome = n;
        this.idade = i;
        this.titulo = t;
        this.desc = d;
        quant++;
    }
}
