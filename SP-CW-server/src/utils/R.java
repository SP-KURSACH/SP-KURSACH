package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * Клас, що надає доступ до ресурсів програми.
 * 
 * @author Andrey
 *
 */
public final class R {

	private R() {
	}

	private static HashMap<String, Object> res = new HashMap<>();

	static {
		String config = null;
		try {
			config = getConfig();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		res.put("config", config);
	}

	/**
	 * Надає ресурс за ключем.
	 * 
	 * @param key
	 *            - унікальний ключ ресурса.
	 * @return об'єкт-ресурс.
	 */
	public static Object getResource(String key) {
		return res.get(key);
	}

	private static String getConfig() throws IOException {
		// String path = R.class.getProtectionDomain().getCodeSource()
		// .getLocation().getPath();
		String path = ClassLoader.getSystemClassLoader().getResource(".")
				.getPath();
		File config = new File(new File(path).getAbsoluteFile() + "/config.xml");
		if (!config.exists()) {
			URL url = R.class.getClassLoader().getResource("config.xml");
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream(config);
			byte[] buffer = new byte[512];
			int len;
			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			is.close();
			fos.close();
		}
		return config.getPath();
	}
}
