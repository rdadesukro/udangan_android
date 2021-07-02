package com.example.undangan.model.konfrimasi;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response_konfirmasi {

	@SerializedName("kode")
	private boolean kode;

	@SerializedName("message")
	private String message;

	@SerializedName("isi")
	private List<IsiItem_konfirmasi> isi;

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

	public void setIsi(List<IsiItem_konfirmasi> isi){
		this.isi = isi;
	}

	public List<IsiItem_konfirmasi> getIsi(){
		return isi;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"kode = '" + kode + '\'' + 
			",message = '" + message + '\'' + 
			",isi = '" + isi + '\'' + 
			"}";
		}
}