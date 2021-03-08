package com.tp.cozubu.util;

import com.tp.cozubu.advice.exception.CommonUtilException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtil {

    private CommonUtil() { throw new AssertionError();};

    /**
     * @apiNote xml로 넘어온 데이터를 listMap으로 변환
     * @param xmlData
     * @author seungglee
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> getMapResultFromXml(String xmlData) {

        // XML 파싱
        InputSource is =  new InputSource(new StringReader(new String(xmlData)));
        Document doc;
        List<Map<String, String>> xmlList = new ArrayList<>();
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            doc.getDocumentElement().normalize();
            NodeList nodeList;
            Map<String, String> childNodeMap;

            String getNodeName;

            if(doc.getFirstChild().getChildNodes().getLength() > 0) {

                getNodeName = doc.getFirstChild().getFirstChild().getNodeName();
                nodeList = doc.getElementsByTagName(getNodeName);

                for(int i=0; i<nodeList.getLength(); i++) {

                    NodeList childList = nodeList.item(i).getChildNodes();

                    childNodeMap = new HashMap<String, String>();
                    for (int j = 0; j < childList.getLength(); j++) {
                        String childnodename = childList.item(j).getNodeName();
                        String childnodevalue = childList.item(j).getTextContent();

                        childNodeMap.put(childnodename, childnodevalue);
                    }
                    xmlList.add(childNodeMap);
                }

            }
        } catch (SAXException e) {
            throw new CommonUtilException(e.getMessage());
        } catch (IOException e) {
            throw new CommonUtilException(e.getMessage());
        } catch (ParserConfigurationException e) {
            throw new CommonUtilException(e.getMessage());
        }

        return xmlList;
    }
    
    public static boolean isBusinessId(String str) {
        String[] strs = str.split("");
        if (strs.length != 10) {
            return false;
        }
        int[] ints = new int[10];
        for(int i=0; i< strs.length; i++) {
            ints[i] = Integer.valueOf(strs[i]);
        }
        int sum = 0;
        int[] indexs = new int[] { 1, 3, 7, 1, 3, 7, 1, 3 };
        for (int i = 0; i < 8; i++) {
            sum += ints[i] * indexs[i];
        }
        int num = ints[8] * 5;
        sum += (num / 10) + (num % 10);
        sum = 10 - (sum % 10);
        if(sum== 10){
            return 0 == ints[9];
        }else{
            return sum == ints[9];
        }
    }
    
    public static String getClientIp() {
        return getClientIp(false);
    }

    /**
     * @apiNote flag 가 true면 client ip, 아니면 web server 혹은 load balancer ip
     * @param flag
     * @author es-seungglee
     * @return
     */
    public static String getClientIp(boolean flag) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = request.getHeader("X-Forwarded-For");
        if (flag || ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * @apiNote 두 object의 내용을 비교하는 메서드
     * @param obj
     * @param compareObj
     * @author es-seungglee
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static boolean compareObject(Object obj, Object compareObj) throws IllegalArgumentException, IllegalAccessException {
        e: for(Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);          // private 속성에 접근하려면 이 설정을 해 줘야 한다.
            Object value = field.get(obj);
            String fieldName = field.getName();
            for(Field compareField : compareObj.getClass().getDeclaredFields()) {
                compareField.setAccessible(true);
                String compareFieldName = compareField.getName();
                // 리스트는 서로 비교가 어려우니 리스트 비교는 하지 않고 넘긴다.
                // 해당 함수의 목적은 두 객체의 내부 값이 같은지만 판단하기 때문에 필요하다면 해당 리스트끼리 비교하는 로직을 호출하는 쪽에서 구현해야 한다.
                if(value instanceof List) {
                    continue e;
                }
                if(fieldName.equals(compareFieldName)) {
                    Object compareValue = compareField.get(compareObj);
                    // 두개의 값을 비교 했을 때 현재 값이 null이 아니면서 값이 다르다면 
                    if(value != null && !value.equals(compareValue)) {
                        // 바로 false를 리턴하고 해당 함수를 종료 시킨다.
                        return false;
                    }
                    // 두 개의 필드가 같다면 그 이외의 값들은 비교하면 메모리 낭비라 바로 다음 단계로 넘어간다.
                    continue e;
                }

            }
        }
        return true;
    }

    /**
     * 원본 객체를 넘겨진 복사 객체에 복사해서 복사된 객체를 리턴하는 함수
     * @param originObj
     * @param copyObj
     * @author es-seungglee
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object copyObject(Object originObj, Object copyObj) throws IllegalArgumentException, IllegalAccessException {
        for(Field field : originObj.getClass().getDeclaredFields()) {       // 선언된 모든 필드들에 대해 접근한다.
            field.setAccessible(true);                                      // private 변수에 접근하기 위해 설정.
            Object value = field.get(originObj);                            // 객체에서 해당 변수의 값을 가져온다.
            String fieldName = field.getName();                             // 객체에서 선언된 변수명
            for(Field copyField : copyObj.getClass().getDeclaredFields()) { // 복사할 객체의 모든 필드에 접근
                copyField.setAccessible(true);
                String copyFieldName = copyField.getName();
                if(fieldName.equals(copyFieldName)) {                       // 같은 객체이기 때문에 동일한 변수는 통일한 타입이므로 값을 그냥 넣는다.
                    copyField.set(copyObj, value);
                    // 값을 넣었다면 더 이상의 반복문은 불필요 하니 다음 반복문으로 패스
                    continue;
                }
            }
        }
        return copyObj;
    }
    
    /**
     * @apiNote sha256 암호화를 위한 함수
     * @author es-seungglee
     * @param msg
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());
        return md.digest();
    }

    /**
     * 바이트를 헥사값으로 변환한다, type 1
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex1(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
