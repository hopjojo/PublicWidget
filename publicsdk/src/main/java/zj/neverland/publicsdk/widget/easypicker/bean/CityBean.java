package zj.neverland.publicsdk.widget.easypicker.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cefoc on 2017/5/4.
 * Class Note:
 */

public class CityBean implements Serializable{
    /**
     * name : 城市
     * area : ["东城区","西城区","崇文区","昌平区"]
     */
    private List<String> area;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }
}
