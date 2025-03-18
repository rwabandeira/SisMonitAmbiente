public class ProcessadorDadosSensor {

  public String gerarDadosTeste() {
    double temperatura = 25 + Math.random() * 5;
    int luminosidade = (int) (Math.random() * 1024);
    int umidadeAr = 40 + (int) (Math.random() * 20);
    int umidadeSolo = 40 + (int) (Math.random() * 20);
    return "Temperatura: " + String.format("%.2f", temperatura) + "ºC / " +
        "Luminosidade: " + luminosidade + "Lux / " +
        "Umidade do ar: " + umidadeAr + "% / " +
        "Umidade do solo: " + umidadeSolo + "%";
  }

  public void processarDadosSensor(String dados) {
    String[] valores = dados.split(" / ");
    if (valores.length >= 4) {
      try {
        String temperaturaStr = valores[0].split(": ")[1].replace("ºC", ""); // Remove "ºC"
        String luminosidadeStr = valores[1].split(": ")[1].replace("Lux", ""); // Remove "Lux"
        String umidadeArStr = valores[2].split(": ")[1].replace("%", "");
        String umidadeSoloStr = valores[3].split(": ")[1].replace("%", "");

        double temperatura = Double.parseDouble(temperaturaStr);
        int luminosidade = Integer.parseInt(luminosidadeStr);
        int umidadeAr = Integer.parseInt(umidadeArStr);
        int umidadeSolo = Integer.parseInt(umidadeSoloStr);

        System.out.println("_________________________________________________");
        System.out.printf("Temperatura: %.2f ºC ", temperatura);
        if (temperatura >= 27) {
          System.out.print("(ALERTA)");
        }
        System.out.println();

        System.out.printf("Luminosidade: %d Lux ", luminosidade);
        if (luminosidade >= 512) {
          System.out.print("(ALERTA)");
        }
        System.out.println();

        System.out.printf("Umidade do ar: %d %% ", umidadeAr);
        if (umidadeAr <= 45) {
          System.out.print("(ALERTA)");
        }
        System.out.println();

        System.out.printf("Umidade do solo: %d %% ", umidadeSolo);
        if (umidadeSolo <= 45) {
          System.out.print("(ALERTA)");
        }
        System.out.println();
        System.out.println();

        if (temperatura >= 27) {
          System.out.println("ALERTA: Temperatura elevada (" + temperatura + "°C). Sistema de refrigeração ativado.");
        }

        if (luminosidade >= 512) {
          System.out.println("ALERTA: Luminosidade alta (" + luminosidade + "). Luzes apagadas.");
        }

        if (umidadeAr <= 45) {
          System.out.println("ALERTA: Umidade do ar baixa (" + umidadeAr + "). Sistema de humidificação ativado.");
        }

        if (umidadeSolo <= 45) {
          System.out.println("ALERTA: Umidade do solo baixa (" + umidadeSolo + "). Sistema de irrigação ativado.");
        }
      } catch (NumberFormatException e) {
        System.err.println("Erro de conversão numérica: " + e.getMessage() + ". Dados recebidos: " + dados);
      } catch (ArrayIndexOutOfBoundsException e) {
        System.err.println("Erro de índice do array: " + e.getMessage() + ". Dados recebidos: " + dados);
      } catch (Exception e) {
        System.err.println("Erro inesperado: " + e.getMessage() + ". Dados recebidos: " + dados);
      }
    } else {
      System.err.println("Erro ao processar dados do sensor: Dados incompletos. Dados recebidos: " + dados);
    }
  }
}