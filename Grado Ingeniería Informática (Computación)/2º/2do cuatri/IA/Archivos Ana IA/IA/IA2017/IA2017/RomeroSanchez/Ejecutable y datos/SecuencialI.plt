set title "SecuencialI" 
set key top left vertical inside
set grid
set xlabel "Talla (n)" 
set ylabel "Tiempo (milisegundos)" 
plot "SecuencialI.dat" using 1:2
Lineal(x) =m*x + n
fit Lineal(x) "SecuencialI.dat" using 1:2 via m,n
plot "SecuencialI.dat" using 1:2 title "SecuencialI", Lineal(x)
plot "SecuencialI.dat" with lines
set terminal pdf
set output "SecuencialI.pdf 
replot
pause 5"Pulsa Enter para continuar....