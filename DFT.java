
package foi;

public class DFT {
	
	/* 
	 * Computes the discrete Fourier transform (DFT) of the given vector.
	 */
	public static double[] discreteFT(double[]fourierdata, int N, boolean forward)
	{
		double X[] = new double[2*N];
		double omega;
		int k, ki, kr, n;
		
			if (forward){// if forward fourier transform is true
				omega = 2.0*Math.PI/N;
				 } 
			else {// if inverse fourier transform is true
				 omega = -2.0*Math.PI/N;
				 }
		for(k=0; k<N; k++)
		{
			kr = 2*k;//real part
			ki = 2*k + 1;// imag part
			X[kr] = 0.0;
			X[ki] = 0.0;
		
				for(n=0; n<N; ++n) 
				{
					X[kr] += fourierdata[2*n]*Math.cos(omega*n*k) + fourierdata[2*n+1]*Math.sin(omega*n*k); // iteration over all segments of fourierdata REAL PART!
					X[ki] += -fourierdata[2*n]*Math.sin(omega*n*k) + fourierdata[2*n+1]*Math.cos(omega*n*k);// iteration over all segments of fourierdata imag PART!
				}
			  
		}
					
		if ( forward ) 
			{
			for(k=0; k<N; ++k) {
			X[2*k] /= N;
			X[2*k + 1] /= N;   }
			}
	return X;
	}
}
