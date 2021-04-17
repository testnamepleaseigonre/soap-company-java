/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.vsubotkovski.soapcompanyjava;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import lt.viko.eif.vsubotkovski.companytoxml.models.Company;
import lt.viko.eif.vsubotkovski.companytoxml.models.Car;
import lt.viko.eif.vsubotkovski.companytoxml.models.EnginePower;

/**
 * Service interface consists of multiple methods.
 * 
 * @author valde
 */
@WebService(name = "CompanyService")
public interface CompanyService {
    
    @WebMethod
    Company getAllCompany();
    @WebMethod
    Car addCar(@WebParam(name = "car")Car newCar);
    @WebMethod
    String setPhoneNumber(@WebParam(name = "new-phone")String newPhone);
    @WebMethod
    String removeCar(@WebParam(name = "model-name")String modelName, @WebParam(name = "engine-power")EnginePower ep);
}
