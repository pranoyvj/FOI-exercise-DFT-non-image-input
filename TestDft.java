package foi;

class TestDft
{
	public static void main(String args[]) 
	{
		int N = 64;
		double T = 2.0;
		double tn, fk;
		double fourierdata[] = new double[2*N];
		
		for(int i=0; i<N; ++i) 
		{
			fourierdata[2*i] = Math.cos(4.0*Math.PI*i*T/N);
			fourierdata[2*i+1] = 0.0;
		}
		
		double X[] = DFT.discreteFT(fourierdata, N, true);
		System.out.println("                         ");
		System.out.println("    Doing  DFT       ");
		System.out.println("                                                                                                                   ");
		for (int k=0; k<N; ++k) 
		{
			fk = k/T;
			System.out.println("f["+k+"] =   "+fk+"   Xr["+k+"] =   "+X[2*k]+ "         Xi["+k+"] =   "+X[2*k + 1]) ;
		}
		for (int i=0; i<N; ++i) 
		{
			fourierdata[2*i] = 0.0;
			fourierdata[2*i+1] = 0.0;
			if (i == 4 || i == N-4 )
				{
				fourierdata[2*i] = 0.5;
				} 
		}
		double x[] = DFT.discreteFT(fourierdata, N, false);
		System.out.println("                         ");
		System.out.println("    Doing Inverse DFT       ");
		System.out.println("                                                                                                                   ");
		for (int n=0; n<N; ++n) 
			{
			tn = n*T/N;
			System.out.println("t["+n+"] =   "+tn+"   xr["+n+"] =   "+x[2*n]+"       xi["+n+"] =   "+x[2*n + 1]);
			}
	}
}
//reference : AC 2011-451: A TASTE OF JAVA - DISCRETE AND FAST FOURIER TRANSFORMS
//Mohammad Rafiq Muqri, DeVry University, Pomona
