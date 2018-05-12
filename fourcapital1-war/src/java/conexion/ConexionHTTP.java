/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ConexionHTTP {
    URL url;
    InputStreamReader in;
    HttpURLConnection con;
    BufferedReader br;
    Gson gs;
    public <T> T conectarServicio(String url,String parametro,Type resp){
        //http://localhost:8080/fourcapitalservice/webresources/entidades.cliente/
        int code;
        String recurso=url+"/"+parametro;
        String linea,json="";
        
        try {
            this.url=new URL(recurso);
            con=(HttpURLConnection)this.url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            code=con.getResponseCode();
            if(code==200){
                in= new InputStreamReader(con.getInputStream());
                br=new BufferedReader(in);
                while((linea=br.readLine())!=null){
                json+=linea;
                }
                in.close();
                br.close();
            }
            gs=new Gson();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ConexionHTTP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConexionHTTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            con.disconnect();
        }
    return gs.fromJson(json,resp);
    }
}
