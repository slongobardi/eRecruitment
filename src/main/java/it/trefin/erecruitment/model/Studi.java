package it.trefin.erecruitment.model;

public enum Studi {

	DIPLOMA("Diploma"), LAUREA_TRIENNALE("Laurea Triennale"), LAUREA_MAGISTRALE("Laurea Magistrale"), MASTER("Master"),
	ALTRO("Altro");

	private String titolo;

	private Studi(String titolo) {

		this.titolo = titolo;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

}
