package Persistencia;
/**
 * Caso 
 */
public class caso {
    private String codCaso;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
	
   /**
   * Constructor por defecto   
    */
    public caso() {
	this.codCaso = null;
        this.nombre = null;
        this.fechaFin = null;
        this.fechaInicio = null;
    }
    

    /** Constructor con parametros 
    * 
    * @param cc cadena que será el código del caso
    * @param n cadena que será el nombre del caso
    * @param fi cadena que será la fecha de inicio del caso
    * @param ff cadena que será la fecha de finalización del caso
    */ 
    public caso(String cc,String n, String fi, String ff) {
	this.codCaso = cc;
        this.nombre = n;
        this.fechaInicio = fi;
        this.fechaFin = ff;
    }
	
    /**
     * Devuelve el código del caso que lo invoca
     * @return codCaso
     */
    public String getCodCaso(){
      return codCaso;
    }	
	
    /**
     * Devuelve el nombre del caso que lo invoca
     * @return nombre
     */
    public String getNombre(){
    return nombre;
    }
    
    /**
     * Devuelve la fecha de inicio del caso que lo invoca
     * @return fechaInicio
     */
    public String getFechaInicio(){
       return fechaInicio;
    }
    /**
     * Devuelve la fecha de finalización del caso que lo invoca
     * @return fechaFin
     */
    public String getFechaFin(){
        return fechaFin;
    }

    /**
     * Asigna un código pasado por parámetro al caso que lo invoca
     * @param c cadena con el codigo a asignar
     */	
    public void setCodCaso(String c){
       this.codCaso = c;
    }

    /**
     * Asigna un nombre pasado por parámetro al caso que lo invoca
     * @param n cadena con el nombre a asignar
     */	
    public void setNombre(String n){
     this.nombre = n;
    }
    
    /**
     * Asigna una fecha de inicio pasada por parámetro al caso que lo invoca
     * @param fi cadena con la fecha a asignar
     */	
    public void setFechaInicio(String fi){
       this.fechaInicio = fi;
    }
	  
    /**
     * Asigna una fecha de finalización pasada por parámetro al caso que lo invoca
     * @param ff cadena con la fecha a asignar
     */	     
    public void setFechaFin(String ff){
       this.fechaFin = ff;
    }
	    
}
