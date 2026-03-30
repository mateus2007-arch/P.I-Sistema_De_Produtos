/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author mateu
 */
import model.Produto;
import util.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto p){
        String sql = "INSERT INTO produtos (nome,categoria,marca,preco,estoque) VALUES (?,?,?,?,?)";

        try(Connection con = Conexao.conectar();
            PreparedStatement stmt = con.prepareStatement(sql)){

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCategoria());
            stmt.setString(3, p.getMarca());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getEstoque());

            stmt.execute();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    
        public List<Produto> buscarPorCategoria(String categoria) {

        List<Produto> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE categoria = ?";

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, categoria);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getString("categoria"));
                p.setMarca(rs.getString("marca"));
                p.setPreco(rs.getDouble("preco"));
                p.setEstoque(rs.getInt("estoque"));

                lista.add(p);
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("Erro filtro: " + e.getMessage());
        }

        return lista;
    }
    public List<Produto> buscarPorNome(String nome) {

    List<Produto> lista = new ArrayList<>();

    String sql = "SELECT * FROM produtos WHERE nome LIKE ?";

    try {
        Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, "%" + nome + "%");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setCategoria(rs.getString("categoria"));
            p.setMarca(rs.getString("marca"));
            p.setPreco(rs.getDouble("preco"));
            p.setEstoque(rs.getInt("estoque"));

            lista.add(p);
        }

        conn.close();

    } catch (Exception e) {
        System.out.println("Erro busca: " + e.getMessage());
    }

        return lista;
    }
    
    public ArrayList<Produto> listar(){
        ArrayList<Produto> lista = new ArrayList<>();

        try(Connection con = Conexao.conectar();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM produtos")){

            while(rs.next()){
                Produto p = new Produto();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getString("categoria"));
                p.setMarca(rs.getString("marca"));
                p.setPreco(rs.getDouble("preco"));
                p.setEstoque(rs.getInt("estoque"));

                lista.add(p);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public void deletar(int id) {

        String sql = "DELETE FROM produtos WHERE id = ?";

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.execute();

            conn.close();

        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }

    public void atualizar(Produto p){
        String sql = "UPDATE produtos SET nome=?, categoria=?, marca=?, preco=?, estoque=? WHERE id=?";

        try(Connection con = Conexao.conectar();
            PreparedStatement stmt = con.prepareStatement(sql)){

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCategoria());
            stmt.setString(3, p.getMarca());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getEstoque());
            stmt.setInt(6, p.getId());

            stmt.execute();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}