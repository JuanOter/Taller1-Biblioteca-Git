package com.mycompany.biblioteca;

public class Material {
    protected String codigo;
    protected String titulo;
    protected int anioPublicacion;

    public Material(String codigo, String titulo, int anioPublicacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
    }

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public int getAnioPublicacion() { return anioPublicacion; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Título: " + titulo + " | Año: " + anioPublicacion;
    }
}
