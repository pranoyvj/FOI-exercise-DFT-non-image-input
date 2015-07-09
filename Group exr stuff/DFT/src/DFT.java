import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.DFTDescriptor;

public class DFT {

	static {
		System.setProperty("com.sun.media.jai.disableMediaLib", "true");
	}

	public static void main(String[] args) {
		try {
			// File input //all the input images are by provided in bin folder of eclipse project path.
			//File file = new File(DFT.class.getResource("image4.jpg").getFile());
			
			final BufferedImage srcImg = ImageIO.read(new File(DFT.class.getResource("image4.jpg").toURI()));// we provide input image here			
			//String outFolder = file.getParent()+"/../image/out/";
			String outFolder = "F:/pvj files/java eclipse workspace/DFT/image/out/";//output path provided
			// Create the ParameterBlock.
			ParameterBlock par = new ParameterBlock();
			par.addSource(srcImg);
			par.add(DFTDescriptor.SCALING_NONE);
			par.add(DFTDescriptor.REAL_TO_COMPLEX);
			
			convertToDFT(par, outFolder);
			
			// Confirmation
			System.out.println("Completed");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void convertToDFT(ParameterBlock pb, String path) {
		try {
			
			PlanarImage dft = (PlanarImage) JAI.create("dft", pb, null);

			// Get the DFT image information.
			int width = dft.getWidth();
			int height = dft.getHeight();

			// Get the DFT data.
			Raster dftData = dft.getData();
			BufferedImage myBufImg = new BufferedImage(width, height, BufferedImage.SCALE_DEFAULT);
			myBufImg.setData(dftData);
			// Write data to Image
			ImageIO.write(myBufImg, "jpg", new File(path+"/fourier_dft4.jpg"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
