package tp.tema1.polinom;
import java.util.*;



public class Polinom
{
	protected int[] coef;
	protected int grad;
	
	public Polinom(int grad, int coef[]) //constructor
	{									//se initializeaza gradul si coeficientii polinomului
		this.coef = Arrays.copyOf(coef, coef.length);
		this.grad = grad;
	}
	
	public int[] getCoef() //metoda care returneaza coeficientii polinomului
	{
		return coef;
	}
	
	public int getGrad() //metoda care returneaza gradul polinomului
	{
		return grad;
	}
	
	public void setCoef(int[] coefNoi) //metoda prin care se pot seta coeficientii cu alti coeficienti primiti ca parametru
	{
		this.coef = Arrays.copyOf(coefNoi, coefNoi.length);
	}
	
	public void setGrad(int gradNou) //metoda prin care se seteaza gradul cu unul nou transmis ca parametru
	{
		this.grad = gradNou;
	}
	
	public Polinom calculeazaDerivata() //metoda care calculeaza si returneaza derivata polinomului
	{
		Polinom rez;
		
		if(this.grad == 0)
		{
			rez = new Polinom(0, new int[]{0}); //daca gradul polinomului este 0 atunci derivata este 0
			return rez;
		}
		int gradDerivata = this.grad - 1; //gradul derivatei este mai mic cu 1 decat gradul polinomului
		int[] coefDeriv = new int[gradDerivata+1];
		
		for(int i=0 ; i<=gradDerivata ; i++)
		{
			coefDeriv[i] = this.coef[i]*(this.grad-i);
		}
		
		rez = new Polinom(gradDerivata, coefDeriv);
		
		return rez;
	}
	public String toString() //metoda care returneaza un sir de caractere de forma
	{						//coef[0]*x^grad+coef[1]*x^(grad-1)+...+coef[grad-1]
		String sir = "";
		int power = grad;
		
		if(grad==0) //daca gradul polinomul este 0 atunci sirul returnat va fi "coef[0]"
		{
			sir = sir+coef[0];
			return sir;
		}
		if(coef[0]==1)
			sir = sir + "x^" + power; //daca un coeficient este 1, atunci nu il mai afisam
		else if(coef[0]==-1)
			sir = sir + "-x^" + power; //daca un coficient este -1, atunci afisam doar -x^
		else if(coef[0]!=0)
			sir = sir + coef[0] + "x^" + power; 
		
		power--;
		
		for(int i = 1 ; i < grad; i++)
		{
			if(coef[i]!=0)
			{
				if(coef[i]==1)
					sir = sir + "+x^" + power;
				else if(coef[i]==-1)
					sir = sir + "-x^" + power;
				else if(coef[i]>0)
					sir = sir + "+" + coef[i] + "x^" + power;
				else if(coef[i]<0)
					sir = sir + coef[i] + "x^" + power;
			}
			power--;
		}
		if ( coef[grad] != 0) //atunci cand am ajuns la ultimul coeficient nu mai afisam x^0, doar coeficientul
		{
			if(coef[grad]>0)
				sir = sir + "+" + coef[grad];
			else if(coef[grad]<0)
				sir = sir + coef[grad];
		}
		return sir;
		
	}
	
	public double getFuncValue(double x) //metoda care returneaza valoarea polinomului in punctul x
	{
		int power = grad;
		
		double value = 0;
		
		for(int a: coef) //parcurgem vectorul de coeficienti
		{
			value += a * Math.pow(x, power); //value = value + a*x^power
			power--;
		}
		
		return value;
	}
	
	public double getDerivValue(double x) //metoda care returneaza valoarea derivatei polinomului in x
	{	
		return this.calculeazaDerivata().getFuncValue(x); //calculam derivata polinomului iar apoi valoarea acesteia in x
	}
	public Polinom add(Polinom p) //metoda care returneaza suma dintre doua polinoame
	{
		Polinom res ;
		int[] coefP = p.getCoef();
		
		int[] sum ;
		
		int i = coef.length - 1, j = coefP.length - 1;
		
		if(coef.length < coefP.length) //daca al doilea polinom are gradul mai mare decat primul
		{
			sum = new int[coefP.length]; //polinomul rezultat are gradul celui de-al doilea polinom
			for(int k=0;k<j-i;k++) // sum[i] = coefP[i] pana la pozitia corespunzatoare 
				sum[k] = coefP[k]; //gradului primului polinom
			for(int k=j-i;k<coefP.length;k++) //in continuare coef. rezultatului sunt egali cu
				sum[k] = coefP[k]+coef[k-j+i];//suma dintre coef. celor doua polinoame
			res = new Polinom(coefP.length-1, sum);
			
		}
		else if(coef.length > coefP.length)//daca al doilea polinom are gradul mai mic decat primul
		{
			sum = new int[coef.length];//polinomul rezultat are gradul primului polinom
			for(int k=0;k<i-j;k++)// sum[i] = coef[i] pana la pozitia corespunzatoare 
				sum[k] = coef[k];//gradului celui de-al doilea polinom
			for(int k=i-j;k<coef.length;k++)//in continuare coef. rezultatului sunt egali cu
				sum[k] = coef[k]+coefP[k-i+j];//suma dintre coef. celor doua polinoame
			
			res = new Polinom(coef.length-1, sum);
		}
		else //daca polinoamele au acelasi grad
		{
			sum = new int[coefP.length];
			for(int k=0 ; k<coefP.length ; k++)
				sum[k] = coefP[k] + coef[k]; //coeficientii rezultatului sunt egali cu suma coeficientilor corespunzatori
											//celor doua polinoame
			
			while(sum.length>0 && sum[0] == 0) //atata timp cat primul coeficient este 0 il eliminam
			{
				for(int k=0 ; k<sum.length-1 ; k++) //copiem pe pozitia curenta valoarea de la pozitia urmatoare
					sum[k] = sum[k+1];
				sum = Arrays.copyOf(sum, sum.length-1); //eliminam ultima valoare a tabloului
			}
			if(sum.length>0)
				res = new Polinom(sum.length-1, sum);
			else
				res = new Polinom(0, new int[]{0});
			
		}
		return res;
	}
	
	public Polinom substract(Polinom p) throws ArithmeticException//metoda care returneaza diferenta a doua polinoame
	{
		Polinom res = null;
		
		int[] coefP = p.getCoef();
		
		int[] sub = null ;
		
		int i = coef.length - 1, j = coefP.length - 1;
		if(coef.length < coefP.length)
		{
			throw new ArithmeticException("Gradul primului polinom trebuie sa fie mai mare sau egal decat gradul celui de-al doilea polinom.");
		
		}
		if(coef.length > coefP.length) //daca gradul primului polinom este mai mare decat gradul
		{								//celui de-al doilea polinom
			sub = new int[coef.length]; //gradul rezultatului va fi egal cu gradul mai mare al polinoamelor
			for(int k=0;k<i-j;k++) //coef. rezultatului sunt egali cu coef. primului polinom pana 
				sub[k] = coef[k];  //la coef. corespunzator gradului mai mic al polinoamelor
			for(int k=i-j;k<coef.length;k++)
				sub[k] = coef[k]-coefP[k-i+j]; // coef. rezultatului sunt egali cu diferenta dintre coef. polinoamelor
			res = new Polinom(coef.length-1, sub);
		}
		else if(coef.length == coefP.length) //daca polinoamele au gradul egal 
		{
			sub = new int[coefP.length];
			for(int k=0 ; k<coefP.length ; k++)
				sub[k] = coef[k] - coefP[k];// coef. rezultatului sunt egali cu diferenta dintre coef. polinoamelor
			
			while(sub.length>0 && sub[0] == 0)//atata timp cat primul coeficient este 0 il eliminam
			{
				for(int k=0 ; k<sub.length-1 ; k++)
					sub[k] = sub[k+1];
				sub = Arrays.copyOf(sub, sub.length-1);
			}
			if(sub.length>0)
				res = new Polinom(sub.length-1, sub);
			else
				res = new Polinom(0, new int[]{0});
		}
		return res;
	}
	
	public Polinom multiply(Polinom p) //metoda care returneaza rezultatul inmultirii a doua polinoame
	{
		Polinom res = null;
		
		int[] coefP = p.getCoef();
		int pGrad = p.getGrad();
		int gradTotal = grad + pGrad; //polinomul rezultat are gradul egal cu suma gradelor polinoamelor
		int[] rez = new int[gradTotal+1];
		
		for(int i=0 ; i<=grad ; i++)		//coeficient este dat de produsul
			for(int j=0 ; j<=pGrad ; j++)	//coeficientilor de acelasi grad adunat la vechiul
				rez[i+j] += coef[i] * coefP[j]; //coeficient de pe acea pozitie 
												 										
		res = new Polinom(gradTotal, rez);
		return res;
	}
	
	public String[] divide(Polinom p) throws ArithmeticException//metoda care returneaza catul si restul impartirii a doua polinoame
	{
		
		int[] rest = this.getCoef(); //initial restul este egal cu deimpartitul
		int[] coefP = p.getCoef();
		
		if(rest.length < coefP.length) //daca gradul deimpartitului este mai mic ca al impartitorului
		{
			throw new ArithmeticException("Gradul primului polinom trebuie sa fie mai mare sau egal decat gradul celui de-al doilea polinom.");
		}
		double[] d = new double[rest.length]; //impartitorul
		double[] r = new double[rest.length]; //restul
		double[] q = new double[rest.length - coefP.length +1]; //catul
		double t;
		
		int  k=0, j=1;
		int gradD = p.getGrad()+1;
		
		for(int i=0;i<=p.getGrad();i++) //coeficientii impartitorului sunt completati cu 0
		{								//pana cand lungimea acestuia este egala cu lungimea deimpartitului
			d[i] = coefP[i];
		}
		
		for(int i=0;i<rest.length;i++)
		{
			r[i] = rest[i];
		}
		
		
		while(r.length >= gradD) //atata timp cat gradul restului este >= gradul impartitorului
		{
			t = r[0]/d[0]; //se calculeaza raportul coeficientilor de grad maxim din polinoame
			q[k+j-1] = t; //valoarea se pune pe pozitia corespunzatoare in cat
			
			for(int i=0;i<r.length;i++) //se scade din rest t*impartitorul
			{
				r[i] = r[i] - d[i]*t;
			}
			
			j=r.length;
			
			while(r.length>0 && r[0] == 0) //atata timp cat primul coef al deimpartitului este 0 il eliminam
			{
				for(int i=0 ; i<r.length-1 ; i++)
					r[i] = r[i+1];
				r = Arrays.copyOf(r, r.length-1);
			}
			
			j=j-r.length;
			
			d = Arrays.copyOf(d, d.length-j); //eliminam ultimele valori ale impartitorului, in functie de
			k++;							  //cat a scazut gradul deimpartitului
		}
		//transformam restul si catul in siruri de caractere
		String[] sir = new String[]{"", ""};
		
		int powerC = q.length-1;
		int powerR = r.length-1;
		
			
		if(powerC==0)
		{
			sir[0] = sir[0]+Math.round(q[0]*100.0)/100.0;
		}
		else if(powerC!=-1)
		{
			if(q[0]==1.0)
				sir[0] = sir[0] + "x^" + powerC;
			else if(q[0]==-1.0)
				sir[0] = sir[0] + "-x^" + powerC;
			else if(q[0]!=0)
				sir[0] = sir[0] + Math.round(q[0]*100.0)/100.0 + "x^" + powerC;
			
			powerC--;
			
			for(int i = 1 ; i < q.length-1; i++)
			{
				if(q[i]!=0)
				{
					if(q[i]==1.0)
						sir[0] = sir[0] + "+x^" + powerC;
					else if(q[i]==-1.0)
						sir[0] = sir[0] + "-x^" + powerC;
					else if(q[i]>0)
						sir[0] = sir[0] + "+" + Math.round(q[i]*100.0)/100.0 + "x^" + powerC;
					else if(q[i]<0)
						sir[0] = sir[0] +  Math.round(q[i]*100.0)/100.0 + "x^" + powerC;
				}
				powerC--;
			}
			if ( q[q.length-1] != 0)
			{
				if(q[q.length-1]>0)
					sir[0] = sir[0] + "+" +  Math.round(q[q.length-1]*100.0)/100.0;
				else if(q[q.length-1]<0)
					sir[0] = sir[0] +  Math.round(q[q.length-1]*100.0)/100.0;
			}
		}
		if(powerR==0)
		{
			sir[1] = sir[1]+Math.round(r[0]*100.0)/100.0;
		}
		else if(powerR!=-1)
		{
			if(r[0]==1.0)
				sir[1] = sir[1] + "x^" + powerR;
			else if(r[0]==-1.0)
				sir[1] = sir[1] + "-x^" + powerR;
			else if(r[0]!=0)
				sir[1] = sir[1] + Math.round(r[0]*100.0)/100.0 + "x^" + powerR;
			
			powerR--;
			
			for(int i = 1 ; i < r.length-1; i++)
			{
				if(r[i]!=0)
				{
					if(r[i]==1.0)
						sir[1] = sir[1] + "+x^" + powerR;
					else if(r[i]==-1.0)
						sir[1] = sir[1] + "-x^" + powerR;
					else if(r[i]>0)
						sir[1] = sir[1] + "+" + Math.round(r[i]*100.0)/100.0 + "x^" + powerR;
					else if(r[i]<0)
						sir[1] = sir[1] +  Math.round(r[i]*100.0)/100.0 + "x^" + powerR;
				}
				powerR--;
			}
			if ( r[r.length-1] != 0)
			{
				if(r[r.length-1]>0)
					sir[1] = sir[1] + "+" +  Math.round(r[r.length-1]*100.0)/100.0;
				else if(r[r.length-1]<0)
					sir[1] = sir[1] +  Math.round(r[r.length-1]*100.0)/100.0;
			}
		}
		
		sir[0] = "Catul impartirii este :\n"+sir[0];
		sir[1] = "Restul impartirii este :\n"+sir[1];
		return sir;
	
	}
	public double[] solve(double startValue) throws ArithmeticException 
	{//metoda care returneaza radacina polinomului conform metodei lui Newton pornind d la startValue
		double precizie = 1e-6;
		double x = startValue;
		double x1;
		
		//Se fac maxim 500 iteratii
		for(int i=0; i < 500 ; i++)
		{
			try{
				if(Math.abs(this.getDerivValue(x)) < precizie*precizie)
				{//Trebuie sa alegem alt punct de start pentru ca suntem blocati intr-un minim local
					break;
				}
				x1 = x - this.getFuncValue(x)/this.getDerivValue(x);
				if(Math.abs(x1-x) < precizie)
				{
					break;
				}
				x=x1;
			}
			catch(Exception e)
			{
				break;
			}
		}

		if(Math.abs(this.getFuncValue(x)) > precizie)
		{
			throw new ArithmeticException("Nu pot gasi radacinile");
		}
		double rez[] = new double[1];
		rez[0] = Math.round(x *100.0) / 100.0;
		return rez;
	}
}
