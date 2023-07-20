create or replace function  facturacion (telefono varchar, anio integer ) return float is

precio float;
fac float;

fac_muy_peque�a exception;

BEGIN

SELECT sum(ll.duracion/60 * ta.coste) into fac
FROM MF.TELEFONO t inner join MF.TARIFA ta using (tarifa, compa�ia)
inner join MF.LLAMADA ll on ll.tf_origen = t.numero
where extract (year from fecha_hora) = anio and ll.tf_origen = telefono;

if fac<1 then
  raise fac_muy_peque�a;
end if;

return fac;

exception
  when fac_muy_peque�a then
   dbms_output.put_line('Facturacion demasiado baja');
   return -1;
   
   when NO_DATA_FOUND then
   dbms_output.put_line('El telefono no exiaste o no ha realizado llamadas ese a�o');
   return -1;
   
end;


set serveroutput on;
create or replace
procedure LlamadaFacturacion (a�o integer ) is

cursor c_telefonos is
  select distinct tf_origen from LLAMADA
  where extract (year from fecha_hora ) = a�o;
  
  begin
  dbms_output.put_line('N� telefono             importe(en E)');
  dbms_output.put_line('----------------------');
  
  end;
