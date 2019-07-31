package com.github.alexmao86.openimaj;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openimaj.feature.DoubleFVComparison;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.pixel.statistics.HistogramModel;
import org.openimaj.math.statistics.distribution.MultidimensionalHistogram;
public class HistogramTest {
	public static void main(String args[]) throws Exception {
		ProxyUtil.apply();
		URL[] imageURLs=new URL[] {
				new URL("http://www.vision.caltech.edu.s3-us-west-2.amazonaws.com/Image_Datasets/Caltech101/SamplePics/image_0083.jpg"),
				new URL("http://www.vision.caltech.edu.s3-us-west-2.amazonaws.com/Image_Datasets/Caltech101/SamplePics/image_0028.jpg"),
				new URL("http://www.vision.caltech.edu.s3-us-west-2.amazonaws.com/Image_Datasets/Caltech101/SamplePics/image_0031.jpg")
		};
		List<MultidimensionalHistogram> histograms=new ArrayList<MultidimensionalHistogram>(3);
		HistogramModel model=new HistogramModel(4,4,4);
		for(URL u:imageURLs) {
			model.estimateModel(ImageUtilities.readMBF(u));
			histograms.add(model.histogram.clone());
		}
		
		for(int i=0;i<histograms.size();i++) {
			for(int j=0;j<histograms.size();j++) {
				double distance = histograms.get(i).compare(histograms.get(j), DoubleFVComparison.EUCLIDEAN);
				System.out.printf("image %d vs %d distance equals %s\n", i, j, distance);
			}
		}
	}
}
