package libclases;

import java.util.Scanner;

public class Empresa implements Cloneable {

	private Cliente[] clientes;	//Cuando llegue a 10 clientes el array aumenta de 10 en 10
	private int ncli; //para saber cuántos clientes hay en el array (al principio 0)
	private int nmaxcli;
	//No hacen falta los contratos 
	
	
	public Empresa() {
		this.ncli = 0;
		this.nmaxcli = 10;
		this.clientes = new Cliente [10];
	}
	
	public Empresa(Empresa e) {
		this.ncli = e.ncli;
		this.nmaxcli = e.nmaxcli;
		this.clientes = new Cliente [e.ncli];
		
		for (int i=0; i< ncli; i++) {
			this.clientes[i] = (Cliente) e.clientes[i].clone();
			
		}
	
	}
	
	public void alta() {
		
		Boolean nuevo = true; //El cliente es nuevo
		String NIF;
		Scanner s = new Scanner(System.in);


		System.out.println ("Introduzca el NIF del cliente: ");
		 NIF = s.next(); //Hay que poner el nif
		System.out.println();

		int i =0;

		while(i<ncli && nuevo ) { 
			
			if(NIF.equals(clientes[i].getnif()))
		        nuevo = false;

			if(nuevo)
		    	i++;
		
		}

		if(!nuevo) {     //Si ya existe
		    
		    System.out.println (" El cliente con NIF " + NIF + " ya existe: ");
		    clientes[i].ver();

		}
		    
		else {  //El cliente es nuevo
		    	
		    	if (this.ncli >= this.nmaxcli) { //Hemos llegado al maximo numero de CLIENTES. Mem dinamica

		    		Cliente [ ] aux = this.clientes;
		    		this.clientes = new Cliente [this.nmaxcli*2]; //es buena idea ampliarlo duplicando su tamaño en vez de ampliarlo poco
		    		for(int j = 0 ; j < aux.length; j++) //la operacion es costosa porque hay que copiar todo
		    		this.clientes[j]=aux[j];
		    		
		    		this.nmaxcli = this.nmaxcli *2;

			    }


		    //CREAMOS EL NUEVO CLIENTE
		     System.out.println ("El cliente " + NIF + " es nuevo, procedemos a darlo de alta: \n\n");
		     String nom;
		     double mins;
		     
		     System.out.print( "Introduzca el nombre del cliente: " );
		     nom = s.next();
		     
		     


		     System.out.println( "\n\nFecha de nacimiento del cliente): \n");
		   /*  System.out.println ("dia : ");
		        dia = s.nextInt();
		        System.out.println ("\nmes : ");
		        mes = s.nextInt();
		        System.out.println ("\nanio : ");
		        anio = s.nextInt();
*/
		     Fecha fnac = Fecha.pedirFecha(); //(New blablabla)

		     System.out.println ("Introduzca la fecha de alta: ");
		     
		     Fecha fAlta = Fecha.pedirFecha();
		    
		     System.out.println("Introduzca los minutos hablados al mes: ");
		    
		     s.nextLine();
		     mins = s.nextDouble();


		//ELEGIMOS CONTRATO PARA EL NUEVO CLIENTE

		    int opcion;
		    Boolean opcioncorrecta2 = false;

		 do{

		    System.out.println ("\nIndique el tipo de cliente: (1->Movil, 2-> Tarifa Plana, 3-> TPNavega ): ");
		    opcion = s.nextInt() ;

		    //  HAGO UN SWITCH PARA VER SI QUIERE CONTRATO MOVIL O TARIFA.

		    switch (opcion){

		        case 1: {           //CLIENTE MOVIL
		            double  preciomin;

		        System.out.println ("\nIndique la fecha de fin de permanencia: \n");

		        Fecha fperm = Fecha.pedirFecha();

		        System.out.println ("\nIndique el precio por minuto que desea: ");
		        preciomin = s.nextDouble();

		        ClienteMovil cm = new ClienteMovil(NIF, nom, fnac, fAlta, mins, preciomin, fperm);

		        this.clientes[this.ncli] = cm;
		        this.ncli++;
		        opcioncorrecta2 = true;


		        break;
		        }

		        case 2: {           //CLIENTE TARIFA ClienteTarifa(String NIF, String nom, Fecha fNac, Fecha fAlta, double minhab, String Nac )

		        String nac;
		        
		        System.out.println( "Introduzca la nacionalidad del cliente: " );
		        nac= s.next();
		        

		        ClienteTarifa ct = new ClienteTarifa(NIF, nom, fnac, fAlta, mins, nac);

		        this.clientes[this.ncli] = ct;
		        this.ncli++;
		        opcioncorrecta2 = true;

		        break;
		        }
		        
		        case 3: {           //CLIENTE TARIFA ClienteTarifa(String NIF, String nom, Fecha fNac, Fecha fAlta, double minhab, String Nac,int MegasUsados )

			        String nac;
			        int megas;
			        
			        System.out.println( "Introduzca la nacionalidad del cliente: " );
			        nac= s.next();
			        
			        System.out.println( "Introduzca los megas consumidos por el cliente: " );
			        megas = s.nextInt();

			        ClienteTarifaNavega ct = new ClienteTarifaNavega(NIF, nom, fnac, fAlta, mins, nac, megas);

			        this.clientes[this.ncli] = ct;
			        this.ncli++;
			        opcioncorrecta2 = true;

			        break;
			        }

		        default:

		            System.out.println ("\n\nELIGE UNA OPCION CORRECTA");


		            break;

		    }

		} while (!opcioncorrecta2);
		 
		
		 
		}
		
		
	}
	
	public void alta(ClienteMovil cm) {		//NOS PASAN CLIENTE POR PARAMETRO, SOLO PONEMOS LOS VALORES NO SE PREGUNTA NADA!!!1
		
		Boolean nuevo = true; //El cliente es nuevo
		String NIF = cm.getnif();

		int i =0;

		while(i<this.ncli && nuevo ) { 
		    if(NIF.equals(clientes[i].getnif()))		// || es null
		        nuevo = false;
		    
		    if(nuevo)
		    i++;
		
		}

		if(!nuevo) {     //Si ya existe
		    
		    System.out.println ("El cliente con NIF " + NIF + " ya existe: ");
		    cm.ver();

		}
		    
		else {  //El cliente es nuevo
		    	
		    	if (this.ncli >= this.nmaxcli) { //Hemos llegado al maximo numero de CLIENTES. Mem dinamica

		    		Cliente [ ] aux = this.clientes;
		    		this.clientes = new Cliente [this.nmaxcli*2]; //es buena idea ampliarlo duplicando su tamaño en vez de ampliarlo poco
		    		for(int j = 0 ; j < aux.length; j++) //la operacion es costosa porque hay que copiar todo
		    		this.clientes[j]=aux[j];
		    		
		    		this.nmaxcli = this.nmaxcli *2;

			    }


		    //CREAMOS EL NUEVO CLIENTE
		     System.out.println ("El cliente " + NIF + " es nuevo, procedemos a darlo de alta \n\n");

		        this.clientes[this.ncli] = cm;
		        this.ncli++;
		     
		}
		
		
		
	}
	
public void alta(ClienteTarifa ct) {		//NOS PASAN CLIENTE POR PARAMETRO, SOLO PONEMOS LOS VALORES NO SE PREGUNTA NADA!!!1
		
		Boolean nuevo = true; //El cliente es nuevo
		String NIF = ct.getnif();

		int i =0;

		while(i<this.ncli && nuevo ) { 
		    if(NIF.equals(clientes[i].getnif()))
		        nuevo = false;
		   
		    if(nuevo)
		    i++;
		}

		
		if(!nuevo) {     //Si ya existe
		    
		    System.out.println ("El cliente con NIF " + NIF +" ya existe: ");
		    ct.ver();

		}
		    
		else {  //El cliente es nuevo
		    	
		    	if (this.ncli >= this.nmaxcli) { //Hemos llegado al maximo numero de CLIENTES. Mem dinamica

		    		Cliente [ ] aux = this.clientes;
		    		this.clientes = new Cliente [this.nmaxcli*2]; //es buena idea ampliarlo duplicando su tamaño en vez de ampliarlo poco
		    		for(int j = 0 ; j < aux.length; j++) //la operacion es costosa porque hay que copiar todo
		    		this.clientes[j]=aux[j];
		    		
		    		this.nmaxcli = this.nmaxcli *2;

			    }


		    //CREAMOS EL NUEVO CLIENTE
		     System.out.println ("El cliente " + NIF + " es nuevo, procedemos a darlo de alta \n\n");
		    

		        this.clientes[this.ncli] = ct;
		        this.ncli++;
		  
		 
		}
			
	}


public void alta(ClienteTarifaNavega ct) {		//NOS PASAN CLIENTE POR PARAMETRO, SOLO PONEMOS LOS VALORES NO SE PREGUNTA NADA!!!1
	
	Boolean nuevo = true; //El cliente es nuevo
	String NIF = ct.getnif();

	int i =0;

	while(i<this.ncli && nuevo ) { 
	    if(NIF.equals(clientes[i].getnif()))
	        nuevo = false;
	   
	    if(nuevo)
	    i++;
	}

	
	if(!nuevo) {     //Si ya existe
	    
	    System.out.println ("El cliente con NIF " + NIF +" ya existe: ");
	    ct.ver();

	}
	    
	else {  //El cliente es nuevo
	    	
	    	if (this.ncli >= this.nmaxcli) { //Hemos llegado al maximo numero de CLIENTES. Mem dinamica

	    		Cliente [ ] aux = this.clientes;
	    		this.clientes = new Cliente [this.nmaxcli*2]; //es buena idea ampliarlo duplicando su tamaño en vez de ampliarlo poco
	    		for(int j = 0 ; j < aux.length; j++) //la operacion es costosa porque hay que copiar todo
	    		this.clientes[j]=aux[j];
	    		
	    		this.nmaxcli = this.nmaxcli *2;

		    }


	    //CREAMOS EL NUEVO CLIENTE
	     System.out.println ("El cliente " + NIF + " es nuevo, procedemos a darlo de alta \n\n");
	    

	        this.clientes[this.ncli] = ct;
	        this.ncli++;
	  
	 
	}
		
}
	

	public void baja() {
		
		Boolean nuevo = true; //El cliente no existe (no se puede dar de baja)
		String NIF;
		Scanner s = new Scanner(System.in);
		int i = 0;
		
		System.out.println( "Introduzca nif cliente a dar de baja: " );
		NIF = s.nextLine();
		
		while(i<this.ncli && nuevo ) { 
		    if(NIF.equals(clientes[i].getnif()))
		        nuevo = false;

		    if(nuevo)
		    i++;
		}
		
		if(nuevo)
			System.out.println("El cliente con NIF " + NIF + " no existe.");
		
		else {
			
			this.clientes[i].ver();
			
			String opcion;
			
								
			System.out.println("\n\nSeguro que desea borrarlo? (s/n)");
			opcion = s.next();			//.charAt(0);		ALMACENA UN CHAR (ANDA QUE NO ES DIFICIL EN JAVA)
			
			
			if (opcion.equals("s") || opcion.equals("S") ) {
				
				System.out.println("El cliente " + this.clientes[i].getnombre() +  " con NIF " + NIF + " ya no existe.");
				
				this.clientes[i] = null;		//COMO EN JAVA TODO SON REFERENCIAS A PUNTEROS, PARA ELIMINAR UN CLIENTE HAGO QUE SEÑALE A NULL. SE BORRARÁ AUTOMATICAMENTE
				System.gc();
				//Reorganizo la tabla (Tengo que llamar al basurero?)
				
				
				while(i<this.ncli-1){
					
			        this.clientes[i] =this.clientes[i + 1];   //DESLAZAMIENT0
			        i++;

			    }
				
			this.ncli--;
			
			

			}
			
			else
				System.out.println("\n\nEl cliente con NIF " + NIF + " no se elimina.");
		}
	}
	
	
	public void baja(String NIF) {
		Boolean nuevo = true; //El cliente no existe (no se puede dar de baja)
		Scanner s = new Scanner(System.in);
		int i = 0;
		
		while(i < this.ncli && nuevo ) { 
		    if(NIF.equals(clientes[i].getnif()))
		        nuevo = false;

		    if(nuevo)
		    i++;
		}
		
		if(nuevo)
			System.out.println("El cliente con NIF " + NIF + " no existe.");
		
		else {
			
				
				System.out.println("El cliente con NIF " + NIF + " ya no existe.");
				
				this.clientes[i] = null;		//COMO EN JAVA TODO SON REFERENCIAS A PUNTEROS, PARA ELIMINAR UN CLIENTE HAGO QUE SEÑALE A NULL. SE BORRARÁ AUTOMATICAMENTE
				System.gc();
				//Reorganizo la tabla (Tengo que llamar al basurero?)
				
				
				while(i<this.ncli-1){
					
			        this.clientes[i] =this.clientes[i + 1];   //DESLAZAMIENT0
			        i++;

			    }
			this.ncli--;
			
			

			}
			
			
		}
		
	
	
	public void descuento (int porcentaje) {	//Hace falta saber que clientes son MOVIL
		
		for (int i=0; i< this.ncli; i++) {
	       
			ClienteMovil cm = null;		//Lo pongo a null pq es local
	       
			
	       if(clientes[i] instanceof ClienteMovil){ //¿cm y clientes son de la misma clase?
	           
	    	    cm= (ClienteMovil) clientes[i].clone();	//NO SE SI FUNCIONA
	    	    
	    	   cm.setprecioporminuto(cm.getpreciopormin() * (1-porcentaje/100) );
	       
	       }
	      
	       
		}
		
		
	}
	
	public int getN() {
		return this.ncli;
	}
	
	public int nClienteMovil() {		//Devuelve el numero de ClienteMovil
		
		int cmov = 0;
		
		for (int i=0; i< this.ncli; i++) {
	       
			
	       if(clientes[i] instanceof ClienteMovil){ //¿cm y clientes son de la misma clase?
	           
	    	    cmov++;
	    	    
	       
	       }
	      
	       
		}
		
		return cmov;
	}
	
	public double factura() {
		
		double fact=0;
		ClienteMovil cm = null;
		ClienteTarifa ct = null;
	
		for (int i=0; i< this.ncli; i++) {
			
			 if(clientes[i] instanceof ClienteMovil){ //¿cm y clientes son de la misma clase?
		           
		    	    cm = (ClienteMovil) clientes[i].clone();	//NO SE SI FUNCIONA
		    	    
		    	   fact += cm.factura();
		       
		       }
			
			 else
				 if(clientes[i] instanceof ClienteTarifa) {
					 
					 ct = (ClienteTarifa) clientes[i].clone();	//NO SE SI FUNCIONA
			    	    
			    	   fact += ct.factura();
				 }
		
		}
		
		return fact;
		
	}
	
	public String toString() {	//devuelve una cadena con la información de la Empresa (Rellenar)
		
		String s = "";
		
		for(int i=0; i< this.ncli; i++) {
			s = s + this.clientes[i].toString() + "\n";
			
		}
		
		return s;
	}
	
	public Object clone() {
    	//return new Fecha(this);
    	
	 return new Empresa(this);
    	
	}
	
	
	//EXAMEN
	
	//Ejercicio 2a) examen martes
	
	public void mostrarMejoresClientes() {	//Muestra los clientes de clientemovil que tienen una factura superior a la media de la factura de los clientemovil
		
		int contador =0;
		double fact=0;
		double media;
		ClienteMovil cm = null;
		
		for (int i=0; i< this.ncli; i++) {
			
			 if(clientes[i] instanceof ClienteMovil){ //¿cm y clientes son de la misma clase?
		           
				 contador ++;
		    	 
				 cm = (ClienteMovil) clientes[i].clone();	
		    	    
		    	 fact += cm.factura();
			 }
		}
		
		//Sacamos la factura media de los cliente movil
		
			media = fact/contador;
		
			System.out.println("La media de las facturas de los ClienteMovil es: " + media + "\n\nLos mejores clientes de tarifa movil son:\n ");
			
		for (int i=0; i< this.ncli; i++) {
			
			 if(clientes[i] instanceof ClienteMovil){ //¿cm y clientes son de la misma clase?
		           
		    	 cm = (ClienteMovil) clientes[i].clone();	
		    	
		    	 if(cm.factura() > media)
		    		System.out.println( cm.toString() + "\n");
		    	
			 }
		}
	}
	
	//Ejercicio 3a) examen martes
	
	public void bajaTPNacionalidad(String nac, int anio) {
		
		ClienteTarifa ct = null;
		
		for (int i =0; i < this.ncli; i++) {
			 
			if(this.clientes[i] instanceof ClienteTarifa) {
				 
				 ct = (ClienteTarifa) clientes[i].clone();
				 
				 if( (ct.getfechaNac().getanio() == anio) && ( nac.equals(ct.getNacionalidad()) ) ) {
					 this.baja(ct.getnif());
					 
					i--;	//Decrementamos i porque al dar de baja a un cliente la tabla clientes[] se desplaza
					 
				 }
			
		}
		
	}
	
}
	 
	public static void main(String[] args) {
		Fecha fprueba1 = new Fecha(1,4,2000), fprueba2 = new Fecha(7,5,2003);
		Fecha f1=new Fecha(29,2,2001), f2= new Fecha(f1), f3=new Fecha(29,2,2004);
		Fecha fnac1 = new Fecha(7,3,1980), fnac2 = new Fecha(27,06,1995);
		System.out.println("Fechas:" + f1 + ", " + f2 + ", " + f3);
		ClienteTarifa [] ct= new ClienteTarifa[4];
		ClienteMovil cm1 = new ClienteMovil("547B", "Luis Perez", fnac2, 50.50f, 0.03f);
		ClienteMovil cm2 = (ClienteMovil) cm1.clone(); //lo crea con los mismos datos que cm1
		ClienteMovil cm3 = new ClienteMovil("777F", "Joe Sam", fnac2.diaSig(), 50.50f, 0.02f);
		ct[0] = new ClienteTarifa("805W","Luz Casal", fnac1, f3, 375.09f, "Española");
		ct[1] = new ClienteTarifa("953H","Paz Padilla", fnac2, f2, 290.00f, "Española");
		ct[2] = new ClienteTarifa("106T","Elton John", fnac2, 340.75f, "Inglesa");
		ct[3] = new ClienteTarifa("467X","Messi", fnac2.diaSig(), 300.00f, "Argentina");
		ClienteTarifaNavega ctn = new ClienteTarifaNavega("666X","Motumbo", fnac2.diaSig(), 300.00f, "Ugandes", 990);
		System.out.println("Codigos: " + cm1.getcodcliente() +", "+ cm2.getcodcliente() + ", " + ct[0].getcodcliente() +", "+ ct[2].getcodcliente() +"\n");
		Empresa g=new Empresa(), gcopia;
		g.alta(cm1); g.alta(ct[0]); g.alta(ct[2]); g.alta(cm2); g.alta(ct[1]);g.alta(cm3); g.alta(ctn);;
		//g.alta(); g.alta(); //añade un ClienteMovil 100Z Pepe Luis, 2/2/1972 1/1/2001,
		//40.30, 0.04 1/1/2010 y otro con nif 106T
		g.alta(ct[1]); //no lo debe de añadir ya que existe
		System.out.println("\n\nGrupo g:\n" + g);
		g.baja("547B"); //elimina el cliente con nif 547B
		//g.baja(); //pregunta que cliente desea eliminar (953H) y decimos que NO
		//g.baja(); //pregunta que cliente desea eliminar (106T) y decimos que SI
		g.alta(cm2);
		g.mostrarMejoresClientes();
		g.bajaTPNacionalidad("Española", 1980);
		System.out.println("#####\nClientes del grupo g:");
		System.out.println(g + "Factura: " + g.factura() +"\n---\n");
		gcopia=(Empresa)g.clone();
		//gcopia.baja("805W"); 
		//gcopia.baja("106T"); gcopia.alta(ct[3]); //el 106T no existe
	    //g.baja("953H"); //elimina el cliente con 953H
		gcopia.descuento(50);
		System.out.println("Grupo g:\n" + g + "\nGrupo gcopia:\n" + gcopia + "\n");
		System.out.println("g tiene " + g.getN() + " clientes y gcopia " + gcopia.getN());
		System.out.print("g tiene " + g.nClienteMovil() + " clientes de Tarifa Movil ");
		System.out.println("y " + (g.getN()-g.nClienteMovil()) + " de Tarifa Plana");

	}

}
