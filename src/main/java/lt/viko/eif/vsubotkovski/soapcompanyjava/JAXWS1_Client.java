/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.vsubotkovski.soapcompanyjava;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * Main method, implementation of service client side.
 * 
 * @author valde
 */
public class JAXWS1_Client {
     public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:5051/CompanyService/MainService?wsdl");
        QName qname = new QName("http://soapcompanyjava.vsubotkovski.eif.viko.lt/",
        "CompanyServiceImplService");
        Service service = Service.create(url, qname);
        CompanyService eif = service.getPort(CompanyService.class);
    }
}
