package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
            String nomeFuncionario = "Luiz";
            String cargoFuncionario = "Analista";
            String sqlInsertFuncionario = "INSERT INTO funcionarios (nome, cargo) VALUES (?, ?)";

            PreparedStatement stmtInsertFuncionario = conn.prepareStatement(sqlInsertFuncionario);
            stmtInsertFuncionario.setString(1, nomeFuncionario);
            stmtInsertFuncionario.setString(2, cargoFuncionario);
            stmtInsertFuncionario.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'funcionarios'!");

            String nomeFuncionario2 = "Carla";
            String cargoFuncionario2 = "Gerente";
            String sqlInsertFuncionario2 = "INSERT INTO funcionarios (nome, cargo) VALUES (?, ?)";

            PreparedStatement stmtInsertFuncionario2 = conn.prepareStatement(sqlInsertFuncionario2);
            stmtInsertFuncionario2.setString(1, nomeFuncionario2);
            stmtInsertFuncionario2.setString(2, cargoFuncionario2);
            stmtInsertFuncionario2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'funcionarios'!");

            // Inserir dados na tabela departamentos
            String nomeDepartamento = "TI";
            String sqlInsertDepartamento = "INSERT INTO departamentos (nome_departamento) VALUES (?)";

            PreparedStatement stmtInsertDepartamento = conn.prepareStatement(sqlInsertDepartamento);
            stmtInsertDepartamento.setString(1, nomeDepartamento);
            stmtInsertDepartamento.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'departamentos'!");

            String nomeDepartamento2 = "Recursos Humanos";
            String sqlInsertDepartamento2 = "INSERT INTO departamentos (nome_departamento) VALUES (?)";

            PreparedStatement stmtInsertDepartamento2 = conn.prepareStatement(sqlInsertDepartamento2);
            stmtInsertDepartamento2.setString(1, nomeDepartamento2);
            stmtInsertDepartamento2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'departamentos'!");

            deletarDadosFuncionarios(conn, 1);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }

    // função para deletar um funcionário com base no ID
    public static void deletarDadosFuncionarios(Connection conn, int idFuncionario){
        String sql = "DELETE FROM funcionarios WHERE id_funcionario = " + idFuncionario; // remove uma linha da tabela

        try (Statement stmt = conn.createStatement()) {
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("Funcionário deletado com sucesso");

                String sql2 = "DELETE FROM departamentos WHERE id_departamento = " + idFuncionario;

                if (stmt.executeUpdate(sql2) > 0) { // se o id inserido for encontrado, os departamentos também serão removidos
                    System.out.println("Departamentos relacionados ao funcionário deletado também foram removidos");
                }
            } else {
                System.out.println("Erro ao deletar, verifique o id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
