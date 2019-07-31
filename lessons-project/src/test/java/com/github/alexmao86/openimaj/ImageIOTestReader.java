/*
 * Copyright (c) 2017, Robert Bosch (Suzhou) All Rights Reserved.
 * This software is property of Robert Bosch (Suzhou). 
 * Unauthorized duplication and disclosure to third parties is prohibited.
 */
package com.github.alexmao86.openimaj;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.spi.ServiceRegistry;
import javax.imageio.stream.ImageInputStream;

public class ImageIOTestReader {
	public static void main(String args[]) throws Exception {
		final InputStream in = new FileInputStream("kc.jpg");
		
		for (int i = 0; i < 10; i++) {
			ImageInputStream iin=ImageIO.createImageInputStream(in);
			BufferedImage img=ImageIO.read(iin);
			System.out.println(in.available());
			System.out.println(img);
		}
		/*
		ImageInputStream imageIn = new RaspistillImageInputStream(in);
		Iterator<ImageReader> it = ImageIO.getImageReaders(imageIn);
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		ImageReader reader = null;
		Iterator ita = register.getServiceProviders(ImageReaderSpi.class, true);
		while (ita.hasNext()) {
			Object obj=ita.next();
			System.out.println(obj);
			if((obj instanceof ImageReader)&&obj.getClass().getName().contains(".jpeg.")) {
				reader =(ImageReader)obj;
				System.out.println("selected reader: "+reader);
				break;
			}
		}
		for (int i = 0; i < 10; i++) {
			ImageReadParam param = reader.getDefaultReadParam();
	        reader.setInput(new RaspistillImageInputStream(in), true, true);
			BufferedImage img = reader.read(0, param);
			System.out.println(img);
		}
		*/
		in.close();
	}

	static class CanDecodeInputFilter implements ServiceRegistry.Filter {

		Object input;

		public CanDecodeInputFilter(Object input) {
			this.input = input;
		}

		public boolean filter(Object elt) {
			try {
				ImageReaderSpi spi = (ImageReaderSpi) elt;
				ImageInputStream stream = null;
				if (input instanceof ImageInputStream) {
					stream = (ImageInputStream) input;
				}

				// Perform mark/reset as a defensive measure
				// even though plug-ins are supposed to take
				// care of it.
				boolean canDecode = false;
				if (stream != null) {
					stream.mark();
				}
				canDecode = spi.canDecodeInput(input);
				if (stream != null) {
					stream.reset();
				}

				return canDecode;
			} catch (IOException e) {
				return false;
			}
		}
	}
}
