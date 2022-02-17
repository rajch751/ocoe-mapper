package com.uob.xml.parse.ocoemapper.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;

import com.uob.xml.parse.ocoemapper.model.ExObject;

public class TestClient implements Serializable {

	public static void main(String[] args) throws ClassNotFoundException, KeyStoreException, NoSuchAlgorithmException,
			CertificateException, UnrecoverableKeyException, KeyManagementException, NoSuchProviderException {

		try {

			URL url = new URL("https://localhost:8080/v1/parse/test");
			// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			javax.net.ssl.HttpsURLConnection conn = (javax.net.ssl.HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			// conn.s
			conn.setSSLSocketFactory(initssl());
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			ExObject exObject = new ExObject("first", 1, 2, "fourth", "fifth");
			ObjectOutputStream oos = new ObjectOutputStream(conn.getOutputStream());
			oos.writeObject(exObject);
			oos.flush();
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			/*
			 * BufferedReader br = new BufferedReader(new
			 * InputStreamReader((conn.getInputStream())));
			 * 
			 * String output; System.out.println("Output from Server .... \n"); while
			 * ((output = br.readLine()) != null) { System.out.println(output); }
			 */

			ObjectInputStream ois = new ObjectInputStream(conn.getInputStream());

			/*
			 * InputStream inputStream = conn.getInputStream(); byte[] bytedata =
			 * StreamUtils.copyToByteArray(inputStream);
			 * 
			 * 
			 * ByteArrayInputStream bis = new ByteArrayInputStream(bytedata); ObjectInput in
			 * = new ObjectInputStream(bis);
			 * 
			 * Object obj=in.readObject(); System.out.println(obj+"clientreq");
			 * 
			 * ExObject retrunObject=(ExObject) obj;
			 */
			ExObject obj = (ExObject) ois.readObject();

			System.out.println("Server return" + obj);

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	private static SSLSocketFactory initssl() throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, NoSuchProviderException, UnrecoverableKeyException, KeyManagementException {
		// TODO Auto-generated method stub

		new Properties();

		Properties props = System.getProperties();
		// KeyStoreInfo keyStore = new KeyStoreInfo(keyStorePath, keyStorePassword,
		// keyStoreType);
		props.put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		System.setProperties(props);
		// Security.addProvider(props);
//     Security.addProvider(new TestProvider("localhost")); 
		
		//ResourceLoader resourceLoader = new DefaultResourceLoader();


		FileInputStream fis = new FileInputStream(
				"src/main/resources/localhost.jks");
		String password = "changeit";
		KeyStore ks = KeyStore.getInstance("pkcs12");
		ks.load(fis, password.toCharArray());
		String alia = "localhost";
		X509Certificate c = (X509Certificate) ks.getCertificate(alia);
		String certBankCode = c.getSubjectDN().getName().substring(0).toUpperCase();
		System.out.println(certBankCode);

		//String password = "changeit";
		FileInputStream cafis = new FileInputStream(
				"src/main/resources/localhost.jks");
		// String password="changeit";
		KeyStore caks = KeyStore.getInstance("jks");
		caks.load(cafis, password.toCharArray());

		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");

		tmf.init(caks);

		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
		kmf.init(caks, password.toCharArray());

		KeyManager[] keyM = kmf.getKeyManagers();
		TrustManager[] trustm = tmf.getTrustManagers();
		SSLContext sslctx = SSLContext.getInstance("TLS", "SunJSSE");
		SecureRandom rand = SecureRandom.getInstance("SHA1PRNG", "SUN");
		sslctx.init(keyM, trustm, rand);

		System.out.println("creating ssl");
		SSLSocketFactory sl = sslctx.getSocketFactory();
		System.out.println("done");
		return sl;
	}

	private static class TestProvider extends Provider {
		TestProvider(String name) {
			super(name, "0.0", "Not for use in production systems!");
		}
	}

	private static byte[] getByteArray(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (ObjectOutputStream os = new ObjectOutputStream(bos)) {
			os.writeObject(obj);
		}
		return bos.toByteArray();
	}

}
