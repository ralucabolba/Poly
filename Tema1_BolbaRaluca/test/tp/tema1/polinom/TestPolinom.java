package tp.tema1.polinom;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TestPolinom 
{
	Polinom p, q, r, s, t, u ;
	@BeforeTest
	public void initialize()
	{
		p = new Polinom(4, new int[]{1,2,3,-4,-5});
		q = new Polinom(1, new int[]{2,-1});
		r = new Polinom(2, new int[]{1, -5, 6});
		s = new Polinom(3, new int[]{1, 6, 12, 8});
		t = new Polinom(0, new int[]{4});
		u = new Polinom(2, new int[]{-1, 5, -6});
	}
	@DataProvider
	public Object[][] valoarePolinom()
	{
		return new Object[][]{
				{ 0.0,  -5.0},
				{ 1.0, -3.0},
				{ 32, 1117051},
				{ 15.04, 58584.7527706}
		};
	}
	  @Test(dataProvider = "valoarePolinom")
	  public void testValoarePolinom(double input, double expected) 
	  {
		  assertEquals(p.getFuncValue(input), expected, 0.0001);
	  }
	  
	 @DataProvider
	 public Object[][] valoareDerivataP()
	 {
		 return new Object[][]{
				 {0.0, -4.0},
				 {15.04, 15051.737856},
				 {32, 137404},
				 {1.0, 12},
				 {0.028, -3.827208192},
				 {-300, -107461804}
		 };
	 }
	 @Test(dataProvider = "valoareDerivataP")
	 public void testValoareDerivataP(double input, double expected)
	 {
		 assertEquals(p.getDerivValue(input), expected, 0.0001);
	 }
	 
	 @DataProvider
	 public Object[][] valoareDerivataQ()
	 {
		 return new Object[][]{
				 {0.0, 2.0},
				 {15.04, 2.0},
				 {32, 2.0},
				 {1.0, 2.0},
				 {0.028, 2.0},
				 {-300, 2.0}
		 };
	 }
	 @Test(dataProvider = "valoareDerivataQ")
	 public void testValoareDerivataQ(double input, double expected)
	 {
		 assertEquals(q.getDerivValue(input), expected, 0.0001);
	 }
	 
	 @DataProvider
	 public Object[][] valoareDerivataT()
	 {
		 return new Object[][]{
				 {0.0, 0.0},
				 {15.04, 0.0},
				 {32, 0.0},
				 {1.0, 0.0},
				 {0.028, 0.0},
				 {-300, 0.0}
		 };
	 }
	 @Test(dataProvider = "valoareDerivataT")
	 public void testValoareDerivataT(double input, double expected)
	 {
		 assertEquals(t.getDerivValue(input), expected, 0.0001);
	 }
	 @Test
	 public void testAdunare()
	 {
		 
		 int[] coef1 = new int[]{1,2,3,-2,-6}; //p+q
		 int[] coef2 = new int[]{1,6,14,7}; //q+s
		 int[] coef3 = new int[]{0};//r+u
		 
		 assertEquals(coef1, p.add(q).getCoef());
		 assertEquals(coef2, q.add(s).getCoef());
		 assertEquals(coef3, r.add(u).getCoef());
	 }
	 
	 @Test
	 public void testScadere()
	 {
		
		 int[] coef1 = new int[]{1,2,3,-6,-4}; //p-q
		 int[] coef2 = new int[]{2, -10, 12};//r-u
		 int[] coef3 = new int[]{0}; //s-s
		 
		 
		 assertEquals(coef1, p.substract(q).getCoef());
		 assertEquals(coef2, r.substract(u).getCoef());
		 assertEquals(coef3, s.substract(s).getCoef());
		
		
			
	 }
	 
	 @Test
	 public void testImpartire()
	 {
		assertEquals(p.divide(r)[0]+p.divide(r)[1], "Catul impartirii este :\nx^2+7.0x^1+32.0"+"Restul impartirii este :\n114.0x^1-197.0");
			
	 }
}
