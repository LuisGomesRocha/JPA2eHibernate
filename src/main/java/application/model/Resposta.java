package application.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    
    
    @Deprecated
    public Resposta() {
    }


	public Resposta(Long id, String resolucao, Usuario usuario, Avaliacao avaliacao) {
		super();
		this.id = id;
		this.resolucao = resolucao;
		this.usuario = usuario;
		this.avaliacao = avaliacao;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getResolucao() {
		return resolucao;
	}


	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Avaliacao getAvaliacao() {
		return avaliacao;
	}


	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
    
   


    
	
}
