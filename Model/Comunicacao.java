package Main.Model;

public interface Comunicacao {
	int getId();
	void setId(int id);

	String getData();
	void setData(String data);

	String getMensagem();
	void setMensagem(String mensagem);

	int getProfessorId();
	void setProfessorId(int professorId);

	void setComunicacao(int id, String data, String mensagem, int professorId);
	void setComunicacao(String data, String mensagem);


}
