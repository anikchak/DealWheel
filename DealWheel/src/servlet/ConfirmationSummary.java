package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.CustomerControllerService;
import services.utility.CommonUtility;
import services.utility.GenericConstant;

/**
 * Servlet implementation class ConfirmationSummary
 */
@WebServlet("/ConfirmationSummary")
public class ConfirmationSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmationSummary() {
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
		System.out.println("Hitting Confirmation Summary post method");
		String paymentStatus = request.getParameter("paymentOption");
		System.out.println("Payment Status="+paymentStatus);
		String pageContext = request.getContextPath();
		if(paymentStatus!=null && "success".equalsIgnoreCase(paymentStatus)){
			HttpSession session = request.getSession();
			long tempBookingseq = (Long)session.getAttribute(GenericConstant.TEMPBOOKINGSEQ);
			System.out.println("tempBookingseq="+tempBookingseq);
			CustomerControllerService s = new CustomerControllerService();
			boolean status = s.updateBookingWithOrderIdonSuccess(tempBookingseq,"ORD-KA-BLR-"+tempBookingseq);
			if(status){
				request.getSession().setAttribute("BookingOrderId", "ORD-KA-BLR-"+tempBookingseq);
				response.sendRedirect(pageContext+"/ConfirmationPage.jsp");
				//new CommonUtility().sendEmailNotification("ORD-KA-BLR-"+tempBookingseq) ;
			}else{
				response.sendRedirect(pageContext+"/BookingError.jsp");
			}
		}else{
			response.sendRedirect(pageContext+"/BookingError.jsp");
		}
		
	}

	 
	}

