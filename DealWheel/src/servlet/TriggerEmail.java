package servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.mail.SendMail;
import services.utility.CommonUtility;
import services.utility.QueryConstant;

/**
 * Servlet implementation class TriggerEmail
 */
@WebServlet("/TriggerEmail")
public class TriggerEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TriggerEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DealWheel");
		EntityManager em = emf.createEntityManager();
		System.out.println("Trigger Email: Post Hit");
		String actionCode = (String)request.getParameter("actionCode");
		if("confirmationEmail".equalsIgnoreCase(actionCode)){
			String bookingId = (String)request.getParameter("bookingId");
			String vehicleDetails = (String)request.getParameter("vehicleDetails");
			String userId = (String)request.getParameter("userId");
			/*Query q1= em.createNativeQuery(QueryConstant.GET_USER_EMAIL);
			q1.setParameter(1,userId);
			System.out.println("UserID"+ userId);
			System.out.println(bookingId);
			
			String uMail = (String) q1.getSingleResult();
			
		//	System.out.println("userId="+userId+" BookingId="+bookingId +" vehicleDetails="+vehicleDetails);
			//logger.info("User Email"+uMail);
			System.out.println(uMail); */
			SendMail.sendEmailNotification("confirmationEmail",vehicleDetails,"Booking Confirmation from Deal Wheel",userId);
			//CommonUtility.sendEmailNotification("confirmationEmail",bookingId,userId,vehicleDetails,null);
		}else if("forgotPassword".equalsIgnoreCase(actionCode)){
			String tempPwd = (String)request.getParameter("tempPwd");
			String userEmail = (String)request.getParameter("userId");
			System.out.println("tempPwd= "+tempPwd+" userEmail="+userEmail);
			//CommonUtility.sendEmailNotification("forgotPassword",null,userEmail,null,tempPwd);
		}
	}

}
