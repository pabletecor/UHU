package Persistence;
/**
 * Caso 
 */


public class Policialcase {
    private String codCase;
    private String name;
    private String startDate;
    private String endDate;
	
   /**
   * Constructor por defecto   
    */
    public Policialcase() {
	codCase=null;
        name=null;
        startDate=null;
        endDate=null;
    }
    
    /**
   * Constructor con parametros
   * @param cc, n, fi, ff
    */
    public Policialcase(String cc,String n, String fi, String ff) {
	codCase=cc;
        name=n;
        startDate=fi;
        endDate=ff;
    }
	     
    public String getCodCase(){
        return codCase;
    }	
	
    public String getName(){
        return name;
    }

    public String getStartDate(){
       return startDate;
    }

    public String getEndDate(){
        return endDate;
    }

    public void setCodCase(String c){
       codCase=c;
    }

    public void setName(String n){
        name=n;
    }

    public void setStartDate(String fi){
       startDate=fi;
    }
	     
    public void setEndDate(String ff){
       endDate=ff;
    }
	    
}
