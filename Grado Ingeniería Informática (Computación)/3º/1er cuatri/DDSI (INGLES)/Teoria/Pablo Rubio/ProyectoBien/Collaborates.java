package Persistence;

/**
 * Collaborates 
 * Class that will have the information about the collaborates
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
	codEXPERT=null;
        codCase=null;
        dateC=null;
        descripcion=null;
    }
    
     /**
   * Constructor with parameters
   * @ param ce, cc, f, dc
    */
    public Collaborates(String ce,String cc,String f, String dc) 
    {
	codEXPERT=ce;
        codCase=cc;
        dateC=f;
        descripcion=dc;
    }
	     
    /**
     * getCodEXPERT()
     * @return codExpert
     */
    public String getCodEXPERT(){
        return codEXPERT;
    }
    
    /**
     * getCodCase
     * @return 
     */
    public String getCodCase(){
        return codCase;
    }

    /**
     * 
     * @return 
     */
    public String getDate(){
        return dateC;
    }

    /**
     * 
     * @return 
     */
    public String getDescriptionColaboration(){
       return descripcion;
    }
    
    /**
     * 
     * @param ce 
     */
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
