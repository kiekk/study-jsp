package com.koreait.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/Hello")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub                    내 경로를 제외한 경로
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//getWriter() : 응답할 html코드를 작성할때 사용
		
		//응답할때 contentType을 전달한 값으로 셋팅
		response.setContentType("text/html; charset=UTF-8");
		//html코드를 작성하기 위해서 getWriter()를 out에 담아줍니다.
		PrintWriter out = response.getWriter();
		
		//out.print와 out.println의 차이 -> 둘다 줄바꿈은 되지 않습니다.
		
		out.println("<html><body><h1>서블릿으로 만든 페이지</h1>");
		out.println("<a href='intro'>JSP로 이동</a></body></html>");
		
		//PrintWriter도 버퍼이기 때문에 항상 close()를 호출해서 닫아줘야 합니다.
		out.close();
		
	}
}
