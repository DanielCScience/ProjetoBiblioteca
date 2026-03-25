package Repository;

import Model.Emprestimo;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    private static List<Emprestimo> emprestimosRepository = new ArrayList<>();

    public Emprestimo salvar(Emprestimo emprestimo) {
        if (emprestimo != null) {
            emprestimosRepository.add(emprestimo);
            return emprestimo;

        }
        return null;
    }

    public Emprestimo buscarPorId(int id) {
        for (Emprestimo e : emprestimosRepository) {
            if (e.getId() == id) {
                return e;

            }
        }
        return null;
    }

    public List<Emprestimo> buscarPorUsuario(int usuarioId) {

        List<Emprestimo> encontrados = new ArrayList<>();

        for (Emprestimo e : emprestimosRepository) {
            if (e.getUsuarioId() == usuarioId) {
                encontrados.add(e);

            }
        }
        return encontrados;
    }

    public List<Emprestimo> buscarEmprestimosAtivos(int usuarioId) {

        List<Emprestimo> ativosUsuario = new ArrayList<>();

        for (Emprestimo e : emprestimosRepository) {
            if (e.getUsuarioId() == usuarioId && e.isAtivo()) {
                ativosUsuario.add(e);
            }
        }
        return ativosUsuario;
    }

    public List<Emprestimo> listarTodos() {
        return emprestimosRepository;
    }

    public List<Emprestimo> listarEmprestimosAtivos() {

        List<Emprestimo> todosAtivos = new ArrayList<>();

        for (Emprestimo e : emprestimosRepository) {
            if (e.isAtivo()) {
                todosAtivos.add(e);
            }
        }
        return todosAtivos;
    }

    public void atualizar(Emprestimo emprestimo) {

        for (int i = 0; i < emprestimosRepository.size(); i++) {
            if (emprestimosRepository.get(i).getId() == emprestimo.getId()) {
                emprestimosRepository.set(i, emprestimo);
                break;
            }
        }
    }

    public void deletar(int id) {
        emprestimosRepository.removeIf(emprestimo -> emprestimo.getId() == id);
    }
}