package RpgGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Item[] items = new Item[3];
    private static Unit u = new Unit();
    private static int choice;
    private static int Weapon,Armor,Accessory;
    private static int WeaponUpgrade,ArmorUpgrade,AccessoryUpgrade;
    private static File file = new File("RpgGame.txt");

    public static void main(String[] args) throws Exception {
        
        Start();        

        while (true) {
            if(u.jop.equals("Novice") && u.level >= 10){
                System.out.println();
                System.out.println("Novice 10렙이상 달성 전직이 가능합니다.");
                System.out.println("1. 나이트 2. 위자드");
                int choice = sc.nextInt();
                if(choice == 1){
                    u.jop = "Knigth"; u.maxHp = u.maxHp + 200; u.maxMp = u.maxMp + 100; u.att = u.att + 50; u.def = u.def + 60;
                }else if(choice == 2){
                    u.jop = "Wizard"; u.maxHp = u.maxHp + 100; u.maxMp = u.maxMp + 200; u.att = u.att + 60; u.def = u.def + 40;
                }else{
                    System.out.println("잘못 입력했습니다.");
                }
            }

            System.out.println("1. 내 정보");
            System.out.println( "2. 휴식 하기");
            System.out.println("3. 던전으로");
            System.out.println("4. 상점으로");
            System.out.println("5. 저장하기");
            System.out.println("6. 불러오기");
            System.out.println("0. 게임 종료");
            System.out.print("실행할 번호를 입력하세요> ");            
            choice = sc.nextInt();

            if(choice == 1){
                u.showInfo();
            }else if(choice == 2){
                System.out.println("체력이 회복되었습니다.");
                u.hp = u.maxHp;
                u.mp = u.maxMp;
            }else if(choice == 3){
                hunt();
            }else if(choice == 4){
                shop();
            }else if(choice == 5){                
                dataSave();				
            }else if(choice == 6){
                dataLoad();
            }else if(choice == 0){
                
                System.out.println("게임을 종료합니다.");
                sc.close();
                System.exit(0);
            }else{
                System.out.println("잘못 선택했습니다.");
            }
        }
    }



    private static void Start(){
        
        System.out.println("기본 클래스 Novice로시작하며 10렙 달성시 전직 가능합니다.");
        System.out.println("영웅의 이름을 입력하세요");
        u.setName(sc.next());
        u = new Unit(u.getName(), "Novice", 100, 50 , 30, 10);

        items[0] = new Item("단검", 0, 0, 10, 0);
        items[1] = new Item("면티", 0, 0, 0, 10);        
        items[2] = new Item("반지", 0, 0, 0, 0);
        u.setItem(items[0]);
        u.setItem(items[1]);
        u.setItem(items[2]);
    }

    public static boolean check(File file) {
        if(file.exists()) {
            return true; 
        }
            return false;
    }

    public static void create() throws Exception{
        boolean exist = check(file);
        if(exist){
            file.delete();
            file.createNewFile();

        }else{
            file = new File("RpgGame.txt");
            file.createNewFile();
        }
    }

    public static void write(String str) throws Exception{
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(str);
        fw.close();
    
    }
    public static void dataSave() throws Exception{        
        create();
        String str =  u.getName()+"/"+u.getJop()+"/"+u.getHp()+"/"+u.getMaxHp()+ "/"+u.getMp()+"/"
        +u.getMaxMp()+"/"+u.getAtt()+"/"+u.getDef()+"/"+u.getTemp()+"/"+u.getLevel()+"/"+u.getExp()+"/"+WeaponUpgrade+"/"+ArmorUpgrade+"/"+AccessoryUpgrade;
        write(str);
        System.out.println("데이터를 저장했습니다.");
    }
    
    public static void dataLoad() {
        String[] st = new String[15];
        String str;
        boolean exist = check(file);
        if(exist){                 
            try {
                FileReader fr = new FileReader(file);
                BufferedReader bw = new BufferedReader(fr);
                str=bw.readLine();
                st = str.split("/");
                fr.close();
                bw.close();                    
            } catch (Exception e) {                    
                System.out.println("불러오기 오류");
            }
            String n,j,h,h2,m,m2,a,d,t,l,e,i1,i2,i3;
            n=st[0];j=st[1];h=st[2];h2=st[3];m=st[4];m2=st[5];a=st[6];d=st[7];t=st[8];l=st[9];e=st[10];i1=st[11];i2=st[12];i3=st[13];            
            u.setName(n); u.setJop(j); u.setHp(Integer.parseInt(h)); u.setMaxHp(Integer.parseInt(h2));
            u.setMp(Integer.parseInt(m)); u.setMaxMp(Integer.parseInt(m2)); u.setAtt(Integer.parseInt(a));
            u.setDef(Integer.parseInt(d)); u.setTemp(Integer.parseInt(t)); u.setLevel(Integer.parseInt(l));
            u.setExp(Integer.parseInt(e)); Weapon=Integer.parseInt(i1); Armor=Integer.parseInt(i2); Accessory=Integer.parseInt(i3);

            if(u.jop.equals("Knigth") ){                                                    
                items[0].name = "장검+[ "+Weapon+" ]";
                items[0].Maxhp = 5+Weapon;
                items[0].Maxmp = Weapon;
                items[0].att = 20+(Weapon*2);
                items[0].def = Weapon;
            }else if(u.jop.equals("Wizard")){                                                    
                items[0].name = "지팡이+[ "+Weapon+" ]";
                items[0].Maxhp = Weapon;
                items[0].Maxmp = 5+Weapon;
                items[0].att = 20+(Weapon*2);
                items[0].def = Weapon;
            }

            if(u.jop.equals("Kingth")){                                                    
                items[1].name = "갑옷+[ "+Armor+" ]";
                items[1].Maxhp = 10+Armor;
                items[1].Maxmp = 10+Armor;
                items[1].att = Armor;
                items[1].def = 20+(Armor*2);
            }else if(u.jop.equals("Wizard")){                                                    
                items[1].name = "로브+[ "+Armor+" ]";
                items[1].Maxhp = 10+Armor;
                items[1].Maxmp = 15+Armor;
                items[1].att = Armor;
                items[1].def = 15+(Armor*2);
            }

            if(i3 != "0"){
                items[2].name = "반지+[ "+(Accessory)+" ]";
                items[2].Maxhp = Accessory;
                items[2].Maxmp = Accessory;
                items[2].att = Accessory;
                items[2].def = Accessory;
            }
            System.out.println("불러오기 완료");
        }else{
            System.out.println("읽을 파일이 없습니다.");
        }
    }

    public static void hunt(){
        int ran = (int)(Math.random()*10);
        Monster m = new Monster();

        while (true) {
            System.out.println("======================================");
            System.out.println("던전에 입장합니다.");
            System.out.println("1.던전1층 2.던전2층 3.던전3층 4.마을로");
            System.out.println("레벨 : " + u.getLevel() + " 경험치 "+u.getExp()+"/"+u.exp2()+" HP : " + u.getHp() +" MP : " + u.getMp());
            System.out.print("입장할 던전 층을 선택하세요> ");
            int choice = sc.nextInt();
            int join = (int)(Math.random()*10);

            if(choice == 1){
                System.out.println("던전1층 입니다.");
                
                    if(join <= 5){                        
                        m = new Monster("개", 40, 5, 10, 5, 10);                        
                    }else if(join > 6 && join < 9){
                        m = new Monster("늑대", 60, 10, 20, 10, 20);
                    }else if(join >= 9){
                        m = new Monster("고블린", 100, 20, 30, 15, 30);
                    }
                    System.out.println(m.name+" 와 전투를 시작합니다.");

            }else if(choice == 2){
                System.out.println("던전2층 입니다.");
                if(join <= 7){
                    m = new Monster("오크", 500, 100, 100, 100, 70);
                }else if(join > 6 && join < 9){
                    m = new Monster("트롤", 800, 200, 200, 160, 80);
                }else if(join >= 9){
                    m = new Monster("오우거", 1000, 250, 300, 200, 90);
                }

                System.out.println(m.name+" 와 전투를 시작합니다.");
                
            }else if(choice == 3){
                System.out.println("던전3층 입니다");
                if(join <= 7){
                    m = new Monster("하이오크", 2000, 1000, 500, 380, 150);
                }else if(join > 6 && join < 9){
                    m = new Monster("귀신", 2500, 1300, 700, 400, 170); 
                }else if(join >= 9){
                    m = new Monster("키메라", 3000, 2000, 800, 600, 200);
                }
                System.out.println(m.name+" 와 전투를 시작합니다.");
                
            }else if(choice == 4){
                System.out.println("마을로 돌아갑니다.");
                break;
            }else{System.out.println("잘못 입력했습니다.");}

            battle : while(true){
                System.out.println("================================");
                System.out.println("1. 공격 2.스킬공격(MP:100) 3.도망");
                System.out.println("레벨 : " + u.getLevel() + " 경험치 "+u.getExp()+"/"+u.exp2()+" HP : " + u.getHp() +" MP : " + u.getMp());
                System.out.print("번호를 입력하세요> ");
                choice = sc.nextInt();

                if(u.getHp() <=0 ){
                    System.out.println("죽었습니다 hp1로 부활 합니다..");
                    u.hp = 1;
                    u.mp = 1;
                }else{
                    switch (choice) {
                        case 1 :
                            u.attack(m,choice);
                            if(m.hp <= 0){
                                System.out.println(m.name + "을 처치했습니다.");
                                u.setExp(m.getExp());
                                if(ran >= 1){
                                    u.setTemp(u.getTemp()+1);
                                    System.out.println("강화석을 얻었습니다 ");
                                    System.out.print("총 강화석 : " + u.getTemp());
                                    System.out.println();
                                }
                                break battle;
                            }
                            if(ran >= 2){
                                m.attack(u,1);
                            }else{
                                m.attack(u,2);
                            }                                    
                            break;

                        case 2 :
                            if(u.getMp() >= 100){
                                u.attack(m,choice);
                                if(m.hp <= 0){
                                    System.out.println(m.name + "을 처치했습니다.");
                                    u.setExp(m.getExp());
                                    if(ran >= 1){                                        
                                        u.setTemp(u.getTemp()+1);
                                        System.out.println("강화석을 얻었습니다 ");
                                        System.out.print("총 강화석 : " + u.getTemp());
                                        System.out.println();
                                    }
                                    break battle;
                                }
                                if(ran >= 2){
                                    m.attack(u,1);
                                }else{
                                    m.attack(u,2);
                                }
                            }else{
                                System.out.println("MP가 부족합니다 현재 MP는 " + u.mp);
                            }
                            break;
                        case 3 :
                            System.out.println("도망쳤습니다.");
                            break battle;
                    }
                }
            }
        }
    }

    private static void shop() {
        WeaponUpgrade=0+Weapon; AccessoryUpgrade=0+Accessory; ArmorUpgrade=0+Armor;
        
        while (true) {
            System.out.println("====================");
            System.out.println("상점에 입장했습니다");
            System.out.println("1. 무기");
            System.out.println("2. 방어구");
            System.out.println("3. 악세사리");
            System.out.println("4. 나가기");
            System.out.print("선택하기> ");
            choice = sc.nextInt();

            if(choice == 1){
                System.out.println("1.직업 무기로 변경 2.강화(강화석필요) 3.나가기");
                int num = sc.nextInt();

                if(num == 1){
                    if(u.jop.equals("Novice") ){
                        System.out.println("아직 Novice 입니다.");
                    }else{
                        if(items[0].name.equals("단검")){
                            if(u.jop.equals("Knigth")){
                                items[0].name = "장검";
                                items[0].Maxhp = 5;
                                items[0].Maxmp = 0;
                                items[0].att = 20;
                                items[0].def = 0;
                                System.out.println("단검가 장검으로 변경되었습니다");
                            }else if(u.jop.equals("Wizard")){
                                items[0].name = "지팡이";
                                items[0].Maxhp = 0;
                                items[0].Maxmp = 5;
                                items[0].att = 20;
                                items[0].def = 0;
                                System.out.println("단검가 지팡이로 변경되었습니다");
                            }
                        }else{
                            System.out.println("단검이 아닙니다.");
                        }
                        
                    }
                }else if(num == 2){
                    if(items[0].name.equals("단검")){
                        System.out.println("단검는 강화를 할수 없습니다.");                        
                    }else{
                        if(u.getTemp() >= 1){
                            System.out.println("강화석 : " + u.getTemp());
                            System.out.print("한번에 강화할수> ");
                            int cc = sc.nextInt();                            

                            if(cc <= u.getTemp()){
                                for(int i=1; i<=cc;i++){
                                    if(u.jop.equals("Knigth") ){                                                    
                                        items[0].name = "장검+[ "+(WeaponUpgrade+1)+" ]";
                                        items[0].Maxhp = 5+WeaponUpgrade+1;
                                        items[0].Maxmp = WeaponUpgrade+1;
                                        items[0].att = 20+((WeaponUpgrade+1)*2);
                                        items[0].def = WeaponUpgrade+1;
                                        u.setTemp(u.getTemp()-1);
                                        WeaponUpgrade++;
                                    }else if(u.jop.equals("Wizard")){                                                    
                                        items[0].name = "지팡이+[ "+(WeaponUpgrade+1)+" ]";
                                        items[0].Maxhp = WeaponUpgrade+1;
                                        items[0].Maxmp = 5+WeaponUpgrade+1;
                                        items[0].att = 20+((WeaponUpgrade+1)*2);
                                        items[0].def = WeaponUpgrade+1;
                                        u.setTemp(u.getTemp()-1);
                                        WeaponUpgrade++;
                                    }
                                    System.out.println(WeaponUpgrade + " 강화 했습니다.");
                                };
                            }else{
                                System.out.println("강화석 수가 부족합니다 현재 강화석 : " + u.getTemp());
                            }
                        }else{
                            System.out.println("강화석이 부족합니다.");
                        }
                    }
                }else if(num == 3){
                    System.out.println("나가기");
                }else{
                    System.out.println("잘못 입력했습니다.");
                }
            }else if(choice == 2){
                System.out.println("1.직업 방어구으로 변경 2.강화(강화석필요) 3.마을로");
                int num = sc.nextInt();

                if(num == 1){
                    if(u.jop.equals("Novice") ){
                        System.out.println("아직 Novice 입니다");
                    }else{
                        if(items[1].name.equals("면티")){
                            if(u.jop.equals("Knigth")){
                                items[1].name = "갑옷";
                                items[1].Maxhp = 10;
                                items[1].Maxmp = 10;
                                items[1].att = 0;
                                items[1].def = 20;
                                System.out.println("면티가 갑옷으로 변경되었습니다");
                            }else if(u.jop.equals("Wizard")){
                                items[1].name = "로브";
                                items[1].Maxhp = 10;
                                items[1].Maxmp = 15;
                                items[1].att = 0;
                                items[1].def = 15;
                                System.out.println("면티가 로브로 변경되었습니다");
                            }
                        }else{
                            System.out.println("면티가 아닙니다.");
                        }
                    }
                }else if(num == 2){
                    if(items[1].name.equals("면티")){
                        System.out.println("면티는 강화를 할수 없습니다.");                        
                    }else{
                        if(u.getTemp() >= 1){
                            System.out.println("강화석 : " + u.getTemp());
                            System.out.print("한번에 강화할수> ");
                            int cc = sc.nextInt();

                            if(cc <= u.getTemp()){
                                for(int i=1; i<=cc;i++){
                                    if(u.jop.equals("Kingth")){                                                    
                                        items[1].name = "갑옷+[ "+(ArmorUpgrade+1)+" ]";
                                        items[1].Maxhp = 10+ArmorUpgrade+1;
                                        items[1].Maxmp = 10+ArmorUpgrade+1;
                                        items[1].att = ArmorUpgrade+1;
                                        items[1].def = 20+((ArmorUpgrade+1)*2);
                                        u.setTemp(u.getTemp()-1);;
                                        ArmorUpgrade++;
                                    }else if(u.jop.equals("Wizard")){                                                    
                                        items[1].name = "로브+[ "+(ArmorUpgrade+1)+" ]";
                                        items[1].Maxhp = 10+ArmorUpgrade+1;
                                        items[1].Maxmp = 15+ArmorUpgrade+1;
                                        items[1].att = ArmorUpgrade+1;
                                        items[1].def = 15+((ArmorUpgrade+1)*2);
                                        u.setTemp(u.getTemp()-1);
                                        ArmorUpgrade++;
                                    }
                                    
                                    System.out.println(ArmorUpgrade+" 강화 했습니다.");
                                }
                            }else{System.out.println("강화석 수가 부족합니다 현재 강화석 : " + u.getTemp());}

                        }else{
                            System.out.println("강화석이 부족합니다.");
                        }                            
                    }
                }else if(num == 3){
                    System.out.println("나가기");
                }
            }else if(choice == 3){
                System.out.println("1.강화 2.나가기");
                int num = sc.nextInt();

                if(num == 1){
                    if(u.getTemp() >= 1){
                        System.out.println("강화석 : " + u.getTemp());
                        System.out.print("한번에 강화할수> ");
                        int cc = sc.nextInt();
                        if(cc <= u.getTemp()){
                            
                            for(int i=1; i<=cc;i++){
                                items[2].name = "반지+[ "+(AccessoryUpgrade+1)+" ]";
                                items[2].Maxhp = 10+AccessoryUpgrade+1;
                                items[2].Maxmp = 10+AccessoryUpgrade+1;
                                items[2].att = 10+AccessoryUpgrade+1;
                                items[2].def = 10+AccessoryUpgrade+1;
                                u.setTemp(u.getTemp()-1);
                                AccessoryUpgrade++;
                                System.out.println(AccessoryUpgrade + " 강화 했습니다.");
                            }
                            //u.setTemp(temp);
                        }else{
                            System.out.println("강화석 수가 부족합니다 현재 강화석 : " + u.getTemp());
                        }
                    }else{
                        System.out.println("강화석이 부족합니다.");
                    }
                }else if(num == 2){
                    System.out.println("나가기");
                }else{
                    System.out.println("다시 입력하세요");
                }
            }else if(choice == 4){
                System.out.println("마을로 가기");
                break;
            }else{
                System.out.println("다시 입력하세요");
            }
        }
    }
}
