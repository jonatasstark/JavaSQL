package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultadosExamesPacientes {
    public static void main(String[] args) {
        // Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela resultados_exames
            String sqlResultadosExames = "CREATE TABLE IF NOT EXISTS resultados_exames (" +
                    "id_resultado INT AUTO_INCREMENT PRIMARY KEY," +
                    "tipo_exame VARCHAR(255) NOT NULL," +
                    "resultado VARCHAR(255) NOT NULL" +
                    ")";

            stmt = conn.createStatement();
            stmt.executeUpdate(sqlResultadosExames);
            System.out.println("Tabela 'resultados_exames' criada com sucesso!");

            // Criação da tabela pacientes
            String sqlPacientes = "CREATE TABLE IF NOT EXISTS pacientes (" +
                    "id_paciente INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_paciente VARCHAR(255) NOT NULL," +
                    "data_nascimento DATE NOT NULL" +
                    ")";

            stmt.executeUpdate(sqlPacientes);
            System.out.println("Tabela 'pacientes' criada com sucesso!");

            // Inserir dados na tabela resultados_exames
            String tipoExame = "Exame A";
            String resultado = "Positivo";
            String sqlInsertResultado = "INSERT INTO resultados_exames (tipo_exame, resultado) VALUES (?, ?)";

            pstmt = conn.prepareStatement(sqlInsertResultado);
            pstmt.setString(1, tipoExame);
            pstmt.setString(2, resultado);
            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'resultados_exames'!");

            // Inserir dados na tabela pacientes
            String nomePaciente = "Paciente A";
            String dataNascimento = "1990-01-01";
            String sqlInsertPaciente = "INSERT INTO pacientes (nome_paciente, data_nascimento) VALUES (?, ?)";

            pstmt = conn.prepareStatement(sqlInsertPaciente);
            pstmt.setString(1, nomePaciente);
            pstmt.setString(2, dataNascimento);
            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'pacientes'!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}