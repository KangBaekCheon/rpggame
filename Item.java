package RpgGame;

public class Item {
    String name;	
	int Maxhp;			
	int Maxmp;			
	int att;		
	int def;

	Item(String name, int maxhp, int maxmp, int att, int def) {
		this.name = name;
		this.Maxhp = maxhp;
		this.Maxmp = maxmp;
		this.att = att;
		this.def = def;
	}

	public String toString(){   
		String info = name + " :";
		if(0 < Maxhp) info += " 체력+" + Maxhp;
		if(0 < Maxmp) info += " 마나+" + Maxmp;
		if(0 < att) info += " 공격+" + att;
		if(0 < def) info += " 방어+" + def;
		return info;
	}
}
