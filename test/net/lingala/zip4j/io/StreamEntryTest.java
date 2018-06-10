package net.lingala.zip4j.io;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import junit.framework.Assert;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.junit.Test;

public class StreamEntryTest
{
	@Test
	public void testAddStream() throws Exception
	{
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		parameters.setEncryptFiles(true);
		parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
		parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
		parameters.setPassword("testcase");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(baos));
		
		//Be careful: Use a new Stream because StreamEntry needs to check the size
		//If you know the data size, use StaticEntry("name", length)
		zos.putNextEntry(new StreamEntry("datafile1.csv", StreamEntryTest.class.getResourceAsStream("datafile1.csv")), parameters);
		
		//New stream!
		InputStream is = StreamEntryTest.class.getResourceAsStream("datafile1.csv");
		
		byte[] byData = new byte[4096];
		
		int iLen;
		
		while ((iLen = is.read(byData)) >= 0)
		{
			zos.write(byData, 0, iLen);
		}
		
		zos.closeEntry();
		
		zos.finish();
		zos.close();
		
		Assert.assertTrue(baos.toByteArray().length > 0);
	}
}
