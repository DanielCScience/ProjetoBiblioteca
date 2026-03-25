package Controller;

import Controller.LivroController;
import Controller.UsuarioController;
import Model.Livro;
import Model.Usuario;
import Service.EmprestimoService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instâncias das camadas
        LivroController livroController = new LivroController();
        UsuarioController usuarioController = new UsuarioController();
        EmprestimoService emprestimoService = new EmprestimoService();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n === SISTEMA DE BIBLIOTECA - ARCH === ");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Cadastrar Usuário");
            System.out.println("4 - Listar Usuários");
            System.out.println("5 - Realizar Empréstimo");
            System.out.println("6 - Devolver Livro");
            System.out.println("7 - Verificar Vencimento/Atraso");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.print("ID: ");
                        int idLivro = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nome: ");
                        String nomeLivro = scanner.nextLine();
                        System.out.print("Autor: ");
                        String autorLivro = scanner.nextLine();
                        System.out.print("Edição: ");
                        int edLivro = Integer.parseInt(scanner.nextLine());
                        System.out.print("Quantidade: ");
                        int quantidadeLivro = Integer.parseInt(scanner.nextLine());
                        livroController.cadastrar(new Livro(idLivro, nomeLivro, autorLivro, edLivro, quantidadeLivro));
                        break;

                    case 2:
                        livroController.listarTodos().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("ID: ");
                        int idUsuario = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nome: ");
                        String nomeUsuario = scanner.nextLine();
                        System.out.print("Email: ");
                        String emailUsuario = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpfUsuario = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefoneUsuario = scanner.nextLine();
                        usuarioController.cadastrar(new Usuario(idUsuario, nomeUsuario, emailUsuario, cpfUsuario, telefoneUsuario));
                        break;

                    case 4:
                        usuarioController.listarTodos().forEach(System.out::println);
                        break;

                    case 5:
                        System.out.print("ID do Usuário: ");
                        int usuarioId = Integer.parseInt(scanner.nextLine());
                        System.out.print("ID do Livro: ");
                        int livroId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Dias de prazo: ");
                        int dias = Integer.parseInt(scanner.nextLine());

                        emprestimoService.realizarEmprestimo(usuarioId, livroId, dias);
                        System.out.println(" Empréstimo realizado com sucesso!");
                        break;

                    case 6:
                        System.out.print("ID do Empréstimo: ");
                        int emprestimoIdDev = Integer.parseInt(scanner.nextLine());
                        emprestimoService.devolverLivro(emprestimoIdDev);
                        System.out.println(" Livro devolvido!");
                        break;

                    case 7:
                        System.out.print("ID do Empréstimo para checar: ");
                        int empIdCheck = Integer.parseInt(scanner.nextLine());

                        if (emprestimoService.verificarAtraso(empIdCheck)) {
                            System.out.println(" Este empréstimo está ATRASADO!");
                        } else {
                            System.out.println(" STATUS: Em dia ou devolvido.");
                        }
                        break;

                    case 0:
                        System.out.println("Saindo");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Erro: Digite números válidos para IDs e prazos.");
            } catch (Exception e) {
                System.out.println( e.getMessage());
            }
        }
        scanner.close();
    }
}
