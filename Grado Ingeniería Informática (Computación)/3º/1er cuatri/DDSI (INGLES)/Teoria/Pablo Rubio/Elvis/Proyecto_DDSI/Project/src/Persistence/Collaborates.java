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
   * Constructor por defecto   
    */
    public Collaborates() 
    {
	codEXPERT=null;
        codCase=null;
        dateC=null;
        descripcion=null;
    }
    
     /**
   * Constructor con parametros 
   * @ param ce, cc, f, dc
    */
    public Collaborates(String ce,String cc,String f, String dc) 
    {
	codEXPERT=ce;
        codCase=cc;
        dateC=f;
        descripcion=dc;
    }
	     
    public String getCodEXPERT(){
        return codEXPERT;
    }

    public String getCodCase(){
        return codCase;
    }

    public String getDate(){
        return dateC;
    }

    public String getDescriptionColaboration(){
       return descripcion;
    }

    public void setCodEXPERT(String ce)
    {
      codEXPERT=ce;
    }

    public void setCodCase(String cc)
    {
      codCase=cc;
    }

    public void setDate(String f)
    {
        dateC=f;
    }

    public void setDescriptionColaboration(String dc)
    {
        descripcion=dc;
    }
}
