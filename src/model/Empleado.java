package model;

import java.time.LocalDate;

public class Empleado {
    private String codEmpleado;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String rol;
    private LocalDate fechaContratacion;
    private double salario;
    private String codDep;
    private String mail;

    public Empleado(String codEmpleado, String dni, String nombre, String apellido1,
                    String apellido2, String rol, LocalDate fechaContratacion,
                    double salario, String codDep, String mail) {
        this.codEmpleado = codEmpleado;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.rol = rol;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.codDep = codDep;
        this.mail = mail;
    }

    public String getCodEmpleado() { return codEmpleado; }
    public void setCodEmpleado(String codEmpleado) { this.codEmpleado = codEmpleado; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido1() { return apellido1; }
    public void setApellido1(String apellido1) { this.apellido1 = apellido1; }

    public String getApellido2() { return apellido2; }
    public void setApellido2(String apellido2) { this.apellido2 = apellido2; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public String getCodDep() { return codDep; }
    public void setCodDep(String codDep) { this.codDep = codDep; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    @Override
    public String toString() {
        return "[" + codEmpleado + "] " + nombre + " " + apellido1 +
                " | Rol: " + rol + " | Dep: " + codDep + " | Salario: " + salario;
    }
}