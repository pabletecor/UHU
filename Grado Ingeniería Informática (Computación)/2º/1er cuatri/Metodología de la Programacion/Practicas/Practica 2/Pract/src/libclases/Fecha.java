package libclases;
import java.util.*;

public final class Fecha implements Cloneable,Proceso {		//Si se pone final significa que la clase no puede tener hijos
	
    private int dia,mes,anio;	//ATRIBUTOS PRIVADOS DE LA CLASE
    
    //METODOS PUBLICOS DE LA CLASE 
    
    public Fecha(int dia, int mes, int anio)
    {
        this.dia=dia;
        this.mes=mes;
        this.anio=anio;
        
        setFecha(dia,mes,anio);
       
    }
    
    public Fecha (Fecha f) {
    	dia = f.dia;
    	mes = f.mes;
    	anio = f.anio;
    	
    	
    }
    
    public void setFecha(int d, int m, int a) {
    	 int dmax, diaMes[] = {31,28,31,30,31,30,31,31,30,31,30,31};
    	 this.anio=a; //VIP debo asignar año para al llamar a bisiesto() tengo el año bien
    	 if (m<1) //si el mes es incorrecto
    	 m=1;
    	 else if (m>12) //si el mes es incorrecto
    	 m=12;
    	 dmax=diaMes[m-1];
    	 if (m==2 && bisiesto())
    	 dmax++;
    	 if (d>dmax)
    	 d=dmax;
    	 else if (d<1)
    	 d=1;
    	 this.dia=d;
    	 this.mes=m;
    	}

    public Object clone() {
    	//return new Fecha(this);
    	Object obj=null;
    	try {
    	obj=super.clone(); //se llama al clone() de la clase base (Object)
    	//que hace copia binaria de los atributos
    	} catch(CloneNotSupportedException ex) {
    	System.out.println(" no se puede duplicar");
    	} return obj;
    	}
    
    
    public boolean equals(Object obj) { //true sin son iguales
    	if (this == obj) return true; //si apuntan al mismo sitio son iguales
    	if (obj == null) return false;
    	if (getClass() != obj.getClass())//if (!(obj instanceof Cliente))
    	return false; // si los 2 no son de la misma clase no son iguales
    	Fecha c = (Fecha) obj;
    	return (dia==c.dia && mes==c.mes && anio==c.anio);
    	}
    
   /*
    public static Fecha pedirFecha()
    {
        int dia,mes,anio, ok=1;
        Fecha f;
        System.out.println("Introduce Fecha (dd/mm/yyyy) ");
        
        do {
    System.out.print("Introduzca el dia por teclado");
    Scanner S=new Scanner(System.in);
    dia=S.nextInt();
    System.out.print("Introduzca el mes por teclado");
    mes=S.nextInt();
    System.out.print("Introduzca el anio por teclado");
    anio=S.nextInt();
    S.close();
    f = new Fecha (dia, mes, anio);
    if(f.getdia()!=dia || f.getmes() != mes) {
    	ok=0;
    	
    System.out.println("Fecha no valida");
    System.out.println("Introduce Fecha (dd/mm/yyyy)");
    }
        } while (ok ==0);
    
        return f;
        
    }
    
    
   ASI SERIA LA CLASE DE FORMA EMPEPINADA
     * 
     * En dicho método vamos a controlar todos los errores que se puedan cometer y que son:
- Que no se introduzcan las 3 partes de la fecha o se introduzcan más de la cuenta
- Que el día, mes y/o año introducido no sea un valor numérico
- Que la fecha introducida no sea una fecha válida
En esos 3 casos vamos a lanzar una excepción que capturaremos en el propio método 
     * 
     * 
     * 
     * 
     * */
    
    public static Fecha pedirFecha() {
Fecha fecha = null;
boolean valida = false;
Scanner sc = new Scanner(System.in);
int dia, mes, anio;
do {
System.out.println("Introduce la Fecha (dd/mm/aaaa): ");
String cadena = sc.next();
String[] tokens = cadena.split("/");
try {
 if (tokens.length != 3)
throw new NumberFormatException();
 dia = Integer.parseInt(tokens[0]); //parseInt lanza la excepcion
 mes = Integer.parseInt(tokens[1]); //NumberFormatException si no
 anio = Integer.parseInt(tokens[2]);//puede convertir el String a int
 fecha = new Fecha(dia, mes, anio);
 if (fecha.getdia() != dia || fecha.getmes() != mes)
throw new NumberFormatException();
 valida=true;
} catch(NumberFormatException e) {
System.out.println("Fecha no valida");
}
} while(!valida);
return fecha;
}

    
    public int getdia() {return dia;}
    public int getmes() {return mes;}
    public int getanio() {return anio;}
    
    public String toString() {
    	return String.format("%02d/%02d/%02d", dia, mes, anio);
    	
    	
    }
    
    public boolean bisiesto() {
    	boolean b=false;
    	if (anio % 4 == 0) {
    	b=true;
    	 if (anio%100 == 0 && anio%400 !=0 )
    	 b=false;
    	}
    	return b;
    	}
    
    
    	public void ver() {
    	System.out.println(this.toString());
    	} 
    
    	public Fecha diaSig() {	//Suma un día a la fecha que se de 
    		
    		Fecha f = new Fecha(this);	//Operamos sobre una copia para no modificar la fecha original 
    		
    		int dMax;
    	    int dMes[]={0,31,28,31,30,31,30,31,31,30,31,30,31};

    	    if(bisiesto()) dMes[2]=29;
    	    f.dia = f.dia+1;
    	    dMax = dMes[f.mes];
    	    if(f.dia>dMax)
    	    {
    	    	f.dia = 1;
    	    	f.mes = this.mes+1;
    	    	if(f.mes > 12)
    	    	{
    	    		f.mes = 1;
    	    		f.anio = f.anio+1;
    	    	}
    	    	
    	    }
    	   // f.setFecha(f.Dia, f.Mes, f.Anio);
    		return f;
    	}
    	
    	static public boolean mayor(Fecha f1, Fecha f2) {
    		/*
    		boolean esmayor=false;
    		if (f1.anio>f2.anio)
    		esmayor= true;
    		else if (f1.anio<f2.anio)
    		esmayor= false;
    		else {
    		if (f1.mes>f2.mes)
    		esmayor= true;
    		else if (f1.mes<f2.mes)
    		esmayor= false;
    		else {
    		if (f1.dia>f2.dia)
    		esmayor= true;
    		else
    		esmayor= false;
    		}
    		}
    		return esmayor;
    		*/
    		if (f1.anio*10000+f1.mes*100+f1.dia>f2.anio*10000+f2.mes*100+f2.dia)
    		return true;
    		else
    		return false;
    		}

    	
    public static void main(String[] args) {
    	
    	Fecha f1 = new Fecha(29,2,2001), f2 = new Fecha(f1), f3 = new Fecha(29,2,2004);
    	 final Fecha f4=new Fecha(05,12,2003); //es constante la referencia f4
    	 System.out.println("Fechas: " + f1.toString() + ", " + f2 + ", " + f3 + ", " + f4);
    	 f1=new Fecha(31,12,2016); //31/12/2016
    	 f4.setFecha(28, 2, 2008); //pero no es constante el objeto al que apunta
    	 System.out.println(f1 +" "+ f2.toString() +" " + f3 + " "+ f4 + " "+ f1);
    	 f1=new Fecha(f4.getdia()-10, f4.getmes(), f4.getanio()-7); //f1=18/02/2001
    	 f3=Fecha.pedirFecha(); // pide una fecha por teclado
    	 if (f3.bisiesto() && Fecha.mayor(f2,f1))
    	 System.out.println("El " + f3.getanio() + " fue bisiesto, " + f1 + ", " + f3);
    	 System.out.print("Fin\n");
 
    }
 
}