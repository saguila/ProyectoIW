package es.fdi.iw.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
/* Queries */
	
@NamedQueries({
		@NamedQuery(name = "todasVotaciones", query = "select u from Votacion u"),
		@NamedQuery(name = "puntuacionMedia", query = "select avg(v.puntuacionMedia) from Votacion v where v.id_receptor = :param1"),
		@NamedQuery(name= "borrarVotacion", query="delete from Votacion u where u.id= :idParam"),
		@NamedQuery(name = "dameVotacion", query = "select u from Votacion u where id = :idParam"),
		@NamedQuery(name = "buscarVotacionesRecibidas", query = "select u from Votacion u where u.id_receptor = :param1"),
		@NamedQuery(name = "buscarVotacionesRealizadas", query = "select u from Votacion u where u.id_emisor = :param1")
})
	/* Clase */
public class Votacion {
	/* Atributos */
	private long id;
	private long id_emisor;
	private long id_receptor;
	private Date fecha;
	private String comentario;
	private Double puntuacionMedia;
	private List<Categoria> valoraciones;

	/* Constructores */
	public Votacion() {}

	/* Metodos */
	public Votacion crearVotacion(long id_emisor, long id_receptor,
			List<Categoria> votaciones, String comentario) {
		double puntuacion = 0;
		Votacion v = new Votacion();
		v.id_emisor = id_emisor;
		v.id_receptor = id_receptor;
		v.valoraciones = votaciones;
		v.comentario = comentario;
		v.fecha = new Date();
		for (Categoria c : votaciones)
			puntuacion += c.getPuntuacion();
		v.puntuacionMedia = puntuacion / votaciones.size();
		return v;
	}

	@Override
	public String toString() {
		return "Votacion [id=" + id + ", id_emisor=" + id_emisor + ", id_receptor=" + id_receptor + ", fecha=" + fecha
				+ ", comentario=" + comentario + ", puntuacionMedia=" + puntuacionMedia + ", valoraciones="
				+ valoraciones + "]";
	}

	/* Getters & Setters */
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Cascade({CascadeType.ALL})
	@OneToMany(targetEntity = Categoria.class, fetch = FetchType.EAGER)
	public List<Categoria> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Categoria> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public long getId_emisor() {
		return id_emisor;
	}

	public void setId_emisor(long id_emisor) {
		this.id_emisor = id_emisor;
	}

	public long getId_receptor() {
		return id_receptor;
	}

	public void setId_receptor(long id_receptor) {
		this.id_receptor = id_receptor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public double getPuntuacionMedia() {
		return puntuacionMedia;
	}

	public void setPuntuacionMedia(double puntuacionMedia) {
		this.puntuacionMedia = puntuacionMedia;
	}

	/* Tablas del Join */
}