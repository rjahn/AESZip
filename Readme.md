Overview
========

This project contains the the original sources of zip4j (http://www.lingala.net/zip4j/)
and adds support for stream oriented zip creation.

Usage
=====

<pre>
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
</pre>

License
-------

Apache 2.0 (http://www.apache.org/licenses/)


Have fun!
