package libclases;

public class ClienteTarifa extends Cliente implements Cloneable, Proceso {

	private String nacionalidad;
	private static int minutostp = 300;
    private static double preciotp = 20;
    double minutoshablados;
    private static double precioexcesominutos = 0.15;
	
	
	public ClienteTarifa(String NIF, String nom, Fecha fNac, Fecha fAlta, double minhab, String Nac ) {
		super(NIF, nom, fNac, fAlta);	//Constructor de la superclase cliente
		this.minutoshablados = minhab;
		this.nacionalidad = Nac;
			
	}
	
	public ClienteTarifa(String NIF, String nom, Fecha fNac, double minhab, String Nac ) {
		super(NIF, nom, fNac);	//Constructor de la superclase cliente
		this.minutoshablados = minhab;
		this.nacionalidad = Nac;
			
	}

	 public ClienteTarifa(ClienteTarifa c) {
		super(c.getnif(), c.getnombre(), c.getfechaNac(), c.getfechaAlta());
		this.minutoshablados = c.minutoshablados;
		this.nacionalidad = c.nacionalidad;
	}

	public static int getLimiteMinutos() { 
		 return ClienteTarifa.minutostp;
	 }
	 
     public static double getPrecio() { 
    	 return ClienteTarifa.preciotp;
     }
     
     public double getminutoshablados() {return this.minutoshablados;}
     
     public String getNacionalidad() {return this.nacionalidad;}
     
     public void setNacionalidad(String nac) {this.nacionalidad = nac;}
     
     public void setMinutosHablados(int m) { this.minutoshablados=m; }
     
     public static void setTarifaPlana(int mtp, double ptp) {
    	 ClienteTarifa.preciotp= ptp;
    	 ClienteTarifa.minutostp = mtp;
     }
     
     public double factura() {
    	 double fact=0;

    	 fact = ClienteTarifa.preciotp + ( (this.minutoshablados - ClienteTarifa.minutostp) * ClienteTarifa.precioexcesominutos );

    	 if ( this.minutoshablados < ClienteTarifa.minutostp )
    	     fact = ClienteTarifa.preciotp;

    	 return fact;

    	 }
     
     public String toString() {	//devuelve una cadena con la información del cliente (ver)
    	
 		return new String (super.toString() +" " + nacionalidad + " [" + ClienteTarifa.minutostp + " por " + ClienteTarifa.preciotp + "] " +  String.format("%.2f", minutoshablados ) + " --> " +  String.format("%.2f", factura() ) );
 		
 	}
     
     public void ver() {
    	 System.out.println(this.toString());
     }
	
     public boolean equals(Object obj) { //true sin son iguales
	    	if (this == obj) return true; //si apuntan al mismo sitio son iguales
	    	if (obj == null) return false;
	    	if (getClass() != obj.getClass())//if (!(obj instanceof Cliente))
	    	return false; // si los 2 no son de la misma clase no son iguales
	    	ClienteTarifa c = (ClienteTarifa) obj;
	    	return (this.getnif().equals(c.getnif()));		
	    	}
     
     public Object clone() {
	    	//return new Fecha(this);
	    	
    	 return new ClienteTarifa(this);
	    	}
     
	

}
