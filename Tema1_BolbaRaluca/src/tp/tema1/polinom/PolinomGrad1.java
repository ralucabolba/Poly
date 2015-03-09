package tp.tema1.polinom;

// Clasa care extinde clasa Polinom
public class PolinomGrad1 extends Polinom
{
	public PolinomGrad1(int a, int b)
	{
		super(1, new int[]{a, b});
	}
	public double[] solve() throws ArithmeticException //metoda de gasire a radacinilor polinomului de grad 1
	{
		double[] rez = new double[1];
		double a1 = (double)coef[0];
		double b1 = (double)coef[1];
		
		if(a1 == 0)
		{
			throw new ArithmeticException("Impartire cu 0 !");
		}
		rez[0] = -b1/a1; //radacina polinomului de grad 1 este -b/a
		
		return rez;
	}
}
