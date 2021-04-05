# JPA2eHibernateAvaliacao

<h1 align="center">
    <a href="https://www.java.com/pt-BR/">🔗 JPA2</a>
</h1>
<p align="center">🚀 Formulário de proposta de solução - Java e JPA: Persista seus objetos com a JPA2 e Hibernate 🚀 </p>

<h4 align="center"> 
	🚧  JPA2  🚀 Em construção...  🚧
</h4>

### Features

- [x] Cadastro de usuário
- [x] Cadastro de resposta
- [x] Cadastro de correção
- [x] Cadastro de resposta


<p align="justify"> :robot: Dado que todo(a) aluno(a) tem um email (máximo de 30 caracteres),nome (máximo de 30 caracteres) e idade (entre 1 e 100). Como você vai modelar essa classe e configurá-la para que possa ser utilizada pelo Hibernate? :robot: </p>



```
@Entity
public class Usuario {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 30)
    @NotNull
    String email;
    @Column(length = 30)
    @NotNull
    String nome;
    int idade;
    
    @OneToMany(mappedBy = "usuario")
    List<Resposta> respostas;
    
    @OneToMany(mappedBy = "usuario")
    List<Correcao> correcao;
    
```

<p align="justify"> :robot: Dentro do bootcamp temos também um conjunto de avaliações que são respondidas pelas pessoas. Toda avaliação tem um título e uma descrição do que precisa ser feito. Como você vai modelar essa classe e configurá-la para que possa ser utilizada pelo Hibernate? :robot: </p>

```
@Entity 
public class Avaliacao {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
    String titulo;
    String descricao;
    String respostaEspecialista;
    
    @OneToMany (mappedBy = "avaliacao")
    List<Resposta> respostas = new ArrayList<Resposta>();
    
    
   @OneToOne(mappedBy = "avaliacao", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = true)
   Correcao correcao;
 
    public Avaliacao(String titulo, String descricao, String respostaEspecialista) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.respostaEspecialista = respostaEspecialista;
    
```
    
<p align="justify"> :robot: Cada aluno(a) responde uma ou mais avaliações que chamamos de cognitive walkthrough, ela tem que descrever os passos da solução dela para determinada situação problema. Toda resposta tem um campo aberto para que a pessoa consiga descrever a solução dela. É necessário que toda resposta seja linkada com a pessoa que a respondeu e também com a avaliação relativa àquela resposta. Como você vai modelar essa classe e configurá-la para que possa ser utilizada pelo Hibernate? :robot: </p>
    
 ```
 @Entity
public class Resposta {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String resolucao;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_avaliacao")
    Avaliacao avaliacao;

 ```
<p align="justify"> :robot: Além de responder sua avaliação, a pessoa também responde um outro formulário onde ela corrige sua resposta em função da resposta de um mentor(a). Essa correção sempre tem uma nota de 1 a 10 e está linkada com a avaliação respondida pela própria pessoa. Como você vai modelar essa classe e configurá-la para que possa ser utilizada pelo Hibernate? :robot: </p>
 
 
 ```
 @Entity
public class Correcao {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@OneToOne
	@JoinColumn (name = "id_avaliacao")
	Avaliacao avaliacao;
	Integer nota;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	Usuario usuario; 

 ```
 
<p align="justify"> :robot: Agora que você modelou e mapeou as classes para que possam ser utilizadas pelo hibernate, temos desafios extras.
Quais são os passos para salvar para salvar um(a) aluno(a)?
Quais são os passos para salvar para salvar uma avaliação?
Quais são os passos para salvar uma resposta de um(a) aluno(a)?
Quais são os passos para salvar a auto correção de um(a) aluno(a)?
 :robot: </p>

 ```
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

 ```
 
<p align="justify"> :robot: Caso você precise carregar uma auto correção e tenha que descobrir o nome do(a) aluno(a) que fez, como você faria? Algum ponto de atenção que deveria ter?

R: Faria uma busca pela relação do ID cadastrado na tabela Avaliação em relação a Correção. Seria interessante ter uma chave única como CPF para correlacionar os dados.
	

Caso você precise carregar as respostas de um(a) aluno(a) a partir do objeto da classe Aluno, como você faria? Algum ponto de atenção?

R: Faria uma busca pelo e-mail do aluno, e posteriormente pelo nome trazendo todo o objeto. Seria interessante ter uma chave como CPF para caso de ter nomes ou e-mail repetidos.
 :robot: </p>
    
 ```
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

 ```
