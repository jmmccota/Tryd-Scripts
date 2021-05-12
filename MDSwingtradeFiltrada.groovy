// @Title: MDSwingtradeFiltrada
// @layout: fundo claro
// @Author: JMMCCota
// @Version: 0.1.3

// Settings //
def mediaAbertura = 9;
def mediaFechamento = 20;
def hiloValor = 20;
// End //

def barras = BARS();

r = newLines();
r.add( barras );

/**
Inicio
  Se (Abertura < MediaExp(9,Close)[1]) e (Fechamento > MediaExp(9,Close)[1]) e (Fechamento > HILOACTIVATOR(20)[1]) então
    PaintBar(clAzul)
  Senão Se (Fechamento > Media(20,Close)[1]) então
    PaintBar(clVerdeClaro)
  Senão Se (Fechamento < Media(20,Close)[1]) então
    PaintBar(RGB(255,204,204));
Fim;
*/
def v  = newLineData();
def fechamentos = close();

for(int i = 0; i < fechamentos.size(); i++){
  v.add(fechamentos.value(i));
}


def mA = MA(v, mediaAbertura,1);
def mF = MA(v, mediaFechamento,1);
def hiloAct = HILO(hiloValor);

for (int i = 0; i < barras.size(); i++) {
    def pBar = barras.bar(i);
	def abertura = pBar.getOpen();
	def fechamento = pBar.getClose();
		
	if(abertura < mA.value(i) && fechamento > mA.value(i) && fechamento > hiloAct.value(i) ) {
		pBar.setFill(0, 0, 222); 
		pBar.setBorder(0, 0, 222); 
	} else if (fechamento > mF.value(i)) {
		pBar.setFill(0, 222,0); 
		pBar.setBorder(0, 222,0); 
	} else if(fechamento > mF.value(i)) {
		pBar.setFill(255,204,204);
		pBar.setBorder(255,204,204);
	}
}
