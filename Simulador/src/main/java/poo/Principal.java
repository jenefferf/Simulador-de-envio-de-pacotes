package poo;

import java.io.IOException;
import java.util.Scanner;

public class Principal {


    public static void main(String[] args) throws IOException {

        String entradaUser;
        int opcao;
        Scanner teclado = new Scanner(System.in);
        Simulador s = new Simulador();

        while(true) {
            System.out.println("Bem vindo ao simulador de redes. Selecione uma das opções: ");
            System.out.println("1. Enviar arquivo de topologia da rede");
            System.out.println("2. Enviar arquivo com tráfego da rede");
            System.out.println("3. Iniciar simulação (necessário ter encaminhado os dois arquivos anteriormente)");
            opcao = teclado.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Entre com o arquivo de topologia da rede \n");
                    entradaUser = teclado.next();
                    if (!s.criaAssociaDispositivo(entradaUser)) {
                        System.out.println("O arquivo possui inconsistencias e o programa será finalizado");
                    }
                    break;
                case 2:
                    System.out.println("Entre com o arquivo de tráfego da rede \n");
                    entradaUser = teclado.next();
                    if (!s.criaPacotes(entradaUser)) {
                        System.out.println("O arquivo possui inconsistencias e o programa será finalizado");
                    }
                    break;
                case 3:
                    s.addPacotesNaOrigem();
                    if (s.rodaSimulacao()) {
                        System.out.println(s.toString());
                    }
                    break;
                default:
                    System.out.println("Entrada inválida \n");

            }
        }

    }
}



