package Repository;

import Model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    private static List<Livro> livrosRepository = new ArrayList<>();

    public Livro salvar(Livro livro) {
        if (livro != null) {
            livrosRepository.add(livro);
            return livro;
        }
        return null;
    }

    public Livro buscarPorId(int id) {
        for (Livro i: livrosRepository){
            if (i.getId() == id){
                return i;
            }
        }
        return null;
    }

    public List<Livro> buscarPorNome(String nome) {
        List<Livro> livroEncontrado = new ArrayList<>();

        for (Livro i: livrosRepository){
            if (i.getNome().equalsIgnoreCase(nome)){
                livroEncontrado.add(i);
            }
        }
        return livroEncontrado;
    }

    public List<Livro> listarTodos() {
        return livrosRepository;
    }

    public void atualizar(Livro livro) {
        for (int i = 0; i < livrosRepository.size(); i++) {
            if (livrosRepository.get(i).getId() == livro.getId()) {
                livrosRepository.set(i, livro);
                break;
                }
            }
    }

    public void deletar(int id) {
        livrosRepository.removeIf(livro -> livro.getId() == id);
    }
}