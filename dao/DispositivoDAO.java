package dao;

import db.ConexionBD;
import model.Dispositivo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DispositivoDAO {

    public List<Dispositivo> listarTodos() throws SQLException {
        List<Dispositivo> lista = new ArrayList<>();
        String sql = "SELECT * FROM dispositivo";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) lista.add(mapearDispositivo(rs));
        }
        return lista;
    }

    public Dispositivo buscarPorCodigo(String codDispositivo) throws SQLException {
        String sql = "SELECT * FROM dispositivo WHERE cod_dispositivo = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codDispositivo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapearDispositivo(rs);
        }
        return null;
    }

    public boolean insertar(Dispositivo d) throws SQLException {
        String sql = "INSERT INTO dispositivo (cod_dispositivo, mac, num_serie, sistema_operativo, ip, tipo, cod_empleado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getCodDispositivo());
            ps.setString(2, d.getMac());
            ps.setString(3, d.getNumSerie());
            ps.setString(4, d.getSistemaOperativo());
            ps.setString(5, d.getIp());
            ps.setString(6, d.getTipo());
            ps.setString(7, d.getCodEmpleado());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Dispositivo d) throws SQLException {
        String sql = "UPDATE dispositivo SET mac=?, num_serie=?, sistema_operativo=?, ip=?, tipo=?, cod_empleado=? WHERE cod_dispositivo=?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getMac());
            ps.setString(2, d.getNumSerie());
            ps.setString(3, d.getSistemaOperativo());
            ps.setString(4, d.getIp());
            ps.setString(5, d.getTipo());
            ps.setString(6, d.getCodEmpleado());
            ps.setString(7, d.getCodDispositivo());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean borrar(String codDispositivo) throws SQLException {
        String sql = "DELETE FROM dispositivo WHERE cod_dispositivo = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codDispositivo);
            return ps.executeUpdate() > 0;
        }
    }

    private Dispositivo mapearDispositivo(ResultSet rs) throws SQLException {
        return new Dispositivo(
                rs.getString("cod_dispositivo"),
                rs.getString("mac"),
                rs.getString("num_serie"),
                rs.getString("sistema_operativo"),
                rs.getString("ip"),
                rs.getString("tipo"),
                rs.getString("cod_empleado")
        );
    }
}