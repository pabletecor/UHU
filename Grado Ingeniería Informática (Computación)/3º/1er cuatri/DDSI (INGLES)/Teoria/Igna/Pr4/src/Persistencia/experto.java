package Persistencia;

/**
 * Experto
 */

public class experto {
    
    private String codExperto;
    private String nombre;
    private String pais;
    private String sexo;
    private String especialidad;
	   
    /**
    * Constructor por defecto
    */
    public experto() {
        codExperto=null;
	nombre=null;
	pais=null;
	sexo=null;
	especialidad=null;
  
    }
	            
    /**
    * Constructor con parametros 
    * 
    * @param c cadena que será el código del experto
    * @param n cadena que será el nombre del experto
    * @param p cadena que será el pais del experto
    * @param s cadena que será el sexo del experto
    * @param e cadena que será la especialidad del experto
    */    	
    public experto(String c, String n, String p, String s, String e) {
        codExperto = c;
        nombre = n;
        pais = p;
        sexo = s;
        especialidad = e;
    }		

    /**
     * Devuelve el código del experto que lo invoca
     * @return codExperto
     */
    public String getCodExperto() {
        return codExperto;
    }

    /**
     * Devuelve el nomnre del experto que lo invoca
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
	
    /**
     * Devuelve el país del experto que lo invoca
     * @return pais
     */
    public String getPais() {
        return pais;
    }
	
    /**
     * Devuelve el sexo del experto que lo invoca
     * @return sexo
     */	
    public String getSexo() {
        return sexo;
    }
  
    /**
     * Devuelve la especialidad del experto que lo invoca
     * @return especialidad
     */
    public String getEspecialidad() {
        return especialidad;
    }
	
    /**
     * Asigna un código pasado por parámetro al experto que lo invoca
     * @param c cadena con el código a asignar
     */	
    public void setCodExperto(String c) {
        codExperto = c;
    }
    
    /**
     * Asigna un nombre pasado por parámetro al experto que lo invoca
     * @param n cadena con el nombre a asignar
     */	
    public void setNombre(String n) {
        nombre = n;
    }
    
    /**
     * Asigna un país pasado por parámetro al experto que lo invoca
     * @param  p con el país a asignar
     */	
    public void setPais(String p) {
        pais = p;
    }
    
    /**
     * Asigna un sexo pasado por parámetro al experto que lo invoca
     * @param s cadena con el país a asignar
     */	
    public void setSexo(String s) {
        sexo = s;
    }
    
    /**
     * Asigna una especialidad pasada por parámetro al experto que lo invoca
     * @param e cadena con la especialidad a asignar
     */	
    public void setEspecialidad(String e) {
        especialidad = e;
    }
}