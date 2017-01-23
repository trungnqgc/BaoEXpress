package com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dell on 1/20/2017.
 */

public class XMLDOMParse {
    public Document getDocument(String xml){
        Document document=null;
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder=factory.newDocumentBuilder();
            InputSource inputSource=new InputSource();
            inputSource.setCharacterStream(new StringReader(xml));
            inputSource.setEncoding("UTF-8");
            document=builder.parse(inputSource);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return document;
    }
    //
    public String getValue(Element item,String name){
        NodeList nodeList=item.getElementsByTagName(name);
       return this.getTextNodeValue(nodeList.item(0));

    }

    private final String getTextNodeValue(Node elem) {
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

}
