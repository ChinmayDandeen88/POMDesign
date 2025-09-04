package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest{

	@BeforeTest
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProducts() {
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Canon","Canon EOS 5D"},
			{"Imac","iMac"},
		};
	}

	@Test(dataProvider = "getProducts")
	public void productHeaderTest(String searchKey,String productNameSelect) {
		searchResultPage = accPage.dosearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productNameSelect);
		String productActualName= productInfoPage.productHeader();
		Assert.assertEquals(productActualName,productNameSelect);
	}

	@DataProvider
	public Object[][] getProductsImages() {
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Canon","Canon EOS 5D",3},
			{"Imac","iMac",3},
		};
	}


	@Test(dataProvider = "getProductsImages")
	public void productImagesCountTest(String searchKey,String productNameSelect,int imageCount ) {
		searchResultPage = accPage.dosearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productNameSelect);
		int productActualCount = productInfoPage.getProductImages();
		Assert.assertEquals(productActualCount,imageCount );
	}

	@Test
	public void getProductDataTest() {
		searchResultPage = accPage.dosearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productDataMap = productInfoPage.getProductData();

		/** Use AAA pattern -> Arrange, Act,Assert
		 * Hard assert when used all the remaining asserts gets failed, where as soft assert it continues the execution
		 * to check multiple functionalities we use soft assert
		 */
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(productDataMap.get("productheader"), "MacBook Pro");
		
		softAssert.assertEquals(productDataMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDataMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(productDataMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDataMap.get("Product Code"), "Product 18");
		
		softAssert.assertEquals(productDataMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productDataMap.get("extproductprice"), "$2,000.00");
		
		softAssert.assertAll();

	}
}

