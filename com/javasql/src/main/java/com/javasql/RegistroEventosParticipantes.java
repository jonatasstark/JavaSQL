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

            Statement stmtEventos = conn.createStatement();
            stmtEventos.executeUpdate(sqlEventos);
            System.out.println("Tabela 'eventos' criada com sucesso!");

            // Criação da tabela participantes
            String sqlParticipantes = "CREATE TABLE IF NOT EXISTS participantes (" +
                    "id_participante INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_evento INT NOT NULL," +
                    "nome_participante VARCHAR(255) NOT NULL," +
                    "FOREIGN KEY (id_evento) REFERENCES eventos(id_evento)" +
                    ")";

            Statement stmtParticipantes = conn.createStatement();        
            stmtParticipantes.executeUpdate(sqlParticipantes);
            System.out.println("Tabela 'participantes' criada com sucesso!");

            // Inserir dados na tabela eventos
            String nomeEvento = "Conferência de Tecnologia";
            String data = "2023-12-15";
            String sqlInsertEvento = "INSERT INTO eventos (nome_evento, data) VALUES (?, ?)";

            PreparedStatement pstmtEventos = conn.prepareStatement(sqlInsertEvento);
            pstmtEventos.setString(1, nomeEvento);
            pstmtEventos.setString(2, data);
            pstmtEventos.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'eventos'!");

            String nomeEvento2 = "Workshop de Marketing Digital";
            String data2 = "2023-11-20";
            String sqlInsertEvento2 = "INSERT INTO eventos (nome_evento, data) VALUES (?, ?)";

            PreparedStatement pstmtEventos2 = conn.prepareStatement(sqlInsertEvento2);
            pstmtEventos2.setString(1, nomeEvento2);
            pstmtEventos2.setString(2, data2);
            pstmtEventos2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'eventos'!");

            // Inserir dados na tabela participantes
            int idEvento = 1;
            String nomeParticipante = "Gabriel";
            String sqlInsertParticipante = "INSERT INTO participantes (id_evento, nome_participante) VALUES (?, ?)";

            PreparedStatement pstmtParticipantes = conn.prepareStatement(sqlInsertParticipante);
            pstmtParticipantes.setInt(1, idEvento);
            pstmtParticipantes.setString(2, nomeParticipante);
            pstmtParticipantes.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'participantes'!");

            int idEvento2 = 2;
            String nomeParticipante2 = "Sofia";
            String sqlInsertParticipante2 = "INSERT INTO participantes (id_evento, nome_participante) VALUES (?, ?)";

            PreparedStatement pstmtParticipantes2 = conn.prepareStatement(sqlInsertParticipante2);
            pstmtParticipantes2.setInt(1, idEvento2);
            pstmtParticipantes2.setString(2, nomeParticipante2);
            pstmtParticipantes2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'participantes'!");

            deletarDadosEventos(conn, 1);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }


    // função para deletar um evento com base no ID
    public static void deletarDadosEventos(Connection conn, int idEvento){
        String sql = "DELETE FROM eventos WHERE id_evento = " + idEvento; // remove uma linha da tabela

        try (Statement stmt = conn.createStatement()) {
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("Evento deletado com sucesso");

                String sql2 = "DELETE FROM participantes WHERE id_evento = " + idEvento;

                if (stmt.executeUpdate(sql2) > 0) { // se o id inserido for encontrado, os participantes também serão removidos
                    System.out.println("Participantes relacionados ao evento deletado também foram removidos");
                }
            } else {
                System.out.println("Erro ao deletar, verifique o id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
