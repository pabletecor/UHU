set serveroutput on;

create or replace procedure llamadas_cia(nom varchar, fecha varchar) is

existe_fecha integer;

cursor c_telefonos_cia is
select numero
from mf.compañia c inner join mf.telefono t on c.cif = t.compañia
where c.nombre =nom;

cursor c_llamadas_tf (tlf MF.LLAMADA.tf_origen%type) is
select L.tf_destino, L.duracion
from MF.LLAMADA L
where to_char (fecha_hora , 'dd/mm/yy') = l.fecha and L.TF_ORIGEN = tlf; 

begin
  select count(*) into existe_fecha
  from MF.LLAMADA
  where to_char(fecha_hora, 'dd/mm/yy') = fecha;

  if existe_fecha = 0 then
    raise no_existe_fecha;
  end if;

  dbms_output.put_line ('Llamadas realizadas por los telefonos de la compañia ' || nom );
  dbms_output.put_line ('Tlf. Origen      Num_LL       Num_LL _100       % ' );
  dbms_output.put_line ('***************************************************** ' );
  
  num_Total := 0;
  
  for v_telefonos in c_telefonos_cia loop
  
  
  
  
  end loop;


end;