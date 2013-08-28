/*
* Copyright 2013 SIB Visions GmbH  
* 
* Licensed under the Apache License, Version 2.0 (the "License"); 
* you may not use this file except in compliance with the License. 
* You may obtain a copy of the License at 
* 
* http://www.apache.org/licenses/LICENSE-2.0 
* 
* Unless required by applicable law or agreed to in writing, 
* software distributed under the License is distributed on an "AS IS" BASIS, 
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
* See the License for the specific language governing permissions and 
* limitations under the License. 
*/

package net.lingala.zip4j.io;

import java.io.IOException;
import java.io.InputStream;

import net.lingala.zip4j.util.InternalZipConstants;

public class StreamEntry implements IZipEntry {
	
	private String sPath;
	
	private long lLastModified;
	
	private long lLength;
	
	private boolean bIsDirectory = false;

	
	public StreamEntry(String pPath, InputStream pStream) throws IOException {
		sPath = pPath;
		lLastModified = System.currentTimeMillis();
		
    	byte[] byTmp = new byte[8192];

    	lLength = 0;
	    int iLen;

        while ((iLen = pStream.read(byTmp)) >= 0) {
            lLength += iLen;
        }

        pStream.close();
	}
	
	public void setDirectory(boolean pDirectory) {
		bIsDirectory = pDirectory;
	}
	
	public boolean isDirectory() {
		return bIsDirectory;
	}
	
	public long length() {
		return lLength;
	}
	
	public boolean exists() {
		return true;
	}
	
	public long lastModified() {
		return lLastModified;
	}
	
	public String getPath()	{
		return sPath;
	}
	
	public int getAttributes() {
		if (isDirectory()) {
			return InternalZipConstants.FOLDER_MODE_NONE;
		}
		else {
			return InternalZipConstants.FILE_MODE_NONE;
		}
	}	
}
