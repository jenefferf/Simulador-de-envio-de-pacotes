package poo;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Dispositivo {

    private String nomeDispositivo;
    protected ArrayList<Dispositivo> dispositivoAssociados = new ArrayList<Dispositivo>();
    protected LinkedList<Pacote> pacotes = new LinkedList<Pacote>();
    protected int totalPacote = 0;
    protected int pacotesDecartados = 0;

    public Dispositivo(String nomeDispositivo) {
        this.nomeDispositivo = nomeDispositivo;
    }


    public abstract void addPacote(Pacote p);


    public String getNomeDispositivo() {
        return nomeDispositivo;
    }

    public abstract boolean encaminhaPacote();


    public  abstract void removePacote(Pacote p);



    @Override
    public boolean equals(Object obj) {
        Dispositivo disp = (Dispositivo) obj;
        return this.getNomeDispositivo().equals(disp.getNomeDispositivo());
    }

    public String pacoteEncaminhado(){
        return "";
    }

    @Override
    public String toString(){
        return "Dispositivo: " + getNomeDispositivo() + " Pacote processados: " + totalPacote + " Pacote descartados: " + pacotesDecartados;
    }

    public abstract boolean associaDispositivos(Dispositivo c);
}

