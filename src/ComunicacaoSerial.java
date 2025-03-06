import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;
import java.util.Scanner;

public class ComunicacaoSerial {
    private SerialPort portaSerial;

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
        }
        return false;
    }

    @SuppressWarnings("resource")
    public String lerDados() {
        if (portaSerial != null && portaSerial.isOpen()) {
            InputStream fluxoEntrada = portaSerial.getInputStream();
            Scanner scanner = new Scanner(fluxoEntrada);
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
            scanner.close();
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
        }
    }
}