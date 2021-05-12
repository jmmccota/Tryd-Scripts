// @Title: Indicador: Indicador de volume de compras
// @layout: fundo escuro
// @Author: JMMCCota
// @Version: 0.1.3

// Settings //
def mediaAbertura = 9;
// End //

def barras = BARS();

r = newLines();
r.add( barras );

def v  = newLineData();
def fechamentos = close();

for(int i = 0; i < fechamentos.size(); i++){
  v.add(fechamentos.value(i));
}

def mA = MA(v, mediaAbertura,1);

for (int i = 0; i < barras.size(); i++) {
    def pBar = barras.bar(i);
	def abertura = pBar.getOpen();
	def fechamento = pBar.getClose();
		

	if(	abertura < mA.value(i) && fechamento > mA.value(i)){
		pBar.setFill(0, 0, 222); 
		pBar.setBorder(0, 0, 222);
	} else if(abertura > mA.value(i) && fechamento < mA.value(i)) {
		pBar.setFill(222, 222, 0); 
		pBar.setBorder(222, 222, 0);
	} 
}
