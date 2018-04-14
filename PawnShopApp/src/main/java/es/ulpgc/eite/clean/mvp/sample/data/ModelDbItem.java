package es.ulpgc.eite.clean.mvp.sample.data;

public class ModelDbItem {
    private Integer Id;
    private String contents;
    private String details;

    public ModelDbItem() {
        this.contents = "";
        this.details = "";
    }

    public ModelDbItem(String contents, String details) {
        this.contents = contents;
        this.details = details;
    }


    @Override
    public String toString() {
        return contents;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ModelDbItem){
            ModelDbItem item = (ModelDbItem) obj;

            if(item.getId() == getId()){
                return true;
            }
        }
        return false;
    }

    //  ------------------------------------------- Getters / Setters

    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        this.Id = id;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
    public String getContents() {
        return contents;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

}
