/**
 * Created by Floglor on 04.08.2017.
 */
public class CharSkill {
    private String SkillName;
    private String Difficulty;
    private String MainAttribute;
    private int CharacterPoints;
    private int Lvl;


    public CharSkill(String skillName, String difficulty, String mainAttribute, int characterPoints) {
        this.SkillName = skillName;
        this.Difficulty = difficulty;
        this.CharacterPoints = characterPoints;
        this.MainAttribute = mainAttribute;

        if (difficulty.equals("E")) {
            if (characterPoints == 0) Lvl = -1;
            else
                switch (characterPoints) {
                    case 1: {
                        Lvl = 0;
                        break;
                    }
                    case 2: {
                        Lvl = 1;
                        break;
                    }
                    default: {
                        Lvl = (characterPoints / 4) + 1;
                        break;
                    }
                }
        } else if (difficulty.equals("A")) {
            if (characterPoints == 0) Lvl = -2;
            else
                switch (characterPoints) {
                    case 1: {
                        Lvl = -1;
                        break;
                    }
                    case 2: {
                        Lvl = 0;
                        break;
                    }
                    default: {
                        Lvl = (characterPoints / 4);
                        break;
                    }
                }
        } else if (difficulty.equals("H")) {
            if (characterPoints == 0) Lvl = -3;
            else
                switch (characterPoints) {
                    case 1: {
                        Lvl = -2;
                        break;
                    }
                    case 2: {
                        Lvl = -1;
                        break;
                    }
                    default: {
                        Lvl = (characterPoints / 4) - 1;
                        break;
                    }
                }

        }
        else if (difficulty.equals("VH")) {
            if (characterPoints == 0) Lvl = -4;
            else
                switch (characterPoints) {
                    case 1: {
                        Lvl = -3;
                        break;
                    }
                    case 2: {
                        Lvl = -2;
                        break;
                    }
                    default: {
                        Lvl = (characterPoints / 4) - 2;
                        break;
                    }
                }

        }

    }

    public String getSkillName() {
        return SkillName;
    }

    public void setSkillName(String skillName) {
        SkillName = skillName;
    }

    public String getDifficulty() {
        return Difficulty;
    }

    public void setDifficulty(String difficulty) {
        Difficulty = difficulty;
    }

    public int getCharacterPoints() {
        return CharacterPoints;
    }

    public void setCharacterPoints(int characterPoints) {
        CharacterPoints = characterPoints;
    }

    public int getLvl() {
        return Lvl;
    }

    public void setLvl(int lvl) {
        Lvl = lvl;
    }

    public String getMainAttribute() {
        return MainAttribute;
    }

    public void setMainAttribute(String mainAttribute) {
        MainAttribute = mainAttribute;
    }
}
