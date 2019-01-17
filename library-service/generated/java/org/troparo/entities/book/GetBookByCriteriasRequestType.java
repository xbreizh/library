
package org.troparo.entities.book;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://troparo.org/entities/book}Token"/>
 *         &lt;element ref="{http://troparo.org/entities/book}BookCriterias"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "token",
    "bookCriterias"
})
@XmlRootElement(name = "getBookByCriteriasRequestType")
public class GetBookByCriteriasRequestType {

    @XmlElement(name = "Token", required = true)
    protected String token;
    @XmlElement(name = "BookCriterias", required = true)
    protected BookCriterias bookCriterias;

    /**
     * Gets the value of the token property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the value of the token property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToken(String value) {
        this.token = value;
    }

    /**
     * Gets the value of the bookCriterias property.
     * 
     * @return
     *     possible object is
     *     {@link BookCriterias }
     *     
     */
    public BookCriterias getBookCriterias() {
        return bookCriterias;
    }

    /**
     * Sets the value of the bookCriterias property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookCriterias }
     *     
     */
    public void setBookCriterias(BookCriterias value) {
        this.bookCriterias = value;
    }

}
