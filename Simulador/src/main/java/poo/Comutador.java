package poo;

import java.util.*;

public class Comutador extends Dispositivo {


    public Comutador(String nomeDispositivo) {
        super(nomeDispositivo);
    }

    @Override
    public boolean associaDispositivos ( Dispositivo d) {
            if (dispositivoAssociados.size() <= 24) {
                dispositivoAssociados.add(d);
                return true;
            } else return false;
        }

            @Override
            public void addPacote (Pacote p) {
                pacotes.add(p);
            }

            @Override
            public void removePacote (Pacote p) {
                pacotes.remove(p);
            }

    @Override
    public boolean encaminhaPacote() {
        if(pacotes.isEmpty()){
            return false;
        }

        ListIterator<Pacote> iterator = pacotes.listIterator();


        while(iterator.hasNext()){
            Pacote pacote = iterator.next();

            if (pacote.getTTL() == 0){
                        removePacote(pacote);
                        pacotesDecartados++;
                        totalPacote++;
                        return true;
                    }
            for(Dispositivo dispositivoAssociado : dispositivoAssociados){
                if ((dispositivoAssociado.getNomeDispositivo().compareTo(pacote.getDestino()) == 0)){
                    dispositivoAssociado.addPacote(pacote);
                    iterator.remove();
                   System.out.println("Encaminhando pacote de dispositivo "+ getNomeDispositivo() + " Payload "+ pacote.getPayload() + " para dispositivo " + dispositivoAssociado.getNomeDispositivo());
                    totalPacote++;
                    return true;
                }
            }
            String ultimo_remetente = pacote.getUltimoDispositivo();
            pacote.setUltimoDispositivo(getNomeDispositivo());
            boolean enviou = false;
            for(Dispositivo dispositivoAssociado : dispositivoAssociados){
                if(dispositivoAssociado instanceof Comutador && dispositivoAssociado.getNomeDispositivo().compareTo(ultimo_remetente)!= 0 ){
                    dispositivoAssociado.addPacote(pacote);
                  System.out.println("Encaminhando pacote de dispositivo "+ getNomeDispositivo() + " Payload "+ pacote.getPayload() + " para dispositivo " + dispositivoAssociado.getNomeDispositivo());
                    totalPacote++;
                    enviou = true;

                }
                else{
                    iterator.remove();
                    totalPacote++;
                    pacotesDecartados++;
                    return true;
                }
            }
            iterator.remove();
            return enviou;

        }
        return false;
    }

}
