

import org.json.JSONObject;
import assets.*;
import assets.Exceptions.ConectException;
import assets.Exceptions.DataException;
public class Main {
    public static void main(String[] argv){
           API conex = new API();
           
        try {
            conex.get();
        } catch (Exception e){
            System.out.println(e);
        } finally {
           JSONObject resp = new JSONObject(conex.resp.toString());
           
           
        }
        try{
            conex.delete(2);
        } catch (ConectException e){

        } catch (DataException x){
            System.out.println(x);
        } catch (Exception y){

        }
        
    }
    
}