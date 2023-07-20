package Persistence;

/**
 * Collaborates 
 */

public class Collaborates {
    private String codEXPERT;
    private String codCase;
    private String dateC;
    private String descripcion;
	
    
    /**
   * Default constructor  
    */
    public Collaborates() 
    {
	// TODO add code
        this.codEXPERT = null;
        this.codCase= null;
        this.dateC=null;
        this.descripcion=null;
    }
    
     /**
   * Constructor with parameters
   * @ param ce, cc, f, dc
    */
    public Collaborates(String ce,String cc,String dc, String d) 
    {
	// TODO add code
        this.codEXPERT=ce;
        this.codCase=cc;
        this.dateC=dc;
        this.descripcion=d;
    }
	     
    public String getCodExperto(){
      // TODO add code
      return this.codEXPERT;
    }

    public String getCodCaso(){
      // TODO add code
      return this.codCase;
    }

    public String getFecha(){
        // TODO add code
        return this.dateC;
    }

    public String getDescripcionColaboracion(){
       // TODO add code
       return this.descripcion;
    }

    public void setCodExperto(String ce){
      // TODO add code
      this.codEXPERT=ce;
    }

    public void setCodcaso(String cc){
      // TODO add code
      this.codCase=cc;
    }

    public void setFecha(String dc){
        // TODO add code
        this.dateC=dc;
    }

    public void setDescripcionColaboracion(String d){
        // TODO add code
        this.descripcion=d;
    }
}
