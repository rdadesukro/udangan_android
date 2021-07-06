package com.example.undangan.model.login;

import com.google.gson.annotations.SerializedName;

public class Response_login {

	@SerializedName("access_token")
	private String accessToken;


	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@SerializedName("total")
	private String total;

	@SerializedName("expires_at")
	private String expiresAt;

	@SerializedName("kode")
	private String kode;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("message")
	private String message;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}

	public String getAccessToken(){
		return accessToken;
	}

	public void setExpiresAt(String expiresAt){
		this.expiresAt = expiresAt;
	}

	public String getExpiresAt(){
		return expiresAt;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}

	public String getTokenType(){
		return tokenType;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"access_token = '" + accessToken + '\'' + 
			",expires_at = '" + expiresAt + '\'' + 
			",kode = '" + kode + '\'' + 
			",token_type = '" + tokenType + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}