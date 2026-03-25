package Model;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private int usuarioId;
    private int livroId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevoluçaoReal;
    private boolean ativo;

    public Emprestimo() {
    }

    public Emprestimo(int id, int usuarioId, int livroId, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevoluçaoReal() {
        return dataDevoluçaoReal;
    }

    public void setDataDevoluçaoReal(LocalDate dataDevoluçaoReal) {
        this.dataDevoluçaoReal = dataDevoluçaoReal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Model.Emprestimo{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", livroId=" + livroId +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucaoPrevista=" + dataDevolucaoPrevista +
                ", dataDevoluçaoReal=" + dataDevoluçaoReal +
                ", ativo=" + ativo +
                '}';
    }
}
