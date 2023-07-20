package libclases;

public class ClienteMovil extends Cliente implements Cloneable, Proceso {

	Fecha fechaPermanencia;
	double minutoshablados;
	double preciopormin;
	
	public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, double minhab, double precioxmin,Fecha perm) {
		super(NIF, nom, fNac, fAlta);
		this.minutoshablados = minhab;
		this.preciopormin = precioxmin;
		this.fechaPermanencia = new Fecha (perm.getdia(), perm.getmes(), perm.getanio());
		
	}
	
	public ClienteMovil(String NIF, String nom, Fecha fNac, double minhab, double precioxmin,Fecha perm) {
		super(NIF, nom, fNac);
		this.minutoshablados = minhab;
		this.preciopormin = precioxmin;
		this.fechaPermanencia = new Fecha (perm.getdia(), perm.getmes(), perm.getanio());
		
	}
	
	public ClienteMovil(String NIF, String nom, Fecha fNac, double minhab, double precioxmin ) {
		super(NIF, nom, fNac);
		this.minutoshablados = minhab;
		this.preciopormin = precioxmin;
		this.fechaPermanencia = new Fecha (this.getfechaAlta().getdia(), this.getfechaAlta().getmes(), this.getfechaAlta().getanio() + 1);
		
	}
	
	
	public ClienteMovil(ClienteMovil c) {
		super(c.getnif(), c.getnombre(), c.getfechaNac(), c.getfechaAlta());
		this.minutoshablados = c.minutoshablados;
		this.preciopormin = c.preciopormin;
		this.fechaPermanencia = new Fecha (c.fechaPermanencia.getdia(), c.fechaPermanencia.getmes(), c.fechaPermanencia.getanio());
		
	}
	
	public double getminutoshablados() {return this.minutoshablados;}
	
	public double getpreciopormin() {return this.preciopormin;}
	
	public Fecha getfechapermanencia() {return this.fechaPermanencia;}
	
	public void setminutoshablados(double minhab) {this.minutoshablados = minhab;}
	
	public void setprecioporminuto(double precxmin) {this.preciopormin = precxmin;}
	
	public void setfechaPermanencia(Fecha f ) {this.fechaPermanencia.setFecha(f.getdia(), f.getmes(), f.getanio()); }
	
	public double factura()  {

		  double fact = this.minutoshablados * this.preciopormin;

		  return fact;

		}
	
	 public String toString() {	//devuelve una cadena con la información del cliente (ver)
	 		
	 		return new String(super.toString() + " " + this.fechaPermanencia.toString() + " "+ this.minutoshablados +" x " + String.format("%.2f", this.preciopormin) + " --> " + String.format("%.2f", factura()) );
	 		
	 		
	 	}
	     
	     
	public void ver() {
	    	 System.out.println(this.toString());
	     }
		
	     public boolean equals(Object obj) { //true sin son iguales
		    	if (this == obj) return true; //si apuntan al mismo sitio son iguales
		    	if (obj == null) return false;
		    	if (getClass() != obj.getClass())//if (!(obj instanceof Cliente))
		    	return false; // si los 2 no son de la misma clase no son iguales
		    	ClienteMovil c = (ClienteMovil) obj;
		    	return (this.getnif().equals(c.getnif()));		
		    	}
	     
	     public Object clone() {
		    	//return new Fecha(this);
		    	
	    	 return new ClienteMovil(this);
		    	
	     }

	

}
