import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.DFTDescriptor;

public class DFTMain {

	static {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
	}

	public static void main(String[] args) {
		try {
			File file = new File(DFTMain.class.getResource("image1.jpg").getFile());
			final BufferedImage srcImg = ImageIO.read(file);			
			String outFolder = file.getParent()+"/../image/out/";
			
			// Create the ParameterBlock.
			ParameterBlock par = new ParameterBlock();
			par.addSource(srcImg);
			par.add(DFTDescriptor.SCALING_NONE);
			par.add(DFTDescriptor.REAL_TO_COMPLEX);
			
			convertToDFT(par, outFolder);
			
			
			System.out.println("Completed");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void convertToDFT(ParameterBlock pb, String path) {
		try {
			// Create the DFT operation.
			PlanarImage dft = (PlanarImage) JAI.create("dft", pb, null);

			// Get the DFT image information.
			int width = dft.getWidth();
			int height = dft.getHeight();

			// Retrieve the DFT data.
			Raster dftData = dft.getData();
			BufferedImage myBufImg = new BufferedImage(width, height, BufferedImage.SCALE_DEFAULT);
			myBufImg.setData(dftData);

			ImageIO.write(myBufImg, "jpg", new File(path+"/fourier_dft.jpg"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
