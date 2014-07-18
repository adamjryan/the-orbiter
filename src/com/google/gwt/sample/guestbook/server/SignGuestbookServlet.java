/* bcwti
 *
 * Copyright (c) 2014 PTC, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of PTC
 * and is subject to the terms of a software license agreement.
 * You shall not disclose such confidential information and shall use it
 * only in accordance with the terms of the license agreement.
 *
 * ecwti
 */
package com.google.gwt.sample.guestbook.server;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class SignGuestbookServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    // private static final Logger log = Logger.getLogger(SignGuestbookServlet.class.getName());

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        String guestbookName = req.getParameter("guestbookName");
        Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
        String content = req.getParameter("content");
        Date date = new Date();
        Entity greeting = new Entity("Greeting", guestbookKey);
        greeting.setProperty("user", user);
        greeting.setProperty("date", date);
        greeting.setProperty("content", content);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(greeting);

        // if (content == null) {
        // content = "(No greeting)";
        // }
        // if (user != null) {
        // log.info("Greeting posted by user: " + user.getNickname() + ": " + content);
        // }
        // else {
        // log.info("Greeting posted anonymously: " + content);
        // }

        resp.sendRedirect("/guestbook.jsp?guestbookName=" + guestbookName);
        // resp.sendRedirect("/guestbook.jsp");
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if( user != null ){
            resp.setContentType( "text/plain" );
            resp.getWriter().println( "Hello, " + user.getNickname() );
//          resp //.getOutputStream()
//          .write( "<a href=\"" + userService.createLogoutURL( req.getRequestURI() ) + "\">sign out</a>" );
//          .getWriter()
//          .write( "<a href=\"" + resp.encodeURL( req.getRequestURI() ) + "\"\\>" );
//          .write( "<a href=\"" + httpServletResponse.encodeURL("/send/rulepush?ip=" + ip) + "\">);
        }
        else{
            resp.sendRedirect(  userService.createLoginURL( req.getRequestURI() )  );
        }

//      <a href="/_ah/logout?continue=%2Fguestbook" >sign out       </a>
//      <a href="http://www.w3schools.com"          >Visit W3Schools</a>
    }
}