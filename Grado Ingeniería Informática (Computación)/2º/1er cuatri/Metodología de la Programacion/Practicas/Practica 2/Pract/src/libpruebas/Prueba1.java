package libpruebas;
import libclases.*;

public class Prueba1 {
	
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
Cliente c1=new Cliente("793X","Ana Pi",new Fecha(2,2,1972),f3), c2=new Cliente(c1);
Cliente c3=new Cliente("953H","Susana", new Fecha(7,2,1984)), c4=(Cliente) c3.clone();
c1.setfechaAlta(fnac1); c1.setnombre("Luis");
c3.setfechaAlta(fnac3); c3.setnombre("Juan");
aux = c1.getfechaAlta(); aux.setFecha(5, 5, 2005);
aux = c1.getfechaAlta(); aux.setFecha(7, 7, 2020);
c1.ver(); c2.ver(); c3.ver(); c4.ver();
if (c2.equals(c1) && c3.equals(c4))
Cliente.setfechaporDefecto(f3.diaSig());
System.out.print("Fecha alta por defecto: " + Cliente.getfechaporDefecto() + "\n");
Cliente [] c = new Cliente[6]; //array de 6 elementos de tipo Cliente
c[0]= new ClienteMovil("547B","Bo Derek", fnac1, f3, 50.50 ,  0.03, f3);	//ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, double minhab, double precioxmin,Fecha perm)
c[1]= new ClienteMovil("107J","Messi", fnac2, f2, 35.00, 0.02, f2);
ClienteMovil cm= (ClienteMovil) c[1].clone();
c[2]=cm; cm.setnombre(c1.getnombre()); cm.setfechaAlta(f1); cm.setfechaPermanencia(f1);
f1.setFecha(4,4,2004); aux=cm.getfechapermanencia(); aux.setFecha(20, 20, 2020);
c[3]=new ClienteTarifa("805W","Iker", fnac2, f1, 375.09, "Española");
c[4]=new ClienteTarifa("953H","Paz", fnac3, f3, 290.00, "Polaca");
ClienteTarifa ct= (ClienteTarifa) c[3].clone();
c[5]=ct; ct.setnombre("Pepe"); ct.setNacionalidad("India"); ct.setMinutosHablados(500);
cm.ver(); ct.ver();
ClienteTarifa.setTarifaPlana(350, 22.50); //cambia la Tarifa Plana a 350 min x 22.50
System.out.print("Tarifa Plana: " + ClienteTarifa.getLimiteMinutos() + " minutos por " + ClienteTarifa.getPrecio() + " euros \n \\ " );
for(int i=0; i<6; i++)
System.out.println(c[i].getnif() + ", " + c[i].getfechaAlta() + ", " + c[i]);
if (c[2].equals(c[1]))
System.out.println("c[2] y c[1] son iguales");
if (c[4].equals(c3)==false) {
System.out.println("c[4] y c3 no son iguales (mismo dni pero distinto tipo)");
}
}
}