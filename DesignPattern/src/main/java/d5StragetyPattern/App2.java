package d5StragetyPattern;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Author: wangyufei
 * CreateTime:2018/03/04
 * Companion:Champion Software
 */
interface IDigestStragety {
    String calcHashString(String data);
}

class MD5DigestStragety implements IDigestStragety {
    @Override
    public String calcHashString(String data) {
        return DigestUtils.md5Hex(data);
    }
}

class SHA1DigestStragety implements IDigestStragety {
    @Override
    public String calcHashString(String data) {
        return DigestUtils.sha1Hex(data);
    }
}

class SHA256DigestStragety implements IDigestStragety {
    @Override
    public String calcHashString(String data) {
        return DigestUtils.sha256Hex(data);
    }
}

public class App2 {
    private String salt = "wyf";
    private IDigestStragety digestStragety;

    public App2(IDigestStragety digestStragety) {
        this.digestStragety = digestStragety;
    }

    public String calcHashData(String data) {
        return this.digestStragety.calcHashString(data+this.salt);
    }

    /**
     * 比较经过加密后的value和hashValue是否相等
     * @param hashValue 加密后的value
     * @param value 原始的value
     * @return
     */
    public boolean checkStringHash(String hashValue,String value)
    {
        String hashValue2 = calcHashData(value);
        return hashValue2.equalsIgnoreCase(hashValue);
    }

    public static void main(String[] args) {
        App2 app2=new App2(new MD5DigestStragety());
        String hashData = app2.calcHashData("123456");
        System.out.println("散列结果："+hashData);
        System.out.println(app2.checkStringHash(hashData,"123456"));
    }

}
