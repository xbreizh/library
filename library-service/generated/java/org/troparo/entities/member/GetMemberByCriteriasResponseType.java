
package org.troparo.entities.member;

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
 *         &lt;element ref="{http://troparo.org/entities/member}MemberListType"/>
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
    "memberListType"
})
@XmlRootElement(name = "getMemberByCriteriasResponseType")
public class GetMemberByCriteriasResponseType {

    @XmlElement(name = "MemberListType", required = true)
    protected MemberListType memberListType;

    /**
     * Gets the value of the memberListType property.
     * 
     * @return
     *     possible object is
     *     {@link MemberListType }
     *     
     */
    public MemberListType getMemberListType() {
        return memberListType;
    }

    /**
     * Sets the value of the memberListType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberListType }
     *     
     */
    public void setMemberListType(MemberListType value) {
        this.memberListType = value;
    }

}
