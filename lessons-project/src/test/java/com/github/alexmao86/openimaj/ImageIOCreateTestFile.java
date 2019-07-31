
package com.github.alexmao86.openimaj;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class ImageIOCreateTestFile {
	public static void main(String args[]) throws IOException {
		File mutilImage=new File("10kc.png");
		for(int i=0;i<10;i++) {
			FileUtils.writeByteArrayToFile(mutilImage, FileUtils.readFileToByteArray(new File("kc.jpg")), true);
			//FileUtils.writeByteArrayToFile(mutilImage, new byte[] {'\n'}, true);
		}
	}
}
