package servlet;

import static services.utility.GenericConstant.USER_MODEL;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import org.apache.log4j.Logger;

@WebServlet("/VerifyEmail")
public class VerifyEmail extends HttpServlet  {
	static final Logger logger = Logger.getLogger(VerifyEmail.class);
	private static final long serialVersionUID = -4722481661615371746L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try{
			String email = req.getParameter("email");
			String output = null;
			BigInteger otp = new BigInteger(req.getParameter("otp"));
			
			User u = null;
			HttpSession session = req.getSession();
			if(session !=null){
				u = (User) req.getSession().getAttribute(USER_MODEL);
			}
			
			if(u.getUserEmailOtp()== String.valueOf(otp)){
				output = "SUCCESS";
			}else{
				output = "FAILURE";
			}
			
			resp.getWriter().write(output);
		}catch(Exception e){
			logger.info("ERROR"+e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
