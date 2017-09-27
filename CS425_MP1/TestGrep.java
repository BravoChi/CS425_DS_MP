import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

//import grep.SysGrep;


public class TestGrep {
	@Test
	public void testInfrequentPattern() {
	    int expectedLineCount = 8;
		System.out.println(System.getProperty("user.dir"));
		SysGrep test = new SysGrep("gibbs.ms.virginia.edu", null, "./vm1.log");
        int count = test.grepCount();
	    assertEquals(expectedLineCount, count);  
	}
	@Test
	public void testFrequentPattern() {
		int expectedLineCount = 343341;
		System.out.println(System.getProperty("user.dir"));
		SysGrep test = new SysGrep("GET", null, "./vm1.log");
        int count = test.grepCount();
	    assertEquals(expectedLineCount, count);  
	}
	@Test
	public void testRegularEx() {
		int expectedLineCount = 62;
		System.out.println(System.getProperty("user.dir"));
		SysGrep test = new SysGrep("gibbs*", null, "./vm1.log");
        int count = test.grepCount();
	    assertEquals(expectedLineCount, count);  
	}
	
}
