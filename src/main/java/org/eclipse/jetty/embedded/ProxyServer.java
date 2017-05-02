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

import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.handler.ConnectHandler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.ProxyServlet;

public class ProxyServer
{
  private static void reverseProxy() throws Exception{
    Server server = new Server();

    SocketConnector connector = new SocketConnector();
    connector.setHost("127.0.0.1");
    connector.setPort(8888);

    server.setConnectors(new Connector[]{connector});

    // Setup proxy handler to handle CONNECT methods
    ConnectHandler proxy = new ConnectHandler();
    server.setHandler(proxy);

    // Setup proxy servlet
    ServletContextHandler context = new ServletContextHandler(proxy, "/", ServletContextHandler.SESSIONS);
    ServletHolder proxyServlet = new ServletHolder(ProxyServlet.Transparent.class);
    proxyServlet.setInitParameter("ProxyTo", "https://localhost:54321/");
    proxyServlet.setInitParameter("Prefix", "/");
    context.addServlet(proxyServlet, "/*");

    server.start();
  }

  public static void main( String[] args ) throws Exception
  {
    reverseProxy();
  }
}
