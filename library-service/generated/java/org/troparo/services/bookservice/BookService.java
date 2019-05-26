package org.troparo.services.bookservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.2
 * 2019-02-11T17:56:47.736+01:00
 * Generated source version: 3.0.2
 * 
 */
@WebServiceClient(name = "BookService", 
                  wsdlLocation = "https://raw.githubusercontent.com/xbreizh/troparo/e481ca9c19f78656678dfab01ca1a01f124c72e2/troparo-web/src/main/resources/org/troparo/web/services/BookService.wsdl",
                  targetNamespace = "http://troparo.org/services/BookService/") 
public class BookService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://troparo.org/services/BookService/", "BookService");
    public final static QName BookServicePort = new QName("http://troparo.org/services/BookService/", "BookServicePort");
    static {
        URL url = null;
        try {
            url = new URL("https://raw.githubusercontent.com/xbreizh/troparo/e481ca9c19f78656678dfab01ca1a01f124c72e2/troparo-web/src/main/resources/org/troparo/web/services/BookService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(BookService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "https://raw.githubusercontent.com/xbreizh/troparo/e481ca9c19f78656678dfab01ca1a01f124c72e2/troparo-web/src/main/resources/org/troparo/web/services/BookService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public BookService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public BookService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public BookService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public BookService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public BookService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public BookService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns IBookService
     */
    @WebEndpoint(name = "BookServicePort")
    public IBookService getBookServicePort() {
        return super.getPort(BookServicePort, IBookService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IBookService
     */
    @WebEndpoint(name = "BookServicePort")
    public IBookService getBookServicePort(WebServiceFeature... features) {
        return super.getPort(BookServicePort, IBookService.class, features);
    }

}
