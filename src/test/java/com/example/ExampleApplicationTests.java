package com.example;

import com.alibaba.fastjson.JSON;
import com.example.service.UserinfoService;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleApplicationTests {

	protected final Logger log = LoggerFactory.getLogger(ExampleApplication.class);

	@Autowired
	private UserinfoService userinfoService;

	private final static String xml = "<xml>"+
			"  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>" +
			"  <attach><![CDATA[支付测试]]></attach>" +
			"  <bank_type><![CDATA[CFT]]></bank_type>" +
			"  <fee_type><![CDATA[CNY]]></fee_type>" +
			"  <is_subscribe><![CDATA[Y]]></is_subscribe>" +
			"  <mch_id><![CDATA[10000100]]></mch_id>" +
			"  </xml>";
	private final static String xml2 = "<xml>"+
			"<appid>wx2421b1c4370ec43b</appid>" +
			"<attach>支付测试</attach>" +
			"<bank_type>CFT</bank_type>" +
			"<fee_type>CNY</fee_type>" +
			"<is_subscribe>Y</is_subscribe>" +
			"<mch_id>10000100</mch_id>" +
			"</xml>";

	@Test
	public void testXml(){
		String json = null;
		com.alibaba.fastjson.JSONObject jsonObject = null;
		try{
			JSONObject xmlJSONObj = XML.toJSONObject(xml2);
			json = xmlJSONObj.toString();
			jsonObject = JSON.parseObject(json);
			Object jsonStr = jsonObject.get("xml");
			net.sf.json.JSONObject jsonObject1 = net.sf.json.JSONObject.fromObject(jsonStr);
			System.out.println( "@@@"+ jsonObject1.toString());
			net.sf.json.JSONObject jsonObject2 = net.sf.json.JSONObject.fromObject(jsonObject1);
			TestDto testdto = (TestDto)net.sf.json.JSONObject.toBean(jsonObject2, TestDto.class);
			System.out.println(testdto.toString());
		}catch(Exception e){
			log.info("json串转json对象时发生异常,json串="+json);
			throw new RuntimeException("json串转json对象时发生异常,json串="+json,e);
		}
	}
}
