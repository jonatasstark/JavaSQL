package main.java.com.javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AdicaoFornecedoresCompras {
    public static void main(String[] args) {
        // Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela fornecedores
            String sqlFornecedores = "CREATE TABLE IF NOT EXISTS fornecedores (" +
                    "id_fornecedor INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "contato VARCHAR(100) NOT NULL" +
                    ")";

            Statement stmtFornecedores = conn.createStatement();
            stmtFornecedores.executeUpdate(sqlFornecedores);
            System.out.println("Tabela 'fornecedores' criada com sucesso!");

            // Criação da tabela compras
            String sqlCompras = "CREATE TABLE IF NOT EXISTS compras (" +
                    "id_compra INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_fornecedor INT NOT NULL," +
                    "produto_comprado VARCHAR(255) NOT NULL," +
                    "quantidade INT NOT NULL," +
                    "FOREIGN KEY (id_fornecedor) REFERENCES fornecedores(id_fornecedor)" +
                    ")";

            Statement stmtCompras = conn.createStatement();
            stmtCompras.executeUpdate(sqlCompras);
            System.out.println("Tabela 'compras' criada com sucesso!");

            // Inserir dados na tabela fornecedores
            String nomeFornecedor = "Empresa A";
            String contatoFornecedor = "contato@empresaA.com";
            String sqlInsertFornecedor = "INSERT INTO fornecedores (nome, contato) VALUES (?, ?)";

            PreparedStatement stmtInsertFornecedor = conn.prepareStatement(sqlInsertFornecedor);
            stmtInsertFornecedor.setString(1, nomeFornecedor);
            stmtInsertFornecedor.setString(2, contatoFornecedor);
            stmtInsertFornecedor.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'fornecedores'!");

            String nomeFornecedor2 = "Empresa B";
            String contatoFornecedor2 = "contato@empresaB.com";
            String sqlInsertFornecedor2 = "INSERT INTO fornecedores (nome, contato) VALUES (?, ?)";

            PreparedStatement stmtInsertFornecedor2 = conn.prepareStatement(sqlInsertFornecedor2);
            stmtInsertFornecedor2.setString(1, nomeFornecedor2);
            stmtInsertFornecedor2.setString(2, contatoFornecedor2);
            stmtInsertFornecedor2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'fornecedores'!");

            // Inserir dados na tabela compras
            int idFornecedor = 1; // Coloque o ID correto do fornecedor
            String produtoComprado = "Peças de computador";
            int quantidade = 100;
            String sqlInsertCompra = "INSERT INTO compras (id_fornecedor, produto_comprado, quantidade) VALUES (?, ?, ?)";

            PreparedStatement stmtInsertCompra = conn.prepareStatement(sqlInsertCompra);
            stmtInsertCompra.setInt(1, idFornecedor);
            stmtInsertCompra.setString(2, produtoComprado);
            stmtInsertCompra.setInt(3, quantidade);
            stmtInsertCompra.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'compras'!");

            int idFornecedor2 = 2; // Coloque o ID correto do fornecedor
            String produtoComprado2 = "Material de escritório";
            int quantidade2 = 500;
            String sqlInsertCompra2 = "INSERT INTO compras (id_fornecedor, produto_comprado, quantidade) VALUES (?, ?, ?)";

            PreparedStatement stmtInsertCompra2 = conn.prepareStatement(sqlInsertCompra2);
            stmtInsertCompra2.setInt(1, idFornecedor2);
            stmtInsertCompra2.setString(2, produtoComprado2);
            stmtInsertCompra2.setInt(3, quantidade2);
            stmtInsertCompra2.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'compras'!");

            deletarDadosFornecedores(conn, 1);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }

    // função para deletar um fornecedor com base no ID
    public static void deletarDadosFornecedores(Connection conn, int idFornecedor)  {
        String sql = "DELETE FROM fornecedores WHERE id_fornecedor = " + idFornecedor; // remove uma linha da tabela

        try (Statement stmt = conn.createStatement()) {
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("Fornecedor deletado com sucesso");

                String sql2 = "DELETE FROM compras WHERE id_fornecedor = " + idFornecedor;

                if (stmt.executeUpdate(sql2) > 0) { // se o id inserido for encontrado, as compras também serão removidas
                    System.out.println("Compras relacionadas ao fornecedor deletado também foram removidas");
                }
            } else {
                System.out.println("Erro ao deletar, verifique o id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

