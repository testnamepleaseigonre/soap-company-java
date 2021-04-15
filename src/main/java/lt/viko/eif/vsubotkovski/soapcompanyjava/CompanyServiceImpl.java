/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.vsubotkovski.soapcompanyjava;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import lt.viko.eif.vsubotkovski.companytoxml.models.Company;
import lt.viko.eif.vsubotkovski.companytoxml.models.Car;
import lt.viko.eif.vsubotkovski.companytoxml.models.Vehicles;
import lt.viko.eif.vsubotkovski.companytoxml.transform.Transformator;

@WebService(endpointInterface
    ="lt.viko.eif.vsubotkovski.soapcompanyjava.CompanyService")

public class CompanyServiceImpl implements CompanyService{
    
    /**
     *
     * @return
     */
    @Override
    public Company getAllCompany() {
        Transformator transformator = new Transformator();
        Company company = new Company();
        try {
            company = transformator.transformToObject("company.xml");
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return company;
    }
    
    @Override
    public Car addCar(Car newCar) {
        Car car = newCar;
        //System.out.println(car);
        Transformator transformator = new Transformator();
        Company company = new Company();
        try {
            company = transformator.transformToObject("company.xml");
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Vehicles vehicles = company.getVehicles();
        List<Car> cars = vehicles.getCars();
        cars.add(car);
        vehicles.setCars(cars);
        company.setVehicles(vehicles);
        try {
            transformator.transformToXml(company, "company_added.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return car;
    } 
}
