package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CadastroLivrosAutores {
    public static void main(String[] args) {
        // Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela livros
            String sqlLivros = "CREATE TABLE IF NOT EXISTS livros (" +
                    "id_livro INT AUTO_INCREMENT PRIMARY KEY," +
                    "titulo VARCHAR(255) NOT NULL," +
                    "ano_publicacao INT NOT NULL" +
                    ")";

            Statement stmtLivros = conn.createStatement();
            stmtLivros.executeUpdate(sqlLivros);
            System.out.println("Tabela 'livros' criada com sucesso!");

            // Criação da tabela autores
            String sqlAutores = "CREATE TABLE IF NOT EXISTS autores (" +
                    "id_autor INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_autor VARCHAR(255) NOT NULL" +
                    ")";

            Statement stmtAutores = conn.createStatement();
            stmtAutores.executeUpdate(sqlAutores);
            System.out.println("Tabela 'autores' criada com sucesso!");

            // Inserir dados na tabela livros
            String tituloLivro = "Aprendendo Python";
            int anoPublicacao = 2020;
            String sqlInsertLivro = "INSERT INTO livros (titulo, ano_publicacao) VALUES (?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sqlInsertLivro);
            pstmt.setString(1, tituloLivro);
            pstmt.setInt(2, anoPublicacao);
            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'livros'!");

            String tituloLivro2 = "Introdução à Inteligência Artificial";
            int anoPublicacao2 = 2019;
            String sqlInsertLivro2 = "INSERT INTO livros (titulo, ano_publicacao) VALUES (?, ?)";

            PreparedStatement pstmt2 = conn.prepareStatement(sqlInsertLivro2);
            pstmt2.setString(1, tituloLivro2);
            pstmt2.setInt(2, anoPublicacao2);
            pstmt2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'livros'!");

            // Inserir dados na tabela autores
            String nomeAutor = "Carlos Silva";
            String sqlInsertAutor = "INSERT INTO autores (nome_autor) VALUES (?)";

            PreparedStatement pstmt3 = conn.prepareStatement(sqlInsertAutor);
            pstmt3.setString(1, nomeAutor);
            pstmt3.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'autores'!");

            String nomeAutor2 = "Ana Souza";
            String sqlInsertAutor2 = "INSERT INTO autores (nome_autor) VALUES (?)";

            PreparedStatement pstmt4 = conn.prepareStatement(sqlInsertAutor2);
            pstmt4.setString(1, nomeAutor2);
            pstmt4.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'autores'!");

            deletarDadosLivros(conn, 1);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }

    // função para deletar um livro com base no ID
    public static void deletarDadosLivros(Connection conn, int idLivro) {
        String sql = "DELETE FROM livros WHERE id_livro = " + idLivro; // remove uma linha da tabela

        try (Statement stmt = conn.createStatement()) {
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("Livro deletado com sucesso");

                String sql2 = "DELETE FROM autores WHERE id_autor = " + idLivro;

                if (stmt.executeUpdate(sql2) > 0) { // se o id inserido for encontrado, os autores também serão removidos
                    System.out.println("Autores relacionados ao livro deletado também foram removidos");
                }
            } else {
                System.out.println("Erro ao deletar, verifique o id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}