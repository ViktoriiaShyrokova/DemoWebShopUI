package de.demoshop.data;

import de.demoshop.model.Apparel;
import de.demoshop.model.Desktop;

import java.util.List;

public class ProductData {

    public static Desktop computerWithoutDetails(){
        return new Desktop()
                .setDataProductid(31)
                .setTitle("14.1-inch Laptop");
    }
    public static Desktop simpleComputer(){
        return new Desktop()
                .setDataProductid(75)
                .setTitle("Simple Computer")
                .setProcessor("Processor: Slow")
                .setRam("RAM: 2 GB")
                .setHdd("HDD: 320 GB")
                .setValueAttributes(List.of("product_attribute_75_5_31_96"));
    }
    public static Desktop expensiveComputer(){
        return new Desktop()
                .setDataProductid(74)
                .setTitle("Build your own expensive computer")
                .setProcessor("Processor: Fast [+100.00]")
                .setRam("RAM: 8GB [+60.00]")
                .setHdd("HDD: 400 GB [+100.00]")
                .setSoftware("Software: Office Suite [+100.00]")
                .setValueAttributes(List.of("product_attribute_74_5_26_82", "product_attribute_74_6_27_85", "product_attribute_74_3_28_87", "product_attribute_74_8_29_89"));
    }
    public static Apparel OutOfStockBag(){
        return new Apparel()
                .setDataProductid(29);
    }
}
