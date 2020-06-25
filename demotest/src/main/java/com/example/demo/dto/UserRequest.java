package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRequest implements Serializable{

	@NotEmpty(message = "名前を入力してください")
	  @Size(max = 100, message = "名前は100桁以内で入力してください")
	  private String name;

	 @Size(max = 255, message = "住所は255桁以内で入力してください")
	  private String address;

}
