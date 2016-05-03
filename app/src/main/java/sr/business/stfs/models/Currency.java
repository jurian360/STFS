package sr.business.stfs.models;

/**
 * Created by jurian360 on 5/2/2016.
 */
public class Currency {

    private Long c_id;
    private String c_currency;
    private String c_description;
    private String c_active;

    public Long getC_id() {
        return c_id;
    }

    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }

    public String getC_currency() {
        return c_currency;
    }

    public void setC_currency(String c_currency) {
        this.c_currency = c_currency;
    }

    public String getC_description() {
        return c_description;
    }

    public void setC_description(String c_description) {
        this.c_description = c_description;
    }

    public String getC_active() {
        return c_active;
    }

    public void setC_active(String c_active) {
        this.c_active = c_active;
    }
}
