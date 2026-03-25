package Controller;
import Model.Livro;
import Service.LivroService;
import java.util.List;

public class LivroController {
    private LivroService livroService;

    public LivroController() {
        this.livroService = new LivroService();
    }

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    public void cadastrar(Livro livro) {
        try {
            livroService.cadastrar(livro);
            System.out.println("Livro cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Livro buscarPorId(int id) {
        try {
            return livroService.buscarPorId(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Livro> buscarPorNome(String nome) {
        try {
            return livroService.buscarPorNome(nome);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

    public void alterar(Livro livro) {
        try {
            livroService.alterar(livro);
            System.out.println("Livro alterado com sucesso");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remover(int id) {
        try {
            livroService.remover(id);
            System.out.println("Livro removido com sucesso");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
