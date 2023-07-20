package Persistence;

/**
 * Expert
 */

public class Expert {
	private String codExpert;
	private String name;
	private String country;
	private String sex;
	private String specialism;
	   
	 /**
        * Default constructor
         */
	public Expert() {
	   codExpert=null;
	   name=null;
	   country=null;
	   sex=null;
	   specialism=null;
  
	}
	            
	 /**
   * Constructor with params 
   * @param c, n, p, s, e
    */    	
	public Expert(String c, String n, String p, String s, String e) {
            codExpert = c;
            name = n;
            country = p;
            sex = s;
            specialism = e;
        }		
		
	public String getCodExperto() {
            return codExpert;
        }
	
	public String getName() {
            return name;
        }
	
	public String getCountry() {
            return country;
        }
		
	public String getSex() {
            return sex;
        }

	public String getSpecialism() {
            return specialism;
        }
		
	public void setCodExpert(String c) {
            codExpert = c;
        }
		
	public void setName(String n) {
            name = n;
        }
		
	public void setCountry(String p) {
            country = p;
        }
		
	public void setSex(String s) {
            sex = s;
        }
		
	public void setSpecialism(String e) {
            specialism = e;
        }
}