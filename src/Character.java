import java.util.ArrayList;
import java.util.Random;


public class Character {
    Random rand = new Random();

    private boolean Alive = true;
    private String CharName = "Безымянный";
    private int MaxHT = 10;
    private int MaxDX = 10;
    private int MaxST = 10;
    private int MaxIQ = 10;
    private int MaxHP = 10;
    private int MaxPer = MaxIQ;
    private int MaxWill = MaxIQ;
    private double MaxBS = ((double) MaxHP + (double) MaxDX) / 4.0;
    private int MaxMove = (int) MaxBS;

    private int CurrHT = MaxHT;
    private int CurrDX = MaxDX;
    private int CurrST = MaxST;
    private int CurrIQ = MaxIQ;
    private int CurrHP = MaxHP;
    private int CurrPer = MaxPer;
    private int CurrWill = MaxWill;
    private double CurrBS = MaxBS;
    private int CurrMove = (int) CurrBS;

    private int CorX = 0;
    private int CorY = 0;

    private int thrustDmgRollValue;
    private int thrustDmgModifierValue;
    private int swingDmgRollValue;
    private int swingDmgModifierValue;

    private boolean LowHPDebuffed = false;

    private boolean AllOutDefenceWasOn = false;
    private boolean AllOutAttackWasOn = false;

    private ArrayList<CharArmor> ArmorList;
    private CharWeapon Weapon = new CharWeapon();
    private CharShield Shield = new CharShield();
    private ArrayList<CharSkill> SkillList;
//    private ArrayList<String> AdvList new ArrayList<;

    private int DeathSaver = 1;


    boolean Roll3d6(int Difficulty) {
        int firstroll = rand.nextInt(6) + 1;
        int secondroll = rand.nextInt(6) + 1;
        int thirdroll = rand.nextInt(6) + 1;
        int rollresult = firstroll + secondroll + thirdroll;

        System.out.println(CharName + " rolls: " + firstroll +
                " + " + secondroll + " + " + thirdroll + " = " +
                rollresult + " vs " + Difficulty);

        if (rollresult <= 3) {
            System.out.println("Critical Success!");
            return true;
        } else if (rollresult <= Difficulty) {
            System.out.println("Success");
            return true;
        } else if (rollresult > Difficulty) {
            System.out.println("Fail");
            return false;
        }

        return false;

    }

    public int[] QuickContest(int AttrOwn, int AttrAnother) {
        int firstroll = rand.nextInt(6) + 1;
        int secondroll = rand.nextInt(6) + 1;
        int thirdroll = rand.nextInt(6) + 1;
        int RollResultFirst = firstroll + secondroll + thirdroll - AttrOwn;


        firstroll = rand.nextInt(6) + 1;
        secondroll = rand.nextInt(6) + 1;
        thirdroll = rand.nextInt(6) + 1;
        int RollResultSecond = firstroll + secondroll + thirdroll - AttrAnother;

        int RollsResult[] = new int[2];

        RollsResult[0] = RollResultFirst;
        RollsResult[1] = RollResultSecond;

        return RollsResult;

    }

    public void Damage(Character _char, String Zone) {

        int DamageSum = 0;

        if (this.Weapon.isAmpDamageType()) {
            for (int i = 0; i < swingDmgRollValue; i++) {
                DamageSum += rand.nextInt(5) + 1;
            }

            System.out.println(this.CharName + " damages " + _char.CharName + " for " + DamageSum);
            DamageSum += swingDmgModifierValue + this.Weapon.getDmg()
                    - _char.getArmor(_char.getArmorList(), Zone);
        } else {
            for (int i = 0; i < thrustDmgRollValue; i++) {
                DamageSum += rand.nextInt(5) + 1;
            }

            System.out.println(this.CharName + " damages " + _char.CharName + " for " + DamageSum);

            DamageSum += thrustDmgModifierValue + this.Weapon.getDmg()
                    - _char.getArmor(_char.getArmorList(), Zone);

        }

        if (DamageSum <= 0) {
            System.out.println(_char.CharName + " blocks all damage with " +
                    _char.getArmorName(_char.getArmorList(), Zone));
            return;
        }


        System.out.println(_char.CharName + " losing " + DamageSum + " HP!");
        _char.setCurrHP(_char.getCurrHP() - DamageSum);

        if (!_char.LowHPDebuffed) _char.LowHPDebuffCheck();

        _char.DeathCheck();
    }

    public void StrongDamage(Character _char, String Zone) {
        int DamageSum = 0;

        if (swingDmgRollValue > 2) DamageSum += swingDmgRollValue;

        if (this.Weapon.isAmpDamageType()) {
            for (int i = 0; i < swingDmgRollValue; i++) {
                DamageSum += rand.nextInt(5) + 1;
            }
            DamageSum += swingDmgModifierValue + this.Weapon.getDmg()
                    - _char.getArmor(getArmorList(), Zone);
        } else {
            for (int i = 0; i < thrustDmgRollValue; i++) {
                DamageSum += rand.nextInt(5) + 1;
            }
            DamageSum += thrustDmgModifierValue + this.Weapon.getDmg()
                    - _char.getArmor(getArmorList(), Zone);

        }

        if (DamageSum <= 0) {
            System.out.println(_char.CharName + "blocks all damage with " +
                    _char.getArmorName(getArmorList(), Zone));
            return;
        }


        System.out.println(_char.CharName + " losing " + DamageSum + " HP!");
        _char.setCurrHP(getCurrHP() - DamageSum);

        if (!_char.LowHPDebuffed) _char.LowHPDebuffCheck();

        _char.DeathCheck();
        return;

    }

    Character(int _HT, int _DX, int _ST, int _IQ, String _CharName) {
        this.MaxHT = _HT;
        this.MaxDX = _DX;
        this.MaxST = _ST;
        this.MaxIQ = _IQ;
        this.MaxHP = _HT;
        this.MaxWill = this.MaxIQ;
        this.MaxBS = ((double) this.MaxHP + (double) this.MaxDX) / 4.0;
        this.MaxMove = (int) this.MaxBS;
        this.CurrWill = this.MaxWill;
        this.CurrBS = this.MaxBS;
        this.CurrMove = (int) this.CurrBS;
        this.CurrHT = MaxHT;
        this.CurrDX = MaxDX;
        this.CurrST = MaxST;
        this.CurrIQ = MaxIQ;
        this.CurrHP = MaxHP;
        this.CurrPer = MaxPer;
        this.CorX = 0;
        this.CorY = 0;
        this.CharName = _CharName;

        this.StrengthToDamage();

        CharSkill SabreSkill = new CharSkill("SabreSkill", "A", "DX", 16);
        CharSkill ShieldSkill = new CharSkill("Shield", "A", "DX", 16);

        this.SkillList = new ArrayList<CharSkill>();

        this.SkillList.add(SabreSkill);
        this.SkillList.add(ShieldSkill);

        this.ArmorList = new ArrayList<CharArmor>();

        ArmorList.add(new CharArmor("Без брони", "Skull", 0));
        // ArmorList.add(new CharArmor("Без брони", "Torso", 0));
        //  ArmorList.add(new CharArmor("Без брони", "Groin", 0));
        ArmorList.add(new CharArmor("Без брони", "Arms", 0));
        ArmorList.add(new CharArmor("Без брони", "Legs", 0));



    }

    boolean AttackChoice(String Zone, Character _char) {

        if (AllOutDefenceWasOn) {
            System.out.println(this.CharName + " can't attack now.");
            AllOutDefenceWasOn = false;
            return false;
        }

        int AttackChoice = rand.nextInt(4);

        switch (AttackChoice) {
            case 0:
                return NormalAttack("Torso", _char);
            case 1:
                return AllOutAttackDetermined("Torso", _char);
            case 2: {
                if (AllOutAttackDouble("Torso", "Torso", _char) == 0) return false;
                else return true;
            }
            case 3:
                return AllOutAttackFeint("Torso", _char);
            case 4:
                return AllOutAttackStrong("Torso", _char);
        }
        return NormalAttack("Torso", _char);
    }

    boolean NormalAttack(String Zone, Character _char) {
        if (AllOutDefenceWasOn) {
            System.out.println(this.CharName + " can't attack now.");
            AllOutDefenceWasOn = false;
            return false;
        }

        System.out.println(this.CharName + " trying to attack "
                + _char.CharName + " in " + Zone);
        int ZoneModifier = 0;
        if (Zone.equals("Torso")) ZoneModifier = 0;
        else if (Zone.equals("Skull")) ZoneModifier = -7;
        else if (Zone.equals("Leg") || Zone.equals("Arm")) ZoneModifier = -2;

        if (Roll3d6(getSkill(getSkillList(), getWeapon().getRelatedSkill()))) {

            if (_char.DefenceChoice(0)) return false;
            else {
                Damage(_char, Zone);
                return true;
            }
        } else {
            System.out.println(this.CharName + " missed (lox)");
            return false;
        }
    }

    boolean AllOutAttackDetermined(String Zone, Character _char) {
        if (AllOutDefenceWasOn) {
            System.out.println(this.CharName + " can't attack now.");
            AllOutDefenceWasOn = false;
            return false;
        }

        this.AllOutAttackWasOn = true;

        System.out.println(this.CharName + " attack with Determination!");

        System.out.println(this.CharName + " trying to attack "
                + _char.CharName + " in " + Zone);
        int ZoneModifier = 0;
        if (Zone.equals("Torso")) ZoneModifier = 0;
        else if (Zone.equals("Skull")) ZoneModifier = -7;
        else if (Zone.equals("Leg") || Zone.equals("Arm")) ZoneModifier = -2;

        if (Roll3d6(getSkill(getSkillList(), getWeapon().getRelatedSkill()) + 4)) {

            if (_char.DefenceChoice(0)) return false;
            else {
                Damage(_char, Zone);
                return true;
            }
        } else {
            System.out.println(this.CharName + " missed (lox)");
            return false;
        }
    }

    int AllOutAttackDouble(String Zone, String Zone2, Character _char) {
        this.AllOutAttackWasOn = true;
        boolean FirstAttackSucc = false;
        boolean SecondAttackSucc = false;


        if (AllOutDefenceWasOn) {
            System.out.println(this.CharName + " can't attack now.");
            AllOutDefenceWasOn = false;
            return 0;
        }

        System.out.println("Double attack!");


        System.out.println(this.CharName + " trying to attack "
                + _char.CharName + " in " + Zone);
        int ZoneModifier = 0;
        if (Zone.equals("Torso")) ZoneModifier = 0;
        else if (Zone.equals("Skull")) ZoneModifier = -7;
        else if (Zone.equals("Leg") || Zone.equals("Arm")) ZoneModifier = -2;

        if (Roll3d6(getSkill(getSkillList(), getWeapon().getRelatedSkill()))) {

            if (_char.DefenceChoice(0)) FirstAttackSucc = false;
            else {
                Damage(_char, Zone);
                FirstAttackSucc = true;
            }
        } else {
            System.out.println(this.CharName + " missed (lox)");
            FirstAttackSucc = false;
        }


        //==============================

        System.out.println(this.CharName + " trying to attack "
                + _char.CharName + " in " + Zone);
        ZoneModifier = 0;
        if (Zone.equals("Torso")) ZoneModifier = 0;
        else if (Zone.equals("Skull")) ZoneModifier = -7;
        else if (Zone.equals("Leg") || Zone.equals("Arm")) ZoneModifier = -2;

        if (Roll3d6(getSkill(getSkillList(), getWeapon().getRelatedSkill()))) {

            if (_char.DefenceChoice(0)) SecondAttackSucc = false;
            else {
                _char.Damage(_char, Zone);
                SecondAttackSucc = true;
            }
        } else {
            System.out.println(this.CharName + " missed (lox)");
            SecondAttackSucc = false;
        }

        if (FirstAttackSucc && SecondAttackSucc) return 1;
        else if (FirstAttackSucc || SecondAttackSucc) return 1;
        else return 0;
    }

    private void StrengthToDamage(){

        switch (this.MaxST) {
            case 1: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -6;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = -5;
                break;
            }
            case 2: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -6;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = -5;
                break;
            }
            case 3: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -5;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = -4;
                break;
            }
            case 4: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -5;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = -4;
                break;
            }
            case 5: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -4;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = -3;
                break;
            }
            case 6: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -4;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = -3;
                break;
            }
            case 7: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -3;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = -2;
                break;
            }
            case 8: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -3;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = -2;
                break;
            }
            case 9: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -2;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = -1;
                break;
            }
            case 10: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -2;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = 0;
                break;
            }
            case 11: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -1;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = 1;
                break;
            }
            case 12: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = -1;
                this.swingDmgRollValue = 1;
                this.swingDmgModifierValue = 2;
                break;
            }
            case 13: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = 0;
                this.swingDmgRollValue = 2;
                this.swingDmgModifierValue = -1;
                break;
            }
            case 14: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = 0;
                this.swingDmgRollValue = 2;
                this.swingDmgModifierValue = 0;
                break;
            }
            case 15: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = 1;
                this.swingDmgRollValue = 2;
                this.swingDmgModifierValue = 1;
                break;
            }
            case 16: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = 1;
                this.swingDmgRollValue = 2;
                this.swingDmgModifierValue = 2;
                break;
            }
            case 17: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = 2;
                this.swingDmgRollValue = 3;
                this.swingDmgModifierValue = -1;
                break;
            }
            case 18: {
                this.thrustDmgRollValue = 1;
                this.thrustDmgModifierValue = 2;
                this.swingDmgRollValue = 3;
                this.swingDmgModifierValue = 0;
                break;
            }
            case 19: {
                this.thrustDmgRollValue = 2;
                this.thrustDmgModifierValue = -1;
                this.swingDmgRollValue = 3;
                this.swingDmgModifierValue = 1;
                break;
            }
            case 20: {
                this.thrustDmgRollValue = 2;
                this.thrustDmgModifierValue = -1;
                this.swingDmgRollValue = 3;
                this.swingDmgModifierValue = 2;
                break;
            }


        }

    }

    boolean AllOutAttackFeint(String Zone, Character _char) {
        int FeintHelper[] = Feint(_char);
        if (FeintHelper[0] == 0) return false;
        else
            System.out.println(this.CharName + " trying to attack with Feint bonus (-"
                    + FeintHelper[1] + ")" + _char.CharName + " in " + Zone);
        int ZoneModifier = 0;
        if (Zone.equals("Torso")) ZoneModifier = 0;
        else if (Zone.equals("Skull")) ZoneModifier = -7;
        else if (Zone.equals("Leg") || Zone.equals("Arm")) ZoneModifier = -2;

        if (Roll3d6(getSkill(getSkillList(), getWeapon().getRelatedSkill()))) {

            if (_char.DefenceChoice(FeintHelper[1])) return false;
            else {
                Damage(_char, Zone);
                return true;
            }
        } else {
            System.out.println(this.CharName + " missed (lox)");
            return false;
        }

    }

    int[] Feint(Character _char) {
        int FeintArray[] = new int[2];
        int SuccCheck[] = QuickContest(this.getSkill(this.SkillList, this.getWeapon().getRelatedSkill()),
                _char.getSkill(_char.SkillList, _char.getWeapon().getRelatedSkill()));

        if (SuccCheck[0] >= SuccCheck[1]) {
            FeintArray[0] = 0;
            return FeintArray;
        } //defeat
        else if (SuccCheck[0] < SuccCheck[1]) {
            FeintArray[0] = 1;
            FeintArray[1] = Math.abs(SuccCheck[0] - SuccCheck[1]);
            return FeintArray;
        }

        return FeintArray;
    }

    boolean AllOutAttackStrong(String Zone, Character _char) {

         if (AllOutDefenceWasOn) {
            System.out.println(this.CharName + " can't attack now.");
            AllOutDefenceWasOn = false;
            return false;
        }

        System.out.println(this.CharName + " trying to attack stronger!");

        System.out.println(this.CharName + " trying to attack "
                + _char.CharName + " in " + Zone);
        int ZoneModifier = 0;
        if (Zone.equals("Torso")) ZoneModifier = 0;
        else if (Zone.equals("Skull")) ZoneModifier = -7;
        else if (Zone.equals("Leg") || Zone.equals("Arm")) ZoneModifier = -2;

        if (Roll3d6(getSkill(getSkillList(), getWeapon().getRelatedSkill()))) {

            if (_char.DefenceChoice(0)) return false;
            else {
                StrongDamage(_char, Zone);
                return true;
            }
        } else {
            System.out.println(this.CharName + " missed (lox)");
            return false;
        }
    }

    boolean DefenceChoice(int Debuff) {

        if (AllOutAttackWasOn) {
            System.out.println(this.CharName + " can't defend himself yet.");
            AllOutAttackWasOn = false;
            return false;
        }

        int DefChoice = rand.nextInt(2);

        switch (DefChoice) {
            case 0:
                return NormalDef(Debuff);
            case 1:
                return AllOutDefImproved(Debuff);
            case 2:
                return AllOutDefDouble(Debuff);
        }

        return false;
    }

    boolean NormalDef(int Debuff) {


        if (AllOutAttackWasOn) {
            System.out.println(this.CharName + " can't defend himself yet.");
            AllOutAttackWasOn = false;
            return false;
        }

        System.out.println(this.CharName + " current HP is " + this.CurrHP);

        String DefenceType;

        int DefChoice = rand.nextInt(5);

        if (DefChoice == 0) {

        }

        if (rand.nextInt(2) + 1 == 1) DefenceType = "Dodge";
        else if (rand.nextInt(2) + 1 == 2) DefenceType = "Parry";
        else DefenceType = "Block";


        if (DefenceType.equals("Dodge")) {
            System.out.println(CharName + " trying to dodge");
            if (Roll3d6((int) CurrBS + 3)) {
                System.out.println(this.CharName + " dodged");
                return true;
            } else return false;
        } else if (DefenceType.equals("Parry")) {
            System.out.println(CharName + " trying to parry");
            if (Roll3d6(((int) (double) getSkill(getSkillList(), getWeapon().getRelatedSkill()) / 4) + 3)) {
                System.out.println(this.CharName + " parried");
                return true;
            } else return false;
        } else if (DefenceType.equals("Block")) {
            System.out.println(CharName + " trying to block");
            if (Roll3d6(((int) (double) getSkill(getSkillList(), "Shield") / 4) + 3 + Shield.getDB())) {
                System.out.println(this.CharName + " blocked");
                return true;
            } else return false;
        }

        return false;
    }

    boolean AllOutDefImproved(int Debuff) {
        if (this.AllOutAttackWasOn) {
            System.out.println(this.CharName + " can't defend himself yet.");
            this.AllOutAttackWasOn = false;
            return false;
        }

        this.AllOutDefenceWasOn = true;

        System.out.println(this.CharName + " current HP is " + this.CurrHP);

        System.out.println(this.CharName + " trying to do improved defence");

        String DefenceType;

        int DefChoice = rand.nextInt(5);

        if (DefChoice == 0) {

        }

        if (rand.nextInt(2) + 1 == 1) DefenceType = "Dodge";
        else if (rand.nextInt(2) + 1 == 2) DefenceType = "Parry";
        else DefenceType = "Block";


        if (DefenceType.equals("Dodge")) {
            System.out.println(CharName + " trying to dodge");
            if (Roll3d6((int) CurrBS + 3 + 2)) {
                System.out.println(this.CharName + " dodged");
                return true;
            } else return false;
        } else if (DefenceType.equals("Parry")) {
            System.out.println(CharName + " trying to parry");
            if (Roll3d6(((int) (double) getSkill(getSkillList(), getWeapon().getRelatedSkill()) / 4) + 3 + 2)) {
                System.out.println(this.CharName + " parried");
                return true;
            } else return false;
        } else if (DefenceType.equals("Block")) {
            System.out.println(CharName + " trying to block");
            if (Roll3d6(((int) (double) getSkill(getSkillList(), "Shield") / 4) + 3 + Shield.getDB() + 2)) {
                System.out.println(this.CharName + " blocked");
                return true;
            } else return false;
        }

        return false;
    }

    boolean AllOutDefDouble(int Debuff) {
        if (AllOutAttackWasOn) {
            System.out.println(this.CharName + " can't defend himself yet.");
            AllOutAttackWasOn = false;
            return false;
        }

        System.out.println(this.CharName + " current HP is " + this.CurrHP);

        System.out.println(this.CharName + "trying to do double defence");

        String DefenceType;

        int DefChoice = rand.nextInt(5);

        if (rand.nextInt(2) + 1 == 1) DefenceType = "Dodge";
        else if (rand.nextInt(2) + 1 == 2) DefenceType = "Parry";
        else DefenceType = "Block";


        if (DefenceType.equals("Dodge")) {
            System.out.println(CharName + " trying to dodge");
            if (Roll3d6((int) CurrBS + 3)) {
                System.out.println(this.CharName + " dodged");
                return true;
            } else return false;
        } else if (DefenceType.equals("Parry")) {
            System.out.println(CharName + " trying to parry");
            if (Roll3d6(((int) (double) getSkill(getSkillList(), getWeapon().getRelatedSkill()) / 4) + 3)) {
                System.out.println(this.CharName + " parried");
                return true;
            } else return false;
        } else if (DefenceType.equals("Block")) {
            System.out.println(CharName + " trying to block");
            if (Roll3d6(((int) (double) getSkill(getSkillList(), "Shield") / 4) + 3 + Shield.getDB())) {
                System.out.println(this.CharName + " blocked");
                return true;
            } else return false;
        }

        System.out.println(this.CharName + " current HP is " + this.CurrHP);


        if (rand.nextInt(2) + 1 == 1) DefenceType = "Dodge";
        else if (rand.nextInt(2) + 1 == 2) DefenceType = "Parry";
        else DefenceType = "Block";


        if (DefenceType.equals("Dodge")) {
            System.out.println(CharName + " trying to dodge");
            if (Roll3d6((int) CurrBS + 3)) {
                System.out.println(this.CharName + " dodged");
                return true;
            } else return false;
        } else if (DefenceType.equals("Parry")) {
            System.out.println(CharName + " trying to parry");
            if (Roll3d6(((int) (double) getSkill(getSkillList(), getWeapon().getRelatedSkill()) / 4) + 3)) {
                System.out.println(this.CharName + " parried");
                return true;
            } else return false;
        } else if (DefenceType.equals("Block")) {
            System.out.println(CharName + " trying to block");
            if (Roll3d6(((int) (double) getSkill(getSkillList(), "Shield") / 4) + 3 + Shield.getDB())) {
                System.out.println(this.CharName + " blocked");
                return true;
            } else return false;
        }

        return false;
    }

    void DeathCheck() {

        if (this.getDeathSaver() == 5) {
            System.out.println(this.CharName + " HP is at -5*HP level. Instant Death!");
            this.Alive = false;
            return;
        }

        if (CurrHP <= this.getDeathSaver() * (0 - MaxHP))
            if (Roll3d6(CurrHT)) {
                System.out.println(this.CharName + " at death's door!");
                this.setDeathSaver(this.getDeathSaver()+1);
                return;
            } else {
                System.out.println(this.CharName + " dies!");
                this.Alive = false;
            }
    }

    void LowHPDebuffCheck() {
        if ((double) this.CurrHP <= (double) MaxHP / 3) {
            LowHPDebuffed = true;
            CurrBS = CurrBS / 2;
            System.out.println(this.CharName + " is at low HP, his BS is decreased by half\n");
        }

    }



    //=================Getters And Setters


    public int getMaxHT() {
        return MaxHT;
    }

    public int getMaxDX() {
        return MaxDX;
    }

    public void setMaxDX(int maxDX) {
        MaxDX = maxDX;
    }

    public int getMaxST() {
        return MaxST;
    }

    public void setMaxST(int maxST) {
        MaxST = maxST;
        this.StrengthToDamage();
    }

    public int getMaxIQ() {
        return MaxIQ;
    }

    public void setMaxIQ(int maxIQ) {
        MaxIQ = maxIQ;
    }

    public int getMaxHP() {
        return MaxHP;
    }

    public void setMaxHP(int maxHP) {
        MaxHP = maxHP;
    }

    public int getMaxPer() {
        return MaxPer;
    }

    public void setMaxPer(int maxPer) {
        MaxPer = maxPer;
    }

    public int getMaxWill() {
        return MaxWill;
    }

    public void setMaxWill(int maxWill) {
        MaxWill = maxWill;
    }

    public double getMaxBS() {
        return MaxBS;
    }

    public void setMaxBS(double maxBS) {
        MaxBS = maxBS;
    }

    public int getMaxMove() {
        return MaxMove;
    }

    public void setMaxMove(int maxMove) {
        MaxMove = maxMove;
    }

    public int getCurrHT() {
        return CurrHT;
    }

    public void setCurrHT(int currHT) {
        CurrHT = currHT;
    }

    public int getCurrDX() {
        return CurrDX;
    }

    public void setCurrDX(int currDX) {
        CurrDX = currDX;
    }

    public int getCurrST() {
        return CurrST;
    }

    public void setCurrST(int currST) {
        CurrST = currST;
        this.StrengthToDamage();
    }

    public int getCurrIQ() {
        return CurrIQ;
    }

    public void setCurrIQ(int currIQ) {
        CurrIQ = currIQ;
    }

    public int getCurrPer() {
        return CurrPer;
    }

    public void setCurrPer(int currPer) {
        CurrPer = currPer;
    }

    public int getCurrWill() {
        return CurrWill;
    }

    public void setCurrWill(int currWill) {
        CurrWill = currWill;
    }

    public double getCurrBS() {
        return CurrBS;
    }

    public void setCurrBS(double currBS) {
        CurrBS = currBS;
    }

    public int getCurrMove() {
        return CurrMove;
    }

    public void setCurrMove(int currMove) {
        CurrMove = currMove;
    }

    public CharWeapon getWeapon() {
        return Weapon;
    }

    public void AddArmor(CharArmor armor) {
        ArmorList.add(armor);

    }

    public void AddSkill(CharSkill skill) {
        SkillList.add(skill);

    }

    public int getSkill(ArrayList<CharSkill> skillList, String skill) {

        for (CharSkill S :
                SkillList) {
            if (S.getSkillName().equals(skill))
                switch (S.getMainAttribute()) {
                    case "DX":
                        return CurrDX + S.getLvl();
                    case "ST":
                        return CurrST + S.getLvl();
                    case "IQ":
                        return CurrIQ + S.getLvl();
                    case "HT":
                        return CurrHT + S.getLvl();
                    case "Per":
                        return CurrPer + S.getLvl();
                }
        }

        return getCurrDX();
    }

    public ArrayList<CharSkill> getSkillList() {
        return SkillList;
    }

    public void setWeapon(CharWeapon weapon) {
        Weapon = weapon;
        return;

    }

    public void setShield(CharShield shield) {
        Shield = shield;
    }

    public ArrayList<CharArmor> getArmorList() {
        return ArmorList;
    }

    public void setCurrHP(int currHP) {
        CurrHP = currHP;
    }

    public void setMaxHT(int maxHT) {
        MaxHT = maxHT;
    }

    public int getCurrHP() {
        return CurrHP;
    }

    public int getArmor(ArrayList<CharArmor> ArmorList, String _zone) {
        for (CharArmor R :
                ArmorList) {
            if (R.getZone().equals(_zone)) return R.getDR();
        }
        return 0;
    }

    public boolean isAlive() {
        return Alive;
    }

    String getArmorName(ArrayList<CharArmor> ArmorList, String _zone) {
        for (CharArmor R :
                ArmorList) {
            if (R.getZone().equals(_zone)) return R.getArmorName();
        }
        return null;
    }

    public int getDeathSaver() {
        return DeathSaver;
    }

    public void setDeathSaver(int deathSaver) {
        DeathSaver = deathSaver;
    }
}
