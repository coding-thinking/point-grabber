package com.sss.point.grabber.configuration;

import java.io.IOException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.HttpClients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public class HttpsRestTemplate extends RestTemplate {
    private static final Logger log = LoggerFactory.getLogger(HttpsRestTemplate.class);
	public HttpsRestTemplate() {
		super();
		try {
			super.setRequestFactory(clientHttpRequestFactory());
			//super.setErrorHandler(new CustomErrorHandler() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public class CustomErrorHandler extends DefaultResponseErrorHandler {

		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
			log.error(response.getStatusText());
			log.error(response.getBody().toString());
			log.error(response.getStatusCode().toString());
		}

	} 

	private ClientHttpRequestFactory clientHttpRequestFactory() throws Exception {
		// HttpComponentsClientHttpRequestFactory httpRequestFactory = new
		// HttpComponentsClientHttpRequestFactory();
		//
		X509TrustManager tm = new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
					throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
					throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}

		};
		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		sslContext.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(
				HttpClients.custom().setSSLSocketFactory(ssf).build());
        
        
        int apiTimeOut = 5 * 1000 ;
		httpRequestFactory.setConnectionRequestTimeout(apiTimeOut);
		httpRequestFactory.setConnectTimeout(apiTimeOut);
		httpRequestFactory.setReadTimeout(apiTimeOut);
		return httpRequestFactory;
	}

}
