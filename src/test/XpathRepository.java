package test;

interface XpathRepository {
	
	String homePageUrl = "http://www.walmart.com/account/login";
	String userNameXpath = "//*[@id='login-username']";
	String passwordXpath = "//*[@id='login-password']";
	String userName = "vbalabh1@binghamton.edu";
	String password = "abc123";
	String signInButton = "html/body/div[1]/section/section[4]/div/div/div/div/div/div/div/form/div/button";
	String logoutUrl = "https://www.walmart.com/account/logout";
	String searchBar = "//*[@id='search']";
	String searchBarClick = "//button[@type='submit']";
	String addThisSearchElement ="//*[@id='tile-container']/div[1]/div/div/h4/a";
	String itemQtyDropDown = "//select[@id='WMItemQtyDropDown']";
	String addToCartButton = "//*[@id='WMItemAddToCartBtn']";
	String itemAddedToCart = "//*[@id='CartItemInfo']/span";
	String qtyAddedToCart = "//*[@id='spa-layout']/div/div/div[1]/div/h3/span";
	String alternateLink = "html/body/div[1]/section/section[4]/div/div/div[3]/div[1]/div[1]/a";
	String alternateURL = "http://www.walmart.com/search/?query=Toys&redirect=false";
	String cartPopUpWindow = "//div[@id='spa-layout']/div/div/div/div/div[2]/div/div/div[2]/div/div[3]/div/a/span";
	String closePopUpWindow = "//*[@id='spa-layout']/div/div/div/button";
	String TrolleyShape = "//*[@id='top']/div[3]/div/div/div/div/div[4]/div/div[2]/div/a/i";
	String deleteItemFromCart = "//button[@id='CartRemItemBtn']";
	String transientPopUp = "html/body/div[1]/section/section[4]/div/div[2]/div[1]/div[4]/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div[2]/div/div/div/button";
	String iphoneColor1 = "css=span.variant-swatch";
	//String iphoneColor2 = "css=#product-swatch-actual_color-0";
	String iphoneColor2 = ".variant-swatch";
	String upArrowForTv = "//*[@id='spa-layout']/div/div/div/div/div[2]/div/div[1]/div[2]/div/div[3]/div[1]/a/div";
	
	
	

}
