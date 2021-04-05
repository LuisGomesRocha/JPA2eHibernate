package application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

	
	

	public Correcao(Long id, Avaliacao avaliacao, Integer nota, Usuario usuario) {
		super();
		this.id = id;
		this.avaliacao = avaliacao;
		this.nota = nota;
		this.usuario = usuario;
	}
	
	
	@Deprecated
	public Correcao() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Avaliacao getAvaliacao() {
		return avaliacao;
	}


	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}


	public Integer getNota() {
		return nota;
	}


	public void setNota(Integer nota) {
		this.nota = nota;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

}