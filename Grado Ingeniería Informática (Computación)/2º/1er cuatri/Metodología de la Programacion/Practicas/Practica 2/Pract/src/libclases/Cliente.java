package libclases;

public class Cliente implements Cloneable,Proceso {

	private static int contador=0;
	private final String nif; //dni del cliente (letra incluida) (NO puede cambiar)
	private final int codCliente; //codigo único (y fijo) generado por la aplicación
	private String nombre; //nombre completo del cliente (SI se puede modificar)
	private final Fecha fechaNac; //fecha nacimiento del cliente (NO se puede cambiar) 
	private final Fecha fechaAlta; //fecha de alta del cliente (SI se puede modificar)
	private static Fecha fechaporDef = new Fecha(01, 01, 2018);//Hacer fecha por defecto estatica. 
	
	
	public Cliente (String NIF, String nom, Fecha fNac, Fecha fAlta) {	//constructor
		this.nif = NIF;
		this.nombre = nom;
		this.fechaNac = new Fecha(fNac.getdia(),fNac.getmes(),fNac.getanio());
		this.fechaAlta = new Fecha (fAlta.getdia(),fAlta.getmes(), fAlta.getanio());
		this.codCliente= ++Cliente.contador;
		
	} 
	
	public Cliente (String NIF, String nom, Fecha fNac) {	//constructor (FechaAlta = fechapordef)
		this.nif = NIF;
		this.nombre = nom;
		this.fechaNac = new Fecha(fNac.getdia(),fNac.getmes(),fNac.getanio());
		this.fechaAlta = new Fecha (Cliente.fechaporDef.getdia(),Cliente.fechaporDef.getmes(), Cliente.fechaporDef.getanio());
		this.codCliente= ++Cliente.contador;	
			
	}
	
	//HACER CONSTRUCTOR DE COPIA!!!
	
	public Cliente (Cliente c) {	
		this.nif = c.nif;
		this.nombre = c.nombre;
		this.fechaNac = new Fecha(c.fechaNac.getdia(),c.fechaNac.getmes(),c.fechaNac.getanio());
		this.fechaAlta = new Fecha (c.fechaAlta.getdia(), c.fechaAlta.getmes(), c.fechaAlta.getanio());
		this.codCliente= ++Cliente.contador;	
			
	}
	
	
	public String toString() {	//devuelve una cadena con la información del cliente (ver)
		return new String (nif + " " + fechaNac.toString() + ": " + nombre + " ("+ codCliente + " - " + fechaAlta.toString() + ")");
		
	}
	
	public void ver() {
    	System.out.println(this.toString());
    	} 
	
	 public boolean equals(Object obj) { //true sin son iguales
	    	if (this == obj) return true; //si apuntan al mismo sitio son iguales
	    	if (obj == null) return false;
	    	if (getClass() != obj.getClass())//if (!(obj instanceof Cliente))
	    	return false; // si los 2 no son de la misma clase no son iguales
	    	Cliente c = (Cliente) obj;
	    	return (nif.equals(c.getnif()));		//Queda ver si son del mismo tipo, lo comprobamos en las clases hijas (usar super)
	    	}
	
	public String getnif() {return nif;}
	public int getcodcliente() {return codCliente;}
	public String getnombre() {return nombre;}
	public Fecha getfechaNac() {return fechaNac;}
	public Fecha getfechaAlta() {return fechaAlta;}
	public static Fecha getfechaporDefecto() {return fechaporDef;}
	
	public void setnombre(String nombre) {
		
		this.nombre = nombre;
	}
	
	public void setfechaAlta(Fecha FechaAlt) {
		fechaAlta.setFecha(FechaAlt.getdia(), FechaAlt.getmes(), FechaAlt.getanio());
	
	}
	
	public static void setfechaporDefecto(Fecha Fechadef){
		Cliente.fechaporDef.setFecha(Fechadef.getdia(), Fechadef.getmes(), Fechadef.getanio());
		
	}
	
	
	
	 public Object clone() {
	    	//return new Fecha(this);
		 return new Cliente(this);
	    	}
	 
	 public double factura() {
		 double a = 0;
		 
		 return a;
	 }
	 
	 static public boolean mayor(Cliente c1, Cliente c2) {
		 if(Fecha.mayor(c1.fechaAlta, c2.fechaAlta))
			 return true;
		 else
			 return false;
		 
	 }
	 
	public static void main(String[] args) {
		final Fecha f1 = new Fecha(29,2,2001), f2 = new Fecha(f1), f3 = (Fecha) f1.clone();
		Fecha fnac1 = new Fecha(7,3,1980), fnac2 = fnac1.diaSig(),
		fnac3 = new Fecha(27,06,1995), aux;
		System.out.print("Fechas: " + f1.toString() + ", " + f2 + ", " + f3 + "\n");
		System.out.println(f2.diaSig()+ " " + (f2.getdia()-2) + " " +f2+ " " + f2.getanio());
		if (!f3.bisiesto() && f1.equals(f2))
		System.out.println(f3.getanio() + " no fue bisiesto. " + f1 + " igual a " + f3);
		f3.setFecha(5,12,2001);
		if (!f1.equals(f3) && Fecha.mayor(f1,f2)==false && Fecha.mayor(f3,f1))
		System.out.println(f3 + " mayor que " + f1 + ". " + f1 + " no es mayor que " + f2);
		f1.setFecha(1,1,2001); f2.setFecha(2,2,2002); f3.setFecha(3,3,2003);
		System.out.print("Fecha alta por defecto: " + Cliente.getfechaporDefecto() + "\n");
		Cliente c1=new Cliente("793X","Ana Pi",new Fecha(2,2,1972),f3), c2 = new Cliente(c1);
		Cliente c3=new Cliente("953H","Susana", new Fecha(7,2,1984)), c4=(Cliente) c3.clone();
		c1.setfechaAlta(fnac1); c1.setnombre("Luis");
		c3.setfechaAlta(fnac3); c3.setnombre("Juan");
		aux = c1.getfechaNac(); aux.setFecha(5, 5, 2005);
		aux = c1.getfechaAlta(); aux.setFecha(7, 7, 2020);
		c1.ver(); c2.ver(); c3.ver(); c4.ver();
		if (c2.equals(c1) && c3.equals(c4))
			Cliente.setfechaporDefecto(f3.diaSig());

	}

}
