public class ReceptorDadosSensor {
  private boolean modoTeste = true; // true para modo teste, false para hardware físico
  private ComunicacaoSerial comunicacaoSerial;
  private ProcessadorDadosSensor processadorDados;
  private PortaSerialSimulada portaSerialSimulada;

  public ReceptorDadosSensor(String nomePorta) {
    this.processadorDados = new ProcessadorDadosSensor();
    if (modoTeste) {
      this.portaSerialSimulada = new PortaSerialSimulada(nomePorta);
      this.comunicacaoSerial = new ComunicacaoSerial(portaSerialSimulada);
    } else {
      this.comunicacaoSerial = new ComunicacaoSerial(nomePorta);
    }
  }

  public void iniciar() {
    if (!modoTeste && !comunicacaoSerial.inicializar()) {
      return;
    } else if (modoTeste && !comunicacaoSerial.inicializar()) {
      return;
    }

    System.out.println("Iniciando a leitura de dados da porta serial...");
    System.out.println();

    while (true) {
      String dados = modoTeste ? processadorDados.gerarDadosTeste() : comunicacaoSerial.lerDados();
      if (dados != null) {
        dados = dados.replace(",", ".");
        try {
          exibirDados(dados);
        } catch (Exception e) {
          System.err.println("Erro ao processar dados: " + e.getMessage());
        }
        try {
          Thread.sleep(modoTeste ? 5000 : 1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public void fechar() {
    comunicacaoSerial.fechar();
  }

  private void exibirDados(String dados) {
    processadorDados.processarDadosSensor(dados);
  }
}