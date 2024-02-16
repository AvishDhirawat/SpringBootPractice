package com.firstspring.project.firstprojectspring.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties("field2") // Static Filtering, Class Level
//@JsonIgnoreProperties({"field2", "field1"}) // For more than one fields (Array of fields)
@JsonFilter("SomeBeanFilter") // For Dynamic Filtering
public class SomeBean {
    private String field1;

    //    @JsonIgnore  // This is Static Filtering, and it is being used to hide field2 from API calls (it won't show with the get requests)
    private String field2;
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
