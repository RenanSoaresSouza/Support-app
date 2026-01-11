package Interface;

public enum Prioridades {
	Problema_de_Sistemas(5,"Problemas de Sistema"),
	Suporte_Residencial(4,"Suporte Residencial"),
	Instalacao(3,"Instalacao"),
	Cancelamento(2,"Cancelamento"),
	Outros(1,"Outros");
    
	private final int urgencia;
	private final String titulo;
	
	Prioridades(int urgencia,String titulo){
		this.urgencia = urgencia;
		this.titulo = titulo;
	}
	
	public int getUrgencia() {return urgencia;}
	
	@Override
	public String toString() { return titulo;}

	String[] tipos = {"Problema de Sistema","Suporte Residencial","Instalacao","Cancelamento","Outros"};
	        
}
