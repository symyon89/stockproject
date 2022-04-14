package com.stockproject.dto;


import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Stoc")
public class ProduseDto implements Serializable {
    @XmlElement(name="id_produs")
    private Integer id;
    private Integer stoc;
}
