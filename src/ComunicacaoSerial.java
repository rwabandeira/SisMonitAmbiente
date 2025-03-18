import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ComunicacaoSerial {
  private SerialPort portaSerial;
  private PortaSerialSimulada portaSerialSimulada;

  // Construtor para comunicação com porta serial real
  public ComunicacaoSerial(String nomePorta) {
    SerialPort[] portasDisponiveis = SerialPort.getCommPorts();
    for (SerialPort porta : portasDisponiveis) {
      if (porta.getSystemPortName().equals(nomePorta)) {
        this.portaSerial = porta;
        break;
      }
    }
    if (this.portaSerial == null) {
      System.err.println("Porta serial '" + nomePorta + "' não encontrada.");
    }
  }

  // Construtor para comunicação com porta serial simulada
  public ComunicacaoSerial(PortaSerialSimulada portaSerialSimulada) {
    this.portaSerialSimulada = portaSerialSimulada;
    if (this.portaSerialSimulada == null) {
      System.err.println("Porta serial simulada não inicializada.");
    }
  }

  public boolean inicializar() {
    if (portaSerial != null) {
      if (portaSerial.openPort()) {
        portaSerial.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        System.out.println("Porta serial '" + portaSerial.getSystemPortName() + "' aberta com sucesso.");
        return true;
      } else {
        System.err.println("Erro ao abrir a porta serial '" + portaSerial.getSystemPortName() + "'.");
        return false;
      }
    } else if (portaSerialSimulada != null) {
      portaSerialSimulada.abrirPorta();
      portaSerialSimulada.definirParametrosPorta(9600, 8, 1, 0); // Simula a configuração
      return true;
    }
    return false;
  }

  @SuppressWarnings("resource")
  public String lerDados() {
    try {
      if (portaSerial != null && portaSerial.isOpen()) {
        InputStream fluxoEntrada = portaSerial.getInputStream();
        Scanner scanner = new Scanner(fluxoEntrada);
        if (scanner.hasNextLine()) {
          return scanner.nextLine();
        }
      } else if (portaSerialSimulada != null && portaSerialSimulada.estaAberta()) {
        InputStream fluxoEntrada = portaSerialSimulada.obterFluxoEntrada();
        Scanner scanner = new Scanner(fluxoEntrada);
        if (scanner.hasNextLine()) {
          return scanner.nextLine();
        }
      }
      // Adiciona tempo limite de leitura (1 segundo)
      TimeUnit.SECONDS.sleep(1);
    } catch (Exception e) {
      System.err.println("Erro ao ler dados da porta serial: " + e.getMessage());
    }
    return null;
  }

  public void fechar() {
    if (portaSerial != null && portaSerial.isOpen()) {
      if (portaSerial.closePort()) {
        System.out.println("Porta serial '" + portaSerial.getSystemPortName() + "' fechada.");
      } else {
        System.err.println("Erro ao fechar a porta serial '" + portaSerial.getSystemPortName() + "'.");
      }
    } else if (portaSerialSimulada != null && portaSerialSimulada.estaAberta()) {
      portaSerialSimulada.fecharPorta();
    }
  }
}