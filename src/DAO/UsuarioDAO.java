/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author mateu
 */

import java.sql.*;
import model.Usuario;
import util.Conexao;

public class UsuarioDAO {

    public Usuario login(String login, String senha) {

        Usuario usuario = null;

        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, login);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setTipo(rs.getString("tipo"));
            }

            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro login: " + e.getMessage());
        }

        return usuario;
    }
}
        
