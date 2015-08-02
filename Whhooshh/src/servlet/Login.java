package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.TestService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TestService s = new TestService();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		System.out.println("Inside post method");
		String radioBtnValue = request.getParameter("option");
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		System.out.println("Value="+radioBtnValue);
		if(radioBtnValue!=null && radioBtnValue.equals("2")){
			try{
				s.signUpUser(userName,passWord);
				RequestDispatcher rd  = request.getRequestDispatcher("/RegistrationSuccessful.jsp");
				rd.forward(request, response);
			}catch(Exception ex){
				System.out.println("Exception caught");
				RequestDispatcher rd  = request.getRequestDispatcher("/Error.jsp");
				rd.forward(request, response);
			}
		}else if(radioBtnValue!=null && radioBtnValue.equals("1")){
			try{
				boolean val = s.fetchUserDetails(userName,passWord);
				if(val){
					RequestDispatcher rd  = request.getRequestDispatcher("/Success.jsp");
					rd.forward(request, response);	
				}else{
					RequestDispatcher rd  = request.getRequestDispatcher("/Unsuccess.jsp");
					rd.forward(request, response);	
				}
			}catch(Exception ex){
				System.out.println("Exception caught while fetching user details");
				RequestDispatcher rd  = request.getRequestDispatcher("/Error.jsp");
				rd.forward(request, response);
			}
		}
	}

}
