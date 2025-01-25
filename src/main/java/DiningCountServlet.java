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

//Servlet implementation class DiningCountServlet
@WebServlet("/DiningCountServlet")
public class DiningCountServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /* @see HttpServlet#HttpServlet()
     */
    public DiningCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String diningDate = request.getParameter("dinning_date").trim();
    System.out.print(response);
    int boys = Integer.parseInt(request.getParameter("boys").trim());
    int girls = Integer.parseInt(request.getParameter("girls").trim());
    int medical_staff = Integer.parseInt(request.getParameter("medical_staff").trim());
    int mess_staff = Integer.parseInt(request.getParameter("mess_staff").trim());
    int events = Integer.parseInt(request.getParameter("events").trim());
    int sports = Integer.parseInt(request.getParameter("sports").trim());
    int parents = Integer.parseInt(request.getParameter("parents").trim());
    store_data(out,diningDate,boys,girls,medical_staff,mess_staff,sports,events,parents);
  }
  public void store_data(PrintWriter out,String diningDate,int boys,int girls,int medical_staff,int mess_staff,int sports,int events,int parents) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmms","root","");
      PreparedStatement pstmt = con.prepareStatement("Insert into dinning_count_status (Dining_date, boys, girls, medical_staff, mess_staff, sports, events, parents) Values (?,?,?,?,?,?,?,?)");
      pstmt.setString(1, diningDate);
      pstmt.setInt(2, boys);
      pstmt.setInt(3, girls);
      pstmt.setInt(4, medical_staff);
      pstmt.setInt(5, mess_staff);
      pstmt.setInt(6, sports);
      pstmt.setInt(7, events);
      pstmt.setInt(8, parents);
      
      int i = pstmt.executeUpdate();
      if(i==1) {
        out.println("Record Inserted");
        pstmt.close();
        con.close();
      }
    }catch(Exception e) {
    	out.println(e.toString());
    	e.printStackTrace();
    }
  }
}