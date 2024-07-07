package RpgGame;

public class Unit {    
    String name,jop;
	int maxHp,maxMp,hp,mp,att,def,level,exp,temp,exp2;
	Item[] items;
	
	public Unit(){}
	public Unit(String name, String jop, int hp, int mp, int att, int def) {
            this.name = name;
            this.jop = jop;
            this.maxHp = hp;
            this.maxMp = mp;
            this.hp = this.maxHp;
            this.mp = this.maxMp;
            this.att = att;
            this.def = def;
            this.level = 1;
            this.exp = 0;
            this.items = new Item[10];
    }    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getJop() {
		return jop;
	}
	public void setJop(String jop) {
		this.jop = jop;
	}

	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public int getMaxMp() {
		return maxMp;
	}
	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}
	
	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
	
	public int getExp(){
		return exp;
	}

    public void setExp(int exp){		
		System.out.println(exp + " 의 경험치를 획득하였습니다. 현재 레벨은 " + level + "입니다.");
		this.exp += exp;
		while(exp2()<=this.exp){
			levelUp();
			this.exp = 0;
		}
	}
	
	public void attack(Monster m,int a){
		int damage=0;	
		if(a == 1){
			damage = att - m.def;
		}else if(a == 2){
			damage = (att+maxMp) - m.def;
			mp=mp-100;
		}		  
		damage = damage <= 0 ? 1 : damage;
		m.hp = m.hp < damage ? m.hp - m.hp : m.hp - damage;
		System.out.println(name + "이 공격 " + m.name + "에게 " + damage + "만큼 데미지를 주었습니다.");
		System.out.println(m.name + "의 현재 HP : "+ m.hp);
				
	}

	public int exp2(){
		
		if(level <= 5){
			this.exp2 = 50;
		}else if(level >= 6 && level <= 9){
			this.exp2 = 100;					
		}else if(level >= 10 && level <= 49){
			this.exp2 = 200;
		}else if(level >= 50 && level <= 69){
			this.exp2 = 400;
		}else {
			this.exp2 = 700;
		}
		return exp2;		
	}
	
    public void levelUp(){
		if(jop.equals("Novice")){
			if(level < 11){
				level++;		
				maxHp += 10;
				maxMp += 5;
				att += 2;
				def += 2;
				hp = maxHp;
				mp = maxMp;
				System.out.println("LEVEL UP!!");
			}else{System.out.println("Novice는 최대 10렙까지 입니다 마을로 돌아가 전문 직업으로 전직 하세요\n");}
		}else if(jop.equals("Knigth")){
			level++;		
			maxHp += 15;
			maxMp += 10;
			att += 3;
			def += 5;
			hp = maxHp;
			mp = maxMp;
			System.out.println("LEVEL UP!!");
		}else if(jop.equals("Wizard") ){
			level++;		
			maxHp += 10;
			maxMp += 15;
			att += 5;
			def += 3;
			hp = maxHp;
			mp = maxMp;
			System.out.println("LEVEL UP!!");
		}
	}

	public void showInfo(){
		System.out.println("------------- 내정보 ---------------");
		System.out.println("이름 : "+ name);
        System.out.println("직업 : " + jop);
		System.out.println("레벨 : "+ level + "("+ exp + " / " + exp2() +")");
		System.out.println("체력 : "+ hp + "/" + maxHp);
		System.out.println("마나 : "+ mp + "/" + maxMp);
		System.out.println("공격 : "+ att);
		System.out.println("방어 : "+ def);
		System.out.println("강화석 : " + temp);
		System.out.println("------------ 아이템 --------------");
		for(int i = 0; i < items.length; i++){
			if(items[i] != null){
				System.out.println(items[i]);
			}
		}
		System.out.println("================================");
	}

    public void setItem(Item item){
		System.out.println(item.name + "을 획득하였습니다.");
		for(int i = 0; i < items.length; i++){
			if(items[i]==null){
				items[i] = item;
				break;
			}
		}
		maxHp += item.Maxhp;
		maxMp += item.Maxmp;
		att += item.att;
		def += item.def;
	}

}
