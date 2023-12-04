package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GerenciamentoProjetosAtribuicoes {
    public static void main(String[] args) {
        //Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela projetos
            String sqlProjetos = "CREATE TABLE IF NOT EXISTS projetos (" +
                    "id_projeto INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_projeto VARCHAR(255) NOT NULL," +
                    "descricao VARCHAR(255) NOT NULL" +
                    ")";

            Statement stmtProjetos = conn.createStatement();
            stmtProjetos.executeUpdate(sqlProjetos);
            System.out.println("Tabela 'projetos' criada com sucesso!");

            // Criação da tabela atribuicoes
            String sqlAtribuicoes = "CREATE TABLE IF NOT EXISTS atribuicoes (" +
                    "id_atribuicao INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_projeto INT NOT NULL," +
                    "id_funcionario INT NOT NULL," +
                    "FOREIGN KEY (id_projeto) REFERENCES projetos(id_projeto)," +
                    "FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id_funcionario)" +
                    ")";

            Statement stmtAtribuicoes = conn.createStatement();
            stmtAtribuicoes.executeUpdate(sqlAtribuicoes);
            System.out.println("Tabela 'atribuicoes' criada com sucesso!");

            // Inserir dados na tabela projetos
            String nomeProjeto = "Projeto Teste";
            String descricaoProjeto = "Descrição do Projeto Teste";
            String sqlInsertProjeto = "INSERT INTO projetos (nome_projeto, descricao) VALUES (?, ?)";

            PreparedStatement stmtInsertProjeto = conn.prepareStatement(sqlInsertProjeto);
            stmtInsertProjeto.setString(1, nomeProjeto);
            stmtInsertProjeto.setString(2, descricaoProjeto);
            stmtInsertProjeto.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'projetos'!");

            // Inserir dados na tabela atribuicoes
            int idProjeto = 1; // Coloque o ID correto do projeto
            int idFuncionario = 1; // Coloque o ID correto do funcionário
            String sqlInsertAtribuicao = "INSERT INTO atribuicoes (id_projeto, id_funcionario) VALUES (?, ?)";

            PreparedStatement stmtInsertAtribuicao = conn.prepareStatement(sqlInsertAtribuicao);
            stmtInsertAtribuicao.setInt(1, idProjeto);
            stmtInsertAtribuicao.setInt(2, idFuncionario);
            stmtInsertAtribuicao.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'atribuicoes'!");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }
}