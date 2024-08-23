/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Ricardo
 */
public class conexion {
    
    //cariable estática para almacenar la conexión
    private static Connection conn;
    
    // método de conexión
    
      static Connection getConexion(){
          
          
          
          // por medio del try se intenta establecer la conexión 
          try{
              conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectoCitas","root","root9126*");
          }catch(Exception e){
              System.out.println(e.toString());
              conn = null;
          
         }
          
          return conn;
      
      }
    
    
}
