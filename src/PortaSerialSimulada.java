import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

public class PortaSerialSimulada {
  private boolean estaAberta = false;
  private String nomePorta;
  private Random random = new Random();

  public PortaSerialSimulada(String nomePorta) {
    this.nomePorta = nomePorta;
  }

  public boolean abrirPorta() {
    try {
      estaAberta = true;
      System.out.println("Porta serial " + nomePorta + " mock aberta.");
      return true;
    } catch (Exception e) {
      System.err.println("Erro ao abrir a porta serial " + nomePorta + ": " + e.getMessage());
      return false;
    }
  }

  public void definirParametrosPorta(int taxaBaud, int bitsDados, int bitsParada, int paridade) {
    // Simula a configuração dos parâmetros da porta
  }

  public InputStream obterFluxoEntrada() {
    if (random.nextDouble() < 0.1) { // Simula erro de leitura (10% de chance)
      return new FluxoEntradaSimuladoErro();
    } else {
      return new FluxoEntradaSimulado();
    }
  }

  public boolean estaAberta() {
    return estaAberta;
  }

  public boolean fecharPorta() {
    try {
      estaAberta = false;
      System.out.println("Porta serial " + nomePorta + " mock fechada.");
      return true;
    } catch (Exception e) {
      System.err.println("Erro ao fechar a porta serial " + nomePorta + ": " + e.getMessage());
      return false;
    }
  }

  private class FluxoEntradaSimulado extends InputStream {
    private String dados = gerarDadosSimulados();
    private ByteArrayInputStream fluxoEntrada = new ByteArrayInputStream(dados.getBytes());

    @Override
    public int read() {
      return fluxoEntrada.read();
    }

    @Override
    public int available() {
      return fluxoEntrada.available();
    }

    private String gerarDadosSimulados() {
      double temperatura = 25 + random.nextGaussian() * 2;
      int luminosidade = (int) (random.nextDouble() * 1024);
      int umidadeAr = 40 + (int) (random.nextGaussian() * 10);
      int umidadeSolo = 40 + (int) (random.nextGaussian() * 10);
      return String.format(
          "Temperatura: %.2f / Luminosidade: %d / Umidade do ar: %d%% / Umidade do solo: %d%%\n",
          temperatura, luminosidade, umidadeAr, umidadeSolo);
    }
  }

  private class FluxoEntradaSimuladoErro extends InputStream {
    private String erro = "ERRO: Falha na leitura de dados.\n";
    private ByteArrayInputStream fluxoEntradaErro = new ByteArrayInputStream(erro.getBytes());

    @Override
    public int read() {
      return fluxoEntradaErro.read();
    }

    @Override
    public int available() {
      return fluxoEntradaErro.available();
    }
  }
}