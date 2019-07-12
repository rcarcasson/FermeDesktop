/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author v-carica
 */
public class Funciones {
    public String Encriptar(String clave){
        String sha1 = "";
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(clave.getBytes("utf8"));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        }catch (Exception err){
            err.printStackTrace();
        }
        return sha1;
    }
    
    public String ObtenerClave(char[] clave){
        String clavefinal="";
        for (int i=0; i<clave.length;i++){
            clavefinal = clavefinal + clave[i];
        }
        return clavefinal;
    }
    
    public boolean ValidarRut(String rut){
        boolean validado = false;
        try{
            rut = rut.toUpperCase();
            rut = rut.replace(".","");
            rut = rut.replace("-","");
            int aux = Integer.parseInt(rut.substring(0,rut.length()-1));
            
            char verificador = rut.charAt(rut.length() -1);
            
            int m = 0, s = 1;
            for (; aux != 0; aux /= 10){
                s = (s + aux % 10 * (9 - m++ % 6)) % 11;
            }
            
            if (verificador == (char) (s != 0 ? s + 47 : 75)){
                validado = true;
            }
        }catch (java.lang.NumberFormatException e){
        }
        return validado;
    }
    
    public boolean ValidarMail(String mail){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher matcher = pattern.matcher(mail);
        
        if (matcher.find()==true){
            return true;
        }else{
            return false;
        }
    }
}
