/*
*	
*	Projeto implementado seguindo arquitetura MVC+DAO
*	
*	@auhtor Orlando Moura
*	
*/
package view;

import java.util.*;
import controller.Controle;
import model.Fone;
import model.User;

public class App {

	private static Scanner input;

	public static void main(String[] argv) {
		input = new Scanner(System.in);

		boolean keep = true;
		boolean keep2 = true;
		boolean r;
		int option = 0; // transformar pra String
		int ret = 0;
		int id = 0;
		int ddd = 0;
		int aux = 1;
		String telefone;
		String tipo;
		String op;
		String email;
		String senha;
		String nome;
		Controle c = new Controle();
		User u = new User();
		Fone f = new Fone();
		ArrayList<User> list;
		ArrayList<Fone> telefonesDoUsuario;

		Menu menu = new Menu();

		do {

			do {
				menu.showMenu();
				option = input.nextInt();
				if (!(option >= 1 && option <= 2)) {
					System.out.println("Opção '" + option + "' incorreta. Tente novamente!\n\n");
				}
			} while (!(option >= 1 && option <= 2));

			switch (option) {
			case 1: // ao selecionar um email não cadastrado, o programa retorna SQLException :/
					// porém continua com o fluxo normalmente
				menu.login();
				System.out.println("\nDigite o Email:");
				email = input.next();
				System.out.println("Digite a Senha:");
				senha = input.next();
				ret = c.efetuarLogin(email, senha);

				if (ret == (-1)) {
					System.out.println("Usuário não cadastrado!");
				} else {
					if (ret == 0) {
						System.out.println("Email/Senha incorretos!");
					} else {
						System.out.println("\nLogin efetuado com sucesso!!");
						do {

							do {
								menu.showMenuLogged();
								option = input.nextInt();
								if (!(option >= 1 && option <= 6)) {
									System.out.println("Opção '" + option + "' incorreta. Tente novamente!\n\n");
								}
							} while (!(option >= 1 && option <= 6));

							switch (option) {
							case 1:
								menu.consulta();
								System.out.println("Informe o Email:");
								email = input.next();
								list = c.consulta(email);

								if (!list.isEmpty()) {
									for (int i = 0; i < list.size(); i++) {
										if (list.get(i).getEmail().contentEquals(email)) {
											aux = 0; // true
											break;
										}
									}
								}

								if (aux == 0) {
									aux = 1;
									System.out.println("\nCódigo do usuário: " + list.get(0).getId());
									System.out.println("Nome: " + list.get(0).getNome());
									System.out.println("Email " + list.get(0).getEmail());
									System.out.println("Senha " + list.get(0).getSenha());
									aux = 0;
									for (User usr : list) {

										telefonesDoUsuario = usr.getFone();

										System.out.println(
												"ddd " + (aux + 1) + ": " + telefonesDoUsuario.get(aux).getDdd());
										System.out.println("Telefone " + (aux + 1) + ": "
												+ telefonesDoUsuario.get(aux).getNumero());
										System.out.println(
												"Tipo " + (aux + 1) + ": " + telefonesDoUsuario.get(aux).getTipo());
										aux++;
									}
									System.out.println("\n");
								} else {
									System.out.println("Usuário não cadastrado!");
								}
								break;
							case 2:
								menu.listagem();

								list = c.listagem();
								for (User usr : list) {
									System.out.println("Código do Usuário: " + usr.getId());
									System.out.println("Nome: " + usr.getNome());
									System.out.println("Email: " + usr.getEmail());
									System.out.println("Senha: " + usr.getSenha());

									telefonesDoUsuario = usr.getFone();

									for (int i = 0; i < telefonesDoUsuario.size(); i++) {
										System.out
												.println("ddd " + (i + 1) + ": " + telefonesDoUsuario.get(i).getDdd());
										System.out.println(
												"Telefone " + (i + 1) + ": " + telefonesDoUsuario.get(i).getNumero());
										System.out.println(
												"Tipo " + (i + 1) + ": " + telefonesDoUsuario.get(i).getTipo());
									}
									System.out.println("\n");
								}

								break;
							case 3:
								aux = 1;
								menu.insert();
								System.out.println("\nInforme o Email:");
								email = input.next();
								list = c.consulta(email);

								if (!list.isEmpty()) {
									for (int i = 0; i < list.size(); i++) {
										if (list.get(i).getEmail().contentEquals(email)) {
											aux = 0; // true
											break;
										}
									}
								}

								if (aux == 0) {
									aux = 1;
									System.out.println("Usuário já cadastrado no sistema!");
								} else {
									int t = 1;
									u.setEmail(email);
									System.out.println("Informe o nome:");
									u.setNome(input.next());
									System.out.println("Informe a senha:");
									u.setSenha(input.next());

									do {
										menu.askFone();
										t = input.nextInt();
										if (!(t == 0 || t == 1)) {
											System.out.println("Escolha uma das opções!");
										}
									} while (!(t == 0 || t == 1));

									while (t == 0) {
										System.out.println("Informe o DDD:");
										ddd = input.nextInt();
										System.out.println("Informe o Telefone:");
										telefone = input.next();
										System.out.println("Informe o tipo do telefone:");
										tipo = input.next();

										u.setFone(ddd, telefone, tipo);

										do {
											menu.askFoneAgain();
											t = input.nextInt();
											if (!(t == 0 || t == 1)) {
												System.out.println("Escolha uma das opções!");
											}
										} while (!(t == 0 || t == 1));
									}

									r = c.incluirUsuario(u);

									if (r == false) {
										System.out.println("Usuário cadastrado com sucesso!");
									} else {
										System.out.println("Erro ao efetuar cadastro. Tente novamente!");
									}
								}

								break;
							case 4:
								menu.update();
								System.out.println("\nDigite o Email:");
								email = input.next();
								list = c.consulta(email);

								if (!list.isEmpty()) {
									for (int i = 0; i < list.size(); i++) {
										if (list.get(i).getEmail().contentEquals(email)) {
											aux = 0; // true
											id = list.get(i).getId();
											break;
										}
									}
								}

								if (aux == 0) { //mostra usuario na tela
									aux = 1;

									System.out.println("\nCódigo do usuário: " + list.get(0).getId());
									System.out.println("Nome: " + list.get(0).getNome());
									System.out.println("Email " + list.get(0).getEmail());
									System.out.println("Senha " + list.get(0).getSenha());
									aux = 0;
									for (User usr : list) {

										telefonesDoUsuario = usr.getFone();

										System.out.println("ddd " + (aux + 1) + ": " + telefonesDoUsuario.get(aux).getDdd());
										System.out.println("Telefone " + (aux + 1) + ": "+ telefonesDoUsuario.get(aux).getNumero());
										System.out.println("Tipo " + (aux + 1) + ": " + telefonesDoUsuario.get(aux).getTipo());
										aux++;
									}
									System.out.println("\n");

									do {
										menu.updateScreenOne();
										option = input.nextInt();
										if (!(option >= 1 && option <= 5)) {
											System.out.println("Opção '" + option + "' incorreta. Tente novamente!\n\n");
										}
									} while (!(option >= 1 && option <= 5));

									switch (option) { //1 - TRATAR TELEFONE NULL; 2 - LISTAR EMAIL's e escolher qual deles alterar
									case 1:
										System.out.println("Informe o novo nome:");
										
										nome = input.next();
										
										ret = c.alterarUsuario(nome, id, 1);
										
										break;
									case 2:
										System.out.println("Informe a nova senha:");
										
										nome = input.next();
										
										ret = c.alterarUsuario(nome, id, 2);
										
										break;
									case 3:
										System.out.println("Informe o novo Email:");
										
										nome = input.next();
										
										ret = c.alterarUsuario(nome, id, 3);
										
										break;
									case 4:
										telefonesDoUsuario = c.mostrarTelefonesdoUsuario(id);
										
										if(telefonesDoUsuario.get(0).getNumero() == null) {
											System.out.println("Não há telefones cadastrados para este usuário!\n");
										}else {
											f = menu.showUserFones(telefonesDoUsuario);
											ret = c.alterarFone(f);
										}										
										
										break;
									default:
										ret = 0;
										break;
									}

									if (ret > 0) {
										System.out.println("Usuário alterado!\n");
									} else {
										System.out.println("Nenhuma alteração feita!\n");
									}

								} else {
									System.out.println("Usuário não cadastrado no sistema!");
								}
								break;
							case 5:
								menu.delete();
								System.out.println("\nDigite o Email:");
								email = input.next();
								list = c.consulta(email);

								if (!list.isEmpty()) {
									for (int i = 0; i < list.size(); i++) {
										if (list.get(i).getEmail().contentEquals(email)) {
											aux = 0; // true
											break;
										}
									}
								}

								if (aux == 1) {
									System.out.println("Usuário não cadastrado no sistema!");
								} else {
									r = c.removeUsuario(list.get(0).getId());
									if (r == false) {
										aux = 1;
										System.out.println("Usuário removido com sucesso!");
									} else {
										System.out.println("Erro ao remover usuário. Tente novamente!");
									}
								}

								break;

							default:
								keep2 = false;
								System.out.println("\n");
								break;
							}

							if (keep2) {
								do {
									System.out.println("Deseja escolher uma nova opção? [S / N]");
									op = input.next();
									op = op.toUpperCase();
									if (!(op.contentEquals("S") || op.contentEquals("N"))) {
										System.out.println("Opção inválida!");
									} else if (op.contentEquals("N")) {
										System.out.println("\n");
										keep2 = false;
									}
								} while (!(op.contentEquals("S") || op.contentEquals("N")));
							}

						} while (keep2);
					}
				}
				break;

			default:
				menu.leave();
				keep = false;
				break;
			}

		} while (keep);

	}

}
