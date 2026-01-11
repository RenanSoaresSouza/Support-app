package Objetos;

import org.json.JSONObject;

import assets.APIData;

public class Funcionario extends Pessoa{
    private APIData api = new APIData();
    private JSONObject data;
    private int id;
    private String Usuario;
    private String Senha;


    public void verPedidos(){
        try {
            api.get();
            data = api.resp;
            
        } catch (Exception e){
            System.out.println(e);
        }
    }
  
    public void concluirPedido(){

    }

}
