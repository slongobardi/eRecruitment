package it.trefin.erecruitment.model;

public class Response<D,S> {
	
	protected D data;
	protected S status;
	protected String descrizione;
	
	public static enum Status {
		OK,
		KO,
		SYSTEM_ERROR,
		MISSED_REQUIRED_PARAM,
		AUTH_REQUIRED,
		EXPIRED
	}
	
	public Response() {
	}
	
	public D getData() {
		return data;
	}
	public void setData(D data) {
		this.data = data;
	}
	public S getStatus() {
		return status;
	}
	public void setStatus(S status) {
		this.status = status;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
}
