package com.weg.gestao_escola.repository;

import com.weg.gestao_escola.conexao.Conexao;
import com.weg.gestao_escola.model.Curso;
import com.weg.gestao_escola.model.Turma;
import com.weg.gestao_escola.model.TurmaResposta;
import com.weg.gestao_escola.util.GerarIn;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaDAO {

    public List<TurmaResposta> listarCursos() throws SQLException {
        List<TurmaResposta> turmas = new ArrayList<>();
        String query = "SELECT t.id, " +
                "t.nome, " +
                "c.nome as curso, " +
                "p.nome as professor " +
                "FROM turma t " +
                "LEFT JOIN curso c " +
                "ON t.curso_id = c.id " +
                "LEFT JOIN professor p " +
                "ON t.professor_id = p.id";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String nomeCurso = rs.getString("curso");
                String nomeProfessor = rs.getString("professor");

                turmas.add(new TurmaResposta(id, nome, nomeCurso, nomeProfessor));
            }
        }

        return turmas;
    }


    public List<String> listarNomeAlunos(int id) throws SQLException {
        List<String> alunos = new ArrayList<>();

        String query = "SELECT a.nome as nome " +
                "FROM turma_aluno ta " +
                "LEFT JOIN aluno a " +
                "ON ta.aluno_id = a.id " +
                "WHERE ta.turma_id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                alunos.add(rs.getString("nome"));
            }
        }

        return alunos;
    }

    public List<String> listaAlunoNome(List<Integer> idsAlunos) throws SQLException {
        String query = """
                SELECT a.nome
                FROM aluno a
                LEFT JOIN turma_aluno ta
                ON a.id = ta.aluno_id
                WHERE a.id IN """ + GerarIn.gerando(idsAlunos.size());

        List<String> nomeAlunos = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for(int i = 0; i < idsAlunos.size(); i++){
                stmt.setInt(i + 1, idsAlunos.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                nomeAlunos.add(rs.getString("nome"));
            }
        }
        return nomeAlunos;
    }

    public TurmaResposta buscarTurmaPorId(int id) throws SQLException{
        List<TurmaResposta> turmas = new ArrayList<>();
        String query = "SELECT t.id, " +
                "t.nome, " +
                "c.nome as curso, " +
                "p.nome as professor " +
                "FROM turma t " +
                "LEFT JOIN curso c " +
                "ON t.curso_id = c.id " +
                "LEFT JOIN professor p " +
                "ON t.professor_id = p.id " +
                "WHERE t.id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int newid = rs.getInt("id");
                String nome = rs.getString("nome");
                String nomeCurso = rs.getString("curso");
                String nomeProfessor = rs.getString("professor");

                return new TurmaResposta(newid, nome, nomeCurso, nomeProfessor);
            }
        }

        return null;
    }

    public Turma criar(Turma turma) throws SQLException{
        String query = "INSERT INTO turma (nome, curso_id, professor_id) VALUES (?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCursoId());
            stmt.setInt(3, turma.getProfessorId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                turma.setId(rs.getInt(1));
            }
        }

        return turma;
    }

    public void inserirTurmaAluno(int idTurma, int idAluno) throws SQLException{
        String query = "INSERT INTO turma_aluno (turma_id, aluno_id) VALUES (?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, idTurma);
            stmt.setInt(2, idAluno);
            stmt.executeUpdate();
        }
    }

    public void atualizar(int id, Turma turma) throws SQLException {
        String query = "UPDATE turma SET nome = ?, curso_id = ?, professor_id = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCursoId());
            stmt.setInt(3, turma.getProfessorId());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String query = "DELETE FROM turma WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
