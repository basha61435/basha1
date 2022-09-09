

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class selector
 */
@WebServlet("/selector")
public class selector extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selector() {
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
		
		String eid=request.getParameter("eid");
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","basha1","basha123");
			PreparedStatement ps=con.prepareStatement("select*from employee where eid=?");
			
			ps.setString(1, eid);
			ResultSet rs=ps.executeQuery();
			
			ResultSetMetaData rss=rs.getMetaData();
			int n=rss.getColumnCount();
			
			out.println("<table border='1' style=border-collapse:collapse");
			
			out.println("<tr>");
				for(int i=1;i<=n;i++)
					
					
					out.println("<td><br>"+rss.getColumnName(i));
					out.println("</tr>");
				
				if(rs.next())
				{
			for(int i=1;i<=n;i++)
			
				out.print("<td><br>"+rs.getString(i));
				out.println("<tr>");
			
			out.println("</table>");
			}
				else
			out.print("norecord found");
			
	}
		catch(Exception ex)
		{
			out.println(ex);
		}

}
}
