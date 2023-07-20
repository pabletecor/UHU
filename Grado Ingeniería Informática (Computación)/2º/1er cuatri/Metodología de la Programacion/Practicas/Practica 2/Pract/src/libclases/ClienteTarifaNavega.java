package libclases;

public class ClienteTarifaNavega extends ClienteTarifa implements Cloneable, Proceso {

	
    private static int megastp = 500;
    int megasUsados;
    private static double precioexcesomegas = 0.01;
	
	
	public ClienteTarifaNavega(String NIF, String nom, Fecha fNac, Fecha fAlta, double minhab, String Nac, int megasUsados ) {
		super(NIF, nom, fNac, fAlta,minhab,Nac);	//Constructor de la superclase cliente
		this.megasUsados = megasUsados;
			
	}
	
	public ClienteTarifaNavega(String NIF, String nom, Fecha fNac, double minhab, String Nac, int megasUsados ) {
		super(NIF, nom, fNac,minhab,Nac);	//Constructor de la superclase cliente
		this.megasUsados = megasUsados;
			
	}

	 public ClienteTarifaNavega(ClienteTarifaNavega c) {
		super(c.getnif(), c.getnombre(), c.getfechaNac(), c.getfechaAlta(), c.getminutoshablados(), c.getNacionalidad());
		this.megasUsados = c.getMegasUsados();
	}

	public int getMegasUsados() { 
		 return this.megasUsados;
	 }
	 
     public static double getPrecioExcesoMegas() { 
    	 return ClienteTarifaNavega.precioexcesomegas;
     }
     
     public static int getLimiteMegas() { 
    	 return ClienteTarifaNavega.megastp;
     }
     
     public void setMegasUsados(int m) { this.megasUsados=m; }
     
     public static void setTarifaMegas(int mtp, double ptp) {
    	 ClienteTarifaNavega.precioexcesomegas= ptp;
    	 ClienteTarifaNavega.megastp = mtp;
     }
     
     public double factura() {
    	 double fact=0;

    	 fact = super.factura();
    	 
    	 if(this.megasUsados > ClienteTarifaNavega.megastp)
    		 fact = fact + (this.megasUsados * ClienteTarifaNavega.precioexcesomegas);

    	 return fact;

    	 }
     
     public String toString() {	//devuelve una cadena con la información del cliente (ver)
    	
 		return new String (super.toString() + ". Actualizacion navega. Limite de megas y precio por exceso: (" + ClienteTarifaNavega.megastp + " , " + ClienteTarifaNavega.precioexcesomegas + "). Megas consumidos: " + this.megasUsados + ". Factura -->" + this.factura() );
 		
 	}
     
     public void ver() {
    	 System.out.println(this.toString());
     }
	
     public boolean equals(Object obj) { //true sin son iguales
	    	if (this == obj) return true; //si apuntan al mismo sitio son iguales
	    	if (obj == null) return false;
	    	if (getClass() != obj.getClass())//if (!(obj instanceof Cliente))
	    	return false; // si los 2 no son de la misma clase no son iguales
	    	ClienteTarifaNavega c = (ClienteTarifaNavega) obj;
	    	return (this.getnif().equals(c.getnif()));		
	    	}
     
     public Object clone() {
	    	//return new Fecha(this);
	    	
    	 return new ClienteTarifaNavega(this);
	    	}
     
}