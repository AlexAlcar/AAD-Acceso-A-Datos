package es.florida.AE1_7;

import java.io.File;
import java.io.FilenameFilter;

public class FiltroExt implements FilenameFilter{
	String ext;
	FiltroExt(String ext) {
		this.ext=ext;
	}
	public boolean accept(File dir, String name) {
		return name.endsWith(ext);
	}
}
