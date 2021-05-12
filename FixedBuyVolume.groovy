// @Title: Indicador: Indicador de volume de compras fixo
// @Author: JMMCCota
// @Version: 0.1.2

// Settings //
def altoVolumeNegocios = 100000;
// End //

def barras = BARS();

r = newLines();
r.add( barras );

def volumeNegociosPorTempo = volume();

for (int i = 1; i < volumeNegociosPorTempo.size(); i++) {
	def bar = barras.bar(i);
	def res = 0;
    
    def cOpen = bar.getOpen();
    def cClose = bar.getClose();

	res = volumeNegociosPorTempo.value(i);

	if (res > altoVolumeNegocios) {
		if (cOpen > cClose) {
            bar.setFill(196, 0, 0); 
            bar.setBorder(196, 0, 0); 
        } else {
            bar.setFill(0, 196, 0); 
            bar.setBorder(0, 196, 0); 
        }
	} else {
		bar.setFill(216,219,221); 
		bar.setBorder(216,219,221);		
	}
}
