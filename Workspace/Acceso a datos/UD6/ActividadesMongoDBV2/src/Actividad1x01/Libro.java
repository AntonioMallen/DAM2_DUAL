package Actividad1x01;

import java.util.ArrayList;
import java.util.List;

public class Libro {

	private Integer codigo;
	private String titulo;
	private String autor;
	private Integer agno;
	private String genero;
	private ArrayList<String> partes;
	private Integer numero_paginas;
	private ArrayList<String> personajes;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Integer getAgno() {
		return agno;
	}
	public void setAgno(Integer agno) {
		this.agno = agno;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public ArrayList<String> getPartes() {
		return partes;
	}
	public void setPartes(ArrayList<String> partes) {
		this.partes = partes;
	}
	public Integer getNumero_paginas() {
		return numero_paginas;
	}
	public void setNumero_paginas(Integer numero_paginas) {
		this.numero_paginas = numero_paginas;
	}
	public ArrayList<String> getPersonajes() {
		return personajes;
	}
	public void setPersonajes(ArrayList<String> personajes) {
		this.personajes = personajes;
	}
	

	
	public Libro(Integer codigo, String titulo, String autor, Integer agno, String genero, List<String> partes,
			Integer numero_paginas, List<String> personajes) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.agno = agno;
		this.genero = genero;
		this.partes = (ArrayList<String>)partes;
		this.numero_paginas = numero_paginas;
		this.personajes = (ArrayList<String>)personajes;
	}

	
	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", agno=" + agno + ", genero="
				+ genero + ", partes=" + partes + ", numero_paginas=" + numero_paginas + ", personajes=" + personajes
				+ "]";
	}
	
	
	
	
	
	
	
	

	
	
	
}
