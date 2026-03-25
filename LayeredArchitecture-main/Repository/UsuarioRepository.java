package Repository;

import Model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {


    private static List<Usuario> usuariosRepository = new ArrayList<>();

    public Usuario salvar(Usuario usuario) {
        if (usuario != null) {
            usuariosRepository.add(usuario);
            return usuario;
        }
        return null;
    }

    public Usuario buscarPorId(int id) {
        for (Usuario u : usuariosRepository) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    public Usuario buscarPorCpf(String cpf) {
        for (Usuario u : usuariosRepository) {
            if (u.getCpf().equals(cpf)) {
                return u;
            }
        }
        return null;
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario u : usuariosRepository) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> buscarPorNome(String nome) {

        List<Usuario> encontrados = new ArrayList<>();

        for (Usuario u : usuariosRepository) {
            if (u.getNome().toLowerCase().contains(nome.toLowerCase())) {
                encontrados.add(u);
            }
        }
        return encontrados;
    }

    public List<Usuario> listarTodos() {
        return usuariosRepository;
    }

    public void atualizar(Usuario usuarioAtualizado) {
        for (int i = 0; i < usuariosRepository.size(); i++) {
            if (usuariosRepository.get(i).getId() == usuarioAtualizado.getId()) {
                usuariosRepository.set(i, usuarioAtualizado);
                break;
            }
        }
    }

    public void deletar(int id) {
        usuariosRepository.removeIf(u -> u.getId() == id);
    }
}