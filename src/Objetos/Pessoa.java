package Objetos;

import java.util.Scanner;

public class Pessoa {
    protected String nome;
    protected int idade;
    protected String telefone;
    protected String email;

    public Pessoa(){
        Scanner leitor = new Scanner(System.in);
        this.nome = leitor.nextLine();
        this.idade = leitor.nextInt();
        this.telefone = leitor.nextLine();
        this.email = leitor.nextLine();

        leitor.close();
    }

    public Pessoa(String nome, int idade, String telefone, String email){
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
    }
}
