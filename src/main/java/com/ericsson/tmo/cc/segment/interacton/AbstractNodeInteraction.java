package com.ericsson.tmo.cc.segment.interacton;

import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;

public abstract class AbstractNodeInteraction {

	public abstract String createRequest(String request,  ConfigPropertiesPojo configPropertiesPojo) throws Exception;

	public abstract String deleteRequest(String request,  ConfigPropertiesPojo configPropertiesPojo) throws Exception;

	public abstract String modifyRequest(String request,  ConfigPropertiesPojo configPropertiesPojo) throws Exception;

	public abstract String queryRequest(String request,  ConfigPropertiesPojo configPropertiesPojo) throws Exception;

}
