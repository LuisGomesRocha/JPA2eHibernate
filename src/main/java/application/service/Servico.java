package application.service;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import application.model.Avaliacao;
import application.model.Correcao;
import application.model.Resposta;
import application.model.Usuario;

public class Servico {

	Scanner dados = new Scanner(System.in);

	// Método Novo Aluno.
	public void novoAluno() {

		String nome = null, email = null;
		Integer idade = null;
		Usuario aluno = new Usuario();

		System.out.println("Informe o nome do aluno:");
		nome = dados.nextLine();
		System.out.println("Informe o email do aluno:");
		email = dados.nextLine();
		System.out.println("Informe a idade do aluno:");
		idade = dados.nextInt();
		

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("zup");
		EntityManager em = emf.createEntityManager();
		aluno.setNome(nome);
		aluno.setEmail(email);
		aluno.setIdade(idade);
		System.out.println(aluno.toString());
		
		em.getTransaction().begin();
		em.persist(aluno);
		em.getTransaction().commit();
		em.close();

	}

	// Método Nova Avaliação.

	public void novaAvaliacao() {

		String titulo, descricao, respostaEspecialista;

		System.out.println("Informe o titulo da avaliação:");
		titulo = dados.nextLine();
		System.out.println("Informe a descriçao da avaliação:");
		descricao = dados.nextLine();
		System.out.println("Informe a resposta correta da avaliação:");
		respostaEspecialista = dados.nextLine();

		Avaliacao avaliacao = new Avaliacao(titulo, descricao, respostaEspecialista);
		System.out.println(avaliacao.toString());

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("zup");
		EntityManager em = emf.createEntityManager();
		avaliacao.setTitulo(titulo);
		avaliacao.setDescricao(descricao);
		avaliacao.setRespostaEspecialista(respostaEspecialista);
		em.getTransaction().begin();
		em.persist(avaliacao);
		em.getTransaction().commit();
		em.close();

	}

	// Método Resposta Aluno.

	public void respostaAluno() {

		String resolucao = "";
		Long nDaAvaliacao;
		String email;

		Avaliacao avaliacao = new Avaliacao();
		Usuario usuario = new Usuario();
		Resposta resposta = new Resposta();

		System.out.println("Informe o número da avaliação:");
		nDaAvaliacao = dados.nextLong();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("zup");
		EntityManager em = emf.createEntityManager();
		
		
		try {
			
			avaliacao = em.find(Avaliacao.class, nDaAvaliacao);
			

		} catch (Exception e) {

			System.out.println("Não existe essa questão para ser respondida!");
			return;
		}
		
		dados.nextLine();		
		System.out.println("Informe seu email: ");
		
		email = dados.nextLine();	
		
		usuario = (Usuario) em.createQuery("SELECT u FROM Usuario u WHERE u.email LIKE :email")
			    .setParameter("email", email)
			    .getSingleResult();
			
		
	    resposta.setUsuario(usuario);
	    resposta.setAvaliacao(avaliacao); 
	    
	    
	  	System.out.println("Responda da avaliação abaixo:");
		System.out.println("Título da Avaliação: " + avaliacao.getTitulo());
		System.out.println("Descrição da Avaliação: " + avaliacao.getDescricao());
		resolucao = dados.nextLine();
		System.out.println("Sua resposta foi: " + resolucao);
	    resposta.setResolucao(resolucao);
	    em.getTransaction().begin();
		em.persist(resposta);
		em.getTransaction().commit();
		em.close();

	}

	
	// Método Correção Aluno.
	public void correcaoAluno() {
		
		String resolucao = "";
		Long nDaAvaliacao;
		String email;
		Integer nota;

		Avaliacao avaliacao = new Avaliacao();
		Usuario usuario = new Usuario();
		Resposta resposta = new Resposta();
		Correcao correcao = new Correcao();
		
		System.out.println("Informe o número da avaliação:");
		nDaAvaliacao = dados.nextLong();

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("zup");
		EntityManager em = emf.createEntityManager();
		
		
		try {
			
			avaliacao = em.find(Avaliacao.class, nDaAvaliacao);
			

		} catch (Exception e) {

			System.out.println("Não existe essa questão para ser respondida!");
			return;
		}
		
		dados.nextLine();		
		System.out.println("Informe seu email: ");
		
		email = dados.nextLine();	
		
		usuario = (Usuario) em.createQuery("SELECT u FROM Usuario u WHERE u.email LIKE :email")
			    .setParameter("email", email)
			    .getSingleResult();
			
	 	
	    
	    System.out.println("---------------------------------");
	  	System.out.println("Sua resposta da avaliação: ");
		System.out.println("Título da Avaliação: " + resposta.getResolucao());
		System.out.println("---------------------------------");
		System.out.println("Resposta do especialista: ");
		System.out.println("Descrição da Avaliação: " + avaliacao.getRespostaEspecialista());
		System.out.println("---------------------------------");
		System.out.println("");
		System.out.println("Agora corrija sua resposta em função da resposta de um mentor");
		System.out.println("            com uma nota de 0 (zero) a 10 (dez)              ");
		nota = new Integer(dados.nextInt());
		System.out.println("Sua resposta foi: " + nota + ", e será avaliada pela nossa equipe! Abraços !o!");
		
		correcao.setNota(nota);
		correcao.setAvaliacao(avaliacao);
		correcao.setUsuario(usuario);
		
		em.getTransaction().begin();
		em.persist(correcao);                                                                                                             
		em.getTransaction().commit();
		em.close();
	
		
	}  
}
