import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;


public class AccesoVideojuegos {


	private static String collection="videojuegos";


	public static void insertar(Videojuego videojuego){

		MongoClient cliente = new MongoClient();
		try {
			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> videojuegosDocument = bd.getCollection(collection);
			Document videojuegoInsertar = new Document();
			videojuegoInsertar.put("codigo", videojuego.getCodigo());
			videojuegoInsertar.put("titulo", videojuego.getTitulo());
			videojuegoInsertar.put("agno", videojuego.getAgno());
			videojuegoInsertar.put("desarrollador", videojuego.getDesarrollador());
			videojuegoInsertar.put("precio", videojuego.getPrecio());
			videojuegosDocument.insertOne(videojuegoInsertar);
		}finally {
			if(cliente!=null)
				cliente.close();
		}
	}

	public static ArrayList<Videojuego> consultar(){
		MongoClient cliente = new MongoClient();
		ArrayList<Videojuego> videojuegosArray = new ArrayList<Videojuego>(); 
		try {
			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> videojuegosDocument = bd.getCollection(collection);
			List<Document> resultados = videojuegosDocument.find()
					.into(new ArrayList<Document>());


			for (int i = 0 ; i < resultados.size() ; i++) {
				Document videojuego = resultados.get(i);
				Videojuego videojuegoObj = new Videojuego(videojuego.getInteger("codigo"),
						videojuego.getString("titulo"),
						videojuego.getInteger("agno"),
						videojuego.getString("desarrollador"),
						videojuego.getDouble("precio")
						);
				videojuegosArray.add(videojuegoObj);
			}

		}finally {
			if(cliente!=null)
				cliente.close();
		}
		return videojuegosArray;
	}

	public static Videojuego consultarCodigo(int codigo) {
		Videojuego videojuegoObj=null;
		MongoClient cliente = new MongoClient();
		try {

			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> videojuegosDocument = bd.getCollection(collection);
			Document videojuego = videojuegosDocument.find(eq("codigo", codigo))
					.sort(ascending("agno"))
					.first();
			if(videojuego!=null) {
				videojuegoObj = new Videojuego(videojuego.getInteger("codigo"),
						videojuego.getString("titulo"),
						videojuego.getInteger("agno"),
						videojuego.getString("desarrollador"),
						videojuego.getDouble("precio")
						);
			}

		}finally {
			if(cliente!=null)
				cliente.close();
		}
		return videojuegoObj;
	}

	public static int actualizar(Videojuego videojuego) {
		MongoClient cliente = new MongoClient();
		try {
			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> videojuegosDocument = bd.getCollection(collection);
			Bson filtro = eq("codigo", videojuego.getCodigo());
			Bson modificaciones = combine(
					set("titulo", videojuego.getTitulo()),
					set("agno", videojuego.getAgno()),
					set("desarrollador", videojuego.getDesarrollador()),
					set("precio", videojuego.getPrecio())
					);

			UpdateResult resultado = videojuegosDocument.updateMany(filtro, modificaciones);
			long videojuegosActualizados = resultado.getModifiedCount();
			return (int)videojuegosActualizados;

		}finally {
			if(cliente!=null)
				cliente.close();
		}
	}
	
	public static int eliminar(int codigo) {
		MongoClient cliente = new MongoClient();
		try {
			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> videojuegos = bd.getCollection(collection);
			DeleteResult resultado = videojuegos.deleteMany(eq("codigo", codigo));
			long videojuegosEliminados = resultado.getDeletedCount();
			return (int)videojuegosEliminados;
		}finally {
			if(cliente!=null)
				cliente.close();
		}

	}
	 
}
