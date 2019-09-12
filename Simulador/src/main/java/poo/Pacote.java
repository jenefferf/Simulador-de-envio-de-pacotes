package poo;

public class Pacote {

    private String origem;
    private String destino;
    private String payload;
    private String ultimoDispositivo = "";
    private int TTL = 3;

    public Pacote(String origem, String destino, String payload) {
        this.origem = origem;
        this.destino = destino;
        this.payload = payload;
        this.TTL = TTL;
    }

    public int getTTL() {
        return TTL;
    }

    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public String getPayload() {
        return payload;
    }

    public String getUltimoDispositivo() {
        return ultimoDispositivo;
    }

    public void setUltimoDispositivo(String ultimoDispositivo) {
        this.ultimoDispositivo = ultimoDispositivo;
    }

    @Override
    public String toString() {
        return  " Origem " + origem + " Payload " + payload + " Destino " + destino;

    }
}
