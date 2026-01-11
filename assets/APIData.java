package assets;
import org.json.JSONObject;

import assets.Exceptions.*;
import java.time.LocalDate;
import org.json.JSONObject;
import org.json.JSONArray;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;



public class APIData {
    public JSONObject resp;
    //especifica
    private String acesshash="$2a$10$8C8qaqNhJ9HV6cS42hCV9ewD0IjIdtf6oj9Mp1AWcqQhQbbDuhT7G$2a$10$Py6MKyabZP/OjYFTB1FT0uyFDgOCYPNSJCJdn7zz6cny./tZnwwUa";
    //obrigatorio
    private String masterhash="$2a$10$negQhtyqGktYvUMnzrxYLu4NrAYs9BbBDJbrO.TJu/.j9YNL0uCg2";    
    static final String apiUrl = "https://api.jsonbin.io/v3/b/695e5420d0ea881f405aac00";
    private JSONObject data;

    HttpClient client = HttpClient.newHttpClient();
        private void dataMount(String n,String t,String d,int w){
            LocalDate hoje = LocalDate.now();
            JSONObject add = new JSONObject();
            add.put("nome",n);
            add.put("titulo",t);
            add.put("descricao",d);
            add.put("warn",w);
            add.put("data",hoje.toString());
            System.out.println(this.resp.getJSONObject("record"));
            JSONArray dataobj = new JSONArray(this.resp.getJSONObject("record").getJSONArray("data"));
            if (dataobj.length() == 0){
                add.put("id",1);
            }else {
                JSONObject lastObj = new JSONObject(dataobj.get(dataobj.length()-1).toString());
                int lastid = lastObj.getInt("id");
                add.put("id", lastid +1);
            }
            this.data = this.resp;
            this.data.getJSONObject("record").getJSONArray("data").put(add); // VAI MONTAR OS DADOS NESCESSÁRIO PARA ENVIAR PARA API 
        }
        public int get () throws Exception,ConectException { //VAI COLETAR OS DADOS DA API
                HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET() 
                .header("Accept", "application/json")
                .header("X-Master-Key",masterhash)
                .header("X-Access-Key",acesshash)
                .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                this.resp = new JSONObject(response.body());
                if (response.statusCode() == 200){
                     System.out.println("Conexão bem Sucedida");
                    return 200;
                } else {
                    throw new ConectException("Erro de conexão");
                }
        }
        public void add (String n,String t,String d, int w) throws Exception,ConectException {            
            dataMount(n,t, d, w);

            HttpRequest requestput = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .PUT(BodyPublishers.ofString(data.getJSONObject("record").toString())) 
                .header("Content-Type", "application/json")
                .header("X-Master-Key",masterhash)
                .header("X-Access-Key",acesshash)
                .build();

            HttpResponse<String> response = client.send(requestput, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                    System.out.println("Envio bem sucedido");
            } else {
                throw new ConectException("erro ao enviar os dados");
            }


        }
        public void delete(int i) throws ConectException,Exception,DataException {
            excludeMout(i);

            HttpRequest requestput = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .PUT(BodyPublishers.ofString(data.getJSONObject("record").toString())) 
                    .header("Content-Type", "application/json")
                    .header("X-Master-Key",masterhash)
                    .header("X-Access-Key",acesshash)
                    .build();

                    HttpResponse<String> response = client.send(requestput, HttpResponse.BodyHandlers.ofString());
                    if (response.statusCode() == 200){
                        System.out.println("Delete bem sucedido");
                     } else {
                        throw new ConectException("erro ao enviar os dados");
                    }
        }
        private void excludeMout(int i) throws DataException{
            int id =i;
            boolean finded = false;
            this.data = this.resp;
            JSONArray dataarr =  new JSONArray(this.resp.getJSONObject("record").getJSONArray("data"));
            for (int x = 0; x<dataarr.length();x++){
                JSONObject indexOf = new JSONObject(dataarr.get(x).toString());

                if (indexOf.getInt("id") == id){
                    finded = true;
                    this.data.getJSONObject("record").getJSONArray("data").remove(x);
                }
            }
            if (finded == false){
                throw new DataException("Item não encontrado");
            }
        }
}
