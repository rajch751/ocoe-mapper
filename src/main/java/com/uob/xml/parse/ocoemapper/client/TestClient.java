package com.uob.xml.parse.ocoemapper.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
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

import org.springframework.util.StreamUtils;

import com.uob.xml.parse.ocoemapper.model.ExObject;

public class TestClient implements Serializable {

	public static void main(String[] args) throws ClassNotFoundException {

		try {

			URL url = new URL("http://localhost:8080/v1/parse/test");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
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

	private static byte[] getByteArray(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (ObjectOutputStream os = new ObjectOutputStream(bos)) {
			os.writeObject(obj);
		}
		return bos.toByteArray();
	}

}
