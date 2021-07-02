package com.example.undangan.model.konfrimasi;

import com.google.gson.annotations.SerializedName;

public class IsiItem_konfirmasi {

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;


	public String getBaca() {
		return baca;
	}

	public void setBaca(String baca) {
		this.baca = baca;
	}

	@SerializedName("baca")
	private String baca;

	@SerializedName("nama_panggilan")
	private String namaPanggilan;

	@SerializedName("ucapan")
	private String ucapan;

	@SerializedName("status")
	private String status;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNamaPanggilan(String namaPanggilan){
		this.namaPanggilan = namaPanggilan;
	}

	public String getNamaPanggilan(){
		return namaPanggilan;
	}

	public void setUcapan(String ucapan){
		this.ucapan = ucapan;
	}

	public String getUcapan(){
		return ucapan;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"IsiItem{" + 
			"nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",nama_panggilan = '" + namaPanggilan + '\'' + 
			",ucapan = '" + ucapan + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}