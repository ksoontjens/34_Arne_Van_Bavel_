package hellotvxlet;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.tv.xlet.*;
import org.davic.resources.*;
import org.dvb.ui.*;
import org.havi.ui.*;
import org.havi.ui.event.*;



public class HelloTVXlet implements Xlet, HBackgroundImageListener, 
        ResourceClient, HActionListener
       {
     Question[] questionArray = new Question[70];
    
    private XletContext actueleXletContext;
    private HScene scene;
    private HTextButton btn1;
    private HTextButton btn2;
    private HTextButton btn3;
    private HTextButton btn4;
    private HTextButton btn5;
    private HTextButton btn6;
    private HTextButton btn7;
    private HTextButton btn8;
    private HTextButton btn9;
    private HTextButton btnTrue;
    private HTextButton btnFalse;
    private HTextButton revange;
    private HStaticText vraaglabel;
    private HStaticText redlabel;
    private HStaticText bluelabel;
    int scoreblauw = 0;
    int scorerood = 0;
    int drawScoreRood = 0;
    int drawScoreBlauw = 0;
    boolean Btn1Enable = true;
    boolean Btn2Enable = true;
    boolean Btn3Enable = true;
    boolean Btn4Enable = true;
    boolean Btn5Enable = true;
    boolean Btn6Enable = true;
    boolean Btn7Enable = true;
    boolean Btn8Enable = true;
    boolean Btn9Enable = true;
    boolean NoButtonLeft = false;
    String ButtonCol1 = "Zwart";
    String ButtonCol2 = "Zwart";
    String ButtonCol3 = "Zwart";
    String ButtonCol4 = "Zwart";
    String ButtonCol5 = "Zwart";
    String ButtonCol6 = "Zwart";
    String ButtonCol7 = "Zwart";
    String ButtonCol8 = "Zwart";
    String ButtonCol9 = "Zwart";
    boolean CanWin = false;
    boolean EnemyCanWin = false;
    int randomInt; 
    boolean Speler = true;
    int buttonKlik;
    
    private HBackgroundConfigTemplate bgTemplate; 
    private HScreen screen;
    private HBackgroundDevice bgDevice;
    private HStillImageBackgroundConfiguration bgConfiguration;
    private HBackgroundImage agrondimg = new HBackgroundImage("achtergrond1.jpg");
    

    public void notifyRelease (ResourceProxy proxy){}
    public void release (ResourceProxy proxy){}
    public boolean requestRelease (ResourceProxy proxy, Object requestData){
        return false;  }
    
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {

        this.actueleXletContext = context;
      HSceneTemplate sceneTemplate = new HSceneTemplate();
      
      sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION, 
              new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED); 
      
      sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, 
              new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED); 
      
      scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);

      
    //Background Image
      screen = HScreen.getDefaultHScreen();
    //HBackground device opvragen
      bgDevice = screen.getDefaultHBackgroundDevice();
    //HBackground device reserveren
      if (bgDevice.reserveDevice((ResourceClient) this)){
        System.out.println("BackgroundImage device has been reserved");
      } else{
        System.out.println("Background Image device cannot be reserved");
      }
      //Template maken
      bgTemplate = new HBackgroundConfigTemplate();
      
      bgTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, 
              HBackgroundConfigTemplate.REQUIRED);
      
      bgConfiguration = (HStillImageBackgroundConfiguration)bgDevice.getBestConfiguration(bgTemplate);
      
      try{
        bgDevice.setBackgroundConfiguration(bgConfiguration);
      } 
      catch(java.lang.Exception e){
        System.out.println(e.toString());
      }
        
      
   //Buttons and Labels   
      btn1 = new HTextButton("1");
      btn2 = new HTextButton("2");
      btn3 = new HTextButton("3");
      btn4 = new HTextButton("4");
      btn5 = new HTextButton("5");
      btn6 = new HTextButton("6");
      btn7 = new HTextButton("7");
      btn8 = new HTextButton("8");
      btn9 = new HTextButton("9");
      btnTrue = new HTextButton("YHEP");
      btnFalse = new HTextButton("NOPE");
      revange = new HTextButton("REVANCHE");
      
      vraaglabel = new HStaticText ("");
      redlabel = new HStaticText ("--> Speler 1" + "\n" + "€ " + scorerood );
      bluelabel = new HStaticText ("Speler 2" +  "\n" + "€ " + scoreblauw);
      
     //Propterties Buttons and Labels 
      vraaglabel.setLocation(40, 460);
      vraaglabel.setSize(460, 85);
      vraaglabel.setBackground(new DVBColor(0,0,0,180));
      vraaglabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
      vraaglabel.setFont(new Font("Tiresias",Font.PLAIN,17));
      
      
      redlabel.setLocation(530, 30);
      redlabel.setSize(150, 120);
      redlabel.setForeground(new DVBColor(255,255,255,255));
      redlabel.setBackground(new DVBColor(255,0,0,230));
      redlabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
      redlabel.setBordersEnabled(Speler);
      
      
      bluelabel.setLocation(530, 170);
      bluelabel.setSize(150, 120);
      bluelabel.setForeground(new DVBColor(0,0,0,255));
      bluelabel.setBackground(new DVBColor(0,0,255,230));
      bluelabel.setBackgroundMode(HVisible.BACKGROUND_FILL);
      bluelabel.setBordersEnabled(!Speler);
      
      btn1.setLocation(40, 30);
      btn1.setSize(140, 120);
      btn1.setBackground(new DVBColor(0,0,0,220));
      btn1.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      btn2.setLocation(200, 30);
      btn2.setSize(140, 120);
      btn2.setBackground(new DVBColor(0,0,0,220));
      btn2.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      btn3.setLocation(360, 30);
      btn3.setSize(140, 120);
      btn3.setBackground(new DVBColor(0,0,0,220));
      btn3.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      btn4.setLocation(40, 170);
      btn4.setSize(140, 120);
      btn4.setBackground(new DVBColor(0,0,0,220));
      btn4.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      
      btn5.setLocation(200, 170);
      btn5.setSize(140, 120);
      btn5.setBackground(new DVBColor(0,0,0,220));
      btn5.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      btn6.setLocation(360, 170);
      btn6.setSize(140, 120);
      btn6.setBackground(new DVBColor(0,0,0,220));
      btn6.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      btn7.setLocation(40, 310);
      btn7.setSize(140, 120);
      btn7.setBackground(new DVBColor(0,0,0,220));
      btn7.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      
      btn8.setLocation(200, 310);
      btn8.setSize(140, 120);
      btn8.setBackground(new DVBColor(0,0,0,220));
      btn8.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      btn9.setLocation(360, 310);
      btn9.setSize(140, 120);
      btn9.setBackground(new DVBColor(0,0,0,220));
      btn9.setBackgroundMode(HVisible.BACKGROUND_FILL);
      
      btnTrue.setLocation(530, 450);
      btnTrue.setSize(120, 50);
      btnTrue.setBackground(new DVBColor(0,0,0,220));
      btnTrue.setBackgroundMode(HVisible.BACKGROUND_FILL);
      btnTrue.setVisible(false);
      
      btnFalse.setLocation(530, 510);
      btnFalse.setSize(120, 50);
      btnFalse.setBackground(new DVBColor(0,0,0,220));
      btnFalse.setBackgroundMode(HVisible.BACKGROUND_FILL);
      btnFalse.setVisible(false);
      
      
      revange.setLocation(530, 460);
      revange.setSize(160, 80);
      revange.setBackground(new DVBColor(0,51,0,255));
      revange.setBackgroundMode(HVisible.BACKGROUND_FILL);
      revange.setVisible(false);
      

      scene.add(btn1);
      scene.add(btn2);
      scene.add(btn3);
      scene.add(btn4);
      scene.add(btn5);
      scene.add(btn6);
      scene.add(btn7);
      scene.add(btn8);
      scene.add(btn9);
      scene.add(btnTrue);
      scene.add(btnFalse);
      scene.add(revange);
      scene.add(vraaglabel);
      scene.add(redlabel);
      scene.add(bluelabel);
      
      btn1.setActionCommand("knop1");
      btn2.setActionCommand("knop2");
      btn3.setActionCommand("knop3");
      btn4.setActionCommand("knop4");
      btn5.setActionCommand("knop5");
      btn6.setActionCommand("knop6");
      btn7.setActionCommand("knop7");
      btn8.setActionCommand("knop8");
      btn9.setActionCommand("knop9");
      btnTrue.setActionCommand("True");
      btnFalse.setActionCommand("False");
      revange.setActionCommand("Revanche");
      
      btn1.setFocusTraversal(null, btn4, null, btn2);
      btn2.setFocusTraversal(null, btn5, btn1, btn3);
      btn3.setFocusTraversal(null, btn6, btn2, null);
      btn4.setFocusTraversal(btn1, btn7, null, btn5);
      btn5.setFocusTraversal(btn2, btn8, btn4, btn6);
      btn6.setFocusTraversal(btn3, btn9, btn5, null);
      btn7.setFocusTraversal(btn4, null, null, btn8);
      btn8.setFocusTraversal(btn5, null, btn7, btn9);
      btn9.setFocusTraversal(btn6, null, btn8, null);
      revange.setFocusTraversal(null, null, null, null);
      
      btn5.requestFocus();
      
      btn1.addHActionListener(this);
      btn2.addHActionListener(this);
      btn3.addHActionListener(this);
      btn4.addHActionListener(this);
      btn5.addHActionListener(this);
      btn6.addHActionListener(this);
      btn7.addHActionListener(this);
      btn8.addHActionListener(this);
      btn9.addHActionListener(this);
      btnTrue.addHActionListener(this);
      btnFalse.addHActionListener(this);
      revange.addHActionListener(this);
      
      arraylijst();
      
 
    }
    
     public void startXlet() {
       scene.validate();
       scene.setVisible(true);
       
       agrondimg.load(this);
        
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
      agrondimg.flush();
    }

    public void imageLoaded(HBackgroundImageEvent e) {
        try {
            bgConfiguration.displayImage(agrondimg);
        } catch (Exception s) {
            System.out.println(s.toString());
        } 
        
    }

    public void imageLoadFailed(HBackgroundImageEvent e) {
        
    }

   
    public void actionPerformed(ActionEvent e) {
         if(e.getActionCommand() == "knop1"){
             if(Btn1Enable)
             {
            vraaglabel.setTextContent(vraagAanroeper(), 
                   HState.NORMAL_STATE);
            
            buttonKlik = 1;
            checkWin();
            btn1.setBackground(new DVBColor(0,255,190,220));
            btnTrue.setVisible(true);
            btnFalse.setVisible(true);
            btnTrue.requestFocus();
            btnTrue.setFocusTraversal(null, btnFalse, null, null);
            btnFalse.setFocusTraversal(btnTrue, null, null, null);
            Btn1Enable = false;
            }
             
        }
        
        if(e.getActionCommand() == "knop2"){
            if(Btn2Enable)
             {
            vraaglabel.setTextContent(vraagAanroeper(), 
                   HState.NORMAL_STATE);
            
            buttonKlik = 2;
            checkWin();
            btn2.setBackground(new DVBColor(0,255,190,220));
            btnTrue.setVisible(true);
            btnFalse.setVisible(true);
            btnTrue.requestFocus();
            btnTrue.setFocusTraversal(null, btnFalse, null, null);
            btnFalse.setFocusTraversal(btnTrue, null, null, null);
            Btn2Enable = false;
            }
        }
        
        if(e.getActionCommand() == "knop3"){
            if(Btn3Enable)
             {
            vraaglabel.setTextContent(vraagAanroeper(), 
                   HState.NORMAL_STATE);
            
            buttonKlik = 3;
            checkWin();
            btn3.setBackground(new DVBColor(0,255,190,220));
            btnTrue.setVisible(true);
            btnFalse.setVisible(true);
            btnTrue.requestFocus();
            btnTrue.setFocusTraversal(null, btnFalse, null, null);
            btnFalse.setFocusTraversal(btnTrue, null, null, null);
            Btn3Enable = false;
            }
        }
        
        if(e.getActionCommand() == "knop4"){
            if(Btn4Enable)
             {
            vraaglabel.setTextContent(vraagAanroeper(), 
                   HState.NORMAL_STATE);
            
            buttonKlik = 4;
            checkWin();
            btn4.setBackground(new DVBColor(0,255,190,220));
            btnTrue.setVisible(true);
            btnFalse.setVisible(true);
            btnTrue.requestFocus();
            btnTrue.setFocusTraversal(null, btnFalse, null, null);
            btnFalse.setFocusTraversal(btnTrue, null, null, null);
            Btn4Enable = false;
            }
            
        }
         
         if(e.getActionCommand() == "knop5"){
             if(Btn5Enable)
             {
             vraaglabel.setTextContent(vraagAanroeper(), 
                   HState.NORMAL_STATE);
             
             buttonKlik = 5;
             checkWin();
            btn5.setBackground(new DVBColor(0,255,190,220));
            btnTrue.setVisible(true);
            btnFalse.setVisible(true);
            btnTrue.requestFocus();
            btnTrue.setFocusTraversal(null, btnFalse, null, null);
            btnFalse.setFocusTraversal(btnTrue, null, null, null);
            Btn5Enable = false;
            }
        }
        
        if(e.getActionCommand() == "knop6"){
            if(Btn6Enable)
             {
            vraaglabel.setTextContent(vraagAanroeper(), 
                   HState.NORMAL_STATE);
            
            buttonKlik = 6;
            checkWin();
            btn6.setBackground(new DVBColor(0,255,190,220));
            btnTrue.setVisible(true);
            btnFalse.setVisible(true);
            btnTrue.requestFocus();
            btnTrue.setFocusTraversal(null, btnFalse, null, null);
            btnFalse.setFocusTraversal(btnTrue, null, null, null);
            Btn6Enable = false;
            }
        }
        
        if(e.getActionCommand() == "knop7"){
            if(Btn7Enable)
             {
            vraaglabel.setTextContent(vraagAanroeper(), 
                   HState.NORMAL_STATE);
            
            buttonKlik = 7;
            checkWin();
            btn7.setBackground(new DVBColor(0,255,190,220));
            btnTrue.setVisible(true);
            btnFalse.setVisible(true);
            btnTrue.requestFocus();
            btnTrue.setFocusTraversal(null, btnFalse, null, null);
            btnFalse.setFocusTraversal(btnTrue, null, null, null);
            Btn7Enable = false;
            }
        }
        
        if(e.getActionCommand() == "knop8"){
            if(Btn8Enable)
             {
            vraaglabel.setTextContent(vraagAanroeper(), 
                   HState.NORMAL_STATE);
            
            buttonKlik = 8;
            checkWin();
            btn8.setBackground(new DVBColor(0,255,190,220));
            btnTrue.setVisible(true);
            btnFalse.setVisible(true);
            btnTrue.requestFocus();
            btnTrue.setFocusTraversal(null, btnFalse, null, null);
            btnFalse.setFocusTraversal(btnTrue, null, null, null);
            Btn8Enable = false;
            }
        }
         
         if(e.getActionCommand() == "knop9"){
             if(Btn9Enable)
             {
             vraaglabel.setTextContent(vraagAanroeper(), 
                   HState.NORMAL_STATE);
             
            buttonKlik = 9;
            checkWin();
            btn9.setBackground(new DVBColor(0,255,190,220));
            btnTrue.setVisible(true);
            btnFalse.setVisible(true);
            btnTrue.requestFocus();
            btnTrue.setFocusTraversal(null, btnFalse, null, null);
            btnFalse.setFocusTraversal(btnTrue, null, null, null);
            Btn9Enable = false;
             }
        }
         
         if(e.getActionCommand() == "Revanche")
         {
            revange();
         }
         
         if(e.getActionCommand() == "True"){
        
             if(e.getActionCommand() == questionArray[randomInt].answer)
             {
                 if(!CanWin)
                 {
                    vraaglabel.setTextContent("Je antwoord is CORRECT", HState.NORMAL_STATE);
                if(Speler == true)
                {
                buttonKleur("Rood");
                scorerood += 50;
                
                }
                else
                {
                buttonKleur("Blauw");
                scoreblauw += 50;
                
                }
                btnTrue.setVisible(false);
                btnFalse.setVisible(false);
                btn5.requestFocus();
                checkForDraw();
                spelerSwitch();
                 } 
                 else
                 {
                     if(Speler == true)
                    {
                    buttonKleur("Rood");
                    scorerood += 100;
                    redlabel.setTextContent("--> Speler 1" + "\n" + "€ " + scorerood , HState.NORMAL_STATE);
                    vraaglabel.setTextContent("SPELER 1 IS GEWONNEN", HState.NORMAL_STATE);
                    btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
                    }
                    else
                    {
                    buttonKleur("Blauw");
                    scoreblauw += 100;
                    bluelabel.setTextContent("--> Speler 2" + "\n" + "€ " + scoreblauw , HState.NORMAL_STATE);
                    vraaglabel.setTextContent("SPELER 2 IS GEWONNEN", HState.NORMAL_STATE);
                    btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
                    }   
                    
                 }
             }
             else  
             {  
                 if(!EnemyCanWin)
                 {
                vraaglabel.setTextContent("Je antwoord is NIET CORRECT", HState.NORMAL_STATE);
                
                if(Speler == true)
                {
                buttonKleur("Blauw");
                scoreblauw += 50;
                
                }
                else
                {
                buttonKleur("Rood");
                scorerood += 50;
                
                }
                btnTrue.setVisible(false);
                btnFalse.setVisible(false);
                btn5.requestFocus();
                checkForDraw();
                spelerSwitch();
                 }
                 else
                 {
                     if(Speler == true)
                    {
                    buttonKleur("Blauw");
                    scoreblauw += 100;
                    bluelabel.setTextContent("Speler 2" + "\n" + "€ " + scoreblauw , HState.NORMAL_STATE);
                    vraaglabel.setTextContent("SPELER 2 IS GEWONNEN", HState.NORMAL_STATE);
                    btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
                    }
                    else
                    {
                    buttonKleur("Rood");
                    scorerood += 100;
                    redlabel.setTextContent("Speler 1" + "\n" + "€ " + scorerood , HState.NORMAL_STATE);
                    vraaglabel.setTextContent("SPELER 1 IS GEWONNEN", HState.NORMAL_STATE);
                    btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
                    }
                    
                 
                 }
             }
             
            
         }
             
         if(e.getActionCommand() == "False"){
              
             if(e.getActionCommand() == questionArray[randomInt].answer)
             {
                 if(!CanWin)
                 {
                vraaglabel.setTextContent("JE ANTWOORD IS JUIST", HState.NORMAL_STATE);
                
                if(Speler == true)
                {
                buttonKleur("Rood");
                scorerood += 50;
                
                }
                else
                {
                buttonKleur("Blauw");
                scoreblauw += 50;
                
                }
                btnTrue.setVisible(false);
                btnFalse.setVisible(false);
                btn5.requestFocus();
                checkForDraw();
                spelerSwitch();
                 }
                 else
                 {
                     if(Speler == true)
                    {
                    buttonKleur("Rood");
                    scorerood += 100;
                    redlabel.setTextContent("--> Speler 1" + "\n" + "€ " + scorerood , HState.NORMAL_STATE);
                    vraaglabel.setTextContent("SPELER 1 IS GEWONNEN", HState.NORMAL_STATE);
                    btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
                    }
                    else
                    {
                     buttonKleur("Blauw");
                     scoreblauw += 100;
                     bluelabel.setTextContent("--> Speler 2" + "\n" + "€ " + scoreblauw , HState.NORMAL_STATE);
                     vraaglabel.setTextContent("SPELER 2 IS GEWONNEN", HState.NORMAL_STATE);
                     btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
                    }
                    
                 }
             }
             else
             {
                 if(!EnemyCanWin)
                 {
                vraaglabel.setTextContent("JE ANTWOORD IS FOUT", HState.NORMAL_STATE);
                
                if(Speler == true)
                {
                buttonKleur("Blauw");
                scoreblauw += 50;
                
                }
                else
                {
                buttonKleur("Rood");
                scorerood += 50;
                
                }
                btnTrue.setVisible(false);
                btnFalse.setVisible(false);
                btn5.requestFocus();
                checkForDraw();
                spelerSwitch();
                 }
                 else
                 {
                     if(Speler == true)
                    {
                    buttonKleur("Blauw");
                    scoreblauw += 100;
                    bluelabel.setTextContent("Speler 2" + "\n" + "€ " + scoreblauw , HState.NORMAL_STATE);
                    vraaglabel.setTextContent("SPELER 2 IS GEWONNEN", HState.NORMAL_STATE);
                    btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
                    }
                    else
                    {
                    buttonKleur("Rood");
                    scorerood += 100;
                    redlabel.setTextContent("Speler 1" + "\n" + "€ " + scorerood , HState.NORMAL_STATE);
                    vraaglabel.setTextContent("SPELER 1 IS GEWONNEN", HState.NORMAL_STATE);
                    btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
                    }
                    
                 }
             }
             
         
         }
    }
    
    public void checkWin()
    {
        if(Speler)
        {
            switch(buttonKlik)
            {
                case 1: if(ButtonCol2 == "Rood" && ButtonCol3 == "Rood" || ButtonCol5 == "Rood" && ButtonCol9 == "Rood" || ButtonCol4 == "Rood" && ButtonCol7 == "Rood")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol2 == "Blauw" && ButtonCol3 == "Blauw" || ButtonCol5 == "Blauw" && ButtonCol9 == "Blauw" || ButtonCol4 == "Blauw" && ButtonCol7 == "Blauw")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 2: if(ButtonCol1 == "Rood" && ButtonCol3 == "Rood" || ButtonCol5 == "Rood" && ButtonCol8 == "Rood")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Blauw" && ButtonCol3 == "Blauw" || ButtonCol5 == "Blauw" && ButtonCol8 == "Blauw")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 3: if(ButtonCol1 == "Rood" && ButtonCol2 == "Rood" || ButtonCol6 == "Rood" && ButtonCol9 == "Rood" || ButtonCol5 == "Rood" && ButtonCol7 == "Rood")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Blauw" && ButtonCol2 == "Blauw" || ButtonCol6 == "Blauw" && ButtonCol9 == "Blauw" || ButtonCol5 == "Blauw" && ButtonCol7 == "Blauw")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 4: if(ButtonCol1 == "Rood" && ButtonCol7 == "Rood" || ButtonCol5 == "Rood" && ButtonCol6 == "Rood" )
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Blauw" && ButtonCol7 == "Blauw" || ButtonCol5 == "Blauw" && ButtonCol6 == "Blauw" )
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 5: if(ButtonCol1 == "Rood" && ButtonCol9 == "Rood" || ButtonCol2 == "Rood" && ButtonCol8 == "Rood" || ButtonCol3 == "Rood" && ButtonCol7 == "Rood" || ButtonCol4 == "Rood" && ButtonCol6 == "Rood")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Blauw" && ButtonCol9 == "Blauw" || ButtonCol2 == "Blauw" && ButtonCol8 == "Blauw" || ButtonCol3 == "Blauw" && ButtonCol7 == "Blauw" || ButtonCol4 == "Blauw" && ButtonCol6 == "Blauw")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 6: if(ButtonCol3 == "Rood" && ButtonCol9 == "Rood" || ButtonCol4 == "Rood" && ButtonCol5 == "Rood")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol3 == "Blauw" && ButtonCol9 == "Blauw" || ButtonCol4 == "Blauw" && ButtonCol5 == "Blauw")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 7: if(ButtonCol1 == "Rood" && ButtonCol4 == "Rood" || ButtonCol8 == "Rood" && ButtonCol9 == "Rood")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Blauw" && ButtonCol4 == "Blauw" || ButtonCol8 == "Blauw" && ButtonCol9 == "Blauw")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 8: if(ButtonCol2 == "Rood" && ButtonCol5 == "Rood" || ButtonCol7 == "Rood" && ButtonCol9 == "Rood")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol2 == "Blauw" && ButtonCol5 == "Blauw" || ButtonCol7 == "Blauw" && ButtonCol9 == "Blauw")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 9: if(ButtonCol1 == "Rood" && ButtonCol5 == "Rood" || ButtonCol3 == "Rood" && ButtonCol6 == "Rood" || ButtonCol7 == "Rood" && ButtonCol8 == "Rood")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Blauw" && ButtonCol5 == "Blauw" || ButtonCol3 == "Blauw" && ButtonCol6 == "Blauw" || ButtonCol7 == "Blauw" && ButtonCol8 == "Blauw")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
            }
        }
        else
        {
            switch(buttonKlik)
            {
                case 1: if(ButtonCol2 == "Blauw" && ButtonCol3 == "Blauw" || ButtonCol5 == "Blauw" && ButtonCol9 == "Blauw" || ButtonCol4 == "Blauw" && ButtonCol7 == "Blauw")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol2 == "Rood" && ButtonCol3 == "Rood" || ButtonCol5 == "Rood" && ButtonCol9 == "Rood" || ButtonCol4 == "Rood" && ButtonCol7 == "Rood")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 2: if(ButtonCol1 == "Blauw" && ButtonCol3 == "Blauw" || ButtonCol5 == "Blauw" && ButtonCol8 == "Blauw")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Rood" && ButtonCol3 == "Rood" || ButtonCol5 == "Rood" && ButtonCol8 == "Rood")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 3: if(ButtonCol1 == "Blauw" && ButtonCol2 == "Blauw" || ButtonCol6 == "Blauw" && ButtonCol9 == "Blauw" || ButtonCol5 == "Blauw" && ButtonCol7 == "Blauw")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Rood" && ButtonCol2 == "Rood" || ButtonCol6 == "Rood" && ButtonCol9 == "Rood" || ButtonCol5 == "Rood" && ButtonCol7 == "Rood")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 4: if(ButtonCol1 == "Blauw" && ButtonCol7 == "Blauw" || ButtonCol5 == "Blauw" && ButtonCol6 == "Blauw" )
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Rood" && ButtonCol7 == "Rood" || ButtonCol5 == "Rood" && ButtonCol6 == "Rood" )
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 5: if(ButtonCol1 == "Blauw" && ButtonCol9 == "Blauw" || ButtonCol2 == "Blauw" && ButtonCol8 == "Blauw" || ButtonCol3 == "Blauw" && ButtonCol7 == "Blauw" || ButtonCol4 == "Blauw" && ButtonCol6 == "Blauw")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Rood" && ButtonCol9 == "Rood" || ButtonCol2 == "Rood" && ButtonCol8 == "Rood" || ButtonCol3 == "Rood" && ButtonCol7 == "Rood" || ButtonCol4 == "Rood" && ButtonCol6 == "Rood")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 6: if(ButtonCol3 == "Blauw" && ButtonCol9 == "Blauw" || ButtonCol4 == "Blauw" && ButtonCol5 == "Blauw")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol3 == "Rood" && ButtonCol9 == "Rood" || ButtonCol4 == "Rood" && ButtonCol5 == "Rood")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 7: if(ButtonCol1 == "Blauw" && ButtonCol4 == "Blauw" || ButtonCol8 == "Blauw" && ButtonCol9 == "Blauw")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Rood" && ButtonCol4 == "Rood" || ButtonCol8 == "Rood" && ButtonCol9 == "Rood")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 8: if(ButtonCol2 == "Blauw" && ButtonCol5 == "Blauw" || ButtonCol7 == "Blauw" && ButtonCol9 == "Blauw")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol2 == "Rood" && ButtonCol5 == "Rood" || ButtonCol7 == "Rood" && ButtonCol9 == "Rood")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
                case 9: if(ButtonCol1 == "Blauw" && ButtonCol5 == "Blauw" || ButtonCol3 == "Blauw" && ButtonCol6 == "Blauw" || ButtonCol7 == "Blauw" && ButtonCol8 == "Blauw")
                        { CanWin = true; } else { CanWin = false;}
                        if(ButtonCol1 == "Rood" && ButtonCol5 == "Rood" || ButtonCol3 == "Rood" && ButtonCol6 == "Rood" || ButtonCol7 == "Rood" && ButtonCol8 == "Rood")
                        { EnemyCanWin = true; } else { EnemyCanWin = false;}
                break;
            }
        
        }
    }
    
    public void buttonKleur(String kleur)
    {
        if(kleur == "Rood")
        {
           switch(buttonKlik)
            {
                case 1: btn1.setBackground(new DVBColor(255,0,0,230));
                        ButtonCol1 = "Rood";
                        btn1.requestFocus();
                        
                        
                break;
                case 2: btn2.setBackground(new DVBColor(255,0,0,230));
                        ButtonCol2 = "Rood";
                        btn2.requestFocus();
                break;
                case 3: btn3.setBackground(new DVBColor(255,0,0,230));
                        ButtonCol3 = "Rood";
                        btn3.requestFocus();
                break;
                case 4: btn4.setBackground(new DVBColor(255,0,0,230));
                        ButtonCol4 = "Rood";
                        btn4.requestFocus();
                break;
                case 5: btn5.setBackground(new DVBColor(255,0,0,230));
                        ButtonCol5 = "Rood";
                        btn5.requestFocus();
                break;
                case 6: btn6.setBackground(new DVBColor(255,0,0,230));
                        ButtonCol6 = "Rood";
                        btn6.requestFocus();
                break;
                case 7: btn7.setBackground(new DVBColor(255,0,0,230));
                        ButtonCol7 = "Rood";
                        btn7.requestFocus();
                break;
                case 8: btn8.setBackground(new DVBColor(255,0,0,230));
                        ButtonCol8 = "Rood";
                        btn8.requestFocus();
                break;
                case 9: btn9.setBackground(new DVBColor(255,0,0,230));
                        ButtonCol9 = "Rood";
                        btn9.requestFocus();
                break;
            }  
        }
        else
        {
            switch(buttonKlik)
            {
                case 1: btn1.setBackground(new DVBColor(0,0,255,230));
                        ButtonCol1 = "Blauw";
                        btn1.requestFocus();
                        
                break;
                case 2: btn2.setBackground(new DVBColor(0,0,255,230));
                        ButtonCol2 = "Blauw";
                        btn2.requestFocus();
                break;
                case 3: btn3.setBackground(new DVBColor(0,0,255,230));
                        ButtonCol3 = "Blauw";
                        btn3.requestFocus();
                break;
                case 4: btn4.setBackground(new DVBColor(0,0,255,230));
                        ButtonCol4 = "Blauw";
                        btn4.requestFocus();
                break;
                case 5: btn5.setBackground(new DVBColor(0,0,255,230));
                        ButtonCol5 = "Blauw";
                        btn5.requestFocus();
                break;
                case 6: btn6.setBackground(new DVBColor(0,0,255,230));
                        ButtonCol6 = "Blauw";
                        btn6.requestFocus();
                break;
                case 7: btn7.setBackground(new DVBColor(0,0,255,230));
                        ButtonCol7 = "Blauw";
                        btn7.requestFocus();
                break;
                case 8: btn8.setBackground(new DVBColor(0,0,255,230));
                        ButtonCol8 = "Blauw";
                        btn8.requestFocus();
                break;
                case 9: btn9.setBackground(new DVBColor(0,0,255,230));
                        ButtonCol9 = "Blauw";
                        btn9.requestFocus();
                break;
            } 
        }
        
              
    }
    
    public void spelerSwitch()
    {
        Speler = !Speler;
        
        if(Speler == true)
        {
          redlabel.setTextContent("--> Speler 1" + "\n" + "€ " + scorerood, HState.NORMAL_STATE);
          bluelabel.setTextContent("Speler 2" +  "\n" + "€ " + scoreblauw, HState.NORMAL_STATE);

          redlabel.setForeground(new DVBColor(255,255,255,255));
          bluelabel.setForeground(new DVBColor(0,0,0,255));
        }
        else
        {
          redlabel.setTextContent("Speler 1" + "\n" + "€ " + scorerood, HState.NORMAL_STATE);
          bluelabel.setTextContent("--> Speler 2" +  "\n" + "€ " + scoreblauw, HState.NORMAL_STATE);
          bluelabel.setForeground(new DVBColor(255,255,255,255));
          redlabel.setForeground(new DVBColor(0,0,0,255));
        }
    }
    
    public String vraagAanroeper()
    {
        boolean loopMayBeSkipped = false;

        Random randomCreator = new Random();
        String vraagReturn = "";
        randomInt = randomCreator.nextInt(70);
        
        for (int i = 0; i < questionArray.length; i++) 
        {
            if (!loopMayBeSkipped)
            {
                if (!questionArray[randomInt].used) 
                {
                    vraagReturn = questionArray[randomInt].question;
                    loopMayBeSkipped = true;
                } 
                else 
                {
                    randomInt = randomCreator.nextInt(70);
                }  
            }
        }
        
        questionArray[randomInt].used = true;
        
        System.out.println(randomInt);
        return vraagReturn;
    }

    public void arraylijst()
    {
        // Heel veel vragen

questionArray[0] = new Question("Istanbul is de hoofdstad van Turkije", "False", false);
questionArray[1] = new Question("De Nijl is de langste rivier ter wereld", "True", false);
questionArray[2] = new Question("De diameter van de zon is ongeveer \n 110 maal groter dan die van de aarde", "True", false);
questionArray[3] = new Question("De kleinste planeet is ook de planeet \n die het dichtste bij de zon staat", "True", false);
questionArray[4] = new Question("Hoe hoger in de atmosfeer - hoe hoger de druk", "False", false);
questionArray[5] = new Question("Het Olympische vuur brandde voor het eerst in Amsterdam", "True", false);
questionArray[6] = new Question("Stalingrad is de vroegere naam van Sint-Petersburg", "False", false);
questionArray[7] = new Question("Het Atlasgebergte ligt in Zuid-Amerika", "False", false);
questionArray[8] = new Question("Ijsberen leven op de Zuidpool", "False", false);
questionArray[9] = new Question("De luchtvochtigheid meet men met een barometer", "False", false);

questionArray[10] = new Question("De Titanic werd in Belfast gebouwd", "True", false);
questionArray[11] = new Question("Socrates was een leerling van Plato", "False", false);
questionArray[12] = new Question("Stalin was de leider van de Bolsjewistische \n revolutie in Rusland in 1917", "False", false);
questionArray[13] = new Question("De NAVO werd opgericht in 1949", "True", false);
questionArray[14] = new Question("Karl Marx is de schrijver van 'Das Kapital'", "True", false);
questionArray[15] = new Question("Leopold I was de eerste koning van België", "True", false);
questionArray[16] = new Question("Franklin Roosevelt is de enige Amerikaanse \n president die 4 keer verkozen is", "True", false);
questionArray[17] = new Question("Zwitserland werd niet bezet tijdens Wereldoorlog II", "True", false);
questionArray[18] = new Question("Hitler was getrouwd met Anna Braun", "False", false);
questionArray[19] = new Question("Neil Armstrong was de eerste man op de maan", "True", false);

questionArray[20] = new Question("Cassius Clay was de geboortenaam \n van Mohammed Ali", "True", false);
questionArray[21] = new Question("Voetbal is afkomstig uit Brazilië", "False", false);
questionArray[22] = new Question("Ping Pong is afkomstig uit China", "True", false);
questionArray[23] = new Question("Een Olympisch zwembad is 50 meter lang", "True", false);
questionArray[24] = new Question("Het WK voetbal werd in 2010 gehouden in Zuid-Afrika", "True", false);
questionArray[25] = new Question("Eddy Merckx won 4 maal de Ronde van Vlaanderen", "False", false);
questionArray[26] = new Question("Een volledige hockeywedstrijd duurt 70 minuten", "True", false);
questionArray[27] = new Question("Zizou is de bijnaam van Zinédine Zidane", "True", false);
questionArray[28] = new Question("De Champions League werd in 2014 \n gewonnen door Atlético Madrid", "False", false);
questionArray[29] = new Question("Het eerste WK in het voetbal werd \n gewonnen door Uruguay", "True", false);

questionArray[30] = new Question("Mozart had de Oostenrijkse nationaliteit", "True", false);
questionArray[31] = new Question("Elvis Presley stierf in 1977", "True", false);
questionArray[32] = new Question("Een viool heeft 6 snaren", "False", false);
questionArray[33] = new Question("John Lennon werd vermoord in Los Angeles", "False", false);
questionArray[34] = new Question("Een standaardpiano telt 52 witte toetsen", "True", false);
questionArray[35] = new Question("De eerste woorden van Bart Simpson in \n de serie 'The Simpsons' waren 'Ay carumba'", "True", false);
questionArray[36] = new Question("Al Pacino speelde de hoofdrol in Scarface", "True", false);
questionArray[37] = new Question("Angelina Jolie speelde de hoofdrol in de film 'Pretty Woman'", "False", false);
questionArray[38] = new Question("De achternaam van Rocky in de boksfilm 'Rocky' was 'Balboa'", "True", false);
questionArray[39] = new Question("Jennifer Aniston speelt de rol van 'Samantha' \n in de serie 'Sex and the City'", "False", false);

questionArray[40] = new Question("Diamant is de hardste natuurlijke stof", "True", false);
questionArray[41] = new Question("Bar is de natuurkundige eenheid van druk", "True", false);
questionArray[42] = new Question("Een atoom is groter dan een molecuul", "False", false);
questionArray[43] = new Question("Thomas Edison was de uitvinder van de elektriciteit", "True", false);
questionArray[44] = new Question("Een stukje driehoekig glas waarmee je wit \n licht kan scheiden noemen we een Prisma", "True", false);
questionArray[45] = new Question("Een schaduw wordt groter als het voorwerp \n verder van de lichtbron wordt gezet", "False", false);
questionArray[46] = new Question("Rood, blauw en geel zijn de primaire kleuren", "False", false);
questionArray[47] = new Question("Van Diamant maakt men Koolstof", "True", false);
questionArray[48] = new Question("E=mg² is de beroemde formule van Einstein", "False", false);
questionArray[49] = new Question("Goud heeft als chemisch symbool Ag", "False", false);

questionArray[50] = new Question("Phasmaphobia is vrees voor spoken", "True", false);
questionArray[51] = new Question("Het Himalayagebergte heeft als vertaling \n 'De woonplaats van sneeuw'", "True", false);
questionArray[52] = new Question("'D.C.' in Washington D.C. staat voor \n 'District of Colombia'", "True", false);
questionArray[53] = new Question("1 inch is 2,54 cm", "True", false);
questionArray[54] = new Question("In Romaanse cijfers staat de 'D' voor 1000", "False", false);
questionArray[55] = new Question("Een triljoen heeft 18 nullen", "True", false);
questionArray[56] = new Question("Rood, wit en blauw zijn de kleuren \n van de Poolse vlag", "False", false);
questionArray[57] = new Question("Posseidon was de oppergod in de Griekse mythologie", "False", false);
questionArray[58] = new Question("De EU vlag telt 14 sterren", "False", false);
questionArray[59] = new Question("Via de Schaal van Richter meet men \n de hevigheid van een orkaan", "False", false);

questionArray[60] = new Question("De Mona Lisa staat in het Louvre", "True", false);
questionArray[61] = new Question("Viktor was de eerste naam van Frankenstein", "True", false);
questionArray[62] = new Question("Een limeriek telt 7 regels", "False", false);
questionArray[63] = new Question("Don Diega de la Vega is beter gekend als Zorro", "True", false);
questionArray[64] = new Question("Pablo Picasso had de Italiaanse nationaliteit", "False", false);
questionArray[65] = new Question("William Shakespeare stierf in de 18de eeuw", "False", false);
questionArray[66] = new Question("Da Vinci schilderde het schilderij 'The Last Supper'", "True", false);
questionArray[67] = new Question("Jimmy Jolka is de naam van de baas van de chocoladefabriek \n in het boek 'Sjakie en de chocoladefabriek'", "False", false);
questionArray[68] = new Question("Rudyard Kipling is de schrijver van Jungle Book", "True", false);
questionArray[69] = new Question("Kapitein Haak stierf door een inktvis", "False", false);
    
    }
       
    public void revange()
    {
        revange.setVisible(false);
        
        btn1.setBackground(new DVBColor(0,0,0,220));
        btn2.setBackground(new DVBColor(0,0,0,220));
        btn3.setBackground(new DVBColor(0,0,0,220));
        btn4.setBackground(new DVBColor(0,0,0,220));
        btn5.setBackground(new DVBColor(0,0,0,220));
        btn6.setBackground(new DVBColor(0,0,0,220));
        btn7.setBackground(new DVBColor(0,0,0,220));
        btn8.setBackground(new DVBColor(0,0,0,220));
        btn9.setBackground(new DVBColor(0,0,0,220));
        
        btn5.requestFocus();
        
        spelerSwitch();
        
        Btn1Enable = true;
        Btn2Enable = true;
        Btn3Enable = true;
        Btn4Enable = true;
        Btn5Enable = true;
        Btn6Enable = true;
        Btn7Enable = true;
        Btn8Enable = true;
        Btn9Enable = true;
        NoButtonLeft = false;
        vraaglabel.setTextContent("", HState.NORMAL_STATE);
        
        ButtonCol1 = "Zwart";
        ButtonCol2 = "Zwart";
        ButtonCol3 = "Zwart";
        ButtonCol4 = "Zwart";
        ButtonCol5 = "Zwart";
        ButtonCol6 = "Zwart";
        ButtonCol7 = "Zwart";
        ButtonCol8 = "Zwart";
        ButtonCol9 = "Zwart";
        CanWin = false;
        EnemyCanWin = false;
        drawScoreRood = 0;
        drawScoreBlauw = 0;
        
        for(int i=0;i < 70;i++)
        {
            questionArray[i].used = false;
        }
        
    }
    
    public void checkForDraw()
    {
        if(ButtonCol1 != "Zwart" && ButtonCol2 != "Zwart" && ButtonCol3 != "Zwart" && ButtonCol4 != "Zwart" && ButtonCol5 != "Zwart" && ButtonCol6 != "Zwart" && ButtonCol7 != "Zwart" && ButtonCol8 != "Zwart" && ButtonCol9 != "Zwart" )
        {
            //meeste knoppen
            if(ButtonCol1 == "Blauw")
            { drawScoreBlauw ++;
            }
            else
            {drawScoreRood ++;
            }
            if(ButtonCol2 == "Blauw")
            {drawScoreBlauw ++;
            }
            else
            {drawScoreRood ++;
            }
            if(ButtonCol3 == "Blauw")
            {drawScoreBlauw ++;
            }
            else
            {drawScoreRood ++;
            }
            if(ButtonCol4 == "Blauw")
            {drawScoreBlauw ++;
            }
            else
            {drawScoreRood ++;
            }
            if(ButtonCol5 == "Blauw")
            {
            drawScoreBlauw ++;}
            else
            {drawScoreRood ++;
            }
            if(ButtonCol6 == "Blauw")
            {drawScoreBlauw ++;
            }
            else
            {drawScoreRood ++;
            }
            if(ButtonCol7 == "Blauw")
            {drawScoreBlauw ++;
            }
            else
            {drawScoreRood ++;
            }
            if(ButtonCol8 == "Blauw")
            {drawScoreBlauw ++;
            }
            else
            {drawScoreRood ++;
            }
            if(ButtonCol9 == "Blauw")
            {drawScoreBlauw ++;
            }
            else
            {drawScoreRood ++;
            }
            
            
            if(drawScoreBlauw > drawScoreRood)
            {
                    scoreblauw += 50;
                    bluelabel.setTextContent("Speler 2" + "\n" + "€ " + scoreblauw , HState.NORMAL_STATE);
                    vraaglabel.setTextContent("SPELER 2 IS GEWONNEN", HState.NORMAL_STATE);
                    btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
            }
            else
            {
                    scorerood += 50;
                    redlabel.setTextContent("Speler 1" + "\n" + "€ " + scorerood , HState.NORMAL_STATE);
                    vraaglabel.setTextContent("SPELER 1 IS GEWONNEN", HState.NORMAL_STATE);
                    btnTrue.setVisible(false);
                    btnFalse.setVisible(false);
                    revange.setVisible(true);
                    revange.requestFocus();
            
            
            }
            
           
        }
    }
    
    
}


        
    
       
    

