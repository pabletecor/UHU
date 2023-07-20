CREATE TABLE COMPA�IA (
    cif char(9),
    nombre varchar(20) NOT NULL,
   web varchar (30)NOT NULL,
   CONSTRAINT COMPA�IA PRIMARY KEY(cif)
   
  );
  
  CREATE TABLE TARIFA(
    tarifa char(10),
    compa�ia char(9),
    descripcion varchar(50),
    coste number(3, 2),
    FOREIGN KEY (compa�ia) REFERENCES COMPA�IA (cif)
    
    );
    
  CREATE TABLE CLIENTE(
   dni char(9),
   nombre varchar(50),
   f_nac date,
   direccion varchar(100) , 
   cp char(6),
   ciudad varchar(50),
   provincia varchar(5),
   CONSTRAINT CLIENTE PRIMARY KEY(dni)
  
  );
  CREATE TABLE TELEFONO(
    numero char(9),
    f_contrato date,
    tipo char(1),
    puntos number (6, 0),
    compa�ia char(9),
    tarifa char(10),
    cliente char(9),
    CONSTRAINT TELEFONO PRIMARY KEY(tarifa ,compa�ia)
    
  
  );
  CREATE TABLE LLAMADA(
  
  tf_origen varchar(9),
  tf_destino varchar(9),
  fecha_hora timestamp(6),
  duracion number(5, 0)
  );
  
ALTER TABLE COMPA�IA ADD CONSTRAINT compa�iaRepetida UNIQUE (nombre);
ALTER TABLE COMPA�IA MODIFY nombre NO NULL;
ALTER TABLE TARIFA ADD CONSTRAINT tarifaCosteNoPermitido CHECK (coste <= 1.5 AND coste >= 0.05);
ALTER TABLE TARIFA MODIFY coste NOT NULL;
ALTER TABLE CLIENTE MODIFY nombre NOT NULL;
ALTER TABLE TELEFONO MODIFY (compa�ia,tarifa) NOT NULL;
ALTER TABLE LLAMADA MODIFY duracion NOT NULL;
ALTER TABLE LLAMADA ADD CONSTRAINT llamadaASiMismo CHECK(tf_origen != tf_destino);

  
