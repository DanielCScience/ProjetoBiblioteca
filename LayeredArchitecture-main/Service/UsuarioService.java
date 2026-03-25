package Service;

import Model.Usuario;
import Repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {

        if (usuario == null){
            throw new IllegalArgumentException("Erro: insira dados válidos!");
        }
        if (usuario.getId() <= 0){
            throw new IllegalArgumentException("Erro: Digite um Id maior que 0");
        }
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("Erro: Digite um nome válido!");
        }
        if (usuarioRepository.buscarPorCpf(usuario.getCpf()) != null) {
            throw new IllegalArgumentException("Erro: Este CPF já está cadastrado no sistema!");
        }
        if (usuarioRepository.buscarPorEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Erro: Este Email já está em uso por outro usuário!");
        }
        if (usuarioRepository.buscarPorId(usuario.getId()) != null) {
            throw new IllegalArgumentException("Erro: Já existe um usuário cadastrado com este ID!");
        }

        return usuarioRepository.salvar(usuario);

    }

    public Usuario buscarPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Erro: O ID do usuário deve ser maior que zero!");
        }
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario buscarPorCpf(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("Erro: O CPF deve ser preenchido!");
        }
        return usuarioRepository.buscarPorCpf(cpf);
    }

    public Usuario buscarPorEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Erro: O Email deve ser preenchido!");
        }
        return usuarioRepository.buscarPorEmail(email);
    }

    public List<Usuario> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O nome para busca deve ser preenchido!");
        }
        return usuarioRepository.buscarPorNome(nome);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }

    public void alterar(Usuario usuario) {
        if (usuario == null || usuario.getId() <= 0) {
            throw new IllegalArgumentException("Erro: Dados de usuário inválidos para alteração!");
        }

        Usuario existe = usuarioRepository.buscarPorId(usuario.getId());
        if (existe == null) {
            throw new IllegalArgumentException("Erro: Usuário não encontrado no sistema!");
        }

        usuarioRepository.atualizar(usuario);
    }

    public void remover(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Erro: ID inválido para remoção!");
        }

        if (usuarioRepository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Erro: Usuário não encontrado para remoção!");
        }

        usuarioRepository.deletar(id);
    }
}
