// @Title: Indicador: Indicador de volume de compras
// @layout: fundo escuro
// @Author: JMMCCota
// @Version: 0.1.3

// Settings //
def mediaAbertura = 9;
def mediaFechamento = 17;
def mediaInterna = 20;
def hiloValor = 20;
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
def mF = MA(v, mediaFechamento,1);

for (int i = 0; i < barras.size(); i++) {
    def pBar = barras.bar(i);
	def abertura = pBar.getOpen();
	def fechamento = pBar.getClose();
		
	if (abertura < mediaAbertura && fechamento < mediaAbertura && fechamento > mediaInterna) {
 		pBar.setFill(0, 0, 222); 
		pBar.setBorder(0, 0, 222); 
	} else if (abertura > mediaAbertura && fechamento < mediaAbertura && fechamento < mediaInterna) {
		pBar.setFill(0, 0, 0); 
		pBar.setBorder(0, 0, 0); 
	} else if (fechamento > mediaFechamento) {
   		pBar.setFill(0, 222, 0); 
		pBar.setBorder(0, 222, 0); 
	} else if (fechamento < mediaFechamento){
		pBar.setFill(255,204,204); 
		pBar.setBorder(255,204,204); 
	}
}
