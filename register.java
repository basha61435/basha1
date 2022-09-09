

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		Integer eid=Integer.parseInt(request.getParameter("eid"));
		String name=request.getParameter("uname");
		String password=request.getParameter("pwd");
		String email=request.getParameter("email");
		long mobileno=Long.parseLong(request.getParameter("phno"));
		String gender=request.getParameter("gen");
		String address=request.getParameter("addr");
		String country=request.getParameter("country");
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","basha1","basha123");
			
			PreparedStatement ps=con.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?)");
			ps.setInt(1,eid );
			ps.setString(2,name);
			ps.setString(3,password);
			ps.setString(4,email);
			ps.setLong(5, mobileno);
			ps.setString(6,gender);
			ps.setString(7,address);
			ps.setString(8, country);
		
			int i=ps.executeUpdate();
			out.println(i+"record inserted");
			con.close();
		}
		catch(Exception ex)
		{
			out.println(ex);
		}
	}

}
