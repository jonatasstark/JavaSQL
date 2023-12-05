package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroAlunoCurso {
    //Gabriel Lucas
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela alunos
            String sqlAlunos = "CREATE TABLE IF NOT EXISTS alunos (" +
                    "id_aluno INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "turma VARCHAR(100) NOT NULL" +
                    ")";

            Statement stmtAlunos = conn.createStatement();
            stmtAlunos.executeUpdate(sqlAlunos);
            System.out.println("Tabela 'alunos' criada com sucesso!");

            // Criação da tabela cursos
            String sqlCursos = "CREATE TABLE IF NOT EXISTS cursos (" +
                    "id_curso INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_curso VARCHAR(255) NOT NULL," +
                    "instrutor VARCHAR(100) NOT NULL" +
                    ")";

            Statement stmtCursos = conn.createStatement();
            stmtCursos.executeUpdate(sqlCursos);
            System.out.println("Tabela 'cursos' criada com sucesso!");

            // Inserir dados na tabela alunos
            String nomeAluno = "Lucas";
            String turmaAluno = "Turma A";
            String sqlInsertAluno = "INSERT INTO alunos (nome, turma) VALUES (?, ?)";

            PreparedStatement stmtInsertAluno = conn.prepareStatement(sqlInsertAluno);
            stmtInsertAluno.setString(1, nomeAluno);
            stmtInsertAluno.setString(2, turmaAluno);
            stmtInsertAluno.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'alunos'!");

            String nomeAluno2 = "Julia";
            String turmaAluno2 = "Turma B";
            String sqlInsertAluno2 = "INSERT INTO alunos (nome, turma) VALUES (?, ?)";

            PreparedStatement stmtInsertAluno2 = conn.prepareStatement(sqlInsertAluno2);
            stmtInsertAluno2.setString(1, nomeAluno2);
            stmtInsertAluno2.setString(2, turmaAluno2);
            stmtInsertAluno2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'alunos'!");

            // Inserir dados na tabela cursos
            String nomeCurso = "Matemática";
            String instrutorCurso = "Professor Carlos";
            String sqlInsertCurso = "INSERT INTO cursos (nome_curso, instrutor) VALUES (?, ?)";

            PreparedStatement stmtInsertCurso = conn.prepareStatement(sqlInsertCurso);
            stmtInsertCurso.setString(1, nomeCurso);
            stmtInsertCurso.setString(2, instrutorCurso);
            stmtInsertCurso.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'cursos'!");

            String nomeCurso2 = "Ciencias";
            String instrutorCurso2 = "Professora Ana";
            String sqlInsertCurso2 = "INSERT INTO cursos (nome_curso, instrutor) VALUES (?, ?)";

            PreparedStatement stmtInsertCurso2 = conn.prepareStatement(sqlInsertCurso2);
            stmtInsertCurso2.setString(1, nomeCurso2);
            stmtInsertCurso2.setString(2, instrutorCurso2);
            stmtInsertCurso2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'cursos'!");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}