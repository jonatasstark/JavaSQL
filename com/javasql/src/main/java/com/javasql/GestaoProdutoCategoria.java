package main.java.com.javasql;

import java.sql.*;

public class GestaoProdutoCategoria {
    public static void main(String[] args) {
        // Gabriel Lucas
        String url = "jdbc:mysql://localhost:3306/pessoas_java";
        String username = "root";
        String password = "";

        // Conexão com o banco de dados
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão estabelecida com sucesso!");

            // Criação da tabela produto
            String sqlProduto = "CREATE TABLE IF NOT EXISTS produto (" +
                    "id_produto INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_produto VARCHAR(255) NOT NULL," +
                    "preco DECIMAL(10,2) NOT NULL" +
                    ")";
            
            Statement stmtProduto = conn.createStatement();
            stmtProduto.executeUpdate(sqlProduto);
            System.out.println("Tabela 'produto' criada com sucesso!");

            // Criação da tabela categoria
            String sqlCategoria = "CREATE TABLE IF NOT EXISTS categoria (" +
                    "id_categoria INT AUTO_INCREMENT PRIMARY KEY," +
                    "nome_categoria VARCHAR(255) NOT NULL" +
                    ")";
            
            Statement stmtCategoria = conn.createStatement();
            stmtCategoria.executeUpdate(sqlCategoria);
            System.out.println("Tabela 'categoria' criada com sucesso!");

            //inserir dados na tabela produto
            String nome = "Celular";
            double preco = 1000;
            String sqlInsertProduto = "INSERT INTO produto (nome_produto, preco) VALUES (?, ?)";
            
            PreparedStatement stmtInsertProduto = conn.prepareStatement(sqlInsertProduto);
            stmtInsertProduto.setString(1, nome);
            stmtInsertProduto.setDouble(2, preco);
            stmtInsertProduto.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'produto'!");

            //inserir dados na tabela categoria
            String nome_categoria = "Eletrônico";
            String sqlInsertCategoria = "INSERT INTO categoria (nome_categoria) VALUES (?)";
            
            PreparedStatement stmtInsertCategoria = conn.prepareStatement(sqlInsertCategoria);
            stmtInsertCategoria.setString(1, nome_categoria);
            stmtInsertCategoria.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'categoria'!");

            deletarDadosProdutos(conn, 1);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados");
        }
    }

    // função para deletar um produto com base no ID
    public static void deletarDadosProdutos(Connection conn, int idProduto){
        String sql = "DELETE FROM produto WHERE id_produto = " + idProduto; // remove uma linha da tabela

        try (Statement stmt = conn.createStatement()) {
            if (stmt.executeUpdate(sql) > 0) {
                System.out.println("Produto deletado com sucesso");

                String sql2 = "DELETE FROM categoria WHERE id_categoria = " + idProduto;

                if (stmt.executeUpdate(sql2) > 0) { // se o id inserido for encontrado, as categorias também serão removidas
                    System.out.println("Categorias relacionadas ao produto deletado também foram removidas");
                }
            } else {
                System.out.println("Erro ao deletar, verifique o id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
