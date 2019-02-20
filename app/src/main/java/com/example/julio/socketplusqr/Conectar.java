package com.example.julio.socketplusqr;


import android.os.StrictMode;
import android.util.Log;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Conectar {
    
    static Connection contacto = null;
    private static String usuario = "Administrador";
    private static String password = "Fcnm2018";
    
    
    public static Connection getConexion(){
        // SET CONNECTIONSTRING
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String url = "jdbc:jtds:sqlserver://192.168.1.9:1433/SistemasFCNM;";

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            contacto = DriverManager.getConnection(url+"user=" + usuario + ";password=" + password);
            Log.w("Connection","open");
        }catch (Exception e){
            Log.w("Error connection","" + e.getMessage());
        }
        return contacto;
    }
    
    
  
    public static ResultSet Consulta(String consulta){
        Connection con = getConexion();
        Statement declara;
        try{
            declara=con.createStatement();
            ResultSet respuesta = declara.executeQuery(consulta);
            return respuesta;
        }catch (SQLException e){
            Log.w("Error connection","" + e.getMessage());
        }
        return null;
    }
    public static void ejecutarTransaccion(String Sql){
       Connection pConectar= getConexion();
       CallableStatement statement = null;
       
       try {
            statement = pConectar.prepareCall(Sql);
            try {
                 statement.execute();
            } catch (SQLException e) {
                 e.printStackTrace();
            }
          } catch (SQLException e){
           Log.w("Error connection","" + e.getMessage());
        }
    }
    //public static void main (String[]args) throws SQLException{
        //ResultSet res = Conectar.Consulta("Select * from Pedidos");
        
        //while (res.next()){
            //System.out.print(res.getString(1) + " ");
            //System.out.print(res.getString(2)+" ");
            //System.out.print(res.getString(3)+" ");
            //System.out.println(res.getString(4));
        //}
    //}
}
