// @Title: Indicador: Indicador de volume de compras
// @Author: JMMCCota
// @Version: 0.1.0

// Settings //
def len = 100;
def red = 2.5;
def orange = 1.0;
def yellow = 0.6;
def white = 0.15;
// End //

def barras = BARS();

r = newLines();
r.add( barras );

def vol = volume();
def mean = MA(vol,len,0);

for (int i = 1; i < vol.size(); i++) {
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
		res = vol.value(i)/mean.value(i - len + 1);
	}


	if (res > red) {
		if (cOpen > cClose) {
            bar.setFill(196, 0, 0); 
            bar.setBorder(196, 0, 0); 
        } else {
            bar.setFill(0, 196, 0); 
            bar.setBorder(0, 196, 0); 
        }
	} else if (res > orange) {
		if (cOpen > cClose) {
            bar.setFill(255, 255, 255);
            bar.setBorder(255, 128, 0);
        } else {
            bar.setFill(255, 255, 0); 
            bar.setBorder(149, 255, 0);
        }
	// }else if (res > yellow) { // amarelo
    //     if (cOpen > cClose) {
    //         bar.setFill(250,244,2); // Vermelho
    //         bar.setBorder(250, 155, 2); // Cinza
    //     } else {
    //         bar.setFill(149, 255, 0); // Vermelho
    //         bar.setBorder(159, 250, 2); // Cinza
    //     }	
	// }else if (res > white) { // branco
	// 	bar.setFill(255,255,255);
	// 	bar.setBorder(255,255,255);
		
	} else {
		bar.setFill(255,255,255); // Azul
		bar.setBorder(255,255,255);		
	}
}
