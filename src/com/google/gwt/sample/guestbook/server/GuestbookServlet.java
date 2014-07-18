package com.google.gwt.sample.guestbook.server;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class GuestbookServlet extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if( user != null ){
			resp.setContentType( "text/plain" );
			resp.getWriter().println( "Hello, " + user.getNickname() );
//			resp //.getOutputStream()
//			.write( "<a href=\"" + userService.createLogoutURL( req.getRequestURI() ) + "\">sign out</a>" );
//			.getWriter()
//			.write( "<a href=\"" + resp.encodeURL( req.getRequestURI() ) + "\"\\>" );
//			.write( "<a href=\"" + httpServletResponse.encodeURL("/send/rulepush?ip=" + ip) + "\">);
		}
		else{
			resp.sendRedirect(  userService.createLoginURL( req.getRequestURI() )  );
		}

//		<a href="/_ah/logout?continue=%2Fguestbook"	>sign out		</a>
//		<a href="http://www.w3schools.com"			>Visit W3Schools</a>
	}

//	resp.setContentType("text/plain");
//	resp.getWriter().println("Hello, world");
}