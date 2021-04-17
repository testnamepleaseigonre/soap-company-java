/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.vsubotkovski.soapcompanyjava;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import lt.viko.eif.vsubotkovski.companytoxml.models.Company;
import lt.viko.eif.vsubotkovski.companytoxml.models.Car;
import lt.viko.eif.vsubotkovski.companytoxml.models.EnginePower;
import lt.viko.eif.vsubotkovski.companytoxml.models.Vehicles;
import lt.viko.eif.vsubotkovski.companytoxml.transform.Transformator;

/**
 * Endpoint interface of service.
 * 
 * @author valde
 */
@WebService(endpointInterface
    ="lt.viko.eif.vsubotkovski.soapcompanyjava.CompanyService")

public class CompanyServiceImpl implements CompanyService{
   
    /**
     * Method which takes and returns the Company class from XML file.
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
    
    /**
     * Method which adds a car object into the XML file
     * 
     * @param newCar - Car object which represents new car and will be added to the XML file.
     * @return - returns new Car object added to XML file.
     */
    @Override
    public Car addCar(Car newCar) {
        Car car = newCar;
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
            transformator.transformToXml(company, "company.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Car added to [company.xml]:");
        System.out.println(car);
        return car;
    }
    
    /**
     * Method which sets new value of phone number in XML file.
     * 
     * @param newPhone - string which represents the new number.
     * @return - returns "Phone set to:x" in string format.
     */
    @Override
    public String setPhoneNumber(String newPhone){
        Transformator transformator = new Transformator();
        Company company = new Company();
        try {
            company = transformator.transformToObject("company.xml");
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        company.setTel(newPhone);
        try {
            transformator.transformToXml(company, "company.xml");
        } catch (JAXBException | FileNotFoundException ex) {
            Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(String.format("Phone set to: %s", newPhone));
        return String.format("Phone set to: %s", newPhone);
    }
    
    /**
     * Method which removes the car object from XML file.
     * 
     * @param modelName - String which represents car's model name.
     * @param ep - Object which represents car's engine power.
     * @return - returns "Car successfully removed!" if car was removed, and "Car not found!" if not.
     */
    @Override
    public String removeCar(String modelName, EnginePower ep){
        Transformator transformator = new Transformator();
        Company company = new Company();
        try {
            company = transformator.transformToObject("company.xml");
        } catch (JAXBException | IOException ex) {
            Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Vehicles vehicles = company.getVehicles();
        List<Car> cars = vehicles.getCars();
        for(Car car : company.getVehicles().getCars())
        {
            //System.out.println(String.format("%s=%s", car.getEnginePower(), ep));
            if(car.getModel().equals(modelName) && car.getEnginePower().getHpNumber().equals(ep.getHpNumber()) && car.getEnginePower().getHpUnit().equals(ep.getHpUnit()))
            {
                cars.remove(car);
                vehicles.setCars(cars);
                company.setVehicles(vehicles);
                try {
                    transformator.transformToXml(company, "company.xml");
                } catch (JAXBException | FileNotFoundException ex) {
                    Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                return String.format("Car successfully removed!");
            }
        }
        return String.format("Car not found!");
    }
}
