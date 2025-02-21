package app;

import java.util.List;
import java.util.Scanner;
import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {

	public static void main(String[] args) throws Exception {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Scanner scanner = new Scanner(System.in);

		int op = 0;
		while (op != 5) {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Inserir Usuário");
            System.out.println("2 - Atualizar Usuário");
            System.out.println("3 - Excluir Usuário");
            System.out.println("4 - Mostrar Usuários");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();

			switch (op) {
				case 1:
					inserirUsuario(usuarioDAO, scanner);
                    break;
				case 2:
					atualizarUsuario(usuarioDAO, scanner);
                    break;
				case 3:
					excluirUsuario(usuarioDAO, scanner);
                    break;
				case 4:
                    mostrarUsuarios(usuarioDAO);
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
			}
		}

		scanner.close();
	}

	private static void inserirUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
		System.out.println("\n==== Inserir Usuário === ");
		System.out.print("Digite o id: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Digite o nome: ");
		String nome = scanner.nextLine();
		System.out.print("Digite o login: ");
		String login = scanner.nextLine();
		System.out.print("Digite a senha: ");
	    String senha = scanner.nextLine();
	    System.out.print("Digite o sexo (M/F): ");
	    char sexo = scanner.nextLine().charAt(0);
	        
	    Usuario usuario = new Usuario(id, nome, login, senha, sexo);
	        
	    if (usuarioDAO.insert(usuario)) {
	        System.out.println("Usuário inserido com sucesso: " + usuario);
	    } else {
	        System.out.println("Erro ao inserir o usuário.");
	    }
	}

	// Função para atualizar um usuário
	private static void atualizarUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
	    System.out.println("\n==== Atualizar Usuário === ");
	    System.out.print("Digite o código do usuário para atualizar: ");
	    int id = scanner.nextInt();
	    scanner.nextLine();
	    System.out.print("Digite o novo nome: ");
	    String nome = scanner.nextLine();
	    System.out.print("Digite o novo login: ");
	    String login = scanner.nextLine();
	    System.out.print("Digite a nova senha: ");
	    String senha = scanner.nextLine();
	    System.out.print("Digite o sexo (M/F): ");
	    char sexo = scanner.nextLine().charAt(0);

	    Usuario usuario = usuarioDAO.getById(id);
	    if (usuario != null) {
	        usuario.setNome(nome);
	        usuario.setLogin(login);
	        usuario.setSenha(senha);
	        usuario.setSexo(sexo);
	        
	        if (usuarioDAO.update(usuario)) {
	            System.out.println("Usuário atualizado com sucesso: " + usuario);
	        } else {
	            System.out.println("Erro ao atualizar o usuário.");
	        }
	    } else {
	        System.out.println("Usuário não encontrado.");
	    }
	}

	private static void excluirUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {   
	    System.out.println("\n==== Excluir Usuário === ");
	    System.out.print("Digite o código do usuário para excluir: ");
	    int id = scanner.nextInt();
	    
	    if (usuarioDAO.delete(id)) {
	        System.out.println("Usuário excluído com sucesso.");
	    } else {
	        System.out.println("Erro ao excluir o usuário.");
	    }
	}

	// Função para mostrar todos os usuários
	private static void mostrarUsuarios(UsuarioDAO usuarioDAO) {
	    System.out.println("\n==== Mostrar Usuários === ");
	    List<Usuario> usuarios = usuarioDAO.getAll(); // Método para pegar todos os usuários
	    if (usuarios.isEmpty()) {
	        System.out.println("Nenhum usuário encontrado.");
	    } else {
	        for (Usuario u : usuarios) {
	            System.out.println(u);
	        }
	    }
	}
}

