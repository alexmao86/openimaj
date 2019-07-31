
package com.github.alexmao86.openimaj;

import java.io.File;
import java.io.IOException;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.edges.CannyEdgeDetector;

public class ImageUtilitiesTest {
	public static void main(String args[]) throws IOException {
		MBFImage input=ImageUtilities.readMBF(new File("images/kc.jpg"));
		//input=ColourSpace.convert(input, ColourSpace.CIE_Lab);
		
		input.processInplace(new CannyEdgeDetector());
		DisplayUtilities.display(input);
	}
}
