package RpgGame;

public class Monster {
    String name;
    int hp,mp,att,def,exp;

    public Monster(){}

    public Monster(String name,int hp, int mp, int att, int def, int exp) {
        this.name = name;        
        this.hp = hp;
        this.mp = mp;
        this.att = att;
        this.def = def;
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void attack(Unit u,int a){
        int damage=0;	
		if(a == 1){
			damage = att - u.def;
		}else if(a == 2){
			damage = (att+mp) - u.def;
			mp=mp-100;
		}
		damage = damage <= 0 ? 1 : damage;
		u.hp = u.hp < damage ? u.hp - u.hp : u.hp - damage;
        System.out.println(name + "이 공격 " + u.name + "에게 " + damage + "만큼 데미지를 주었습니다.");
        System.out.println(u.name + "의 현재 HP : "+ u.hp);				
	}
}
