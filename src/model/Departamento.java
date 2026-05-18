package model;

public class Departamento {
    private String codDep;
    private String nomDep;
    private double presupuesto;
    private String codDirector;

    public Departamento(String codDep, String nomDep, double presupuesto, String codDirector) {
        this.codDep = codDep;
        this.nomDep = nomDep;
        this.presupuesto = presupuesto;
        this.codDirector = codDirector;
    }

    public String getCodDep() { return codDep; }
    public void setCodDep(String codDep) { this.codDep = codDep; }

    public String getNomDep() { return nomDep; }
    public void setNomDep(String nomDep) { this.nomDep = nomDep; }

    public double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }

    public String getCodDirector() { return codDirector; }
    public void setCodDirector(String codDirector) { this.codDirector = codDirector; }

    @Override
    public String toString() {
        return "[" + codDep + "] " + nomDep + " | Presupuesto: " + presupuesto + " | Director: " + codDirector;
    }
}