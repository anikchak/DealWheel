package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import services.CustomerControllerService;
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
		logger.info("Get Hit");
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
			String tempBookingseq = (String)request.getParameter("productinfo"); // productinfo:  is holding the selectedVehicle detail
			//String vehicleDetails = (String)request.getParameter("vehicleDetail");
			String orderLocationName = (String)request.getParameter("udf1"); //udf1: is holding the Location of the order
			logger.info("tempBookingseq="+tempBookingseq);
			//logger.info("vehicleDetails="+vehicleDetails);
			logger.info("orderLocationName="+orderLocationName);
			
			String hexString = "Order-"+tempBookingseq;
			String bookingId = orderLocationName+" - "+String.format("%x", new BigInteger(1, hexString.getBytes(StandardCharsets.UTF_8)));
			
			//Fetch values returned from Payu - Starts
			Map<String,String> payuReturnParams = new HashMap<String,String>();
			payuReturnParams.put("bookingId", bookingId);
			payuReturnParams.put("mihpayid", request.getParameter("mihpayid").toString());
			payuReturnParams.put("mode", request.getParameter("mode").toString());
			payuReturnParams.put("status", request.getParameter("status").toString());
			payuReturnParams.put("unmappedstatus", request.getParameter("unmappedstatus").toString());
			payuReturnParams.put("txnid", request.getParameter("txnid").toString());
			payuReturnParams.put("amount", request.getParameter("amount").toString());
			payuReturnParams.put("cardcategory", request.getParameter("cardCategory").toString());
			payuReturnParams.put("discount", request.getParameter("discount").toString());
			payuReturnParams.put("net_amount_debit", request.getParameter("net_amount_debit").toString());
			payuReturnParams.put("addedon", request.getParameter("addedon").toString());
			payuReturnParams.put("productinfo", request.getParameter("productinfo").toString());
			payuReturnParams.put("firstname", request.getParameter("firstname").toString());
			payuReturnParams.put("email", request.getParameter("email").toString());
			payuReturnParams.put("phone", request.getParameter("phone").toString());
			payuReturnParams.put("paymentsource", request.getParameter("payment_source").toString());
			payuReturnParams.put("pg_type", request.getParameter("PG_TYPE").toString());
			payuReturnParams.put("bank_ref_num", request.getParameter("bank_ref_num").toString());
			payuReturnParams.put("bankcode", request.getParameter("bankcode").toString());
			payuReturnParams.put("errorcode", request.getParameter("error").toString());
			payuReturnParams.put("error_message", request.getParameter("error_Message").toString());
			payuReturnParams.put("card_type", request.getParameter("card_type").toString());
			//Fetch values returned from Payu - Ends
			
			CustomerControllerService s = new CustomerControllerService();
			//Persisting Payment details
			s.updatePaymentDetails(payuReturnParams);
			if(request.getParameter("error")!=null && "e000".equalsIgnoreCase(request.getParameter("error").toString())){
			boolean status = s.updateBookingWithOrderIdonSuccess(Long.parseLong(tempBookingseq),bookingId,(String) request.getSession().getAttribute(GenericConstant.USERNAME));
			if(status){
				request.getSession().setAttribute("tempBookingOrderId", tempBookingseq);
				response.sendRedirect(pageContext+"/ConfirmationPage.jsp");
			}else{
				response.sendRedirect(pageContext+"/BookingError.jsp");
			}
		    }else{
		    	logger.info("payment error");
		    	response.sendRedirect(pageContext+"/BookingError.jsp");
		    }
		}else{
			response.sendRedirect(pageContext+"/BookingError.jsp");
		}
		
	}

	 
	}

