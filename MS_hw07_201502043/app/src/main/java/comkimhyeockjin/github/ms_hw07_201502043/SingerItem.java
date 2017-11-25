package comkimhyeockjin.github.ms_hw07_201502043;

/**
 * Created by user on 2017-10-27.
 */

public class SingerItem {

    public int resId;
    public String name;
    public String birth;
    public String phone;

    public SingerItem(int resId, String name, String birth, String phone) {
        this.resId = resId;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
    }

    public int getIcon() {
        return resId;
    }

    public void setIcon(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String team) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String year) {
        this.phone = phone;
    }
}
