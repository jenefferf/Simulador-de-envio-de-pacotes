# Relatório de análise de requisitos

## Escopo do projeto

​	O projeto consiste em um sistema que simula o tráfego de uma rede de computadores. Para realizar a simulação é necessário que o sistema faça a leitura de dois arquivos de texto  contendo a topologia e o tráfego de pacotes da rede o sistema encaminha os pacotes entre os dispositivos da rede até dispositivo destino. Durante  a simulação o sistema informa ao usuário os caminhos que os pacotes estão percorrendo entre os dispositivos e, ao final, apresenta as estatísticas de processamento de pacotes por cada dispositivo da topologia da rede. 

  * **Requisitos funcionais**

    R.1 - O sistema deve permitir a execução de simulação de um tráfego de pacotes em uma rede.

    R.2 - O sistema deve exibir em tela todas as comunicações entre os dispositivos da rede.

    R.3 -  O sistema deve exibir um relatório, ao final da simulação, informando quantos pacotes foram processados e descartados por cada dispositivos da rede.

    R.4 - O sistema deve conseguir realizar as simulações a partir da leitura dos arquivos de topologia e tráfego da rede.

    R.5 - Cada dispositivo da rede deve possuir uma fila para armazenar e encaminhar os pacotes.

    R.6 -  O sistema deve ser desenvolvido em linguagem JAVA de programação.

    R.7 -  O sistema deve abortar o programa caso existam inconsistências nos arquivos de tráfego e topologia da rede.

  * **Requisitos não funcionais:**

    R.1 - Os arquivos de tráfego e topologia devem possuir extensão .txt.

    R.2 - Os arquivos deverão ser informados via terminal do compilador 

    R.3 - O resultado da simulação deverá aparecer na saída do terminal do compilador


## Regras de negócio

| **RN01 - Conexões máximas de um endpoint**                   |
| ------------------------------------------------------------ |
| Possui uma única interface de rede                           |
| **RN02** - **Conexões máximas de um comutador**              |
| Permite  até 24 conexões                                     |
| **RN03 -** **Conexões do endpoint**                          |
| Deve estar, obrigatoriamente, conectado a um comutador       |
| **RN04 - Conexões do comutador**                             |
| Deve estar conectado no mínimo em uma interface sendo que esta poderá ser um endpoint ou outro comutador |
| **RN05 -  Formato dos dispositivos da rede no arquivo de topologia** |
| Cada dispositivo deverá possuir um identificador único, sendo que os comutadores devem iniciar com o caracter `s` e os endopoints com caractere `d` |
| **RN06 - Validação do arquivo  de topologia da rede**        |
| Se o arquivo violar algumas das regras de negócios previstas nas RN 01 à 05 o programa será abortado |
| **RN07 - Formato dos dispositivos da rede no arquivo de tráfego de pacotes** |
| Como previsto na RN05 todos os dispositivos deverão possuir identificadores únicos |
| **RN08 - Formato do pacote no arquivo de tráfego de pacotes** |
| As origens e destinos deverão ser endpoints                  |
| **RN09 - Validação do arquivo de tráfego de pacotes**        |
| Se o arquivo violar algumas das regras de negócios previstas nas RN 07 à 08 o programa será abortado |
| **RN10 - Iniciando a simulação**                             |
| Para iniciar o programa o usuário deverá, obrigatoriamente, informar os arquivos com a topologia da rede e com o tráfego dos pacotes |
| **RN11 - Simulação dos eventos**                             |
| Ao iniciar a simulação dos eventos, o programa irá exebir em tela os tráfegos entre os dispositivos da rede. Ao final da simulção serão exibidos quantos pacotes foram processados e descartados por cada dispositivos da rede. |
| **RN12 - TTL dos pacotes**                                   |
| Todos pacotes possuem, inicialmente, TTL(*time to live*) igual a três. A cada envio do pacote para um comutador o TLL é decrementado em um |
| **RN13 - Pacotes cuja o TTL foi excedido**                   |
| Os pacotes que possuirem TTL igual a zero antes de chegar no destino serão descartados. |
| **RN14 - Encaminhamento dos pacotes**                        |
| Os pacotes serão encaminhados apenas pelos comutadores, sendo estes dispositivos que irão decrementar o TTL do pacote |
| **RN15 - Destino e origem dos pacotes**                      |
| Os destinos dos pacotes são apenas dispositivos do tipo endpoint |
| **RN16 - Descarte de pacotes do pelo endpoints**             |
| Um pacote que chegar ao endpoint cuja sua origem ou destino não sejam este endpoint será descartado. |
| **RN17 - Caminho do pacote**                                 |
| O caminho percorrido pelos pacotes na rede serão apresentados em tela durante a execução da simulação. |
| **RN18 - Final da Simulação**                                |
| Ao final da simulação o sistema imprime em tela um relatório informado os pacotes processados e descartados por todos dispositivos do sistema. |

## Modelos

  * **Casos de uso**

    | Enviar arquivo da topologia da rede (CSU01)                  |
    | ------------------------------------------------------------ |
    | Sumário: O usuário encaminha o arquivo com a topologia da rede |
    | Ator Primário: Usuário do sistema                            |
    | Ator Secundário: Sistema de processamento de arquivos        |
    | Fluxo Principal:  <br />1. O sistema pede ao usuário que informe o arquivo com a topologia da rede<br />2. O usuário informa o endereço do arquivo ou o nome do arquivo (caso o arquivo esteja na pasta raíz do programa) |
    | Fluxo de Exceção: **Violação da RN06**<br />a. O sistema informa ao usuário que o arquivo possui inconsistências e que o programa será abortado. |
    | Pós condições: O sistema faz a leitura do arquivo            |
    | Regras de Negócio: RN01, RN02, RN03, RN04, RN05, RN06        |

    | **Enviar arquivo com tráfego da rede (CSU02)**               |
    | ------------------------------------------------------------ |
    | Sumário: O usuário informa ao sistema o arquivo com o tráfego da rede |
    | Ator Primário: Usuário do sistema                            |
    | Ator Secundário: Sistema de processamento de arquivos        |
    | Fluxo Principal:  <br />1. O sistema pede para o usuário informar o arquivo com o tráfego da rede<br />2. O usuário informa o endereço do arquivo ou o nome do arquivo (caso o arquivo esteja na pasta raíz do programa) |
    | Fluxo de Exceção **Violação da RN09**:<br />a. O sistema informa ao usuário que o arquivo possui inconsistências e que o programa será abortado. |
    | Pós condições: O sistema faz a leitura do arquivo            |
    | Regras de Negócio: RN07, RN08,RN09                           |

    | Iniciar simulação (CS03)                                     |
    | ------------------------------------------------------------ |
    | Sumário: O usuário inicia a simulação do tráfego de rede     |
    | Ator Primário: Usuário do sistema                            |
    | Ator Secundário: Sistema de processamento da simulação       |
    | Fluxo Principal: <br />1. O sistema dá a opção do usuário iniciar a simulação<br />2. O usuário inicia a simulação<br />3. O sistema mostra em tela as interações dos pacotes entre os componentes da rede<br />4. Ao final do simulação do sistema mostra ao usuário quantos pacotes foram processados e descartados por cada dispositivos da rede. |
    | Pós condições: O usuário possui um relatório do tráfego da rede |
    | Regras de Negócio: RN10, RN11                                |


  * **Matriz de rastreabilidade**

| Casos de Uso x Requisitos funcionais | R.1 | R.2 | R.3 | R.4 | R.7 |
|--------------------------------------|-----|-----|-----|-----|-----|
| CSU01                                |     |     |     | X   | X   |
| CSU02                                |     |     |     | X   | X   |
| CSU03                                | X   | X   | X   |     |     |

  * **Diagrama de caso de uso UML**


  ![Diagrama casos de uso](https://github.com/POO29004-classroom/projeto-pratico-03-2019-01-jenefferf/blob/master/relatorio/casosDeUso.png)

## Diagrama de classes UML
 ![Diagrama de classes](https://github.com/POO29004-classroom/projeto-pratico-03-2019-01-jenefferf/blob/master/relatorio/SimuladorDeEventos.png)

## Diagrama de sequência UML
 ![Diagrama de classes](https://github.com/POO29004-classroom/projeto-pratico-03-2019-01-jenefferf/blob/master/relatorio/DiagramaDeSequencia.png)

​    

