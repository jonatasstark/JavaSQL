package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
            String nomeProjeto = "Sistema de controle";
            String descricaoProjeto = "Desenvolvimento de um sistema interno";
            String sqlInsertProjeto = "INSERT INTO projetos (nome_projeto, descricao) VALUES (?, ?)";

            PreparedStatement stmtInsertProjeto = conn.prepareStatement(sqlInsertProjeto);
            stmtInsertProjeto.setString(1, nomeProjeto);
            stmtInsertProjeto.setString(2, descricaoProjeto);
            stmtInsertProjeto.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'projetos'!");

            String nomeProjeto2 = "Portal corporativo";
            String descricaoProjeto2 = "Desenvolvimento de um portal para clientes";
            String sqlInsertProjeto2 = "INSERT INTO projetos (nome_projeto, descricao) VALUES (?, ?)";

            PreparedStatement stmtInsertProjeto2 = conn.prepareStatement(sqlInsertProjeto2);
            stmtInsertProjeto2.setString(1, nomeProjeto2);
            stmtInsertProjeto2.setString(2, descricaoProjeto2);
            stmtInsertProjeto2.executeUpdate();
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

            int idProjeto2 = 2; // Coloque o ID correto do projeto
            int idFuncionario2 = 2; // Coloque o ID correto do funcionário
            String sqlInsertAtribuicao2 = "INSERT INTO atribuicoes (id_projeto, id_funcionario) VALUES (?, ?)";

            PreparedStatement stmtInsertAtribuicao2 = conn.prepareStatement(sqlInsertAtribuicao2);
            stmtInsertAtribuicao2.setInt(1, idProjeto2);
            stmtInsertAtribuicao2.setInt(2, idFuncionario2);
            stmtInsertAtribuicao2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'atribuicoes'!");

            deletarDadosProjetos(conn, 1);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }

    // função para deletar um projeto com base no ID
    public static void deletarDadosProjetos(Connection conn, int idProjeto) {
        String sql = "DELETE FROM projetos WHERE id_projeto = " + idProjeto; // remove uma linha da tabela

        try (Statement stmt = conn.createStatement()) {
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("Projeto deletado com sucesso");

                String sql2 = "DELETE FROM atribuicoes WHERE id_atribuicao = " + idProjeto;

                if (stmt.executeUpdate(sql2) > 0) { // se o id inserido for encontrado, as atribuições também serão removidas
                    System.out.println("Atribuições relacionadas ao projeto deletado também foram removidas");
                }
            } else {
                System.out.println("Erro ao deletar, verifique o id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
}