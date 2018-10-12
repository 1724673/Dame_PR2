package ui;

import spiel.Spiel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SpielUI {
    private Spiel spiel = new Spiel();
    private ArrayList<Object> options = new ArrayList<>();

    public static void main(String[] args) {
        SpielUI main = new SpielUI();
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(main.getWelcomeMessage());
        while(true){
            System.out.println(main.getMenueElemente());
            try {
                String input = scan.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getWelcomeMessage(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Wilkommen zum Dame Spiel!\n");
        return this.fancyFormat(sb).toString();
    }
    public String getMenueElemente(){
        StringBuilder sb = new StringBuilder();
        ArrayList<String> opt= new ArrayList<>();

        int options = 0;


        this.options.clear();
        if(!spiel.getIstSpielZuEde() && !spiel.getSpielGestartet()) {
            if(spiel.getAnzahlSpieler()!=2){
                sb.append("["+options+"] Spieler hinzufuegen ("+spiel.getAnzahlSpieler()+"/2)\n");
                options++;
                this.options.add("SH");
            }else{
                sb.append("["+options+"] Spieler Starten!\n");
                options++;
                this.options.add("SS");
            }
            if(spiel.getAnzahlSpieler() != 0){
                sb.append("["+options+"] Spieler Entfernen\n");
                options++;
                this.options.add("SE");
            }
            sb.append("["+options+"] Credits\n");
            options++;
            this.options.add("CR");
        }
        sb.append("["+options+"] Beenden\n");
        options++;
        this.options.add("EXIT");
        return sb.toString();
    }
    public StringBuilder fancyFormat(StringBuilder toFancysize){
        StringBuilder sb = new StringBuilder();
        int lineLength = 40;
        String fancysizer = "-";
        int breakPoint = 0;

        String[] token= toFancysize.toString().split("\n",-1);
        for(String str: token){
            breakPoint = (lineLength/2- (str.length()-str.length()/2));
            for(int i= 1; i < lineLength; i++){
                if(i != breakPoint){
                    sb.append(fancysizer);
                }else {
                    sb.append(str);
                    i += str.length();
                }
            }
            sb.append("\n");
        }
        return sb;
    }
    }
