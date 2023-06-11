package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

	private int sessionCount;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		sessionCount++;
		System.out.println("[리스너]세션 생성:"+ se.getSession().getId());	
		System.out.println("[리스너]세션카운트:"+ this.sessionCount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		sessionCount--;
		System.out.println("[리스너]세션 소멸:"+ se.getSession().getId());
		System.out.println("[리스너]세션카운트:"+ this.sessionCount);
	}
}
