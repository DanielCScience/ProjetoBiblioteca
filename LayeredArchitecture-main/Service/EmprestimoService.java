package Service;

import Model.Emprestimo;
import Model.Livro;
import Model.Usuario;
import Repository.EmprestimoRepository;
import Repository.LivroRepository;
import Repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private UsuarioRepository usuarioRepository;
    private LivroRepository livroRepository;

    private static int proximoId = 1;

    public EmprestimoService() {
        this.emprestimoRepository = new EmprestimoRepository();
        this.usuarioRepository = new UsuarioRepository();
        this.livroRepository = new LivroRepository();
    }

    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public Emprestimo realizarEmprestimo(int usuarioId, int livroId, int diasEmprestimo) {

        Usuario usuario = usuarioRepository.buscarPorId(usuarioId);
        if (usuario == null){
            throw new IllegalArgumentException("Erro: Digite o Id de um usuário válido!");
        }

        Livro livro = livroRepository.buscarPorId(livroId);
        if (livro == null){
            throw new IllegalArgumentException("Erro: Digite o Id de um livro válido!");
        }

        if (livro.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Erro: Este livro não está disponível no estoque!");
        }

        List<Emprestimo> ativos = emprestimoRepository.buscarEmprestimosAtivos(usuarioId);
        for (Emprestimo i : ativos) {
            if (i.getLivroId() == livroId) {
                throw new IllegalArgumentException("Erro: O usuário já possui um empréstimo ativo deste livro!");
            }
        }

        livro.setQuantidade(livro.getQuantidade() - 1);
        livroRepository.atualizar(livro);


        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setId(proximoId++);
        novoEmprestimo.setUsuarioId(usuarioId);
        novoEmprestimo.setLivroId(livroId);
        novoEmprestimo.setDataEmprestimo(LocalDate.now());
        novoEmprestimo.setDataDevolucaoPrevista(LocalDate.now().plusDays(diasEmprestimo));
        novoEmprestimo.setAtivo(true);

        return emprestimoRepository.salvar(novoEmprestimo);
    }

    public void devolverLivro(int emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(emprestimoId);
        if (emprestimo == null) {
            throw new IllegalArgumentException("Erro: Empréstimo não encontrado!");
        }

        if (emprestimo.isAtivo() == false) {
            throw new IllegalArgumentException("Erro: Este empréstimo já foi finalizado!");
        }

        emprestimo.setAtivo(false);
        emprestimo.setDataDevoluçaoReal(LocalDate.now());
        emprestimoRepository.atualizar(emprestimo);

        Livro livro = livroRepository.buscarPorId(emprestimo.getLivroId());
        if (livro != null) {
            livro.setQuantidade(livro.getQuantidade() + 1);
            livroRepository.atualizar(livro);
        }

    }

    public List<Emprestimo> buscarEmprestimosDoUsuario(int usuarioId) {
        return emprestimoRepository.buscarPorUsuario(usuarioId);

    }

    public List<Emprestimo> buscarEmprestimosAtivosDoUsuario(int usuarioId) {
        return emprestimoRepository.buscarEmprestimosAtivos(usuarioId);
    }

    public List<Emprestimo> listarTodosEmprestimos() {
       return emprestimoRepository.listarTodos();
    }

    public List<Emprestimo> listarEmprestimosAtivos() {
        return emprestimoRepository.listarEmprestimosAtivos();
    }

    public Emprestimo buscarPorId(int id) {
        return emprestimoRepository.buscarPorId(id);
    }

    public boolean verificarAtraso(int emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(emprestimoId);
        if (emprestimo == null) {
            throw new IllegalArgumentException("Erro: Empréstimo não encontrado!");
        }

        return emprestimo.isAtivo() && LocalDate.now().isAfter(emprestimo.getDataDevolucaoPrevista());

    }
}
