/*-Espera señal 10 procedente de proceso “dos”. Muestra mensaje al recibir la señal.
-Lee de la FIFO el número aleatorio y hace un alarm de la cantidad de segundos leída
de la FIFO. Cuando salte por primera vez el alarm, se muestra mensaje y se aborta la
alarma.
-Finaliza.*/

#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>
