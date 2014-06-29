package com.krunchykreme.server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.server.TServer.Args;

import com.krunchykreme.server.KrunchyKreme.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Server {
	public final static int PORT = 9090;
    final static Logger logger = LoggerFactory.getLogger(Server.class);

	public static void main(String[] args) {
		try {
			TServerTransport serverTransport = new TServerSocket(PORT);
			Processor<KrunchyKremeHandler> processor = new KrunchyKreme.Processor<KrunchyKremeHandler>(
					new KrunchyKremeHandler());
			TServer server = new TSimpleServer(
					new Args(serverTransport).processor(processor));
			logger.info("Server starting on port "+PORT);
			server.serve();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
