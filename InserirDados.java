import java.sql.*; /*Importa todas as classes disponíveis no pacote, aqui utilizei:
PreparedStatement - Permite a execução de consultas, evita SQL Injection
SQLException - Capturar erros
Connection - Estabelece conexão com o BD
Driver Manager - Gerencia os drivers relacionados ao BD
*/
import java.util.Scanner; //Leitura dos dados do usuário

public class InserirDados { //Classe Principal

    public static void main(String[] args) { //Função principal
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/senai";  // URL banco de dados
        String user = "root";  // Usuário do MySQL
        String password = "";  // Senha do MySQL

        Scanner scanner = new Scanner(System.in);

        try { //Serve para capturar os erros que porventura possam ocorrer
            // Carrega o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão bem-sucedida!");

            // Inserção de dados na tabela alunos
            System.out.print("Digite o nome do aluno: ");
            String nomeAluno = scanner.nextLine();  // Lê o nome do aluno
            System.out.print("Digite a idade do aluno: ");
            int idadeAluno = scanner.nextInt();  // Lê a idade do aluno
            scanner.nextLine(); //Consumo da linha pra evitar bugs

            String sqlAluno = "INSERT INTO alunos (nome_aluno, idade) VALUES (?, ?)"; //Inserção de dados na coluna aluno
            PreparedStatement pstAluno = conn.prepareStatement(sqlAluno);
            pstAluno.setString(1, nomeAluno);  // Define o nome do aluno, parameter index é a posição que o item ocupará
            pstAluno.setInt(2, idadeAluno);    // Define a idade do aluno
            pstAluno.executeUpdate();
            System.out.println("Aluno inserido com sucesso!");

            // Inserção de dados na tabela professores
            System.out.print("Digite o nome do professor: ");
            String nomeProfessor = scanner.nextLine();  // Lê o nome do professor
            System.out.print("Digite o salário do professor: ");
            double salarioProfessor = scanner.nextDouble();  // Lê o salário do professor
            scanner.nextLine();  // Consumo da linha pra evitar bugs

            // O campo id_prof será gerado automaticamente
            String sqlProfessor = "INSERT INTO professores (nome_prof, salario) VALUES (?, ?)";
            PreparedStatement pstProfessor = conn.prepareStatement(sqlProfessor);
            pstProfessor.setString(1, nomeProfessor);  // Define o nome do professor
            pstProfessor.setDouble(2, salarioProfessor); // Define o salário do professor
            pstProfessor.executeUpdate();
            System.out.println("Professor inserido com sucesso!");

            // Inserção de dados na tabela disciplinas
            System.out.print("Digite o nome da matéria: ");
            String materia = scanner.nextLine();  // Lê o nome da matéria
            System.out.print("Digite o ID do professor que leciona a matéria: ");
            int idProfessorDisciplina = scanner.nextInt();  // Lê o ID do professor
            scanner.nextLine();  // Consumo da linha pra evitar bugs

            // O campo id_disciplina será gerado automaticamente
            String sqlDisciplina = "INSERT INTO disciplinas (materia, id_prof) VALUES (?, ?)";
            PreparedStatement pstDisciplina = conn.prepareStatement(sqlDisciplina);
            pstDisciplina.setString(1, materia);  // Define o nome da matéria
            pstDisciplina.setInt(2, idProfessorDisciplina);  // Define o ID do professor
            pstDisciplina.executeUpdate();
            System.out.println("Disciplina inserida com sucesso!");

            // Inserção de dados na tabela notas
            System.out.print("Digite a nota do aluno: ");
            double nota = scanner.nextDouble();  // Lê a nota do aluno
            System.out.print("Digite o ID do aluno: ");
            int idAlunoNota = scanner.nextInt();  // Lê o ID do aluno
            System.out.print("Digite o ID da disciplina: ");
            int idDisciplinaNota = scanner.nextInt();  // Lê o ID da disciplina
            scanner.nextLine();  // Consumo da linha pra evitar bugs

            // O campo id_nota será gerado automaticamente
            String sqlNota = "INSERT INTO notas (nota, id_aluno, id_disciplina) VALUES (?, ?, ?)";
            PreparedStatement pstNota = conn.prepareStatement(sqlNota);
            pstNota.setDouble(1, nota);  // Define a nota
            pstNota.setInt(2, idAlunoNota);  // Define o ID do aluno
            pstNota.setInt(3, idDisciplinaNota);  // Define o ID da disciplina
            pstNota.executeUpdate();
            System.out.println("Nota inserida com sucesso!");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                scanner.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
