all: servidor_ncurses pruebagraf principal cliente

servidor_ncurses: servidor_ncurses.c comun.c definiciones.h comun.h
	cc servidor_ncurses.c comun.c -o servidor_ncurses -lncurses

pruebagraf: pruebagraf.c
	cc pruebagraf.c comun.c -o pruebagraf

principal: principal.c comun.c
	cc principal.c comun.c -o principal

cliente: cliente.c comun.c comun.h
	cc cliente.c comun.c -o cliente
