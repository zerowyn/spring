//package com.example;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.LinkedList;
//import java.util.List;
//
//public class TestXml2Json {
//    public static void main(String[] args) throws JDOMException, IOException {
//        String xml1 = "<xml>\n" +
//                "  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
//                "  <attach><![CDATA[支付测试]]></attach>\n" +
//                "  <bank_type><![CDATA[CFT]]></bank_type>\n" +
//                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
//                "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
//                "  <mch_id><![CDATA[10000100]]></mch_id>\n" +
//                "  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
//                "  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
//                "  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
//                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
//                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
//                "  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
//                "  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
//                "  <time_end><![CDATA[20140903131540]]></time_end>\n" +
//                "  <total_fee>1</total_fee><coupon_fee><![CDATA[10]]></coupon_fee>\n" +
//                "   <coupon_count><![CDATA[1]]></coupon_count>\n" +
//                "   <coupon_type><![CDATA[CASH]]></coupon_type>\n" +
//                "   <coupon_id><![CDATA[10000]]></coupon_id>\n" +
//                "   <coupon_fee><![CDATA[100]]></coupon_fee>\n" +
//                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
//                "  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
//                "</xml>";
//        JSONObject json=xml2Json(xml1.getBytes());
//        String s = json.toString();
//        JSONObject coupon_fee = json.getJSONObject("xml");
//        List<Integer> coupon_fee1 = (List) coupon_fee.get("coupon_fee");
//        String r1 = s.replace("[", "");
//        String r2 = r1.replace("]", "");
//        JSONObject jsonObject = JSON.parseObject(r2);
//        QueryOrderResultDto testDto = jsonObject.getObject("xml", QueryOrderResultDto.class);
//        System.out.println(testDto);
//    }
//
//    public static JSONObject xml2Json(byte[] xml) throws JDOMException, IOException {
//        JSONObject jsonObject = new JSONObject();
//        InputStream is = new ByteArrayInputStream(xml);
//        SAXBuilder saxBuilder = new SAXBuilder();
//        Document document = saxBuilder.build(is);
//        Element rootElement = document.getRootElement();
//        jsonObject.put(rootElement.getName(),iterateElement(rootElement));
//        return jsonObject;
//    }
//
//    private static JSONObject iterateElement(Element rootElement) {
//        List note = rootElement.getChildren();
//        Element et;
//        JSONObject jsonObject = new JSONObject();
//        List list ;
//        for(int i=0;i<note.size();i++){
//            list = new LinkedList();
//            et = (Element) note.get(i);
//            if(et.getTextTrim().equals("")){
//                if(et.getChildren().size() == 0) continue;
//                if(jsonObject.containsKey(et.getName())){
//                    list = (List)jsonObject.get(et.getName());
//                }
//                list.add(iterateElement(et));
//                jsonObject.put(et.getName(),list);
//            }else{
//                if(jsonObject.containsKey(et.getName())){
//                    list = (List)jsonObject.get(et.getName());
//                }
//                list.add(et.getTextTrim());
//                jsonObject.put(et.getName(),list);
//            }
//        }
//        return jsonObject;
//    }
//
//
//}
