/**
 * Created by Floglor on 28.07.2017.
 */
public class CharShield {
    private String ShieldName = "Без щита";
    private int DB = 0;

    public CharShield(String shieldName, int DB) {
        ShieldName = shieldName;
        this.DB = DB;
    }
    public CharShield() {
        ShieldName = "Basic Shield+1";
        this.DB = 1;
    }

    public int getDB() {
        return DB;
    }
}
