public class ReceptorDadosSensor {
    private boolean modoTeste = true; // true para modo teste, false para hardware físico
    private ComunicacaoSerial comunicacaoSerial;
    private ProcessadorDadosSensor processadorDados;

    public ReceptorDadosSensor(String nomePorta) {
        this.comunicacaoSerial = new ComunicacaoSerial(nomePorta);
        this.processadorDados = new ProcessadorDadosSensor();
    }

    public void iniciar() {
        if (!modoTeste && !comunicacaoSerial.inicializar()) {
            return;
        }

        System.out.println("Iniciando a leitura de dados da porta serial...");
        System.out.println();

        while (true) {
            String dados = modoTeste ? processadorDados.gerarDadosTeste() : comunicacaoSerial.lerDados();
            if (dados != null) {
                processadorDados.processarDadosSensor(dados);
                try {
                    Thread.sleep(modoTeste ? 5000 : 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void fechar() {
        if (!modoTeste) {
            comunicacaoSerial.fechar();
        }
    }
}
