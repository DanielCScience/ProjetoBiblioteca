package Service;

import Model.Livro;
import Repository.LivroRepository;

import java.util.List;

public class LivroService {
    private LivroRepository livroRepository;

    public LivroService() {
        this.livroRepository = new LivroRepository();
    }

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro cadastrar(Livro livro) {
        if (livro == null){
            throw new IllegalArgumentException("Erro: Dados de cadastro inválido");
        }
        if (livro.getId() <= 0){
            throw new IllegalArgumentException("Erro: Digite um id maior que 0");
        }
        if (livro.getNome() == null || livro.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("Erro: Digite um nome válido!");
        }
        Livro existe = livroRepository.buscarPorId(livro.getId());
        if (existe != null) {
            throw new IllegalArgumentException("Erro: Já existe um livro cadastrado com este ID!");
        }

        return livroRepository.salvar(livro);

    }

    public Livro buscarPorId(int id) {
        if (id <= 0){
            throw new IllegalArgumentException("Erro: O ID do livro deve ser maior que zero!");
        }

        return livroRepository.buscarPorId(id);
    }

    public List<Livro> buscarPorNome(String nome) {
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Erro: O nome deve ser preenchido!");
        }
        return livroRepository.buscarPorNome(nome);
    }

    public List<Livro> listarTodos() {
        return livroRepository.listarTodos();
    }

    public void alterar(Livro livro) {
        if (livro == null || livro.getId() <= 0){
            throw new IllegalArgumentException("Erro: Dados inválidos! ");

        }

        Livro existe = livroRepository.buscarPorId(livro.getId());
        if (existe == null){
            throw new IllegalArgumentException("Erro: Livro não encontrado!");
        }

        livroRepository.atualizar(livro);
    }

    public void remover(int id) {
        if (livroRepository.buscarPorId(id) == null){
            throw new IllegalArgumentException("Erro: Livro não encontrado pra remoção!");
        }

        livroRepository.deletar(id);
    }
}
