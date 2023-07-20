/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holamundo;

/**
 *
 * @author usuario
 */
public class Saluda implements ISaludar {       //Para cambiar el nombre de la clase, señalamos y en refactor hacemos rename

    private String defecto;
    
    public Saluda(String defecto){
        
        this.defecto=defecto;
    
    
    }
    
    /**
     * Este metodo saluda al invocador con el mensaje que se pasa como parametro 
     * @param mensaje
     * @param n
     * @throws Exception 
     */
    @Override 
    public void saludo(String mensaje,int n) throws Exception {
        if(mensaje==null){
            throw new Exception("No se puede pasar un null");
        
        }
        else{
        
        System.out.println(mensaje);
        
        }

    }

   
    
}
                            //Para poner el codigo bonito: Source format