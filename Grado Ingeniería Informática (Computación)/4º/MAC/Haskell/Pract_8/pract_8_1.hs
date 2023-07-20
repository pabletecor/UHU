--typeDecl ::= type simpleType = expresion_de_tipo

--Se usa para declarar un sinonimo para un tipo existente

libretaDirecciones::[(String,String)]
libretaDirecciones =
	[("Antonio","662446572")]
	
--sinonimos
type Registro = [(Nombre,Telefono)]
--type Registro = [(_,Telefono)]
--type Registro = [(Nombre,_)]

type Telefono = String
type Nombre = String

existeRegistro::Nombre -> Telefono -> Registro ->Bool
existeRegistro nombre telefono libreta = (nombre,telefono) ` elem `  libreta
--existeRegistro _ telefono libreta = (nombre,telefono) ` elem `  libreta
--existeRegistro nombre _ libreta = (nombre,telefono) ` elem `  libreta

{-


-}

type Pos = (Int,Int)

origen :: Pos
origen = (0,0)

izquierda :: Pos -> Pos
izquierda (a, b) =
	(a-1,b)

	
data Dias = Lunes| Martes| Miercoles| Jueves| Viernes| Sabado| Domingo deriving (Show,Eq)

data Persona = Persona String String Int

data RGB = RGB Int Int Int deriving (Show,Eq)

red = RGB 255 0 0 
green = RGB 0 255 0 
blue = RGB 0 0 255 

mezclar (RGB x1 x2 x3) (RGB y1 y2 y3) = RGB (x1 + y1) (x2 + y2) (x3 + y3)

data Persona = Persona {nombre :: String,apellidos::String,edad::Int} deriving (Show,Eq)

usuario1 = Persona {nombre = "n1",apellidos = "a11 a21", edad = 22}
usuario1 = Persona {nombre = "n2",apellidos = "a12 a22", edad = 23}
usuario1 = Persona {nombre = "n3",apellidos = "a13 a23", edad = 24}
usuario1 = Persona {nombre = "n4",apellidos = "a14 a24", edad = 25}
usuario1 = Persona {nombre = "n5",apellidos = "a15 a25", edad = 26}

personas = [usuario1,usuario2,usuario3,usuario4,usuario5]


