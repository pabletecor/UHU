all: principal servidor_ncurses cliente bus

principal: principal.c comun.h
	cc principal.c -o principal

servidor_ncurses: servidor_ncurses.c comun.c definiciones.h comun.h
	cc servidor_ncurses.c comun.c -o servidor_ncurses -lncurses

cliente: cliente.c comun.c comun.h
	cc cliente.c comun.c -o cliente

bus: bus.c comun.c comun.h 
	cc bus.c comun.c -o bus

