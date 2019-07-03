package com.ericsson.tmo.cc.segment.interacton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.utils.GenericUtils;

public class NAPNodeInteraction extends AbstractNodeInteraction {

	@Override
	public String createRequest(String request,  ConfigPropertiesPojo configPropertiesPojo) throws Exception {
		String createReponse = execute(request, configPropertiesPojo);
		return createReponse;
	}

	@Override
	public String deleteRequest(String request,  ConfigPropertiesPojo configPropertiesPojo) throws Exception {
		String deleteReponse = execute(request, configPropertiesPojo);
		return deleteReponse;
	}

	@Override
	public String modifyRequest(String request,  ConfigPropertiesPojo configPropertiesPojo) throws Exception {
		String modifyReponse = execute(request, configPropertiesPojo);
		return modifyReponse;
	}

	@Override
	public String queryRequest(String request,  ConfigPropertiesPojo configPropertiesPojo) throws Exception {
		String queryReponse = execute(request, configPropertiesPojo);
		return queryReponse;
	}

	private static String execute(String request,  ConfigPropertiesPojo configPropertiesPojo) {
		
		String response = null;
		HttpEntity httpEntity = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>(5);
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(configPropertiesPojo.getNapUrl());
			String requestPayload = request.replace(SegmentConstants.TRANS_ID_DATA,
					GenericUtils.fetchOriginTransactionId());
			System.out.println("NAP Query: " + requestPayload);
			
			String hexString = GenericUtils.toShaHexString(configPropertiesPojo.getNapPassword() + GenericUtils.getTimestamp());
			params.add(new BasicNameValuePair(SegmentConstants.PASSWORD, hexString));
			System.out.println("timestamp & Password"+ hexString);
			params.add(new BasicNameValuePair(SegmentConstants.TIMESTAMP, GenericUtils.getTimestamp()));
			System.out.println("patnerID::"+  configPropertiesPojo.getNapPartnerId());
			params.add(new BasicNameValuePair(SegmentConstants.PARTNERID, configPropertiesPojo.getNapPartnerId()));
			params.add(new BasicNameValuePair(SegmentConstants.XMLPAYLOAD, requestPayload));
			httpPost.setEntity(new UrlEncodedFormEntity(params, SegmentConstants.FILE_FORMAT));
			CloseableHttpResponse httpResponse = client.execute(httpPost);
			try {
				httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity).toString();
				System.out.println("httpResponse:::===="+response);
			} catch (Exception e) {
				System.out.println("exception occured");
				e.printStackTrace();
			} finally {
				httpResponse.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (client != null)
					client.close();
				if (httpEntity != null)
					httpEntity = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("response: " + response);
		return response;
	}

}
