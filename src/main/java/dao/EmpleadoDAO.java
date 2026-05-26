package dao;

import db.ConexionBD;
import model.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    public List<Empleado> listarTodos() throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) lista.add(mapearEmpleado(rs));
        }
        return lista;
    }

    public Empleado buscarPorCodigo(String codEmpleado) throws SQLException {
        String sql = "SELECT * FROM empleado WHERE cod_empleado = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codEmpleado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapearEmpleado(rs);
        }
        return null;
    }

    public List<Empleado> listarPorDepartamento(String codDep) throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado WHERE cod_dep = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codDep);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapearEmpleado(rs));
        }
        return lista;
    }

    public boolean insertar(Empleado e) throws SQLException {
        String sql = "INSERT INTO empleado (cod_empleado, dni, nombre, apellido_1, apellido_2, rol, fecha_contratacion, salario, cod_dep, mail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getCodEmpleado());
            ps.setString(2, e.getDni());
            ps.setString(3, e.getNombre());
            ps.setString(4, e.getApellido1());
            ps.setString(5, e.getApellido2());
            ps.setString(6, e.getRol());
            ps.setDate(7, Date.valueOf(e.getFechaContratacion()));
            ps.setDouble(8, e.getSalario());
            ps.setString(9, e.getCodDep());
            ps.setString(10, e.getMail());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Empleado e) throws SQLException {
        String sql = "UPDATE empleado SET dni=?, nombre=?, apellido_1=?, apellido_2=?, rol=?, fecha_contratacion=?, salario=?, cod_dep=?, mail=? WHERE cod_empleado=?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getDni());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellido1());
            ps.setString(4, e.getApellido2());
            ps.setString(5, e.getRol());
            ps.setDate(6, Date.valueOf(e.getFechaContratacion()));
            ps.setDouble(7, e.getSalario());
            ps.setString(8, e.getCodDep());
            ps.setString(9, e.getMail());
            ps.setString(10, e.getCodEmpleado());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean borrar(String codEmpleado) throws SQLException {
        String sql = "DELETE FROM empleado WHERE cod_empleado = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codEmpleado);
            return ps.executeUpdate() > 0;
        }
    }

    private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
        return new Empleado(
                rs.getString("cod_empleado"),
                rs.getString("dni"),
                rs.getString("nombre"),
                rs.getString("apellido_1"),
                rs.getString("apellido_2"),
                rs.getString("rol"),
                rs.getDate("fecha_contratacion").toLocalDate(),
                rs.getDouble("salario"),
                rs.getString("cod_dep"),
                rs.getString("mail")
        );
    }
}