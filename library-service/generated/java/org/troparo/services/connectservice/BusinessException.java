
package org.troparo.services.connectservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.2
 * 2019-01-17T16:28:08.166+01:00
 * Generated source version: 3.0.2
 */

@WebFault(name = "BusinessFaultType", targetNamespace = "http://troparo.org/entities/connect")
public class BusinessException extends Exception {
    
    private org.troparo.entities.connect.BusinessFaultType businessFaultType;

    public BusinessException() {
        super();
    }
    
    public BusinessException(String message) {
        super(message);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, org.troparo.entities.connect.BusinessFaultType businessFaultType) {
        super(message);
        this.businessFaultType = businessFaultType;
    }

    public BusinessException(String message, org.troparo.entities.connect.BusinessFaultType businessFaultType, Throwable cause) {
        super(message, cause);
        this.businessFaultType = businessFaultType;
    }

    public org.troparo.entities.connect.BusinessFaultType getFaultInfo() {
        return this.businessFaultType;
    }
}