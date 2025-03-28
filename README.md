# Sistema de Monitoramento Ambiental

Este projeto Java simula um sistema de monitoramento ambiental que lê e processa dados de sensores, como temperatura, luminosidade, umidade do ar e umidade do solo, utilizando uma porta serial simulada ou uma porta serial real.

## Funcionalidades

* **Leitura de dados de sensores:** O projeto lê dados de sensores simulados ou reais através de uma porta serial.
* **Processamento de dados:** Os dados lidos são processados para extrair os valores de temperatura, luminosidade e umidade.
* **Geração de alertas:** O projeto gera alertas caso os valores dos sensores estejam fora de faixas predefinidas.
* **Simulação de porta serial:** O projeto inclui uma simulação de porta serial para testes e desenvolvimento sem hardware físico.
* **Simulação de erros de leitura:** O projeto simula erros de leitura da porta serial para testar o tratamento de erros.
* **Comunicação com porta serial real:** O projeto também pode se comunicar com uma porta serial real para leitura de dados de sensores físicos.

## Classes Principais

* **Principal:** Classe principal que inicia a aplicação.
* **ComunicacaoSerial:** Classe responsável pela comunicação com a porta serial (simulada ou real).
* **PortaSerialSimulada:** Classe que simula uma porta serial para testes.
* **ProcessadorDadosSensor:** Classe que processa os dados dos sensores e gera alertas.
* **ReceptorDadosSensor:** Classe que orquestra a leitura e o processamento dos dados dos sensores.

## Como Executar

1.  **Clone o repositório:**

    ```bash
    git clone [https://github.com/rwabandeira/SisMonitAmbiente.git](https://github.com/rwabandeira/SisMonitAmbiente.git)
    ```

2.  **Compile o código:**

    ```bash
    javac *.java
    ```

3.  **Execute a aplicação:**

    ```bash
    java Principal
    ```

## Configuração

* A classe `ReceptorDadosSensor` possui uma variável `modoTeste` que controla se a aplicação usa a porta serial simulada (`true`) ou a porta serial real (`false`).
* A porta serial utilizada pode ser configurada no construtor da classe `ReceptorDadosSensor`.

## Dependências

* [fazecast/jSerialComm](https://github.com/fazecast/jSerialComm) (para comunicação com porta serial real)

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests para melhorar o projeto.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).