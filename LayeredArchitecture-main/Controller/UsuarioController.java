package Controller;

import Model.Usuario;
import Service.UsuarioService;
import java.util.List;

public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void cadastrar(Usuario usuario) {
        try {
            usuarioService.cadastrar(usuario);
            System.out.println("Usuario cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Usuario buscarPorId(int id) {
        try {
            return usuarioService.buscarPorId(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Usuario buscarPorCpf(String cpf) {
        try {
            return usuarioService.buscarPorCpf(cpf);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Usuario buscarPorEmail(String email) {
        try {
            return usuarioService.buscarPorEmail(email);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Usuario> buscarPorNome(String nome) {
        try {
            return usuarioService.buscarPorNome(nome);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    public void alterar(Usuario usuario) {
        try {
            usuarioService.alterar(usuario);
            System.out.println("Usuarios atualizados com sucesso");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remover(int id) {
        try {
            usuarioService.remover(id);
            System.out.println("Usuario removido do sistema");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}