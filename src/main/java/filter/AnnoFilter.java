package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter(filterName="AnnoFilter", urlPatterns="/15FilterListener/AnnoFilter.jsp")
public class AnnoFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException { 
		/* 처리할 내용이 없다면 오버라이딩하지 않아도 됩니다. */		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		System.out.println("검색필드 : "+ searchField);
		System.out.println("검색어 : "+ searchWord);
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		/* 처리할 내용이 없다면 정의하지 않아도 됩니다. */
	}
}