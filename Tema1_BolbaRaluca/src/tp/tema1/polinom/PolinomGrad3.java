package tp.tema1.polinom;


public class PolinomGrad3 extends Polinom
{

	public PolinomGrad3(int a, int b, int c, int d)
	{
		super(3, new int[]{a, b, c, d});
	}
	
	public double[] solve() throws ArithmeticException //metoda de gasire a radacinilor polinomului de grad 3
	{
		double[] rez = null;
		double a1, b1, c1, d1, f, g, h;

		
		a1 = (double)coef[0];
		b1 = (double)coef[1];
		c1 = (double)coef[2];
		d1 = (double)coef[3];
		
		if(a1 == 0)
			throw new ArithmeticException("Impartire cu 0 !");
		
		f = (((3*c1)/a1) - ((b1*b1)/(a1*a1)))/3;
		g = ( ((2*b1*b1*b1)/(a1*a1*a1)) - ((9*b1*c1)/(a1*a1)) + ((27*d1)/a1) )/27;
		h = ((g*g)/4) + ((f*f*f)/27);
		
		if(f==0 && g==0 && h==0) //Toate radacinile sunt reale si egale
		{
			rez = new double[3];
			rez[0] = - Math.pow(d1/a1, 1/3.0);
			rez[1] = rez[0];
			rez[2] = rez[0];
		}
		else if(h>0) //Exista o singura radacina reale si doua sunt complexe
		{
			double R, S, T, U ;
			rez = new double[1];
			
			R = -(g/2) + Math.sqrt(h);
			S = Math.pow(Math.abs(R), 1/3.0);
			S = -S;
			T = -(g/2) - Math.sqrt(h);
			U = Math.pow(Math.abs(T),  1/3.0);
			U = -U;
			rez[0] = (S+U) - (b1/(3*a1));
			rez[0] = Math.round(rez[0] * 10000.0) / 10000.0;
		}
		else if(h<0) //Exista 3 radacini reale si diferite
		{
			double i, j, k, L, M, N, P;
			
			i = Math.sqrt(g*g/4-h);
			j = Math.pow(i,  1/3.0);
			k = Math.acos(-(g/(2*i)) );
			L = -j;
			M = Math.cos(k/3);
			N = Math.sqrt(3)*Math.sin(k/3);
			P = -(b1/(3*a1));
			
			rez = new double[3];
			
			rez[0] = 2*j*M +P;
			rez[1] = L*(M+N)+P;
			rez[2] = L*(M-N)+P;
			
			rez[0] = Math.round(rez[0] * 10000.0) / 10000.0;
			rez[1] = Math.round(rez[1] * 10000.0) / 10000.0;
			rez[2] = Math.round(rez[2] * 10000.0) / 10000.0;
		}
		return rez;
	}
}
