package com.example.tasksmobile.model;

import java.io.Serializable;
import java.util.Date;


public class Task implements Serializable{
	private Long id;
	private String nome;
	private String materia;
	private String descricao;
	private String data;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public Date getDate() {
		String[] split = data.split("/");
		Date novaData = new Date();
		novaData.setDate(Integer.parseInt(split[0]));
		novaData.setMonth(Integer.parseInt(split[1]));
		if(split.length > 2) {
			novaData.setYear(Integer.parseInt(split[2]));
		}
		return novaData;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
