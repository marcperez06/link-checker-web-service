package io.github.marcperez06.link_checker_web_service.domain.api_params;

import io.github.marcperez06.link_checker.report.configuration.LinkCheckerConfiguration;
import lombok.Data;

public class LinkCheckerParams {

	private String url;
	private LinkCheckerConfiguration configuration;
	
	public LinkCheckerParams() {
		this.url = "";
		this.configuration = null;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public LinkCheckerConfiguration getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(LinkCheckerConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public LinkCheckerConfiguration getConfigurationWithoutEmptyMinDepth() {
		if (this.configuration.getMinDepth() == null) {
			this.configuration.setMinDepth(Integer.valueOf(0));
		}
		return this.configuration;
	}
	
}