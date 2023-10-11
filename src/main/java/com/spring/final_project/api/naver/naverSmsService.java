package com.spring.final_project.api.naver;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import static com.spring.final_project.api.util.apiConfig.*;

@Service
public class naverSmsService {
	@SuppressWarnings("unchecked")
	public void send_msg(String tel, String rand) {

		String method = "POST";
		String timestamp = Long.toString(System.currentTimeMillis());


		// JSON 을 활용한 body data 생성
		JSONObject bodyJson = new JSONObject();
		JSONObject toJson = new JSONObject();
		JSONArray toArr = new JSONArray();

		// 난수와 함께 전송
		toJson.put("content","Free 본인인증 ["+rand+"]");
		toJson.put("to",tel);
		toArr.put(toJson);

		// 메시지 Type (sms | lms)
		bodyJson.put("type","sms");
		bodyJson.put("contentType","COMM");
		bodyJson.put("countryCode","82");

		bodyJson.put("content","Free 핸드폰 인증 문자");

		// 발신번호 * 사전에 인증/등록된 번호만 사용할 수 있습니다.
		bodyJson.put("from", NAVER_FROM_NUMBER);
		bodyJson.put("messages", toArr);

		String body = bodyJson.toString();

		System.out.println(body);

		try {
			URL url = new URL(NAVER_SMS_URL);

			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
			con.setRequestProperty("x-ncp-iam-access-key", NAVER_ACCESSKEY);
			con.setRequestProperty("x-ncp-apigw-signature-v2", makeSignature(NAVER_SMS_SIGNATURE_URL, timestamp, method, NAVER_ACCESSKEY, NAVER_SECRETKEY));
			con.setRequestMethod(method);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());

			wr.write(body.getBytes());
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.println("responseCode" +" " + responseCode);
			if(responseCode==202) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

			System.out.println(response.toString());

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String makeSignature(
			String url,
			String timestamp,
			String method,
			String accessKey,
			String secretKey
	) throws NoSuchAlgorithmException, InvalidKeyException {

		String space = " ";
		String newLine = "\n";

		String message = new StringBuilder()
				.append(method)
				.append(space)
				.append(url)
				.append(newLine)
				.append(timestamp)
				.append(newLine)
				.append(accessKey)
				.toString();

		SecretKeySpec signingKey;
		String encodeBase64String;
		try {
			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
		} catch (UnsupportedEncodingException e) {
			encodeBase64String = e.toString();
		}
		return encodeBase64String;
	}

	public String sendRandomMessage(String tel) {
		naverSmsService message = new naverSmsService();
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		System.out.println("회원가입 문자 인증 => " + numStr);

		message.send_msg(tel, numStr);

		return numStr;
	}
}

