
import org.json.JSONObject;
import assets.*;
public class Main {
    public static void main(String[] argv){
           conection conex = new conection();
           
        try {
            conex.List();
        } catch (Exception e){
            System.out.println(e);
        } finally {
           JSONObject resp = new JSONObject(conex.resp);
           System.out.println(resp.get("record"));
        }
    }
    
}