package platzi.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/platzi-java")
public class PlatziJava extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String message = req.getParameter("message");
		resp.setContentType("application/json");
		System.out.println("atributo desde el filtro " + req.getAttribute("segundoEstado") );
		try (PrintWriter out = resp.getWriter()){
			out.print("{\"message\": \"" + message + "\"}");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String line;
		StringBuffer json = new StringBuffer();
		try (BufferedReader reader = req.getReader()){
			while ((line = reader.readLine()) != null){
				json.append(line);
			}
		}
		System.out.println(json.toString());
		ObjectMapper mapper = new ObjectMapper();
		Message message = mapper.readValue(json.toString(), Message.class);
		System.out.println("message size" + message.getSize());
	}
	
}
