package Persistence;
/**
 * PolicialCase
 */


public class Policialcase {
    private String codCase;
    private String name;
    private String startDate;
    private String endDate;
	
   /**
   * Default constructor  
    */
    public Policialcase() {
	// TODO add code
        this.codCase=null;
        this.endDate=null;
        this.name=null;
        this.startDate=null;
    }
    
    /**
   * Constructor with parameters
   * @param cc, n, sd, ed
    */
    public Policialcase(String cc,String n, String sd, String ed) {
	// TODO add code
        this.codCase=cc;
        this.name=n;
        this.endDate=ed;
        this.startDate=sd;
    }
	     
    public String getCodCaso(){
      // TODO add code
      return this.codCase;
    }	
	
    public String getNombre(){
     // TODO add code
     return this.name;
    }

    public String getFechaInicio(){
       // TODO add code
       return this.startDate;
    }

    public String getFechaFin(){
        // TODO add code
        return this.endDate;
    }

    public void setCodCaso(String c){
       // TODO add code
       this.codCase=c;
    }

    public void setNombre(String n){
     // TODO add code
     this.name=n;
    }

    public void setFechaInicio(String sd){
       // TODO add code
       this.startDate=sd;
    }
	     
    public void setFechaFin(String ed){
       // TODO add code
       this.endDate=ed;
    }
	    
}
