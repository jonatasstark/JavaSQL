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

            Statement stmtExames = conn.createStatement();
            stmtExames.executeUpdate(sqlResultadosExames);
            System.out.println("Tabela 'resultados_exames' criada com sucesso!");

            // Criação da tabela pacientes
            String sqlPacientes = "CREATE TABLE IF NOT EXISTS pacientes (" +
                    "id_paciente INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_paciente VARCHAR(255) NOT NULL," +
                    "data_nascimento DATE NOT NULL" +
                    ")";

            Statement stmtPacientes = conn.createStatement();            
            stmtPacientes.executeUpdate(sqlPacientes);
            System.out.println("Tabela 'pacientes' criada com sucesso!");

            // Inserir dados na tabela resultados_exames
            String tipoExame = "Exame de Sangue";
            String resultado = "normal";
            String sqlInsertResultado = "INSERT INTO resultados_exames (tipo_exame, resultado) VALUES (?, ?)";

            PreparedStatement pstmtExames = conn.prepareStatement(sqlInsertResultado);
            pstmtExames.setString(1, tipoExame);
            pstmtExames.setString(2, resultado);
            pstmtExames.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'resultados_exames'!");

            String tipoExame2 = "Raio-X";
            String resultado2 = "Fratura identificada";
            String sqlInsertResultado2 = "INSERT INTO resultados_exames (tipo_exame, resultado) VALUES (?, ?)";

            PreparedStatement pstmtExames2 = conn.prepareStatement(sqlInsertResultado2);
            pstmtExames2.setString(1, tipoExame2);
            pstmtExames2.setString(2, resultado2);
            pstmtExames2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'resultados_exames'!");

            // Inserir dados na tabela pacientes
            String nomePaciente = "Mariana";
            String dataNascimento = "1995-06-10";
            String sqlInsertPaciente = "INSERT INTO pacientes (nome_paciente, data_nascimento) VALUES (?, ?)";

            PreparedStatement pstmtPacientes = conn.prepareStatement(sqlInsertPaciente);
            pstmtPacientes.setString(1, nomePaciente);
            pstmtPacientes.setString(2, dataNascimento);
            pstmtPacientes.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'pacientes'!");

            String nomePaciente2 = "Rafael";
            String dataNascimento2 = "1987-09-25";
            String sqlInsertPaciente2 = "INSERT INTO pacientes (nome_paciente, data_nascimento) VALUES (?, ?)";

            PreparedStatement pstmtPacientes2 = conn.prepareStatement(sqlInsertPaciente2);
            pstmtPacientes2.setString(1, nomePaciente2);
            pstmtPacientes2.setString(2, dataNascimento2);
            pstmtPacientes2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'pacientes'!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}