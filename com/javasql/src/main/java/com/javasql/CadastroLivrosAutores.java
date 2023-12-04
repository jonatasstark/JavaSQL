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

            stmt = conn.createStatement();
            stmt.executeUpdate(sqlLivros);
            System.out.println("Tabela 'livros' criada com sucesso!");

            // Criação da tabela autores
            String sqlAutores = "CREATE TABLE IF NOT EXISTS autores (" +
                    "id_autor INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_autor VARCHAR(255) NOT NULL" +
                    ")";

            stmt.executeUpdate(sqlAutores);
            System.out.println("Tabela 'autores' criada com sucesso!");

            // Inserir dados na tabela livros
            String tituloLivro = "Livro A";
            int anoPublicacao = 2021;
            String sqlInsertLivro = "INSERT INTO livros (titulo, ano_publicacao) VALUES (?, ?)";

            pstmt = conn.prepareStatement(sqlInsertLivro);
            pstmt.setString(1, tituloLivro);
            pstmt.setInt(2, anoPublicacao);
            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'livros'!");

            // Inserir dados na tabela autores
            String nomeAutor = "Autor A";
            String sqlInsertAutor = "INSERT INTO autores (nome_autor) VALUES (?)";

            pstmt = conn.prepareStatement(sqlInsertAutor);
            pstmt.setString(1, nomeAutor);
            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'autores'!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}