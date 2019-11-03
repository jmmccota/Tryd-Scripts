// Indicador: Indicador de volume de compras
// Author: JMMCCota

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
		// bar.setFill(255,0,0); // vermelho
		// bar.setBorder(255,0,0);
		if (cOpen > cClose) {
            bar.setFill(196, 0, 0); // Vermelho
            bar.setBorder(128, 0, 0); // Cinza
        } else {
            bar.setFill(0, 196, 0); // Verde
            bar.setBorder(128, 128, 128); // Cinza
        }
	}else if (res > orange) {
		// bar.setFill(255,128,0); // laranja
		// bar.setBorder(255,128,0);
		if (cOpen > cClose) {
            bar.setFill(255, 128, 0); // Vermelho
            bar.setBorder(128, 0, 0); // Cinza
        } else {
            bar.setFill(149, 255, 0); // Vermelho
            bar.setBorder(0, 128, 0); // Cinza
        }
	}else if (res > yellow) { // amarelo
		// bar.setFill(250,244,2);
		// bar.setBorder(250,244,2);

        if (cOpen > cClose) {
            bar.setFill(250,244,2); // Vermelho
            bar.setBorder(250, 155, 2); // Cinza
        } else {
            bar.setFill(149, 255, 0); // Vermelho
            bar.setBorder(159, 250, 2); // Cinza
        }	
	}else if (res > white) { // branco
		bar.setFill(255,255,255);
		bar.setBorder(255,255,255);
		
	}else {
		bar.setFill(0,252,252); // Azul
		bar.setBorder(0,252,252);
		
	}
}
