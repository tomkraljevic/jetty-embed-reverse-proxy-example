//
//  ========================================================================
//  Copyright (c) 1995-2015 Mort Bay Consulting Pty. Ltd.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.embedded;

import org.eclipse.jetty.proxy.AsyncProxyServlet;
import org.eclipse.jetty.proxy.ConnectHandler;
import org.eclipse.jetty.proxy.ProxyServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

//import org.eclipse.jetty.server.nio.SelectChannelConnector;

public class ProxyServer
{
//  public static void main( String[] args ) throws Exception
//  {
//    Server server = new Server();
//    ServerConnector connector = new ServerConnector(server);
//    connector.setPort(8888);
//    server.addConnector(connector);
//
//    // Setup proxy handler to handle CONNECT methods
//    ConnectHandler proxy = new ConnectHandler();
//    server.setHandler(proxy);
//
//    // Setup proxy servlet
//    ServletContextHandler context = new ServletContextHandler(proxy, "/",
//            ServletContextHandler.SESSIONS);
//    ServletHolder proxyServlet = new ServletHolder(ProxyServlet.class);
//    proxyServlet.setInitParameter("blackList", "www.eclipse.org");
//    context.addServlet(proxyServlet, "/*");
//
//    server.start();
//  }

  /*
  public static void main(String[] args) throws Exception {
    Server server=new Server();
//    SelectChannelConnector selectChannelConnector=new SelectChannelConnector();
//    server.setConnectors(new Connector[]{selectChannelConnector});
//    selectChannelConnector.setPort(8080);

//    ContextHandler.Context servletContext=new ServletContextHandler.Context(Context.NO_SECURITY | Context.NO_SESSIONS);

    ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.NO_SECURITY | ServletContextHandler.NO_SESSIONS);


//    server.setHandler(servletContext);
    ServletHandler servletHandler=contextHandler.getServletHandler();
    ServletHolder proxy=new ServletHolder(ProxyServlet.Transparent.class);
    servletHandler.addServletWithMapping(proxy,"/ws/*");
    proxy.setInitParameter("ProxyTo","http://www.webtide.com");
    proxy.setInitParameter("Prefix","/ws");
//    FilterHolder filter=servletHandler.addFilterWithMapping(ContinuationFilter.class,"/*",0);
//    filter.setInitParameter("debug","true");
    server.start();
    server.join();
  }
  */

//  public static void main(String[] args) throws Exception {
//    Server server = new Server();
//    ServerConnector connector = new ServerConnector(server);
//    connector.setPort(8888);
//    server.addConnector(connector);
//
////    AsyncProxyServlet.Transparent proxy = new AsyncProxyServlet.Transparent();
//
//    ServletHandler servletHandler=contextHandler.getServletHandler();
//    ServletHolder proxy=new ServletHolder(AsyncProxyServlet.Transparent.class);
//    proxy.setInitParameter("ProxyTo","http://www.webtide.com");
//    proxy.setInitParameter("Prefix","/ws");
//
//  //  ConnectHandler handler = new ConnectHandler();
//
//    server.setHandler(proxy);
//
//    // Setup proxy handler to handle CONNECT methods
////    ConnectHandler proxy = new ConnectHandler();
////    server.setHandler(proxy);
//  }



  private static void reverseProxy() throws Exception{
    Server server = new Server();
    ServerConnector connector = new ServerConnector(server);
    connector.setPort(8888);
    server.addConnector(connector);

    // Setup proxy handler to handle CONNECT methods
    ConnectHandler proxy = new ConnectHandler();
    server.setHandler(proxy);

    // Setup proxy servlet
    ServletContextHandler context = new ServletContextHandler(proxy, "/", ServletContextHandler.SESSIONS);
    ServletHolder proxyServlet = new ServletHolder(AsyncProxyServlet.Transparent.class);
    proxyServlet.setInitParameter("proxyTo", "http://localhost:54321");
    proxyServlet.setInitParameter("prefix", "/");
    context.addServlet(proxyServlet, "/*");

    server.start();
  }

  public static void main( String[] args ) throws Exception
  {
    reverseProxy();
  }
}
