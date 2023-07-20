#include <stdio.h>
#include <dirent.h>
#include <sys/types.h>


int main(int argc, char *argv[]){

DIR *dire;	//VAriable de tipo directorio
struct dirent *entrada, info; //Variable para guardar los datos al leer un directorio
entrada = &info;

if(argc<2){
	printf("debes pasar una ruta a un directorio valido\n");
	exit(-1);
}

if(stat(argv[1],&info)==1){
perror("Error de stat");
exit(-1)

}

printf("El inodo es el: %ld\n",info.st_ino);
printf("El propietario es: %d\n",info.st_uid);
printf("El tamaño es: %ld\n",info.st_size);
printf("El inodo es el: %ld\n",info.st_ino);
printf("La fecha de ultimo acceso es: %s\n",info.st_atime);

if(IS_REG(info.st_mode))printf("El tipo de fichero es REGULAR\n");
if(IS_DIR(info.st_mode))printf("El tipo de fichero es DIRECTORIO\n");

return 0;

0}



//EN LA PRACTICA, PARA SACAR PERMISOS Y PROPIETARIO SE HACE: sprintf(%s argv[1] | %s entrada->d_name)





/*dire = opendir(argv[1]);

printf("Contenido de /etc: \n");

entrada = (struct dirent *) malloc (sizeof(entrada));

entrada = readdir(dire);
while(entrada!=NULL){

	printf("Fichero %s, tipo %c, i-nodo %d\n",entrada->d_name,entrada->d_type,entrada->d_ino);

	entrada=readdir(dire);

}


closedir (dire);

*/
