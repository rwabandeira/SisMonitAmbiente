public class ProcessadorDadosSensor {

    public String gerarDadosTeste() {
        double temperatura = 25 + Math.random() * 5;
        int umidade = 40 + (int) (Math.random() * 20);
        int luminosidade = (int) (Math.random() * 1024);
        return "Temperatura: " + String.format("%.2f", temperatura) + "\tUmidade: " + umidade + "%\tLuminosidade: " + luminosidade;
    }

    public void processarDadosSensor(String dados) {
        String[] valores = dados.split("\t");
        if (valores.length >= 3) {
            try {
                String temperatura = valores[0].split(": ")[1];
                String umidade = valores[1].split(": ")[1];
                String luminosidade = valores[2].split(": ")[1];

                System.out.println("_________________________________________________");
                System.out.println("Temperatura: " + temperatura + "°C");
                System.out.println("Umidade: " + umidade);
                System.out.println("Luminosidade: " + luminosidade + " Lux");
                System.out.println();

                double valorTemp = Double.parseDouble(temperatura);
                int valorUmidade = Integer.parseInt(umidade.replace("%", ""));
                int valorLuminosidade = Integer.parseInt(luminosidade);

                if (valorTemp >= 27) {
                    System.out.println("ALERTA: Temperatura elevada (" + temperatura + "°C). Sistema de refrigeração ativado.");
                }

                if (valorUmidade <= 45) {
                    System.out.println("ALERTA: Umidade baixa (" + umidade + "). Sistema de humidificação ativado.");
                }

                if (valorLuminosidade >= 512) {
                    System.out.println("ALERTA: Luminosidade alta (" + luminosidade + "). Luzes apagadas.");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Erro ao processar dados do sensor: Formato inválido. Dados recebidos: " + dados);
            }
        } else {
            System.err.println("Erro ao processar dados do sensor: Dados incompletos. Dados recebidos: " + dados);
        }
    }
}