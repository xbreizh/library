
package org.troparo.services.connectservice;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.0.2
 * 2019-01-22T17:02:15.714+01:00
 * Generated source version: 3.0.2
 */

@WebFault(name = "BusinessConnectFaultType", targetNamespace = "http://troparo.org/entities/connect")
public class BusinessExceptionConnect extends Exception {
    
    private org.troparo.entities.connect.BusinessConnectFaultType businessConnectFaultType;

    public BusinessExceptionConnect() {
        super();
    }
    
    public BusinessExceptionConnect(String message) {
        super(message);
    }
    
    public BusinessExceptionConnect(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessExceptionConnect(String message, org.troparo.entities.connect.BusinessConnectFaultType businessConnectFaultType) {
        super(message);
        this.businessConnectFaultType = businessConnectFaultType;
    }

    public BusinessExceptionConnect(String message, org.troparo.entities.connect.BusinessConnectFaultType businessConnectFaultType, Throwable cause) {
        super(message, cause);
        this.businessConnectFaultType = businessConnectFaultType;
    }

    public org.troparo.entities.connect.BusinessConnectFaultType getFaultInfo() {
        return this.businessConnectFaultType;
    }
}
