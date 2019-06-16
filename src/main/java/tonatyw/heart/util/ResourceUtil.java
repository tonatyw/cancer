package tonatyw.heart.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
public class ResourceUtil {
	/**
	 * @param target
	 * @return
	 * @todo 将Properties中的配置信息转换为HashMap格式
	 */
	public static Map<String, String> propertiesToMap(Properties target) {
		if (null == target) {
			return null;
		}
		Map<String, String> resultMap = new HashMap<String, String>();
		Iterator<Entry<Object, Object>> ite = target.entrySet().iterator();
		while (ite.hasNext()) {
			Map.Entry<Object, Object> entry = ite.next();
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			resultMap.put(key, value);
		}
		return resultMap;
	}

	public static Properties getProperties(String resource) throws IOException {
		InputStream _stream = getResourceAsStream(resource, ResourceUtil.class);
		return loadProperties(_stream);
	}

	public static InputStream getResourceAsStream(String resource, Class<?> loader)
			throws FileNotFoundException {
		InputStream _stream = null;
		ClassLoader _loader = Thread.currentThread().getContextClassLoader();

		if (_loader != null) {
			_stream = _loader.getResourceAsStream(resource);
		}
		if (_stream != null) {
			return _stream;
		} else if (loader != null) {
			_stream = loader.getResourceAsStream(resource);
		} else {
			_stream = ResourceUtil.class.getResourceAsStream(resource);
		}
		if (_stream != null) {
			return _stream;
		} else if (loader != null) {
			_stream = loader.getClassLoader().getResourceAsStream(resource);
		} else {
			_stream = ResourceUtil.class.getClassLoader().getResourceAsStream(resource);
		}
		if (_stream != null) {
			return _stream;
		} else {
			_stream = new FileInputStream(resource);
		}
		return _stream;
	}

	public static Properties loadProperties(InputStream stream) throws IOException {
		Properties _properties = new Properties();
		_properties.load(stream);
		try {
			return _properties;
		} finally {
			stream.close();
		}
	}
	public static void setProperties(String resource, String key, String value){
		FileOutputStream oFile = null;
		try{
			InputStream _stream = getResourceAsStream(resource, ResourceUtil.class);
			Properties prop = loadProperties(_stream);
			
			//true表示追加打开
			oFile = new FileOutputStream(resource, true);
			prop.setProperty(key, value);
			prop.store(oFile, null);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}finally{
			try {
				if(oFile != null){
					oFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
