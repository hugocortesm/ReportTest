package com.pech.webdashboard.reporttest.vo;

import java.io.Serializable;

public class ReportSuite implements Serializable {

	private static final long serialVersionUID = 2631199177633880200L;

	private Integer id;
	private String rsid;
	private String name;
	private String logoUrl;
	private Integer sortBy;
	private String adUnitld;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRsid() {
		return rsid;
	}

	public void setRsid(String rsid) {
		this.rsid = rsid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Integer getSortBy() {
		return sortBy;
	}

	public void setSortBy(Integer sortBy) {
		this.sortBy = sortBy;
	}

	public String getAdUnitld() {
		return adUnitld;
	}

	public void setAdUnitld(String adUnitld) {
		this.adUnitld = adUnitld;
	}

}
