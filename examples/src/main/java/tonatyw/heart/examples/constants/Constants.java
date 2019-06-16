package tonatyw.heart.examples.constants;
/**
 * 常量类
 * @ClassName Constants
 * @Description: 常量类
 * @date 2019年6月11日 下午4:43:30
 */
public class Constants {
	public interface Path{
		public static String CONFIG_PROPERTIES_PATH = Constants.class.getResource("/conf.properties").getPath();
	}
}
