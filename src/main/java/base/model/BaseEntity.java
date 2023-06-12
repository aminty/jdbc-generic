package base.model;

import jdk.nashorn.internal.objects.annotations.Getter;

public class BaseEntity <ID>{

    protected ID id;

    public BaseEntity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }
    public void setId(ID id){
        this.id=id;

    }
}
