package tp.tema1.polinom;


public class PolinomGrad2 extends Polinom
{
	
	public PolinomGrad2(int a, int b, int c)
	{
		super(2, new int[]{a, b, c});
	}
	
	public double[] solve() throws ArithmeticException // metoda de gasire a radacinilor polinomului de grad 2
	{
		double delta  = coef[1]*coef[1] - 4*coef[0]*coef[2]; //delta = b^2-4ac
		double rez[] = null;
		if(coef[0] == 0)
			throw new ArithmeticException("Impartire cu 0 !");
		if(delta == 0) //daca delta este =0 radacinile sunt egale si reale
		{
			rez = new double[2];
			rez[0] = -coef[1]/2*(double)coef[0];
			rez[1] = rez[0];
		}
		else if(delta > 0) //daca delta>0 radacinile sunt diferite si reale
		{
			rez = new double[2];
			rez[0] = (-coef[1] - Math.sqrt(delta))/2*(double)coef[0];
			rez[1] = (-coef[1] + Math.sqrt(delta))/2*(double)coef[0];
		}
		return rez;
	}
}
