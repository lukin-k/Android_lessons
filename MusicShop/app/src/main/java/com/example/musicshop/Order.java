package com.example.musicshop;

import java.io.Serializable;

public class Order implements Serializable{
	String UserEmail;
	String GoodName;
	int quantity;
	int price;
	Order(String UE, String GN, int q, int p){
		UserEmail=UE;
		GoodName=GN;
		quantity=q;
		price=p;
	}
}
