package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Fone;

public class Menu {
	
	private Scanner input = new Scanner(System.in);
	
	public Menu() {}
	
	public void showMenu() {
		System.out.println("1 - Efetuar Login");
		System.out.println("2 - Sair");
	}
	
	public void showMenuLogged() {
		System.out.println("\n1 - Consultar Usu�rio");
		System.out.println("2 - Listar Usu�rios");
		System.out.println("3 - Incluir Usu�rio");
		System.out.println("4 - Alterar Usu�rio");
		System.out.println("5 - Remover Usu�rio");
		System.out.println("6 - Sair");
	}
	
	public void leave() {
		System.out.println("\n\n\n");
		System.out.println("|-------------------------------------------|");
		System.out.println("|-------------------------------------------|");
		System.out.println("|---------- Pitang / Desafio Sefaz ---------|");
		System.out.println("|-------------------------------------------|");
		System.out.println("|-------------------------------------------|\n\n");
	}
	
	public void updateScreenOne() {
		System.out.println("1 - Alterar nome");
		System.out.println("2 - Alterar Senha");
		System.out.println("3 - Alterar Email");
		System.out.println("4 - Alterar Telefone");
		System.out.println("5 - Cancelar\n");
	}
	
	public Fone showUserFones(ArrayList<Fone> list) {
		Fone f = new Fone();
		int ddd;
		String numero;
		String tipo;
		int op;
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println("Telefone " + (i+1) + ":");
			System.out.println("DDD: " + list.get(i).getDdd());
			System.out.println("Numero: " + list.get(i).getNumero());
			System.out.println("Tipo: " + list.get(i).getTipo() + "\n");
		}
		
		do {
			System.out.println("Selecione o Telefone para alterar:");
			op = input.nextInt();
			if(!(op > 0 && op <= list.size())) {
				System.out.println("Informe a op��o correta!\n");
			}
		}while(!(op > 0 && op <= list.size()));
		
		op = op - 1;
		
		System.out.println("Informe o novo DDD:");
		ddd = input.nextInt();
		System.out.println("Informe o novo n�mero:");
		numero = input.next();
		System.out.println("Informe o Tipo do n�mero:");
		tipo = input.next();
		
		f.setDdd(ddd);
		f.setIdFone(list.get(op).getIdFone());
		f.setNumero(numero);
		f.setTipo(tipo);
		f.setIdUser(list.get(op).getIdUser());
		
		return f;
		
	}
	
	public void login() {
		System.out.println("\n----------------");
		System.out.println("   Login:");
	}
	
	public void consulta() {
		System.out.println("\n-----------------------------------");
		System.out.println("   Consultar dados do usu�rio");
	}
	
	public void listagem() {
		System.out.println("\n----------------");
		System.out.println("   Usu�rios:");
	}
	
	public void insert() {
		System.out.println("\n---------------------------");
		System.out.println("   Cadastrar usu�rio");
	}
	
	public void askFone() {
		System.out.println("\nDeseja informar o telefone?");
		System.out.println("0 - Sim");
		System.out.println("1 - N�o\n");
	}
	
	public void askFoneAgain() {
		System.out.println("\nDeseja informar outro telefone?");
		System.out.println("0 - Sim");
		System.out.println("1 - N�o\n");
	}
	
	public void update() {
		System.out.println("\n------------------------------");
		System.out.println("   Altera��o de usu�rio");
	}
	
	public void delete() {
		System.out.println("\n--------------------------");
		System.out.println("   Remover usu�rio");
	}
	
}
