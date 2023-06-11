package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebFilter(urlPatterns={"/09PagingBoard/Write.jsp",
		"/09PagingBoard/Edit.jsp","/09PagingBoard/DeleteProcess.jsp"})
public class IsSessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		
		if(session.getAttribute("UserId")==null) {
			String backUrl = req.getRequestURI();
			JSFunction.alertLocation(resp, 
				"[Filter]로그인 후 이용해주십시오.", 
				"../15FilterListener/LoginFilter.jsp?backUrl="+backUrl);
			return;
		}
		else {
			chain.doFilter(request, response);
		}
	}
}
