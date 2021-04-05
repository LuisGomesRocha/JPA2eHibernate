package application.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

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

    public Usuario(String email, String nome, int idade) {
        this.email = email;
        this.nome = nome;
        this.idade = idade;
    }
    
    @Deprecated
    public Usuario() {
    }

    
    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getIdade() {
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}


	public List<Resposta> getRespostas() {
		return respostas;
	}


	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
    
    
}
