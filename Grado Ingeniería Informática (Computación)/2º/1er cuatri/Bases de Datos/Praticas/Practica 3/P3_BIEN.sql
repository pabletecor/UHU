create or replace function  facturacion (telefono varchar, anio integer ) return float is

precio float;
fac float;

fac_muy_pequeña exception;

BEGIN

SELECT sum(ll.duracion/60 * ta.coste) into fac
FROM MF.TELEFONO t inner join MF.TARIFA ta using (tarifa, compañia)
inner join MF.LLAMADA ll on ll.tf_origen = t.numero
where extract (year from fecha_hora) = anio and ll.tf_origen = telefono;

if fac<1 then
  raise fac_muy_pequeña;
end if;

return fac;

exception
  when fac_muy_pequeña then
   dbms_output.put_line('Facturacion demasiado baja');
   return -1;
   
   when NO_DATA_FOUND then
   dbms_output.put_line('El telefono no exiaste o no ha realizado llamadas ese año');
   return -1;
   
end;


set serveroutput on;
create or replace
procedure LlamadaFacturacion (año integer ) is

cursor c_telefonos is
  select distinct tf_origen from LLAMADA
  where extract (year from fecha_hora ) = año;
  
  begin
  dbms_output.put_line('Nº telefono             importe(en E)');
  dbms_output.put_line('----------------------');
  
  end;
