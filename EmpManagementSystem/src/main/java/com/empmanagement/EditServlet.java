package com.empmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<h1>Update Employee</h1>");
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		
		Emp e=EmpDao.getEmpById(id);
		
		out.print("<form action='EditServlet2' method='post'");
		out.print("<table>");
		out.print("<tr>"+"<td></td><td><input type='hidden' name='id' value='"+e.getId()+"'></td>"+"</tr><br><br>");
		out.print("<tr><td>Name</td><td><input type='text' name='name' value='"+e.getName()+"'></td></tr><br><br>");
		out.print("<tr><td>Mobile</td><td><input type='text' name='phone' value='"+e.getPhone()+"'></td></tr><br><br>");
		out.print("<tr><td>Email</td><td><input type='email' name='email' value='"+e.getEmail()+"'></td></tr><br><br>");
		out.print("<tr><td colsan='2'><input type='submit' value='Edit & Save'></tr></td>");
		out.print("</table>");
		out.print("</form>");
		
		out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
