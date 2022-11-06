package Szakdoga;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;


public class UserInterFace extends JFrame implements ActionListener {
    
    JLayeredPane layeredPane=new JLayeredPane();

    JLabel character;
    JLabel door;
    JLabel door2;
    JLabel merchant;

    ArrayList<JLabel> labelecske= new ArrayList<JLabel>();
    ArrayList<ImageIcon> kepecske =new ArrayList<ImageIcon>();
    //ArrayList<String> area = new ArrayList<String>(Arrays.asList(" field!"," moon!"," campfire!"));

    Action Leftaction;
    Action Rightaction;
    Action Dooraction;
    Action Attackaction;
    Action TalkAction;

    int IDLEFT=0;
    int IDRIGHT=0;
    int XVelocity=10;
    int YVelocity=10;

    boolean Isitrightside=true;

    JMenuBar menu;
    JMenu filemenu;
    JMenu helpmenu;
    JMenuItem exitItem;
    JMenuItem controlsItem;
    ImageIcon exit;

    ImageIcon charjobbicon=createImageIcon("images/runright.png", "runright");
    ImageIcon charbalicon=createImageIcon("images/runleft.png", "runleft");
    ImageIcon charattackricon=createImageIcon("images/attackright.png", "attackright");
    ImageIcon charattacklicon=createImageIcon("images/attackleft.png", "attackleft");  
    ImageIcon dooricon=createImageIcon("images/door.png", "door");
    ImageIcon dooricon2=createImageIcon("images/door.png", "door2");
    ImageIcon merchanticon=createImageIcon("images/merchant1.png", "merchant");
    
    UserInterFace()
    {

        menubar();
        Scene();
        keybinding();
        createWindow();
        
    }

    public void createWindow()
    {
        ImageIcon logo = createImageIcon("images/logo.png", "logo");
        this.setIconImage(logo.getImage());
        this.setTitle("Szakdoga");
        this.pack();
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void labelset(JLabel backgrounds,ImageIcon image/* ,String settext */)
    {
        backgrounds.setBounds(0,0,1200,700);
        //backgrounds.setText("Welcome to the"+settext);
        backgrounds.setText("Press and Hold 'A' to go left or 'D' to go right!");
        backgrounds.setHorizontalTextPosition(JLabel.CENTER);
        backgrounds.setVerticalTextPosition(JLabel.BOTTOM);
        backgrounds.setForeground(Color.black);
        backgrounds.setFont(new Font("MV Boli", Font.BOLD, 20));
        backgrounds.setIconTextGap(0);
        backgrounds.setOpaque(true);
        
    }

    public void Scene()
    {

        for(int i=1;i<=3;i++)
        {
            kepecske.add(createImageIcon("images/back"+i+".jpg","mengyan"));
            labelecske.add(new JLabel(kepecske.get(i-1)));
        }
        for (int i=0;i<3;i++) {
            labelset(labelecske.get(i),kepecske.get(i)/*," field!"*/);
        }

        character=new JLabel(charjobbicon);
        character.setBounds(543,513,127,146);

        door=new JLabel(dooricon);
        door.setBounds(0,513,86,146);
        door.setOpaque(true);

        door2=new JLabel(dooricon2);
        door2.setBounds(1114,513,86,146);
        door2.setOpaque(true);

        merchant=new JLabel(merchanticon);
        merchant.setBounds(300,513,127,146);
        merchant.setVisible(false);

        layeredPane.add(labelecske.get(0),Integer.valueOf(0));
        layeredPane.add(labelecske.get(1),Integer.valueOf(0));
        layeredPane.add(labelecske.get(2),Integer.valueOf(0));
        layeredPane.add(character,Integer.valueOf(1));
        layeredPane.add(door,Integer.valueOf(1));
        layeredPane.add(door2,Integer.valueOf(1));
        layeredPane.add(merchant,Integer.valueOf(1));
        layeredPane.setPreferredSize(new Dimension(1200,700));

        this.add(layeredPane);

    }

    public void keybinding()
    {
        Leftaction=new Leftaction();
        Rightaction=new Rightaction();
        Dooraction=new Dooraction();
        Attackaction=new Attackaction();

        character.getInputMap().put(KeyStroke.getKeyStroke("A"),"leftAction");
        character.getActionMap().put("leftAction", Leftaction);
        character.getInputMap().put(KeyStroke.getKeyStroke("D"),"rightAction");
        character.getActionMap().put("rightAction", Rightaction);
        character.getInputMap().put(KeyStroke.getKeyStroke("F"),"doorAction");
        character.getActionMap().put("doorAction", Dooraction);
        character.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"attackAction");
        character.getActionMap().put("attackAction", Attackaction);
    }
    public class Leftaction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(character.getX()==1083 || character.getX()==993 || character.getX()==93)
            {
                XVelocity=10;
            }
                character.setIcon(charbalicon);
                Isitrightside=false;

            character.setLocation(character.getX()-XVelocity,character.getY());
            System.out.println("Jani balra megy:"+character.getX()+","+character.getY());
            if(character.getX()<93)
            {
                for(JLabel i:labelecske)
                {
                    i.setText("Press F to Open door!");
                }
                if(IDLEFT==2)
                {
                    labelecske.get(2).setText("You can't go there!");
                }
                
            }
            if((character.getX()<=463 && character.getX()>300) && (IDRIGHT==1 || IDLEFT==1))
            {
                labelecske.get(1).setText("Press E to talk with merchant!");
            }
            if(character.getX()>463)
            {
                labelecske.get(1).setText("Use 'SPACE' to attack!");
            }
            if(character.getX()<1000 && character.getX()>93)
            {
                labelecske.get(0).setText("Press and Hold 'A' to go left or 'D' to go right!");
                
                labelecske.get(2).setText("Thanks for playing! ");
                /*for(int i=0;i<=2;i++)
                {
                    labelecske.get(i).setText("Welcome to the"+area.get(i));
                }*/
            }
            if(character.getX()==3)
            {
                XVelocity=0;
            }
            
        }
        
    }
    public class Rightaction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(character.getX()==3 || character.getX()==993 || character.getX()==93)
            {
                XVelocity=10;
            }

            character.setIcon(charjobbicon);
            Isitrightside=true;

            character.setLocation(character.getX()+XVelocity,character.getY());
            System.out.println("Jani jobbra megy :"+character.getX()+","+character.getY());

            if(character.getX()>1000)
            {
                if(IDRIGHT==0)
                {
                    labelecske.get(0).setText("You can't go there!");
                }
                else
                {
                    for(JLabel i:labelecske)
                    {
                        i.setText("Press F to Open door!");
                    }
                }
            }
            if(character.getX()>93 && character.getX()<1000)
            {
                labelecske.get(0).setText("Press and Hold 'A' to go left or 'D' to go right!");
                labelecske.get(1).setText("Use 'SPACE' to attack!");
                labelecske.get(2).setText("Thanks for playing! ");
                /*for(int i=0;i<=2;i++)
                {
                    labelecske.get(i).setText("Welcome to the"+area.get(i));
                }*/
            }
            if(character.getX()==1083)
            {
                XVelocity=0;
            }
            
        }
        
    }
    public class Dooraction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(character.getX()<93)
            {
                if(IDLEFT==0)
                {
                    labelecske.get(0).setVisible(false);
                    labelecske.get(1).setVisible(true);
                    merchant.setVisible(true);
                    character.setLocation(993,513);
                    IDLEFT++;
                    IDRIGHT++;
                }
                else if( IDLEFT==1)
                {
                    labelecske.get(1).setVisible(false);
                    labelecske.get(2).setVisible(true);
                    merchant.setVisible(false);
                    character.setLocation(993,513);
                    
                    IDLEFT++;
                    IDRIGHT++;
                    
                }

            }
            if(character.getX()>1000)
            {
                if(IDRIGHT==1)
                {
                    labelecske.get(0).setVisible(true);
                    labelecske.get(1).setVisible(false);
                    merchant.setVisible(false);
                    character.setLocation(93,513);
                    IDLEFT--;
                    IDRIGHT--;
                    
                }
                if(IDRIGHT==2)
                {
                    labelecske.get(1).setVisible(true);
                    labelecske.get(2).setVisible(false);
                    merchant.setVisible(true);
                    character.setLocation(93,513);
                    IDLEFT--;
                    IDRIGHT--;
                }
            }

        }

    }
    public class Attackaction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(Isitrightside==true)
            {
                character.setIcon(charattackricon);
            }
            else
            {
                character.setIcon(charattacklicon);
            }
        }

    }
    public class Talkaction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }

    }

    public void menubar()
    {


        exit=createImageIcon("images/exit.png", "exit");

        filemenu= new JMenu("File");

        exitItem=new JMenuItem();

        exitItem.addActionListener(this);

        exitItem.setMnemonic(KeyEvent.VK_E); //e for exit

        filemenu.setMnemonic(KeyEvent.VK_F); //Alt+f for file

        exitItem.setIcon(exit);

        filemenu.add(exitItem);

        menu=new JMenuBar();
        menu.add(filemenu);

        this.setLayout(new FlowLayout());
        this.setJMenuBar(menu);
    }

    protected static ImageIcon createImageIcon(String path,String description) 
    {
        java.net.URL imgURL = UserInterFace.class.getResource(path);
        if (imgURL != null) 
        {
        return new ImageIcon(imgURL, description);
        }   
        else 
        {
        System.err.println("Couldn't find file: " + path);
        return null;
        }
    }

    //menubar actionperformer
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exitItem) 
        {
            System.exit(0);
        }
    }
}
