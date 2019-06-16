package tonatyw.heart.examples.constants;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import tonatyw.heart.cancer.HeartBox;
import tonatyw.heart.cancer.util.ResourceUtil;
/**
 * 存储系统内一般缓存对象和字段
 * @ClassName Sets
 * @Description: 存储系统内一般缓存对象和字段
 * @author Tangjc
 * @date 2019年6月11日 下午5:01:47
 */
public class Sets {
	/** 心跳相关 */
	public static HeartBox heartBox = new HeartBox();
	/** 同步列表 */
	public static Map<String,Map<String,Object>> synMap = new HashMap<String,Map<String,Object>>();
	/** 当前登录用户id */
	public String userId;
	public static Map<String,String> confMap;
	static{
		confMap = new HashMap<String,String>();
		Properties pro;
		try {
			pro = ResourceUtil.getProperties(Constants.Path.CONFIG_PROPERTIES_PATH);
			confMap = ResourceUtil.propertiesToMap(pro);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
