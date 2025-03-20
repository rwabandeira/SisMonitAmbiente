public class ProcessadorDadosSensor {
  // Constantes de valores de referência
  private static final int vlrTempBaixa = 20;
  private static final int vlrTempAlta = 32;
  private static final int vlrLumBaixa = 20;
  private static final int vlrUmidArBaixa = 30;
  private static final int vlrUmidArAlta = 60;
  private static final int vlrUmidSoloBaixa = 20;
  private static final int vlrUmidSoloAlta = 60;

  // Constantes de mensagens de alerta
  private static final String msgTempAlta = "ALERTA: Temperatura elevada (%s °C). Ar-condicionado ligado.";
  private static final String msgTempBaixa = "ALERTA: Temperatura baixa (%s °C). Aquecedor ligado.";
  private static final String msgTempNormal = "Status: Temperatura normal (%s °C). Sistemas desligados.";
  private static final String msgLumBaixa = "ALERTA: Luminosidade baixa (%d Lux). Luzes ligadas.";
  private static final String msgLumAlta = "Status: Luminosidade alta (%d Lux). Luzes desligadas.";
  private static final String msgUmidArBaixa = "ALERTA: Umidade do ar baixa (%d%%). Umidificador ligado.";
  private static final String msgUmidArAlta = "ALERTA: Umidade do ar alta (%d%%). Desumidificador ligado.";
  private static final String msgUmidArNormal = "Status: Umidade do ar normal (%d%%). Sistemas desligados.";
  private static final String msgUmidSoloBaixa = "ALERTA: Umidade do solo baixa (%d%%). Irrigacao ligado.";
  private static final String msgUmidSoloAlta = "ALERTA: Umidade do solo alta (%d%%). Drenagem ligado.";
  private static final String msgUmidSoloNormal = "Status: Umidade do solo normal (%d%%). Sistemas desligados.";

  public String gerarDadosTeste() {
      double temperatura = 10 + (Math.random() * 32);
      int luminosidade = 10 + (int) (Math.random() * 20);
      int umidadeAr = 20 + (int) (Math.random() * 60);
      int umidadeSolo = 10 + (int) (Math.random() * 60);
      return "Temperatura: " + String.format("%.2f", temperatura) + " ºC / " +
              "Luminosidade: " + luminosidade + " Lux / " +
              "Umidade do ar: " + umidadeAr + "% / " +
              "Umidade do solo: " + umidadeSolo + "%";
  }

  public void processarDadosSensor(String dados) {
      String[] valores = dados.split(" / ");
      if (valores.length >= 4) {
          try {
              String temperaturaStr = valores[0].split(": ")[1].replace(" ºC", "");
              String luminosidadeStr = valores[1].split(": ")[1].replace(" Lux", "");
              String umidadeArStr = valores[2].split(": ")[1].replace("%", "");
              String umidadeSoloStr = valores[3].split(": ")[1].replace("%", "");

              double temperatura = Double.parseDouble(temperaturaStr);
              int luminosidade = Integer.parseInt(luminosidadeStr);
              int umidadeAr = Integer.parseInt(umidadeArStr);
              int umidadeSolo = Integer.parseInt(umidadeSoloStr);

              System.out.println("_________________________________________________");
              System.out.printf("Temperatura: %.2f ºC ", temperatura);
              if (temperatura >= vlrTempAlta || temperatura <= vlrTempBaixa) {
                  System.out.print("(ALERTA)");
              }
              System.out.println();

              System.out.printf("Luminosidade: %d Lux ", luminosidade);
              if (luminosidade <= vlrLumBaixa) {
                  System.out.print("(ALERTA)");
              }
              System.out.println();

              System.out.printf("Umidade do ar: %d%% ", umidadeAr);
              if (umidadeAr <= vlrUmidArBaixa || umidadeAr >= vlrUmidArAlta) {
                  System.out.print("(ALERTA)");
              }
              System.out.println();

              System.out.printf("Umidade do solo: %d%% ", umidadeSolo);
              if (umidadeSolo <= vlrUmidSoloBaixa || umidadeSolo >= vlrUmidSoloAlta) {
                  System.out.print("(ALERTA)");
              }
              System.out.println();
              System.out.println();

              if (temperatura >= vlrTempAlta) {
                  System.out.println(String.format(msgTempAlta, temperatura));
              } else if (temperatura <= vlrTempBaixa) {
                  System.out.println(String.format(msgTempBaixa, temperatura));
              } else {
                  System.out.println(String.format(msgTempNormal, temperatura));
              }

              if (luminosidade <= vlrLumBaixa) {
                  System.out.println(String.format(msgLumBaixa, luminosidade));
              } else {
                  System.out.println(String.format(msgLumAlta, luminosidade));
              }

              if (umidadeAr <= vlrUmidArBaixa) {
                  System.out.println(String.format(msgUmidArBaixa, umidadeAr));
              } else if (umidadeAr >= vlrUmidArAlta) {
                  System.out.println(String.format(msgUmidArAlta, umidadeAr));
              } else {
                  System.out.println(String.format(msgUmidArNormal, umidadeAr));
              }

              if (umidadeSolo <= vlrUmidSoloBaixa) {
                  System.out.println(String.format(msgUmidSoloBaixa, umidadeSolo));
              } else if (umidadeSolo >= vlrUmidSoloAlta) {
                  System.out.println(String.format(msgUmidSoloAlta, umidadeSolo));
              } else {
                  System.out.println(String.format(msgUmidSoloNormal, umidadeSolo));
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