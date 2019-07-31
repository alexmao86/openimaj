package com.github.alexmao86.openimaj;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.stream.ImageInputStreamImpl;
/**
 * 
 * ClassName: RaspistillImageInputStream <br/> 
 * in final ImageReaderSpi, the core canDecodeInput to check is supported image is
 * mark, read magic bytes for image testing then reset. so make one markable ImageInputStream
 * Demo from JDK as:
 * <pre>
 * GIF:
 * public boolean canDecodeInput(Object input) throws IOException {
        if (!(input instanceof ImageInputStream)) {
            return false;
        }

        ImageInputStream stream = (ImageInputStream)input;
        byte[] b = new byte[6];
        stream.mark();
        stream.readFully(b);
        stream.reset();

        return b[0] == 'G' && b[1] == 'I' && b[2] == 'F' && b[3] == '8' &&
            (b[4] == '7' || b[4] == '9') && b[5] == 'a';
    }
    
    BMP:
    ImageInputStream stream = (ImageInputStream)source;
        byte[] b = new byte[2];
        stream.mark();
        stream.readFully(b);
        stream.reset();

        return (b[0] == 0x42) && (b[1] == 0x4d);
        
        JPEG:
        if (!(source instanceof ImageInputStream)) {
            return false;
        }
        ImageInputStream iis = (ImageInputStream) source;
        iis.mark();
        // If the first two bytes are a JPEG SOI marker, it's probably
        // a JPEG file.  If they aren't, it definitely isn't a JPEG file.
        int byte1 = iis.read();
        int byte2 = iis.read();
        iis.reset();
        if ((byte1 == 0xFF) && (byte2 == JPEG.SOI)) {
            return true;
        }
        return false;
        
 * </pre>
 * @author maoanapex88@163.com (alexmao86)
 */
public class RaspistillImageInputStream extends ImageInputStreamImpl {
	private final InputStream source;
	
	public RaspistillImageInputStream(InputStream source) {
		super();
		this.source = new MarkableInputStream(source);
	}

	@Override
	public int read() throws IOException {
		return source.read();
	}
	
	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return source.read(b, off, len);
	}
}
