package de.demoshop.model;

public abstract class Product<T extends Product<T>> {

    private String title;
    private int dataProductid;

    public int getDataProductid() {
        return dataProductid;
    }

    public T setDataProductid(int dataProductid) {
        this.dataProductid = dataProductid;
        return (T) this;
    }

    public T setTitle(String title) {
        this.title = title;
        return (T) this;
    }

    public String getTitle() {
        return title;
    }
}
