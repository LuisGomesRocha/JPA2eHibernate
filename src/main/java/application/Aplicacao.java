package application;

import java.util.Scanner;

import application.service.Servico;

public class Aplicacao {
    public static void main(String[] args) {

        Servico servico = new Servico();
        while (true) {
            opcoes(servico);
        }

    }

    private static void opcoes(Servico servico) {
        int opcao;
        menu();
        Scanner teclado = new Scanner(System.in);
        opcao = teclado.nextInt();

        switch (opcao) {
            case 1:
                servico.novoAluno();
                break;
            case 2:
                servico.novaAvaliacao();
                break;
            case 3:
            	servico.respostaAluno();
            	break;
            case 4:
            	servico.correcaoAluno();
            	break;
            case 0:
                sairDoSistema();

        }
    }

    static void menu() {
        System.out.println("---------------------------------");
        System.out.println("-1 - Cadastrar aluno            -");
        System.out.println("-2 - Cadastrar avaliacao        -");
        System.out.println("-3 - Cadastrar resposta aluno   -");
        System.out.println("-4 - Cadastrar correção         -");
        System.out.println("-0 - Sair do sistema            -");
        System.out.println("---------------------------------");
    }

    static void sairDoSistema() {
        System.out.println("Finalizando sistema, VOLTE SEMPRE!");
        System.exit(1);
    }


}
