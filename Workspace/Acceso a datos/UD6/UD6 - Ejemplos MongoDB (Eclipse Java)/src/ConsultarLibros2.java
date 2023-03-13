import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConsultarLibros2 {

	public static void main(String[] args) {
		MongoClient cliente = new MongoClient();
		MongoDatabase bd = cliente.getDatabase("biblioteca");
		MongoCollection<Document> libros = bd.getCollection("libros");
		List<Document> resultados = libros.find()
		                                  .into(new ArrayList<Document>());
		if (resultados.isEmpty()) {
			System.out.println("No se ha encontrado ning�n libro.");
		}
		else {
			for (int i = 0 ; i < resultados.size() ; i++) {
				Document libro = resultados.get(i);
				System.out.println("ID=" + libro.getObjectId("_id") +
		                           ", C�digo=" + libro.getInteger("codigo") +
		                           ", T�tulo=" + libro.getString("titulo") +
		                           ", Autor=" + libro.getString("autor") +
		                           ", A�o=" + libro.getInteger("agno") +
		                           ", G�nero=" + libro.getString("genero"));
			}
			System.out.println("Se han consultado " + resultados.size() + " libros.");
		}
		cliente.close();
	}

}