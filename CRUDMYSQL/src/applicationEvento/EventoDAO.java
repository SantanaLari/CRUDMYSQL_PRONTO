package applicationEvento;

import java.util.List;

public interface EventoDAO {
	void salvar(Evento ev);
	void apagar(Evento ev);
	void atualizar(Evento ev);
	List<Evento> listar(String nome);
}
