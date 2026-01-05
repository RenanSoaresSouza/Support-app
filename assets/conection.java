package assets;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;




public class conection {
    public String resp;
    private String acesshash="$2a$10$8C8qaqNhJ9HV6cS42hCV9ewD0IjIdtf6oj9Mp1AWcqQhQbbDuhT7G$2a$10$Py6MKyabZP/OjYFTB1FT0uyFDgOCYPNSJCJdn7zz6cny./tZnwwUa";
    private String masterhash="$2a$10$negQhtyqGktYvUMnzrxYLu4NrAYs9BbBDJbrO.TJu/.j9YNL0uCg2";
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(apiUrl))
        .GET() 
        .header("Accept", "application/json") // Variavel de requisição
        .header("X-Master-Key",masterhash)
        .header("X-Access-Key",acesshash)
        .build();    

    static final String apiUrl = "https://api.jsonbin.io/v3/b/695c2ff1d0ea881f4056b027";
    HttpClient client = HttpClient.newHttpClient();
        public void List () throws Exception {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                this.resp = response.body();
                if (response.statusCode() == 200){
                    throw new Exception("Conexão bem Sucedida");
                } else {
                    throw new Exception("Erro de conexão");
                }
        }
}
