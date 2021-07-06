package com.example.undangan.model.total;

import com.google.gson.annotations.SerializedName;

public class Response_total {

	@SerializedName("total")
	private int total;

	@SerializedName("kode")
	private boolean kode;

	@SerializedName("message")
	private String message;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setKode(boolean kode){
		this.kode = kode;
	}

	public boolean isKode(){
		return kode;
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
			"total = '" + total + '\'' + 
			",kode = '" + kode + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}