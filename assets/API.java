package assets;

import org.json.JSONObject;
import org.json.JSONArray;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;




public class API {
    public JSONObject resp;
    private String acesshash="$2a$10$8C8qaqNhJ9HV6cS42hCV9ewD0IjIdtf6oj9Mp1AWcqQhQbbDuhT7G$2a$10$Py6MKyabZP/OjYFTB1FT0uyFDgOCYPNSJCJdn7zz6cny./tZnwwUa";
    private String masterhash="$2a$10$negQhtyqGktYvUMnzrxYLu4NrAYs9BbBDJbrO.TJu/.j9YNL0uCg2";    
    static final String apiUrl = "https://api.jsonbin.io/v3/b/695e5420d0ea881f405aac00";
    HttpClient client = HttpClient.newHttpClient();
        public int get () throws Exception { //VAI COLETAR OS DADOS DA API
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
                    throw new Exception("Erro de conexão");
                }
        }
        public void add (String n,int i,String t,String d, int w) throws Exception {
            JSONObject add = new JSONObject();
            add.put("nome",n);
            add.put("idade",i);
            add.put("titulo",t);
            add.put("descricao",d);
            add.put("warn",w);
            System.out.println(this.resp.getJSONObject("record"));
            JSONArray dataobj = new JSONArray(this.resp.getJSONObject("record").getJSONArray("data"));
            if (dataobj.length() == 0){
                add.put("id",1);
            }else {
                JSONObject lastObj = new JSONObject(dataobj.get(dataobj.length()-1).toString());
                int lastid = lastObj.getInt("id");
                add.put("id", lastid +1);
            }
            JSONObject data = this.resp;
            data.getJSONObject("record").getJSONArray("data").put(add); // VAI MONTAR OS DADOS NESCESSÁRIO PARA ENVIAR PARA API 
            

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
                throw new Exception("erro ao enviar os dados");
            }


        }
        public void delete(int i) throws Exception {
            int id =i;
            JSONObject data = this.resp;
            JSONArray dataarr =  new JSONArray(this.resp.getJSONObject("record").getJSONArray("data"));
            for (int x = 0; x<dataarr.length();x++){
                JSONObject indexOf = new JSONObject(dataarr.get(x).toString());
                if (indexOf.getInt("id") == id){
                    int index = dataarr.toList().indexOf(dataarr.toList().get(x));
                        data.getJSONObject("record").getJSONArray("data").remove(index); // VAI MONTAR OS DADOS NESCESSÁRIO PARA ENVIAR PARA API 
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
                                throw new Exception("erro ao enviar os dados");
                            }
                };
            }
        }
}
