// @Title: Indicador: Indicador de barras de volume de compras com calculo volume refinado
// @Author: JMMCCota
// @Version: 0.1.6

def mLines = 1; // 0-Disable 1-Enable
def len = 27;
// Fim Parametros de configuracao //

def v  = newLineData();
def l5 = newLineData();

def vol = volume();

def ma = MA(vol, len, 0);


for (int i=len;i<vol.size();i++) {
	if (vol.value(i) > ma.value(i-len)) {
		v.setColor(255,128,0);
	} else {
		v.setColor(64,64,64);
	}

	v.add(vol.value(i));
	v.setType(3);
}

def result = newLines();
result.add(v);

if (mLines){
  for (int i=len;i<vol.size();i++) {
    if(ma.value(i) != null){
      l5.add(ma.value(i));
    }
    l5.setColor(255,0,0);   
  }
  result.add(l5);
}

r = result;
