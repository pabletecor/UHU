set title "Comparacion TernariaR y BinariaR" 
set key top left vertical inside
set grid
set xlabel"Talla(n)"
set ylabel"Tiempo(milisegundos)"
plot"TernariaRBinariaR.dat" using 1:2 with lines title"TernariaR","TernariaRBinariaR.dat" using 1:3 with lines title"BinariaR"
set terminal pdf
set output "TernariaRBinariaR.pdf" 
replot
pause 5 'Pulsa enter para continuar' 
