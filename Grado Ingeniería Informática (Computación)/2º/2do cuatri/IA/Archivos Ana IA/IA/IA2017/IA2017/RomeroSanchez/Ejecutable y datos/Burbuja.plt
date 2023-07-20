set title "Burbuja" 
set key top left vertical inside
set grid
set xlabel "Talla (n)" 
set ylabel "Tiempo (milisegundos)" 
plot "Burbuja.dat" using 1:2
set terminal pdf
set output "Burbuja.pdf 
replot
pause 5"Pulsa Enter para continuar....