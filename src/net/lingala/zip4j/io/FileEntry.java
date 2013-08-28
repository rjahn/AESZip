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

import java.io.File;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.InternalZipConstants;

public class FileEntry implements IZipEntry {
	
	private File file;
	
	public FileEntry(File pFile) throws ZipException {
		if (pFile == null) {
			throw new ZipException("input file is null, cannot get file attributes");
		}
		
		file = pFile;
	}
	
	public boolean isDirectory() {
		return file.isDirectory();
	}
	
	public long length() {
		return file.length();
	}
	
	public boolean exists() {
		return file.exists();
	}
	
	public long lastModified() {
		return file.lastModified();
	}
	
	public String getPath()	{
		return file.getAbsolutePath();
	}
	
	public int getAttributes() {
		if (!file.exists()) {
			return 0;
		}
		
		if (file.isDirectory()) {
			if (file.isHidden()) {
				return InternalZipConstants.FOLDER_MODE_HIDDEN;
			} else {
				return InternalZipConstants.FOLDER_MODE_NONE;
			}
		} else {
			if (!file.canWrite() && file.isHidden()) {
				return InternalZipConstants.FILE_MODE_READ_ONLY_HIDDEN;
			} else if (!file.canWrite()) {
				return InternalZipConstants.FILE_MODE_READ_ONLY;
			} else if (file.isHidden()) {
				return InternalZipConstants.FILE_MODE_HIDDEN;
			} else {
				return InternalZipConstants.FILE_MODE_NONE;
			}
		}
	}	
}
