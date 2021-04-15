/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.vsubotkovski.soapcompanyjava;

import javax.jws.WebMethod;
import javax.jws.WebService;
import lt.viko.eif.vsubotkovski.companytoxml.models.Company;
import lt.viko.eif.vsubotkovski.companytoxml.models.Car;

/**
 *
 * @author valde
 */
@WebService(name = "CompanyService")
public interface CompanyService {
    
    @WebMethod
    Company getAllCompany();
    @WebMethod
    Car addCar(Car newCar);
}
