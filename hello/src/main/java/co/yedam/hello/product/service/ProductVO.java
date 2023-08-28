package co.yedam.hello.product.service;

import lombok.Data;

@Data
public class ProductVO {
	private String productId;
	private String productName;
	private int productPrice;
	private String productModel;
	
	public String toString() {
		System.out.print(productId+" : "); 
		System.out.print(productName+" : "); 
		System.out.print(productPrice+" : "); 
		System.out.println(productModel+" : "); 
		return null;
	}
}
