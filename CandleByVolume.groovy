// @Title: Indicador: Indicador de volume de compras com calculo volume refinado
// @Author: JMMCCota
// @Version: 0.1.4


/**
  Parametros de configuração
*/
def len = 400;
def orange = 2.5;
def yellow = 1;
def white = -0.5;

def mLines = 1; // 0-Disable 1-Enable
// Fim Parametros de configuracao //
def v  = newLineData();

def vol = volume();
def mean = MA(vol,len, 0);
def std = STDDEV(vol,len);

def barras = BARS();

r = newLines();
r.add( barras );

for (int i=len;i<vol.size();i++) {
	if (i>len) {
		double res = ( (vol.value(i) - mean.value(i-len+1)) / (std.value(i-len)));

		double vatual = vol.value(i); 
		double matual = mean.value(i-len+1);
		double desvp  = std.value(i-len);

		double vw=0.0;
		double vy=0.0;
		double vo=0.0;

		def bar = barras.bar(i);
		def cOpen = bar.getOpen();
		def cClose = bar.getClose();
		def cVolume = bar.getVolume();

		vw = matual+desvp*white;

		vy = matual+desvp*yellow;

		vo = matual+desvp*orange;

		if (res > orange){
			if (cOpen > cClose) {
				bar.setFill(222, 0, 0); 
				bar.setBorder(222, 0, 0); 
			} else {
				bar.setFill(0,222,0);
				bar.setBorder(0,222,0);
			}
		} else if (res > yellow) {
			if (cOpen > cClose) {
				bar.setFill(255, 255, 255); 
				bar.setBorder(222, 0, 0); 
			} else {
				bar.setFill(255,255,255);
				bar.setBorder(0,222,0);
			}
		} else {
			bar.setFill(222, 222, 222); 
			bar.setBorder(222, 222, 222); 
		}    
	} 
}
