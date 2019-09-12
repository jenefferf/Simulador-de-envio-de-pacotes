package poo;

import java.util.ListIterator;

public class Endpoint extends Dispositivo {


    public Endpoint(String nomeDispositivo) {
        super(nomeDispositivo);
    }

    @Override
    public boolean associaDispositivos(Dispositivo d){
        if(dispositivoAssociados.size() == 0){
            dispositivoAssociados.add(d);
        } else return false;
        return true;
    }

    @Override
    public void addPacote(Pacote p) {
        pacotes.add(p);

    }

    @Override
    public void removePacote(Pacote p) {
        pacotes.remove(p);
    }

    @Override
    public boolean encaminhaPacote() {
        if(pacotes.isEmpty()) return false;


        ListIterator<Pacote> iterator = pacotes.listIterator();


        while(iterator.hasNext()) {
            Pacote pacote = iterator.next();
            if(pacote.getDestino().compareTo(getNomeDispositivo()) == 0){
                iterator.remove();
                totalPacote++;
                System.out.println("Dispositivo " + getNomeDispositivo() + " recebeu os dados " + pacote.getPayload() );
                return true;
            }
            else if(pacote.getOrigem().compareTo(getNomeDispositivo()) == 0){
                dispositivoAssociados.get(0).addPacote(pacote);
                iterator.remove();
                totalPacote++;
                System.out.println("Encaminhando pacote do dispositivo " + getNomeDispositivo() + " para " + dispositivoAssociados.get(0).getNomeDispositivo());
                return true;

            }
            else{
                iterator.remove();
                totalPacote++;
                pacotesDecartados++;
                return true;

            }

        }

        return true;
    }
}

