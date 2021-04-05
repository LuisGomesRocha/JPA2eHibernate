package application.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



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
    
    }
    
        
    @Deprecated
    public Avaliacao() {
    }
    
    

    public Avaliacao(Long id, String titulo, String descricao, String respostaEspecialista, List<Resposta> respostas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.respostaEspecialista = respostaEspecialista;
		this.respostas = respostas;
	}



	@Override
    public String toString() {
        return "Avaliacao{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", respostaEspecialista='" + respostaEspecialista + '\'' +
                '}';
    }



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public String getRespostaEspecialista() {
		return respostaEspecialista;
	}



	public void setRespostaEspecialista(String respostaEspecialista) {
		this.respostaEspecialista = respostaEspecialista;
	}



	public List<Resposta> getRespostas() {
		return respostas;
	}



	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
	
	
}
