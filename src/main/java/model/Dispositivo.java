package model;

public class Dispositivo {
    private String codDispositivo;
    private String mac;
    private String numSerie;
    private String sistemaOperativo;
    private String ip;
    private String tipo;
    private String codEmpleado;

    public Dispositivo(String codDispositivo, String mac, String numSerie,
                       String sistemaOperativo, String ip, String tipo, String codEmpleado) {
        this.codDispositivo = codDispositivo;
        this.mac = mac;
        this.numSerie = numSerie;
        this.sistemaOperativo = sistemaOperativo;
        this.ip = ip;
        this.tipo = tipo;
        this.codEmpleado = codEmpleado;
    }

    public String getCodDispositivo() { return codDispositivo; }
    public void setCodDispositivo(String codDispositivo) { this.codDispositivo = codDispositivo; }

    public String getMac() { return mac; }
    public void setMac(String mac) { this.mac = mac; }

    public String getNumSerie() { return numSerie; }
    public void setNumSerie(String numSerie) { this.numSerie = numSerie; }

    public String getSistemaOperativo() { return sistemaOperativo; }
    public void setSistemaOperativo(String so) { this.sistemaOperativo = so; }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getCodEmpleado() { return codEmpleado; }
    public void setCodEmpleado(String codEmpleado) { this.codEmpleado = codEmpleado; }

    @Override
    public String toString() {
        return "[" + codDispositivo + "] " + tipo + " | IP: " + ip +
                " | SO: " + sistemaOperativo + " | Responsable: " + codEmpleado;
    }
}