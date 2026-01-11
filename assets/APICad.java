
package assets;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import assets.Exceptions.*;

import org.json.*;

import assets.Exceptions.ConectException;
public class APICad {
    private String acesshash="$2a$10$8C8qaqNhJ9HV6cS42hCV9ewD0IjIdtf6oj9Mp1AWcqQhQbbDuhT7G$2a$10$Py6MKyabZP/OjYFTB1FT0uyFDgOCYPNSJCJdn7zz6cny./tZnwwUa";
    private String masterhash="$2a$10$negQhtyqGktYvUMnzrxYLu4NrAYs9BbBDJbrO.TJu/.j9YNL0uCg2";    
    static final String apiUrl = "https://api.jsonbin.io/v3/b/695ec72443b1c97be9204449";
    private String user;
    private String password;
    private String telefone;
    private String email;
    private String Type;
    JSONObject resp;
    public String data;

    HttpClient client = HttpClient.newHttpClient();
    private void CadApi()throws Exception{
            JSONObject data = new JSONObject(this.resp.toString()); 
            data = data.getJSONObject("record");
            
            JSONObject cad = new JSONObject();
            cad.put("user", this.user);
            cad.put("password", this.password);
            cad.put("type",this.Type);
            cad.put("Telefone",this.telefone);
            cad.put("Email",this.email);
            
            data.getJSONArray("data").put(cad);

            HttpRequest requestput = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .PUT(BodyPublishers.ofString(data.toString())) 
                .header("Content-Type", "application/json")
                .header("X-Master-Key",masterhash)
                .header("X-Access-Key",acesshash)
                .build();

            HttpResponse<String> response = client.send(requestput, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                    System.out.println("Cadastro bem sucedido");
            } else {
                throw new ConectException("erro ao Cadastrar os dados");
            }
    }
    public void Cadastro(String u,String p,String rp,String T,String em, String tel) throws Exception{
        if (p.equals(rp) && !(u.isBlank()||p.isBlank())){
            boolean finded = false;
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
                JSONArray loginarr = new JSONArray(resp.getJSONObject("record").getJSONArray("data"));
                for (int x=0;x<loginarr.length();x++){
                    JSONObject loginobj = new JSONObject(loginarr.get(x).toString());
                    if (loginobj.getString("password").equals(p) || loginobj.getString("user").equals(u)){
                        finded = true;
                        throw new LoginException("usuário e/ou senhas já existentes");
                    }
                }
                if (finded == false){
                    this.user = u;
                    this.password = p;
                    this.Type = T;
                    this.email = em;
                    this.telefone = tel;
                    CadApi();
                }

            } else {
                throw new ConectException("Erro de conexão");
            }
        } else {
            throw new LoginException("as senhas não são iguais");
        }
    }
    public void Login(String u,String p,String T) throws Exception{ if (u.isBlank() || p.isBlank()){
                throw new LoginException("Senha e/ou Usuários não podem ficar brancos");
            } else {
                boolean finded = false;
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
                JSONArray loginarr = new JSONArray(resp.getJSONObject("record").getJSONArray("data"));
                for (int x=0;x<loginarr.length();x++){
                    JSONObject loginobj = new JSONObject(loginarr.get(x).toString());
                    if (loginobj.getString("password").equals(p) && loginobj.getString("user").equals(u) && loginobj.getString("type").equals(T)){
                        finded = true;
                        System.out.println("Login Efetuado com Sucesso");
                        this.data = loginarr.get(x).toString();
                    }
                }
                if (finded == false){
                    throw new LoginException("Usuario e/ou senha incorreta");
                }

            } else {
                throw new ConectException("Erro de conexão");
            }
            }

    }
}
