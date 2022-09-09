

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
 * Servlet implementation class update
 */
@WebServlet("/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		int eid=Integer.parseInt(request.getParameter("eid"));
		String name=request.getParameter("uname");
		String password=request.getParameter("pwd");
		String email=request.getParameter("email");
		String mobileno=request.getParameter("phno");
		String address=request.getParameter("addr");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","basha1","basha123");
			PreparedStatement ps=con.prepareStatement("update employee set name=?,password=?,email=?,mobileno=?,address=? where eid=?");
			ps.setString(1,name);
			ps.setString(2,password);
			ps.setString(3,email);
			ps.setString(4,mobileno);
			ps.setString(5,address);
			ps.setInt(6, eid);
			int i=ps.executeUpdate();
			if(i==0)
			{
				out.println("record not found");
			}else
			{
				out.println(i+"recoard is update");
			}
			con.close();
		}
		catch(Exception ex)
		{
			out.println(ex);
		
			}
	}

}
