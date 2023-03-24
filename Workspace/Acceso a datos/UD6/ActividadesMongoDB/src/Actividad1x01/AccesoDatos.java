package Actividad1x01;

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
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import entrada.Teclado;

public class AccesoDatos {



	public static boolean insertar(Libro libroInsertar){

		MongoClient cliente = new MongoClient();
		try {
			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> libros = bd.getCollection("libros");
			Document libro = new Document();
			libro.put("codigo", libroInsertar.getCodigo());
			libro.put("titulo", libroInsertar.getTitulo());
			libro.put("autor", libroInsertar.getAutor());
			libro.put("agno", libroInsertar.getAgno());
			libro.put("genero", libroInsertar.getGenero());
			libros.insertOne(libro);
			return true;
		}finally {
			cliente.close();
		}
	}

	public static ArrayList<Libro> consultar(){
		MongoClient cliente = new MongoClient();
		ArrayList<Libro> librosArray = new ArrayList<Libro>(); 
		try {
			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> libros = bd.getCollection("libros");
			List<Document> resultados = libros.find()
					.into(new ArrayList<Document>());


			for (int i = 0 ; i < resultados.size() ; i++) {
				Document libro = resultados.get(i);
				Libro libroObj = new Libro(libro.getInteger("codigo"),libro.getString("titulo"),
						libro.getString("autor"),libro.getInteger("agno"),libro.getString("genero"));
				librosArray.add(libroObj);
			}



		}finally {
			cliente.close();
		}
		return librosArray;
	}

	public static Libro consultarCodigo(int codigo) {
		Libro libroObj=null;
		MongoClient cliente = new MongoClient();
		try {

			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> libros = bd.getCollection("libros");
			Document libro = libros.find(eq("codigo", codigo))
					.sort(ascending("agno"))
					.first();
			if(libro!=null) {
				libroObj = new Libro(libro.getInteger("codigo"),libro.getString("titulo"),
						libro.getString("autor"),libro.getInteger("agno"),libro.getString("genero"));
			}

		}finally {
			cliente.close();
		}
		return libroObj;
	}

	public static int actualizar(Libro libro) {
		MongoClient cliente = new MongoClient();
		try {
			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> libros = bd.getCollection("libros");
			Bson filtro = eq("codigo", libro.getCodigo());
			Bson modificaciones = combine(set("titulo", libro.getTitulo()),
					set("autor", libro.getAutor()),
					set("agno", libro.getAgno()),
					set("genero", libro.getGenero()));
			UpdateResult resultado = libros.updateOne(filtro, modificaciones);
			long librosActualizados = resultado.getModifiedCount();
			return (int)librosActualizados;

		}finally {
			cliente.close();
		}
	}

	public static int eliminar(int codigo) {
		MongoClient cliente = new MongoClient();
		try {
			MongoDatabase bd = cliente.getDatabase("biblioteca");
			MongoCollection<Document> amigos = bd.getCollection("libros");
			DeleteResult resultado = amigos.deleteOne(eq("codigo", codigo));
			long librosEliminados = resultado.getDeletedCount();
			return (int)librosEliminados;
		}finally {
			cliente.close();
		}

	}

}
