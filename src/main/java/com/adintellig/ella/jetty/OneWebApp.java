//========================================================================
//Copyright 2007 Mort Bay Consulting Pty. Ltd.
//------------------------------------------------------------------------
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at 
//http://www.apache.org/licenses/LICENSE-2.0
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
//========================================================================

package com.adintellig.ella.jetty;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class OneWebApp
{
    public static void main(String[] args) throws Exception
    {
        String jetty_home = "jetty";
        int port = 8020;

        Server server = new Server();
        
        Connector connector=new SelectChannelConnector();
        connector.setPort(port);
        server.addConnector(connector);
        
        WebAppContext webapp = new WebAppContext(jetty_home + "/web", "/jetty");
        
        server.setHandler(webapp);
        
        server.start();
        
        System.out.println("start");
   		System.getProperties()
   				.put("org.quartz.properties", "quartz.properties");
   		try {
   			StdSchedulerFactory.getDefaultScheduler().start();
   		} catch (SchedulerException e) {
   			e.printStackTrace();
   		}
   		System.out.println("end");
    }
}
