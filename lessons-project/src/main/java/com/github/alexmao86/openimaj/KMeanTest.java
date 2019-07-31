
package com.github.alexmao86.openimaj;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.ColourSpace;
import org.openimaj.image.connectedcomponent.GreyscaleConnectedComponentLabeler;
import org.openimaj.image.pixel.ConnectedComponent;
import org.openimaj.image.processor.PixelProcessor;
import org.openimaj.image.segmentation.FelzenszwalbHuttenlocherSegmenter;
import org.openimaj.image.segmentation.SegmentationUtilities;
import org.openimaj.image.typography.hershey.HersheyFont;
import org.openimaj.ml.clustering.FloatCentroidsResult;
import org.openimaj.ml.clustering.assignment.HardAssigner;
import org.openimaj.ml.clustering.kmeans.FloatKMeans;

import cern.colt.Arrays;

public class KMeanTest {
	public static void main(String args[]) throws IOException {
		MBFImage input=ImageUtilities.readMBF(new File("images/kc.jpg"));
		input=ColourSpace.convert(input, ColourSpace.CIE_Lab);
		FloatKMeans cluster=FloatKMeans.createExact(2);
		float[][] imageData=input.getPixelVectorNative(new float[input.getWidth()*input.getHeight()][3]);
		FloatCentroidsResult result=cluster.cluster(imageData);
		final float centriods[][]=result.getCentroids();
		for(float[] fs:centriods) {
			System.out.println(Arrays.toString(fs));
		}
		//final HardAssigner<float[], ?, ?> assigner=result.defaultHardAssigner();
		/*
		 *method 1
		for(int y=0;y<input.getHeight();y++) {
			for(int x=0;x<input.getWidth();x++) {
				float[] pixel=input.getPixelNative(x, y);
				int centriod=assigner.assign(pixel);
				input.setPixelNative(x, y, centriods[centriod]);
			}
		}
		*/
		/*
		input.processInplace(new PixelProcessor<Float[]>() {
			@Override
			public Float[] processPixel(Float[] pixel) {
				int centriod=assigner.assign(convert(pixel));
				return convert(centriods[centriod]);
			}
		});
		*/
		
		//segment method 1,
		//input = ColourSpace.convert(input, ColourSpace.RGB);
		//GreyscaleConnectedComponentLabeler labeler=new GreyscaleConnectedComponentLabeler();
		//List<ConnectedComponent> components=labeler.findComponents(input.flatten());
		
		FelzenszwalbHuttenlocherSegmenter<MBFImage> felzenszwalbHuttenlocherSegmenter=new FelzenszwalbHuttenlocherSegmenter<>();
		List<ConnectedComponent> components=felzenszwalbHuttenlocherSegmenter.segment(input);
		SegmentationUtilities.renderSegments(input, components);
		/*
		int i=0;
		for(ConnectedComponent comp:components) {
			if(comp.calculateArea()<50) {
				continue;
			}
			input.drawText("p"+(i++), comp.calculateCentroidPixel(), HersheyFont.TIMES_MEDIUM, 8);
		}
		*/
		DisplayUtilities.display(input);
	}
	
	private static Float[] convert(float[] array) {
		Float[] ret=new Float[array.length];
		for(int i=0;i<array.length;i++) {
			ret[i]=array[i];
		}
		return ret;
	}
	private static float[] convert(Float[] array) {
		float[] ret=new float[array.length];
		for(int i=0;i<array.length;i++) {
			ret[i]=array[i];
		}
		return ret;
	}
}
