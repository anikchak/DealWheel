package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
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

import org.apache.log4j.Logger;

import services.CustomerControllerService;
import services.mail.SendMail;
import services.utility.CommonUtility;
import services.utility.GenericConstant;

/**
 * Servlet implementation class ConfirmationSummary
 */
@WebServlet("/ConfirmationSummary")
public class ConfirmationSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(ConfirmationSummary.class);   
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
		logger.info("Hitting Confirmation Summary post method");
		String paymentStatus = "success";
		String pageContext = request.getContextPath();
		if(paymentStatus!=null && "success".equalsIgnoreCase(paymentStatus)){
			HttpSession session = request.getSession();
			String tempBookingseq = (String)request.getParameter("tempSelectedVehicle");
			//String vehicleDetails = (String)request.getParameter("vehicleDetail");
			String orderLocationName = (String)request.getParameter("orderLocationName");
			logger.info("tempBookingseq="+tempBookingseq);
			//logger.info("vehicleDetails="+vehicleDetails);
			logger.info("orderLocationName="+orderLocationName);
			String hexString = "Order-"+tempBookingseq;
			String bookingId = orderLocationName+" - "+String.format("%x", new BigInteger(1, hexString.getBytes(StandardCharsets.UTF_8)));
			CustomerControllerService s = new CustomerControllerService();
			//if()
			boolean status = s.updateBookingWithOrderIdonSuccess(Long.parseLong(tempBookingseq),bookingId,(String) request.getSession().getAttribute(GenericConstant.USERNAME));
			if(status){
				request.getSession().setAttribute("tempBookingOrderId", tempBookingseq);
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

