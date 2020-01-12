// @Title: Indicador: Indicador de barras de volume de compras com calculo volume refinado
// @Author: JMMCCota
// @Version: 0.1.5

def len = 200;
def orange = 4;

def mLines = 0; // 0-Disable 1-Enable
// Fim Parametros de configuracao //

def v  = newLineData();
def l5 = newLineData(); //orange

def vol = volume();
def mean = MA(vol,len, 0);
def std = STDDEV(vol,len);

for (int i=len;i<vol.size();i++) {
	if (i>len) {
		double res = ( (vol.value(i) - mean.value(i-len+1)) / (std.value(i-len)));

		double vatual = vol.value(i); 
		double matual = mean.value(i-len+1);
		double desvp  = std.value(i-len);

		double val=0.0;

		if (mLines){
			val=matual+desvp*orange;
			l5.add(val);
			l5.setColor(255,0,0);   
		}
		if (res > orange) {
			v.setColor(255,128,0);
		} else {
			v.setColor(64,64,64);
		}

		v.add(vatual);
		v.setType(3);
	}
}
def result = newLines();
result.add(v);
if (mLines){
	result.add(l5);
}
r = result;