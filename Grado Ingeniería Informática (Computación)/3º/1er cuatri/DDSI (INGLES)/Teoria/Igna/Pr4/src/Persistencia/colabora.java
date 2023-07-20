package Persistencia;

/**
 * Colabora 
 */

public class colabora {
    private String codExperto;
    private String codCaso;
    private String fecha;
    private String descripcionColaboracion;
	
    
    /**
   * Constructor por defecto   
    */
    public colabora() 
    {
	this.codCaso = null;
        this.codExperto = null;
        this.fecha = null;
        this.descripcionColaboracion = null;
    }
    
    /** Constructor con parametros 
    * 
    * @param cc cadena que será el código del caso
    * @param ce cadena que será el código del experto
    * @param f cadena que será la fecha de la colaboración
    * @param dc cadena que será la descripción de la colaboración
    */ 
    public colabora(String ce,String cc,String f, String dc) 
    {
        this.codExperto = ce;
	this.codCaso = cc;
        this.fecha = f;
        this.descripcionColaboracion = dc;
    }
	 
    /**
     * Devuelve el código del experto de la colaboración que lo invoca
     * @return codExperto
     */     
    public String getCodExperto(){
      return this.codExperto;
    }
    
    /**
     * Devuelve el código del caso de la colaboración que lo invoca
     * @return codCaso
     */
    public String getCodCaso(){
      return this.codCaso;
    }
    
    /**
     * Devuelve la fecha de la colaboración que lo invoca
     * @return fecha
     */
    public String getFecha(){
        return this.fecha;
    }
    /**
     * Devuelve la descripcion de la colaboración que lo invoca
     * @return descripcionColaboracion
     */
    public String getDescripcionColaboracion(){
       return this.descripcionColaboracion;
    }
    
    /**
     * Asigna un código pasado por parámetro al experto de la colaboración que lo invoca
     * @param ce cadena con el codigo a asignar
     */	
    public void setCodExperto(String ce){
      this.codExperto=ce;
    }
    
    /**
     * Asigna un código pasado por parámetro al caso de la colaboración que lo invoca
     * @param cc cadena con el codigo a asignar
     */	
    public void setCodcaso(String cc){
      this.codCaso=cc;
    }
    
    /**
     * Asigna una fecha a la colaboración que lo invoca
     * @param f cadena con la fecha a asignar
     */	
    public void setFecha(String f){
        this.fecha=f;
    }
    
    /**
     * Asigna una descripción a la colaboración que lo invoca
     * @param dc cadena con la fecha a asignar
     */	
    public void setDescripcionColaboracion(String dc){
        this.descripcionColaboracion=dc;
    }
}
