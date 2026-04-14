package de.demoshop.test.entity;

import java.util.List;

public class Desktop extends Product<Desktop>{


    public String processor;
    public String ram;
    public String hdd;
    public String software;
    public List<String> valueAttributes;

    public List<String> getValueAttributes() {
        return valueAttributes;
    }

    public Desktop setValueAttributes(List<String> valueAttributes) {
        this.valueAttributes = valueAttributes;
        return this;
    }

    public String getProcessor() {
        return processor;
    }

    public String getRam() {
        return ram;
    }

    public String getHdd() {
        return hdd;
    }

    public String getSoftware() {
        return software;
    }

    public Desktop setProcessor(String processor) {
        this.processor = processor;
        return this;
    }

    public Desktop setRam(String ram) {
        this.ram = ram;
        return this;
    }

    public Desktop setHdd(String hdd) {
        this.hdd = hdd;
        return this;
    }

    public Desktop setSoftware(String software) {
        this.software = software;
        return this;
    }
}
