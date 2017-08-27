/**
 * Created by Floglor on 28.07.2017.
 */
public class CharWeapon {
    private String WeaponName = "Без оружия";
    private boolean LongRange = false;
    private boolean AmpDamageType = false; //true - amp, false - prm
    private String DamageAttr = "cr";
    private int Reach = 0; //0 - ближний бой
    private int MinST = 0;
    private String RelatedSkill;
    private int dmg = -1;

    public CharWeapon(String weaponName, boolean ampDamageType, int dmg, String relatedSkill) {
        this.WeaponName = weaponName;
        this.AmpDamageType = ampDamageType;
        this.dmg = dmg;
        this.RelatedSkill = relatedSkill;
    }

    public CharWeapon() {
        String WeaponName = "Без оружия";
        boolean LongRange = false;
        boolean AmpDamageType = false; //true - amp, false - prm
        String DamageAttr = "cr";
        int Reach = 0; //0 - ближний бой
        int MinST = 0;
        int dmg = -1;
    }

    public String getRelatedSkill() {
        return RelatedSkill;
    }

    public void setRelatedSkill(String relatedSkill) {
        RelatedSkill = relatedSkill;
    }

    public boolean isAmpDamageType() {
        return AmpDamageType;
    }

    public int getDmg() {
        return dmg;
    }
}
