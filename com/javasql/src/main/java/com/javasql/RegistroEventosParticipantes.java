package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroEventosParticipantes {
    public static void main(String[] args) {
        // Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela eventos
            String sqlEventos = "CREATE TABLE IF NOT EXISTS eventos (" +
                    "id_evento INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_evento VARCHAR(255) NOT NULL," +
                    "data DATE NOT NULL" +
                    ")";

            stmt = conn.createStatement();
            stmt.executeUpdate(sqlEventos);
            System.out.println("Tabela 'eventos' criada com sucesso!");

            // Criação da tabela participantes
            String sqlParticipantes = "CREATE TABLE IF NOT EXISTS participantes (" +
                    "id_participante INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_evento INT NOT NULL," +
                    "nome_participante VARCHAR(255) NOT NULL," +
                    "FOREIGN KEY (id_evento) REFERENCES eventos(id_evento)" +
                    ")";

            stmt.executeUpdate(sqlParticipantes);
            System.out.println("Tabela 'participantes' criada com sucesso!");

            // Inserir dados na tabela eventos
            String nomeEvento = "Evento A";
            String data = "2022-01-01";
            String sqlInsertEvento = "INSERT INTO eventos (nome_evento, data) VALUES (?, ?)";

            pstmt = conn.prepareStatement(sqlInsertEvento);
            pstmt.setString(1, nomeEvento);
            pstmt.setString(2, data);
            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'eventos'!");

            // Inserir dados na tabela participantes
            int idEvento = 1;
            String nomeParticipante = "Participante A";
            String sqlInsertParticipante = "INSERT INTO participantes (id_evento, nome_participante) VALUES (?, ?)";

            pstmt = conn.prepareStatement(sqlInsertParticipante);
            pstmt.setInt(1, idEvento);
            pstmt.setString(2, nomeParticipante);
            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'participantes'!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}
