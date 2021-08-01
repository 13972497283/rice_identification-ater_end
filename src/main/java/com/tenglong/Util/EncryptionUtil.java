package com.tenglong.Util;

import com.tenglong.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.Map;

public class EncryptionUtil {
        public Map<String, String> encryption(String pwd) {
                String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
                String password = new Md5Hash(pwd, salt, 2).toString();
                Map<String,String > map = new HashMap<String,String >();
                map.put("salt", salt);
                map.put("password", password);
                return map;
        }
}
