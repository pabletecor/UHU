package Persistence;

/**
 * Expert
 */

public class Expert {
    
	private String codEXPERT;
	private String name;
	private String country;
	private String sex;
	private String specialism;
	   
	// Default constructor
	public Expert() {
            codEXPERT = null;
	    name = null;
	    country = null;
	    sex = null;
	    specialism = null;

        }// Constructor with parameters	    	
	public Expert(String ce, String n, String c, String s, String sp) {
            codEXPERT = ce;
            name = n;
            country= c;
            sex = s;
            specialism = sp;
        }		
		
	public String getCodEXPERT() {
            return codEXPERT;
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
		
	public void setCodEXPERT(String c) {
            codEXPERT = c;
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

