package dao;

import db.ConexionBD;
import model.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    public List<Departamento> listarTodos() throws SQLException {
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM departamento";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Departamento(
                        rs.getString("cod_dep"),
                        rs.getString("nom_dep"),
                        rs.getDouble("presupuesto"),
                        rs.getString("cod_director")
                ));
            }
        }
        return lista;
    }

    public Departamento buscarPorCodigo(String codDep) throws SQLException {
        String sql = "SELECT * FROM departamento WHERE cod_dep = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codDep);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Departamento(
                        rs.getString("cod_dep"),
                        rs.getString("nom_dep"),
                        rs.getDouble("presupuesto"),
                        rs.getString("cod_director")
                );
            }
        }
        return null;
    }

    public boolean insertar(Departamento d) throws SQLException {
        String sql = "INSERT INTO departamento (cod_dep, nom_dep, presupuesto, cod_director) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getCodDep());
            ps.setString(2, d.getNomDep());
            ps.setDouble(3, d.getPresupuesto());
            ps.setString(4, d.getCodDirector());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Departamento d) throws SQLException {
        String sql = "UPDATE departamento SET nom_dep = ?, presupuesto = ?, cod_director = ? WHERE cod_dep = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getNomDep());
            ps.setDouble(2, d.getPresupuesto());
            ps.setString(3, d.getCodDirector());
            ps.setString(4, d.getCodDep());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean borrar(String codDep) throws SQLException {
        String sql = "DELETE FROM departamento WHERE cod_dep = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codDep);
            return ps.executeUpdate() > 0;
        }
    }
}