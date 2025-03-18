public class Principal {
  public static void main(String[] args) {
    ReceptorDadosSensor receptor = new ReceptorDadosSensor("COM1");
    receptor.iniciar();
    receptor.fechar();
  }
}