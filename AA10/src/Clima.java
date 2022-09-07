import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Clima {
	
	public static void main (String [] args)
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("SU NOMBRE: ");
		String nombre = sc.next();
		System.out.println("Bienvenido " + nombre + "\n");
		sc.close();
		
		try
		{
		
			//https://www.el-tiempo.net/api/json/v2/provincias/28
			String link = "https://www.el-tiempo.net/api/json/v2/provincias/28";
			
			URL url = new URL (link); //Declaro la URL
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //Declaro la conexion
			conn.connect(); //Abro la conexion
			
			int tiempoRespuesta = conn.getResponseCode(); //Para manejo de 
														  //Situaciones
			
			if(tiempoRespuesta != 200)
			{
				throw new RuntimeException("HttpResponse" + tiempoRespuesta);
			}
			else
			{
				StringBuilder sb = new StringBuilder();
				sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
					sb.append(sc.nextLine());
				}
				sc.close();
				JSONObject jsonObject = new JSONObject(sb.toString());
				//System.out.println(jsonObject.toString());
				System.out.println(jsonObject.getString("metadescripcion") + ":");
				System.out.println("----------------------------------------------------");
				System.out.println("- Hoy: " + jsonObject.getJSONObject("today").get("p"));
				System.out.println("- Mañana: " + jsonObject.getJSONObject("tomorrow").get("p"));
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
