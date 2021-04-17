/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.vsubotkovski.soapcompanyjava;

import javax.xml.ws.Endpoint;

/**
 * Represents main class with endpoint of service.
 * 
 * @author valde
 */
public class JAXWS1 {
    public static void main(String[] args){
        
        Endpoint.publish(
            "http://localhost:5051/CompanyService/MainService",
            new CompanyServiceImpl());
    }
}
