package sr.business.stfs.models;

/**
 * Created by jurian360 on 5/2/2016.
 */
public class Category {
    private Long cat_id;
    private String cat_category;
    private String cat_description;
    private Category cat_hcat_id;
    private String cat_active;

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_category() {
        return cat_category;
    }

    public void setCat_category(String cat_category) {
        this.cat_category = cat_category;
    }

    public String getCat_description() {
        return cat_description;
    }

    public void setCat_description(String cat_description) {
        this.cat_description = cat_description;
    }

    public Category getCat_hcat_id() {
        return cat_hcat_id;
    }

    public void setCat_hcat_id(Category cat_hcat_id) {
        this.cat_hcat_id = cat_hcat_id;
    }

    public String getCat_active() {
        return cat_active;
    }

    public void setCat_active(String cat_active) {
        this.cat_active = cat_active;
    }
}
