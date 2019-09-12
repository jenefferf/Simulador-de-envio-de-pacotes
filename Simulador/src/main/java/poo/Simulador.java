package poo;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Simulador {

    private ArrayList<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
    private ArrayList<Pacote> pacotes = new ArrayList<Pacote>();



    public boolean criaAssociaDispositivo(String arqTopologia) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(arqTopologia));
        String linha = new String();
        String[] retornoSplit;
        Comutador c;

        while (linha != null) {
            linha = leitor.readLine();
            if(linha == null ) break;
            retornoSplit = linha.split(" -- ");

            if (retornoSplit.length != 2)
                return false;

            c = new Comutador(retornoSplit[0]);
            if( !dispositivos.contains(c)){
                dispositivos.add(c);
            }
            else{
                for(Dispositivo sw : dispositivos){
                    if(sw.getNomeDispositivo().compareTo(c.getNomeDispositivo())==0){
                      c = (Comutador) sw;
                    }
                }
            }



            retornoSplit = retornoSplit[1].substring(retornoSplit[1].indexOf("{") + 1, retornoSplit[1].indexOf("}")).split(" ");

            for(String dispositivo :retornoSplit){
                if (dispositivo.charAt(0) == 'd'){
                    Endpoint e = new Endpoint(dispositivo);
                    if(! dispositivos.contains(e)){
                        dispositivos.add(e);
                        e.associaDispositivos(c);
                        c.associaDispositivos(e);
                    }
                }
                else if (dispositivo.charAt(0) == 's'){
                    Comutador s = new Comutador(dispositivo);
                    if(! dispositivos.contains(s)) {
                        dispositivos.add(s);
                        c.associaDispositivos(s);
                        s.associaDispositivos(c);
                    } //Se contém, só faz a associacao
                    else{
                        for(Dispositivo sw : dispositivos){
                            if(sw.getNomeDispositivo().compareTo(s.getNomeDispositivo())==0){
                                c.associaDispositivos(sw);
                                sw.associaDispositivos(c);
                            }
                        }

                    }
                }
                else {
                    return false;
                }
            }

        }

        return true;

    }

    public boolean criaPacotes(String arqTrafego) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(arqTrafego));
        String linha = new String();
        String[] retornoSplit;

        while (linha != null) {
            linha = leitor.readLine();
            if (linha == null) break;
            retornoSplit = linha.split(":");

            if (retornoSplit.length != 3) {
                return false;
            }
            Pacote p = new Pacote(retornoSplit[0], retornoSplit[1], retornoSplit[2]);
            pacotes.add(p);
        }

        return true;
    }

    public void addPacotesNaOrigem(){
            for (Pacote pack : pacotes){
                for(Dispositivo disp: dispositivos) {
                    if (pack.getOrigem().compareTo(disp.getNomeDispositivo()) == 0) {
                        disp.addPacote(pack);
                    }
                }
            }


    }

    public boolean  rodaSimulacao(){
        boolean aindaTemPacotes = true;
        do{
            for(int i = 0; i<dispositivos.size();i++){
               dispositivos.get(i).encaminhaPacote();
            }
            aindaTemPacotes = false;

            for(int i = 0; i<dispositivos.size();i++){
                if(!dispositivos.get(i).pacotes.isEmpty()){
                    aindaTemPacotes = true;
                    break;
                }
            }

        }while(aindaTemPacotes);

        return true;

    }


    public String toString(){
        String equipamentos = new String();
        for(Dispositivo d : dispositivos){
            equipamentos += d.toString() + "\n";
        }
        return "Simulacao finalizada: \n" + equipamentos;
    }


}

