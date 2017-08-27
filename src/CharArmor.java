/**
 * Created by Floglor on 28.07.2017.
 */
public class CharArmor {
    private String ArmorName = "Нет брони";
    private String Zone = "";
    private int DR = 0;

    public String getArmorName() {
        return ArmorName;
    }

    public int getDR() {
        return DR;

    }

    public String getZone() {

        return Zone;
    }

    CharArmor(String _ArmorName, String _Zone, int _DR) {
        this.ArmorName = _ArmorName;
        this.Zone = _Zone;
        this.DR = _DR;
    }
}
