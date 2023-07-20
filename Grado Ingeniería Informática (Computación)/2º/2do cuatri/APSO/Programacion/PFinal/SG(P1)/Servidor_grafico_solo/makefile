all: servidor_ncurses pruebagraf

servidor_ncurses: servidor_ncurses.c comun.c definiciones.h comun.h
	cc servidor_ncurses.c comun.c -o servidor_ncurses -lncurses

pruebagraf: pruebagraf.c
	cc pruebagraf.c comun.c -o pruebagraf
