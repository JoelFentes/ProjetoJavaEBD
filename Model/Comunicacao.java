package Main.Model;

public interface Comunicacao {
	int getId();
	void setId(int id);

	String getData();
	void setData(String data);

	String getMensagem();
	void setMensagem(String mensagem);

	String getProfessorNome();
	void setProfessorNome(String nomeProfessor);

	// Métodos sobrecarregados
	void setComunicacao(int id, String data, String mensagem, String nomeProfessor);
	void setComunicacao(String data, String mensagem);
}
