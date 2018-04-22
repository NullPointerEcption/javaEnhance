package d5StragetyPattern;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
public class App1 {
    private String salt="wyf";
    private HashType hashType;
    public App1(HashType hashType) {
        this.hashType=hashType;
    }

    /**
     * 根据value计算出对应的hash值
     * @param value
     * @return
     */
    public String calcStringHash(String value)
    {
        if(hashType==HashType.MD5)
        {
            return DigestUtils.md5Hex(value+salt);
        }
        else if(hashType==HashType.SHA1)
        {
            return DigestUtils.sha1Hex(value+salt);
        }
        else if(hashType==HashType.SHA256)
        {
            return DigestUtils.sha256Hex(value+salt);
        }
        else
        {
            throw new RuntimeException("未知的散列算法");
        }
    }

    /**
     * 比较经过加密后的value和hashValue是否相等
     * @param hashValue 加密后的value
     * @param value 原始的value
     * @return
     */
    public boolean checkStringHash(String hashValue,String value)
    {
        String hashValue2 = calcStringHash(value);
        return hashValue2.equalsIgnoreCase(hashValue);
    }

    public static void main(String[] args) {
        App1 app1=new App1(HashType.MD5);
        String pwd = app1.calcStringHash("123456");
        System.out.println(pwd);

        String hashValue="86d54561e8fdd02b38198bf01f378f6d";
        String value="123456";
        System.out.println(app1.checkStringHash(hashValue,value));

    }


}
