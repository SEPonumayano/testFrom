package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//エラー表示内容
public class UserRequest implements Serializable{

	//名前エラー
	@NotEmpty(message = "名前を入力してください")
	  @Size(max = 10, message = "名前は10文字以内で入力してください")
	  private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//住所エラー
	 @Size(max = 20, message = "住所は20文字以内で入力してください")
	  private String address;


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
