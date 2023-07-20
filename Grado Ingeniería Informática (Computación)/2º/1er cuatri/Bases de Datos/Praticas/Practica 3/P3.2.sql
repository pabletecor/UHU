set serveroutput on;

create or replace procedure LlamadaFacturacion(p_a�o integer) is

  cursor c_telefonos is
    select distinct tf_origen from mf.LLAMADA
    where extract (year from fecha_hora) = p_a�o;
    
BEGIN
  dbms_output.put_line ('N� telefono importe (en E)');
  DBMS_OUTPUT.PUT_LINE('---------------------------');
  for r_telefono in c_telefonos loop
    SYS.DBMS_OUTPUT.PUT_LINE(r_telefono.tf_origen || '      ' || facturacion (r_telefono.tf_origen, p_a�o));
    end loop;
    
exception
  when others then
    dbms_output.put_line('Ha ocurrido un error');
  end;
  
  execute LLAMADAFACTURACION(2006);