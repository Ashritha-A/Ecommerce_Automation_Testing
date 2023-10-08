package Testing1;

import org.testng.annotations.DataProvider;

public class SignInPage {
	
	@DataProvider(name="LoginInputs")
	public Object[][] getData(){
		return new Object[][] {
			{"mallikarjuna@gmail.com","Mallikarjuna@1234"}		
		};
	}

}
