package sr.business.stfs.models;

/**
 * Created by jurian360 on 5/2/2016.
 */
public class ProdCat {

    private Long prod_cat_id;
    private Product p_id;
    private Category cat_id;

    public Long getProd_cat_id() {
        return prod_cat_id;
    }

    public void setProd_cat_id(Long prod_cat_id) {
        this.prod_cat_id = prod_cat_id;
    }

    public Product getP_id() {
        return p_id;
    }

    public void setP_id(Product p_id) {
        this.p_id = p_id;
    }

    public Category getCat_id() {
        return cat_id;
    }

    public void setCat_id(Category cat_id) {
        this.cat_id = cat_id;
    }
}
