package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InclusaoFuncionariosDepartamentos {
    public static void main(String[] args) {
        // Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela funcionarios
            String sqlFuncionarios = "CREATE TABLE IF NOT EXISTS funcionarios (" +
                    "id_funcionario INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "cargo VARCHAR(255) NOT NULL" +
                    ")";
            
            Statement stmtFuncionarios = conn.createStatement();
            stmtFuncionarios.executeUpdate(sqlFuncionarios);
            System.out.println("Tabela 'funcionarios' criada com sucesso!");

            // Criação da tabela departamentos
            String sqlDepartamentos = "CREATE TABLE IF NOT EXISTS departamentos (" +
                    "id_departamento INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_departamento VARCHAR(255) NOT NULL" +
                    ")";
            
            Statement stmtDepartamentos = conn.createStatement();
            stmtDepartamentos.executeUpdate(sqlDepartamentos);
            System.out.println("Tabela 'departamentos' criada com sucesso!");

            // Inserir dados na tabela funcionarios
            String nomeFuncionario = "Funcionario Teste";
            String cargoFuncionario = "Cargo Teste";
            String sqlInsertFuncionario = "INSERT INTO funcionarios (nome, cargo) VALUES (?, ?)";

            PreparedStatement stmtInsertFuncionario = conn.prepareStatement(sqlInsertFuncionario);
            stmtInsertFuncionario.setString(1, nomeFuncionario);
            stmtInsertFuncionario.setString(2, cargoFuncionario);
            stmtInsertFuncionario.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'funcionarios'!");

            // Inserir dados na tabela departamentos
            String nomeDepartamento = "Departamento Teste";
            String sqlInsertDepartamento = "INSERT INTO departamentos (nome_departamento) VALUES (?)";

            PreparedStatement stmtInsertDepartamento = conn.prepareStatement(sqlInsertDepartamento);
            stmtInsertDepartamento.setString(1, nomeDepartamento);
            stmtInsertDepartamento.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'departamentos'!");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}
