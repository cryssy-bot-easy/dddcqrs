package com.incuventure.tests.serializer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import org.junit.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: Jett
 * Date: 6/11/12
 */
public class SerializerTest {

    @Test
    public void testPayloadSerializer() {

        XStream xstream = new XStream();

        HashMap hm = new HashMap();

        List<String> l = new ArrayList<String>();

        l.add("document1");
        l.add("document2");

        hm.put("name", "Jett");
        hm.put("docno", "1234");
        hm.put("docs", l);

        String xml = xstream.toXML(hm);


        // serialize
        StringWriter sw = new StringWriter();
        xstream.marshal(hm,  new CompactWriter(sw));

        System.out.println(sw.toString());

//        xstream.alias("map", java.util.Map.class);
        HashMap<String, Object> hm2 = (HashMap<String, Object>) xstream.fromXML(sw.toString());

        System.out.println(hm2.get("docs"));

    }

    @Test
    public void testGSONSerializer() {
        HashMap hm = new HashMap();

        List<String> l = new ArrayList<String>();

        l.add("document1");
        l.add("document2");

        hm.put("name", "Jett");
        hm.put("docno", "1234");
        hm.put("docs", l);

        Gson gson = new Gson();

        // serialize
        String json = gson.toJson(hm);
        System.out.println(json);

        // deserialize
        HashMap<String, Object> hm2 = gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());
        System.out.println(hm2.get("docs"));
    }
}
