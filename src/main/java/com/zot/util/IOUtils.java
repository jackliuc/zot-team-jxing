package com.zot.util;

import java.io.Closeable;

public class IOUtils {

	public void closeQuetly(Closeable... forCloses) {
		if (null != forCloses && forCloses.length > 0) {
			for (Closeable forClose : forCloses) {
				try {
					forClose.close();
				} catch (Exception ex) {

				}
			}
		}
	}

}
