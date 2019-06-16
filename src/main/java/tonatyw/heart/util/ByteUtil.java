package tonatyw.heart.util;
/**
 * 字节工具类
 * @ClassName ByteUtil
 * @Description: 字节工具类
 * @date 2019年6月10日 下午4:37:09
 */
public class ByteUtil {
	/**
	 * 
	 * @Title: bytesToHex
	 * @Description: 字节数组转化为十六进制字符串
	 * @author tangjc
	 * @param bytes 字节数据
	 * @return String
	 */
	public static String bytesToHex(byte[] bytes) {  
	    StringBuffer sb = new StringBuffer();  
	    for(int i = 0; i < bytes.length; i++) {  
	        String hex = Integer.toHexString(bytes[i] & 0xFF);  
	        if(hex.length() < 2){  
	            sb.append(0);  
	        }  
	        sb.append(hex);  
	    }  
	    return sb.toString();  
	}
	/**
	 * 
	 * @Title: hexToByte
	 * @Description: 16进制转化为byte
	 * @author tangjc
	 * @param inHex 16进制字符串
	 * @return byte
	 */
	public static byte hexToByte(String inHex){  
		   return (byte)Integer.parseInt(inHex,16);  
	} 
	/**
	 * 
	 * @Title: hexToByteArray
	 * @Description: 16进制转化为byte数组
	 * @author tangjc
	 * @param inHex 16进制字符串
	 * @return
	 * @return byte[]
	 */
	public static byte[] hexToByteArray(String inHex){  
	    int hexlen = inHex.length();
	    byte[] result;
	    if (hexlen % 2 == 1){
	        //奇数  
	        hexlen++;
	        result = new byte[(hexlen/2)];
	        inHex="0"+inHex;
	    }else {  
	        //偶数  
	        result = new byte[(hexlen/2)];
	    }  
	    int j=0;
	    for (int i = 0; i < hexlen; i+=2){
	        result[j]=hexToByte(inHex.substring(i,i+2));
	        j++;
	    }
	    return result;
	}
	public static String str2HexStr(String str) {
	    char[] chars = "0123456789ABCDEF".toCharArray();
	    StringBuilder sb = new StringBuilder("");
	    byte[] bs = str.getBytes();
	    int bit;
	    for (int i = 0; i < bs.length; i++) {
	        bit = (bs[i] & 0x0f0) >> 4;
	        sb.append(chars[bit]);
	        bit = bs[i] & 0x0f;
	        sb.append(chars[bit]);
	        // sb.append(' ');
	    }
	    return sb.toString().trim();
	}
	
	public static String hexStr2Str(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toUpperCase().toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}
	/**
	 * 
	 * @Title: getCopyByte
	 * @Description: 获取byte实际大小
	 * @author tangjc
	 * @param bytes
	 * @return
	 * @return byte[]
	 */
	public static byte[] getCopyByte(byte[] bytes,int length){
	    if (null == bytes || 0 == bytes.length)
	        return new byte[1];
	    byte[] bb = new byte[length];
	    System.arraycopy(bytes, 0, bb, 0, length);
	    return bb;
	}
}