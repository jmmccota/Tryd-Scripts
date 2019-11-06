// @Title: Indicador: Indicador de volume de compras
// @Author: JMMCCota
// @Version: 0.1.1

// Settings //
def len = 100;
def constanteNegocios = 0.5;
def altoVolumeNegocios = 5*constanteNegocios;
def medioVolumeNegocios = 2*constanteNegocios;
def baixoVolumeNegocios = 1*constanteNegocios;
// End //

def barras = BARS();

r = newLines();
r.add( barras );

def volumeNegociosPorTempo = volume();

/**
 MA( final LineData source, int period, int maType ) 

  Descrição  - Moving Average 
  Retorno  - LineData 
  Parâmetros:  
     maType  - Tipo de média móvel que será utilizada no cálculo. 
        0 - Simples 
        1 - Exponencial 
        2 - Ponderada 
        3 - Exponencial dupla 
        4 - Exponencial tripla 
        5 - Triangular 
        6 - Kaufman Adaptive 
        7 - MESA Adaptive 
        8 - Exponencial tripla (T3) 
*/
def mean = MA(volumeNegociosPorTempo,len,0);

for (int i = 1; i < volumeNegociosPorTempo.size(); i++) {
	def bar = barras.bar(i);
	def res = 0;

    def pBar = barras.bar(i - 1);

    def pHigh = pBar.getHigh();
    def pLow = pBar.getLow();
    def cLow = bar.getLow();
    def cHigh = bar.getHigh();

    def pOpen = pBar.getOpen();
    def pClose = pBar.getClose();
    def pMin = pOpen > pClose ? pClose : pOpen;
    def pMax = pOpen < pClose ? pClose : pOpen;

    def cOpen = bar.getOpen();
    def cClose = bar.getClose();

	if (i>len) {
		res = volumeNegociosPorTempo.value(i)/mean.value(i - len + 1);
	}


	if (res > altoVolumeNegocios) {
		if (cOpen > cClose) {
            bar.setFill(196, 0, 0); 
            bar.setBorder(196, 0, 0); 
        } else {
            bar.setFill(0, 196, 0); 
            bar.setBorder(0, 196, 0); 
        }
	} else if (res > medioVolumeNegocios) {
		if (cOpen > cClose) {
            bar.setFill(255, 255, 255);
            bar.setBorder(255, 128, 0);
       } else {
           bar.setFill(255, 255, 255); 
           bar.setBorder(149, 255, 0);
       }
	// }else if (res > baixoVolumeNegocios) { // amarelo
    //     if (cOpen > cClose) {
    //         bar.setFill(250,244,2); // Vermelho
    //         bar.setBorder(250, 155, 2); // Cinza
    //     } else {
    //         bar.setFill(149, 255, 0); // Vermelho
    //         bar.setBorder(159, 250, 2); // Cinza
    //     }	
	// }else if (res > baixissimoVolumeNegocios) { // branco
		// bar.setFill(255,255,255);
		// bar.setBorder(255,255,255);
		
	} else {
		bar.setFill(255,255,255); // Azul
		bar.setBorder(255,255,255);		
	}
}
