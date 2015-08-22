package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.TestService;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
		System.out.println("Post method hit for Search");
		TestService s = new TestService();
		try{
			String fromDateString = request.getParameter("fromDate");
			String toDateString = request.getParameter("toDate");
			System.out.println("fromDate = "+fromDateString+" endDate="+toDateString);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd");
			if(fromDateString!=null && toDateString!=null){
				Date fromDate = sdf.parse(fromDateString);
				Date toDate = sdf.parse(toDateString);
				System.out.println("Date:"+fromDate+"....."+toDate);
				List searchResultList = s.fetchSearchResult(fromDate, toDate);
				prepareDisplay(searchResultList);
			}
			
		}catch(Exception e){
			System.out.println("Exception while searching vehicles");
		}
		//RequestDispatcher rd = request.getRequestDispatcher("/SearchResult.jsp");
		//rd.forward(request, response);
	}
	
}
