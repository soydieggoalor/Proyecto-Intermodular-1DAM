package dao;

import db.ConexionBD;
import model.Incidencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaDAO {

    public List<Incidencia> listarTodos() throws SQLException {
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM incidencias";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) lista.add(mapearIncidencia(rs));
        }
        return lista;
    }

    public List<Incidencia> listarPorEstado(String estado) throws SQLException {
        List<Incidencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM incidencias WHERE estado = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapearIncidencia(rs));
        }
        return lista;
    }

    public boolean insertar(Incidencia i) throws SQLException {
        String sql = "INSERT INTO incidencias (cod_incidencia, observaciones, estado, fecha_alta, fecha_cierre, cod_dispositivo, cod_responsable, cod_creador) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, i.getCodIncidencia());
            ps.setString(2, i.getObservaciones());
            ps.setString(3, i.getEstado());
            ps.setDate(4, Date.valueOf(i.getFechaAlta()));
            ps.setDate(5, i.getFechaCierre() != null ? Date.valueOf(i.getFechaCierre()) : null);
            ps.setString(6, i.getCodDispositivo());
            ps.setString(7, i.getCodResponsable());
            ps.setString(8, i.getCodCreador());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Incidencia i) throws SQLException {
        String sql = "UPDATE incidencias SET observaciones=?, estado=?, fecha_cierre=?, cod_responsable=? WHERE cod_incidencia=?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, i.getObservaciones());
            ps.setString(2, i.getEstado());
            ps.setDate(3, i.getFechaCierre() != null ? Date.valueOf(i.getFechaCierre()) : null);
            ps.setString(4, i.getCodResponsable());
            ps.setString(5, i.getCodIncidencia());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean borrar(String codIncidencia) throws SQLException {
        String sql = "DELETE FROM incidencias WHERE cod_incidencia = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codIncidencia);
            return ps.executeUpdate() > 0;
        }
    }

    private Incidencia mapearIncidencia(ResultSet rs) throws SQLException {
        Date fechaCierre = rs.getDate("fecha_cierre");
        return new Incidencia(
                rs.getString("cod_incidencia"),
                rs.getString("observaciones"),
                rs.getString("estado"),
                rs.getDate("fecha_alta").toLocalDate(),
                fechaCierre != null ? fechaCierre.toLocalDate() : null,
                rs.getString("cod_dispositivo"),
                rs.getString("cod_responsable"),
                rs.getString("cod_creador")
        );
    }
}