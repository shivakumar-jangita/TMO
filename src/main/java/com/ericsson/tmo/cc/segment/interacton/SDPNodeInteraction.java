package com.ericsson.tmo.cc.segment.interacton;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.utils.GenericUtils;

public class SDPNodeInteraction extends AbstractNodeInteraction {

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

	private static String execute(String request,  ConfigPropertiesPojo configPropertiesPojo) throws Exception {
		String finalReponse = null;
		CloseableHttpResponse httpResponse = null;
		CloseableHttpClient httpclient = null;
		try {
			httpclient = HttpClients.createDefault();
			
			String requestPayload = request.replace(SegmentConstants.ORIGIN_TRANSACTION_ID_DATA, GenericUtils.fetchOriginTransactionId());
			requestPayload = requestPayload.replace(SegmentConstants.TRANS_DATETIME, GenericUtils.getCurrentTimeStampTZ());
			
			System.out.println("Air Request: " + requestPayload);
			
			HttpPost httpPost = setHttpDetails(configPropertiesPojo.getAirUrl());
			httpPost.setEntity(new StringEntity(requestPayload));
			httpResponse = httpclient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			finalReponse = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return finalReponse;
	}

	private static HttpPost setHttpDetails(String url) {

		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader(SegmentConstants.ACCEPT, SegmentConstants.ACCEPT_VALUE);
		httpPost.setHeader(SegmentConstants.CORRECTION, SegmentConstants.CONNECTION_STATUS);
		httpPost.setHeader(SegmentConstants.CONTENT_TYPE, SegmentConstants.CONTENT_TYPE_VALUE);
		httpPost.setHeader(SegmentConstants.HOST, SegmentConstants.HOST_VALUE);
		httpPost.setHeader(SegmentConstants.USER_AGENT, SegmentConstants.USER_AGENT_VALUE);
		httpPost.setHeader(SegmentConstants.AUTHORIZATION, SegmentConstants.AUTHORIZATION_VALUE);

		return httpPost;
	}

}
