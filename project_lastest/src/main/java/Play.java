
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lolihuntz
 */
public class Play extends javax.swing.JFrame {

    // construct
    private static String name1, name2;
    private static String deckname1, deckname2;
    private String name_temp;
    private int heropowercount, atkcard, atkscore, maxhp1, currenthp1, maxhp2, currenthp2, atkcount, countcard, rank1, rank2, mana, cardmax = 10, temp, reducecost;
    private ArrayList<String> deck1, deck2;
    private ArrayList<defcard> carddef1, carddef2;
    private ArrayList<card> cardhand1, cardhand2;
    private hero hero1, hero2;
    private int plusatk, showwidth = 280, showheight = 360;
    private int directon, refleckon, moreoneon, attackon, cannotatk, counteron, norankupon, cannotdrawon, notdraw, norankup, noatk, flipon, turn, firstturn, side, onlyoneatk;
    private Random rand = new Random();
    private boolean isEnd = false;
    /**
     * Creates new form Play
     *
     * @param n1
     * @param n2
     * @param d1
     * @param d2
     */
    /* @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        JFrame f = new JFrame();
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            int a = JOptionPane.showConfirmDialog(f, "Do you want to surrender?");
            if (a == JOptionPane.YES_OPTION) {
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
    }*/
    public Play(String n1, String n2, String d1, String d2) {

        // construct
        name1 = n1;
        name2 = n2;
        deckname1 = d1;
        deckname2 = d2;
        System.out.println(deckname1);
        // construct
        deck1 = new ArrayList<String>();
        deck2 = new ArrayList<String>();
        carddef1 = new ArrayList<defcard>();
        carddef2 = new ArrayList<defcard>();
        cardhand1 = new ArrayList<card>();
        cardhand2 = new ArrayList<card>();
        makedeck(deckname1, 1);
        makedeck(deckname2, 2);
        turn = 1;
        System.out.println("Deck1");
        for (int i = 0; i < deck1.size(); i++) {
            System.out.println(deck1.get(i));
        }
        try {
            Scanner scan1 = new Scanner(new File(System.getProperty("user.dir")+"/deck/"+deckname1 + ".txt"));
            Scanner scan2 = new Scanner(new File(System.getProperty("user.dir")+"/deck/"+deckname2 + ".txt"));
            String heroname1 = scan1.next();
            String heroname2 = scan2.next();
            //debug
            System.out.println("hero 1 = " + heroname1);
            System.out.println("hero 2 = " + heroname2);
            //debug
            if (heroname1.equals("auden")) {
                hero1 = new hero(1);
                maxhp1 = 7;
            } else if (heroname1.equals("dica")) {
                hero1 = new hero(2);
                maxhp1 = 6;
            } else if (heroname1.equals("natus")) {
                hero1 = new hero(3);
                maxhp1 = 6;
            }
            if (heroname2.equals("auden")) {
                hero2 = new hero(1);
                maxhp2 = 7;
            } else if (heroname2.equals("dica")) {
                hero2 = new hero(2);
                maxhp2 = 6;
            } else if (heroname2.equals("natus")) {
                hero2 = new hero(3);
                maxhp2 = 6;
            }
            hero1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (atkscore > 0 && atkcount > 0 && noatk == 0 && turn == 1) {
                        Attackphase();
                    } else if (turn == 2 && attackon == 1 && carddef2.isEmpty() && noatk == 0) {
                        losehp(atkscore, 2);
                        atkcount--;
                        if (atkcount == 0) {
                            attackon = 0;
                        }
                    } else if (turn == 1 && attackon == 1 && directon == 1) {
                        losehp(atkscore, 2);
                        atkcount--;
                        if (atkcount == 0) {
                            attackon = 0;
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ImageIcon img;
                    if (turn == 2) {
                        img = new ImageIcon(hero2.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                    } else {
                        img = new ImageIcon(hero1.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                    }
                    showLabel.setIcon(img);
                    showLabel.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    showLabel.setVisible(false);
                }
            });
            hero2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (atkscore > 0 && atkcount > 0 && noatk == 0 && turn == 2) {
                        Attackphase();
                    } else if (turn == 1 && attackon == 1 && noatk == 0 && carddef2.isEmpty()) {
                        losehp(atkscore, 2);
                        atkcount--;
                        if (atkcount == 0) {
                            attackon = 0;
                        }
                    } else if (turn == 1 && attackon == 1 && directon == 1) {
                        losehp(atkscore, 2);
                        atkcount--;
                        if (atkcount == 0) {
                            attackon = 0;
                        }
                    }

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ImageIcon img;
                    if (turn == 1) {
                        img = new ImageIcon(hero2.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                    } else {
                        img = new ImageIcon(hero1.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                    }
                    showLabel.setIcon(img);
                    showLabel.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    showLabel.setVisible(false);
                }
            });
            mana = 3;
            atkscore = 0;
            atkcount = 0;
            cannotatk = 0;
            side = 0;
            onlyoneatk = 1;
            currenthp1 = maxhp1;
            currenthp2 = maxhp2;
            moreoneon = 0;
            flipon = 0;
            directon = 0;
            plusatk = 0;
            cannotdrawon = 0;
            counteron = 0;
            countcard = 0;
            refleckon = 0;
            turn = 1;
            firstturn = 1;
            norankupon = 0;
            attackon = 0;
            reducecost = 0;
            notdraw = 0;
            norankup = 0;
            noatk = 0;
            heropowercount = 1;
            rank1 = 1;
            rank2 = 1;

            // init of drag drop
            initComponents();
            // init of drag drop
            heroPanel1.add(hero1);
            heroPanel2.add(hero2);
            repaint();
            validate();
            for (int i = 0; i < 3; i++) {
                drawcard(1);
                drawcard(2);
            }
            drawcard(1);
            deckarea1.setText("" + deck1.size());
            deckarea2.setText("" + deck2.size());
            repaint();
            validate();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentpane = new javax.swing.JPanel();
        defPanel1 = new javax.swing.JPanel();
        heroPanel1 = new javax.swing.JPanel();
        handPanel1 = new javax.swing.JPanel();
        handPanel2 = new javax.swing.JPanel();
        heroPanel2 = new javax.swing.JPanel();
        defPanel2 = new javax.swing.JPanel();
        atk = new javax.swing.JLabel();
        hp1 = new javax.swing.JLabel();
        hp2 = new javax.swing.JLabel();
        deckarea1 = new javax.swing.JLabel();
        deckarea2 = new javax.swing.JLabel();
        manaarea = new javax.swing.JLabel();
        rankarea1 = new javax.swing.JLabel();
        rankarea2 = new javax.swing.JLabel();
        endturn = new javax.swing.JButton();
        showLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        line2 = new javax.swing.JLabel();
        nameLabel1 = new javax.swing.JLabel();
        nameLabel2 = new javax.swing.JLabel();
        p2_hp = new javax.swing.JLabel();
        p2_rank = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        line1 = new javax.swing.JLabel();
        line = new javax.swing.JLabel();
        heropower = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        line3 = new javax.swing.JLabel();
        showCh = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Play");
        setResizable(false);

        contentpane.setBackground(new java.awt.Color(0, 204, 102));
        contentpane.setPreferredSize(new java.awt.Dimension(1280, 720));
        contentpane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contentpaneKeyPressed(evt);
            }
        });
        contentpane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        defPanel1.setBackground(new java.awt.Color(255, 255, 255));
        defPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        defPanel1.setOpaque(false);
        contentpane.add(defPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 510, 110));

        heroPanel1.setBackground(new java.awt.Color(255, 255, 255));
        heroPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        heroPanel1.setOpaque(false);
        contentpane.add(heroPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 110, 140));

        handPanel1.setBackground(new java.awt.Color(255, 255, 255));
        handPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        handPanel1.setOpaque(false);
        contentpane.add(handPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 630, 710, 90));

        handPanel2.setBackground(new java.awt.Color(255, 255, 255));
        handPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        handPanel2.setOpaque(false);
        contentpane.add(handPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 710, 90));

        heroPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        heroPanel2.setOpaque(false);
        contentpane.add(heroPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, 110, 140));

        defPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        defPanel2.setOpaque(false);
        contentpane.add(defPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 510, 110));

        atk.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        atk.setForeground(new java.awt.Color(255, 255, 255));
        atk.setText("0");
        contentpane.add(atk, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 550, -1, 30));

        hp1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        hp1.setText("" + maxhp1);
        contentpane.add(hp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, -1, -1));

        hp2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        hp2.setForeground(new java.awt.Color(255, 0, 0));
        hp2.setText("" + maxhp2);
        contentpane.add(hp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 125, -1, -1));

        deckarea1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        deckarea1.setForeground(new java.awt.Color(255, 255, 255));
        deckarea1.setText("??");
        contentpane.add(deckarea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 530, -1, -1));

        deckarea2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        deckarea2.setForeground(new java.awt.Color(255, 255, 255));
        deckarea2.setText("??");
        contentpane.add(deckarea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 175, -1, -1));

        manaarea.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        manaarea.setForeground(new java.awt.Color(255, 255, 255));
        manaarea.setText("3");
        contentpane.add(manaarea, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, -1, 30));

        rankarea1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rankarea1.setForeground(new java.awt.Color(255, 255, 153));
        rankarea1.setText("1");
        contentpane.add(rankarea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 630, -1, -1));

        rankarea2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        rankarea2.setForeground(new java.awt.Color(204, 102, 255));
        rankarea2.setText("1");
        contentpane.add(rankarea2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 73, -1, -1));

        endturn.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        endturn.setText("endturn");
        endturn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        endturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endturnActionPerformed(evt);
            }
        });
        contentpane.add(endturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 430, -1, -1));

        showLabel.setBackground(new java.awt.Color(102, 204, 255));
        showLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contentpane.add(showLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, 360));

        jLabel1.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("resource/bg/ATK.gif"));
        jLabel1.setText("   atk   :");
        contentpane.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 210, 90));

        jLabel2.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon("resource/bg/HP.gif"));
        jLabel2.setText("  hP    :");
        contentpane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 200, -1));

        jLabel4.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 153));
        jLabel4.setIcon(new javax.swing.ImageIcon("resource/bg/RANK.gif"));
        jLabel4.setText(" rank :");
        contentpane.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, -1, -1));

        jLabel11.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("turn");
        jLabel11.setToolTipText("");
        contentpane.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 650, -1, -1));

        jLabel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        contentpane.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 700));

        jLabel10.setIcon(new javax.swing.ImageIcon("resource/bg/BACK.png"));
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        contentpane.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 500, -1, 100));

        line2.setBackground(new java.awt.Color(255, 255, 255));
        line2.setOpaque(true);
        contentpane.add(line2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 690, 210, 10));

        nameLabel1.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        nameLabel1.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel1.setText(name1);
        contentpane.add(nameLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 600, -1, 50));

        nameLabel2.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        nameLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nameLabel2.setText(name2);
        contentpane.add(nameLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 220, 170, -1));

        p2_hp.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        p2_hp.setForeground(new java.awt.Color(255, 0, 0));
        p2_hp.setText("hp           :");
        contentpane.add(p2_hp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 120, -1, -1));

        p2_rank.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        p2_rank.setForeground(new java.awt.Color(204, 102, 255));
        p2_rank.setText("rank        :");
        contentpane.add(p2_rank, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 70, -1, -1));

        jLabel12.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("card left : ");
        jLabel12.setToolTipText("");
        contentpane.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 170, 150, -1));
        contentpane.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, 210, 260));

        line1.setBackground(new java.awt.Color(255, 255, 255));
        line1.setOpaque(true);
        contentpane.add(line1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 700, 330, 10));

        line.setBackground(new java.awt.Color(255, 255, 255));
        line.setOpaque(true);
        contentpane.add(line, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 590, 290, 10));

        heropower.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        heropower.setIcon(new javax.swing.ImageIcon("resource/bg/HEROPOWER.gif"));
        heropower.setText(" heropower");
        heropower.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        heropower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heropowerActionPerformed(evt);
            }
        });
        contentpane.add(heropower, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 310, -1, -1));

        jLabel3.setFont(new java.awt.Font("DRdeco", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon("resource/bg/MANA.gif"));
        jLabel3.setText("mana LEFT");
        jLabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        contentpane.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 500, -1, -1));

        line3.setBackground(new java.awt.Color(255, 255, 255));
        line3.setOpaque(true);
        contentpane.add(line3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 260, 210, 10));
        contentpane.add(showCh, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 130, 180));

        bg.setForeground(new java.awt.Color(255, 0, 0));
        bg.setIcon(new javax.swing.ImageIcon("resource/bg/PLAY_1.jpg"));
        contentpane.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentpane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentpane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        contentpane.setFocusable(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void heropowerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heropowerActionPerformed
        // TODO add your handling code here:

        if (heropowercount > 0 && mana > 0) {
            System.out.println("Use heropower");
            mana--;
            heropowercount = 0;
            heropower();
            manaarea.setText(Integer.toString(mana));

            repaint();
            validate();
        }
    }//GEN-LAST:event_heropowerActionPerformed

    private void endturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endturnActionPerformed
        // TODO add your handling code here:
        switchside();
    }//GEN-LAST:event_endturnActionPerformed

    private void contentpaneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contentpaneKeyPressed
        //System.out.println(evt.getKeyChar());
        JFrame f = new JFrame();
        int code = evt.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE) {
            int a = JOptionPane.showConfirmDialog(f, "Do you want to surrender?");
            if (a == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(f, "You lose.", "Quick Dialog", JOptionPane.WARNING_MESSAGE);
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
                isEnd=true;
            }
        }
    }//GEN-LAST:event_contentpaneKeyPressed
    public void winloss(int x) {
        JFrame f = new JFrame();
        if (x == 1) {
            JOptionPane.showMessageDialog(f, "You win.", "Quick Dialog", JOptionPane.WARNING_MESSAGE);
        } else if (x == 2) {
            JOptionPane.showMessageDialog(f, "You win.", "Quick Dialog", JOptionPane.WARNING_MESSAGE);
        }
        //linktomainmenu
        this.dispose();
        isEnd=true;
    }

    public void losehp(int dmg, int player) {
        if (player == 1) {
            currenthp1 = currenthp1 - dmg;
            hp1.setText(Integer.toString(currenthp1));
            repaint();
            validate();
            if (currenthp1 <= 0) {
                winloss(2);
            }
        } else if (player == 2) {
            currenthp2 = currenthp2 - dmg;
            hp2.setText(Integer.toString(currenthp2));
            repaint();
            validate();
            if (currenthp2 <= 0) {
                winloss(1);
            }
        }
    }
    
    public boolean isGameEnd(){
        return isEnd;
    }
    
    public void drawtype(String type) {
        if (cardhand1.size() >= 10) {
            return;
        }
        if (deck1.isEmpty()) {
            winloss(1);
        }
        if (type.equals("rankup")) {
            if (deck1.contains("rankup")) {
                card cardmake = new card("rankup");
                cardmake.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (e.getClickCount() == 2 && !e.isConsumed()) {
                            if (cardhand1.contains(cardmake)) {
                                if (canusecard(cardmake) == true) {
                                    actioncard(cardmake);
                                    countcard += 1;
                                    handPanel1.remove(cardmake);
                                    cardhand1.remove(cardmake);
                                    repaint();
                                    validate();
                                }
                            }
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                        //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                        //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                        showLabel.setIcon(img);
                        showLabel.setVisible(true);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        showLabel.setVisible(false);
                    }
                });
                handPanel1.add(cardmake);
                cardhand1.add(cardmake);
                deck1.remove(deck1.indexOf("rankup"));
                Collections.shuffle(deck1);
                repaint();
                validate();
            }
        } else {
            for (int i = 0; i < deck1.size(); i++) {
                char typ = deck1.get(i).charAt(1);
                if (String.valueOf(typ).equals(type)) {
                    card cardmake = new card(deck1.get(i));
                    cardmake.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {
                                if (cardhand1.contains(cardmake)) {
                                    if (canusecard(cardmake) == true) {
                                        actioncard(cardmake);
                                        countcard += 1;
                                        handPanel1.remove(cardmake);
                                        cardhand1.remove(cardmake);
                                        repaint();
                                        validate();
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                            //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                            //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                            showLabel.setIcon(img);
                            showLabel.setVisible(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            showLabel.setVisible(false);
                        }
                    });
                    handPanel1.add(cardmake);
                    cardhand1.add(cardmake);

                    deck1.remove(deck1.get(i));
                    Collections.shuffle(deck1);
                    repaint();
                    validate();
                    break;
                }

            }
        }
    }

    public void gainhp(int heal, int player) {
        if (player == 1) {
            currenthp1 = currenthp1 + heal;
            if (currenthp1 > maxhp1) {
                currenthp1 = maxhp1;
            }
            hp1.setText(Integer.toString(currenthp1));
            repaint();
            validate();
        } else if (player == 2) {
            currenthp2 = currenthp2 + heal;
            if (currenthp2 > maxhp2) {
                currenthp2 = maxhp2;
            }
            hp2.setText(Integer.toString(currenthp2));
            repaint();
            validate();
        }
    }

    public void actioncard(card cardd) {
        int type = cardd.gettype();

        atkcount = 1;
        String code = cardd.getName();
        if (type == 3) {
            if (code.equals("coin")) {
                mana = mana + 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
            } else if (code.equals("rankup")) {
                if (rank1 == 1) {
                    rank1 = 2;
                    currenthp1 = hero1.classup2(currenthp1);
                    rankarea1.setText("2");
                    hp1.setText(Integer.toString(currenthp1));
                    repaint();
                    validate();
                    maxhp1 = hero1.getmaxhp();
                } else if (rank1 == 2) {
                    rank1 = 3;
                    currenthp1 = hero1.classup3(currenthp1);
                    rankarea1.setText("3");
                    hp1.setText(Integer.toString(currenthp1));
                    repaint();
                    validate();
                    maxhp1 = hero1.getmaxhp();
                } else if (rank1 == 3) {

                }

            } else if (code.equals("030101")) {
                Attackphase();
                atkcount++;
            } else if (code.equals("030102")) {
                reducecost = 2;
            } else if (code.equals("030103")) {
                atkscore += atkscore;
            } else if (code.equals("030201")) {
                flipon = 1;
            } else if (code.equals("030202")) {
                int max = 0;
                for (int i = 0; i < carddef1.size(); i++) {
                    if (carddef1.get(i).getdef() > max) {
                        max = carddef1.get(i).getdef();
                    }
                }
                gainhp(max, 1);
            } else if (code.equals("030203")) {
                counteron = 1;
            } else if (code.equals("030301")) {
                int numrand;
                for (int i = 0; i < 2; i++) {
                    if (cardhand1.size() >= 10) {
                        break;
                    }

                    numrand = Math.abs(rand.nextInt()) % cardhand2.size();
                    String cardname = cardhand2.get(numrand).getName();
                    card cardmake = new card(cardname);
                    cardmake.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {
                                if (cardhand1.contains(cardmake)) {
                                    if (canusecard(cardmake) == true) {
                                        actioncard(cardmake);
                                        countcard += 1;
                                        handPanel1.remove(cardmake);
                                        cardhand1.remove(cardmake);
                                        repaint();
                                        validate();
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                            //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                            //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                            showLabel.setIcon(img);
                            showLabel.setVisible(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            showLabel.setVisible(false);
                        }
                    });
                    if (cardhand1.size() < 10) {
                        cardhand1.add(cardmake);
                        handPanel1.add(cardmake);
                    }
                    repaint();
                    validate();
                }
            } else if (code.equals("030302")) {
                for (int i = 0; i < 5; i++) {
                    card cardmake = new card("coin");
                    cardmake.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {
                                if (cardhand1.contains(cardmake)) {
                                    if (canusecard(cardmake) == true) {
                                        actioncard(cardmake);
                                        countcard += 1;
                                        handPanel1.remove(cardmake);
                                        cardhand1.remove(cardmake);
                                        repaint();
                                        validate();
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                            //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                            //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                            showLabel.setIcon(img);
                            showLabel.setVisible(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            showLabel.setVisible(false);
                        }
                    });
                    if (cardhand1.size() < 10) {
                        cardhand1.add(cardmake);
                        handPanel1.add(cardmake);
                    }
                    repaint();
                    validate();
                }
            } else if (code.equals("030303")) {
                defPanel1.removeAll();
                defPanel2.removeAll();
                while (!carddef1.isEmpty()) {

                    String namecard = carddef1.get(0).getName();
                    card cardmake = new card(namecard);
                    cardmake.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {
                                if (cardhand1.contains(cardmake)) {
                                    if (canusecard(cardmake) == true) {
                                        actioncard(cardmake);
                                        countcard += 1;
                                        handPanel1.remove(cardmake);
                                        cardhand1.remove(cardmake);
                                        repaint();
                                        validate();
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                            //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                            //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                            showLabel.setIcon(img);
                            showLabel.setVisible(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            showLabel.setVisible(false);
                        }
                    });
                    if (cardhand1.size() < 10) {
                        cardhand1.add(cardmake);
                        handPanel1.add(cardmake);
                    }
                    carddef1.remove(0);
                    repaint();
                    validate();

                }
                while (!carddef2.isEmpty()) {
                    String namecard = carddef2.get(0).getName();
                    card cardmake = new card(namecard);
                    cardmake.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {
                                if (cardhand1.contains(cardmake)) {
                                    if (canusecard(cardmake) == true) {
                                        actioncard(cardmake);
                                        countcard += 1;
                                        handPanel1.remove(cardmake);
                                        cardhand1.remove(cardmake);
                                        repaint();
                                        validate();
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                            //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                            //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                            showLabel.setIcon(img);
                            showLabel.setVisible(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            showLabel.setVisible(false);
                        }
                    });
                    if (cardhand2.size() < 10) {
                        cardhand2.add(cardmake);
                        handPanel2.add(cardmake);
                    }
                    carddef2.remove(0);
                    repaint();
                    validate();

                }
            } else if (code.equals("030001")) {
                norankupon = 1;
            } else if (code.equals("030002")) {
                gainhp(1, 1);
            } else if (code.equals("030003")) {
                drawtype("rankup");
            } else if (code.equals("030004")) {
                cannotdrawon = 1;
            } else if (code.equals("030005")) {
                drawcard(1);
                drawcard(1);
            } else if (code.equals("030006")) {
                gainhp(3, 1);
            } else if (code.equals("030007")) {
                cannotatk = 1;
            } else if (code.equals("030008")) {
                if (!cardhand2.isEmpty()) {
                    int randnum = Math.abs(rand.nextInt()) % cardhand2.size();
                    cardhand2.remove(randnum);
                    handPanel2.removeAll();
                    for (int i = 0; i < cardhand2.size(); i++) {
                        handPanel2.add(cardhand2.get(i));
                    }
                }
            } else if (code.equals("030009")) {
                atkscore += 2;
            } else if (code.equals("030010")) {
                if (!cardhand1.isEmpty()) {
                    int randnum = Math.abs(rand.nextInt()) % cardhand1.size();
                    cardhand1.remove(randnum);
                    handPanel1.removeAll();
                    for (int i = 0; i < cardhand1.size(); i++) {
                        handPanel1.add(cardhand1.get(i));
                    }
                    mana += 1;
                } else {
                    mana += 1;
                }
            } else if (code.equals("030011")) {
                if (!cardhand1.isEmpty()) {
                    int randnum = Math.abs(rand.nextInt()) % cardhand1.size();
                    cardhand1.remove(randnum);
                    handPanel1.removeAll();
                    for (int i = 0; i < cardhand1.size(); i++) {
                        handPanel1.add(cardhand1.get(i));
                    }
                    drawcard(1);
                } else {
                    drawcard(1);
                }
            } else if (code.equals("030012")) {
                gainhp(6, 1);
            } else if (code.equals("030013")) {
                drawtype("1");
                drawtype("2");
                drawtype("3");
            } else if (code.equals("030014")) {
                defPanel2.removeAll();
                carddef2.clear();
                repaint();
                validate();
            }
        } else if (type == 2) {
            setdefend(cardd);
        } else if (type == 1) {

            if (code.equals("010101")) {
                atkcount = 1;
                atkscore += 3;
            } else if (code.equals("010102")) {
                atkcount = 1;
                atkscore += 3;
                if (!cardhand1.isEmpty()) {
                    dishand1();
                    atkscore += 2;
                }
            } else if (code.equals("010103")) {
                atkcount = 1;
                atkscore += 5;
                side = 1;
            } else if (code.equals("010201")) {
                atkcount = 1;
                atkscore += 2;
                atkscore += maxhp1 - currenthp1;
            } else if (code.equals("010202")) {
                atkcount = 1;
                atkscore += 3;
                defPanel2.removeAll();
                for (int i = 0; i < carddef2.size(); i++) {
                    carddef2.get(i).show();
                    defPanel2.add(carddef2.get(i));
                }
                repaint();
                validate();
            } else if (code.equals("010203")) {
                atkcount = 1;
                atkscore += 4;
                for (int i = 0; i < 2; i++) {
                    if (carddef1.size() >= 3) {
                        break;
                    }
                    while (true) {
                        int no = Math.abs(rand.nextInt()) % 4;
                        int clas = Math.abs(rand.nextInt()) % 7;
                        clas += 1;
                        if (no > 0 && clas <= 3) {
                            String coderand = "020" + Integer.toString(no) + "0" + Integer.toString(clas);
                            card cardrand = new card(coderand);
                            setdefend(cardrand);
                            break;
                        } else if (no == 0) {
                            String coderand = "020" + Integer.toString(no) + "0" + Integer.toString(clas);
                            card cardrand = new card(coderand);
                            setdefend(cardrand);
                            break;
                        }
                    }
                }

            } else if (code.equals("010301")) {
                atkcount = 1;
                atkscore += 1 + countcard;
            } else if (code.equals("010302")) {
                atkscore += 2;
                atkcount = 2;
            } else if (code.equals("010303")) {
                directon = 1;
                atkscore += 4;
            } else if (code.equals("010001")) {
                atkscore += 2;
            } else if (code.equals("010002")) {
                atkscore += 3;
                dishand1();
            } else if (code.equals("010003")) {
                atkscore += 2;
                gainhp(atkscore, 1);
            } else if (code.equals("010004")) {
                atkscore += 3;
                defPanel2.removeAll();
                int no = Math.abs(rand.nextInt()) % carddef2.size();
                carddef2.remove(no);
                for (int i = 0; i < carddef2.size(); i++) {
                    carddef2.get(i).flip();
                    defPanel2.add(carddef2.get(i));
                }
                repaint();
                validate();
            } else if (code.equals("010005")) {
                atkscore += 4;
                refleckon = 1;
            } else if (code.equals("010006")) {
                atkscore += 7;
            } else if (code.equals("010007")) {
                atkscore += 1;
                moreoneon = 1;
            }
        }
        atk.setText(Integer.toString(atkscore));
        repaint();
        validate();
    }

    public void dishand1() {
        if (!cardhand1.isEmpty()) {
            handPanel1.removeAll();
            int disnum = Math.abs(rand.nextInt()) % cardhand1.size();
            cardhand1.remove(disnum);
            for (int i = 0; i < cardhand1.size(); i++) {
                handPanel1.add(cardhand1.get(i));
            }
        }
    }

    public void setdefend(card cardd) {
        if (carddef1.size() == 3) {
            return;
        }
        defcard defcardd = new defcard(cardd.getName());
        defcardd.flip();
        defcardd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (attackon == 1) {
                    if (carddef2.contains(defcardd)) {
                        defcardd.show();
                        abilitydef(defcardd);
                        if (defcardd.beattacked() == 0) {
                            carddef2.remove(defcardd);
                            defPanel2.remove(defcardd);
                        }
                        if (side == 1) {
                            defPanel2.removeAll();
                            for (int i = 0; i < carddef2.size(); i++) {
                                carddef2.get(i).show();
                                abilitydef(defcardd);
                                if (atkscore > defcardd.getdef()) {
                                    gainhp(atkscore - defcardd.getdef(), 2);

                                }
                            }
                        }
                        atkcount--;
                        if (atkcount == 0) {
                            attackon = 0;
                        }
                    }
                    repaint();
                    validate();
                } else if (flipon == 1) {
                    defcardd.show();
                    flipon = 0;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                boolean yes = false;
                if (carddef1.contains(defcardd)) {
                    yes = true;
                }
                ImageIcon img = new ImageIcon(defcardd.getImageIcon(yes).getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                showLabel.setIcon(img);
                showLabel.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                showLabel.setVisible(false);
            }
        });
        carddef1.add(defcardd);
        defPanel1.add(defcardd);
        repaint();
        validate();
    }

    /**
     *
     * @param defcardd
     */
    public void abilitydef(defcard defcardd) {
        int defscore = defcardd.getdef();
        if (moreoneon == 1) {
            atkscore += defscore;
        }
        if (atkscore > defscore || side == 2) {
            losehp(atkscore - defscore, 2);

        }
        if (refleckon == 1 || counteron == 1) {
            losehp(Math.abs(atkscore - defscore), 1);
        }
        String code = defcardd.getName();
        if (code.equals("020102")) {
            if (atkscore < defscore) {
                losehp(defscore - atkscore, 1);
            }
        } else if (code.equals("020103")) {
            plusatk = 5;
        } else if (code.equals("020201")) {
            defPanel2.removeAll();
            for (int i = 0; i < carddef2.size(); i++) {
                carddef2.get(i).gaindef(2);
                defPanel2.add(carddef2.get(i));
            }
        } else if (code.equals("020202")) {
            if (atkscore > defscore) {
                gainhp(atkscore - defscore, 2);

            }
        } else if (code.equals("020203")) {
            if (atkscore < defscore) {
                defcardd.negageattacked();
                defcardd.gaindef(1);
            }
        } else if (code.equals("020301")) {
            for (int i = 0; i < 2; i++) {
                if (cardhand2.size() >= 10) {
                    break;
                }
                card coin = new card("coin");
                coin.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (e.getClickCount() == 2 && !e.isConsumed()) {
                            if (e.getY() > defPanel1.getY()) {
                                if (canusecard(coin) == true) {
                                    actioncard(coin);
                                    countcard += 1;
                                    handPanel1.remove(coin);
                                    cardhand1.remove(coin);
                                    repaint();
                                    validate();
                                }
                            }
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        ImageIcon img = new ImageIcon(coin.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                        //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                        //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                        showLabel.setIcon(img);
                        showLabel.setVisible(true);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        showLabel.setVisible(false);
                    }
                });
                cardhand2.add(coin);
                handPanel2.add(coin);
            }
        } else if (code.equals("020302")) {
            cannotatk = 1;
        } else if (code.equals("020303")) {
            defPanel1.removeAll();
            int del = Math.abs(rand.nextInt()) % carddef1.size();
            carddef1.remove(del);
            for (int i = 0; i < carddef1.size(); i++) {
                defPanel1.add(carddef1.get(i));
            }
        } else if (code.equals("020002")) {
            for (int i = 0; i < 5; i++) {
                disdeck2();
            }
        } else if (code.equals("020003")) {
            gainhp(2, 2);
        } else if (code.equals("020005")) {
            if (!cardhand2.isEmpty()) {
                handPanel2.removeAll();
                int del = Math.abs(rand.nextInt()) % cardhand2.size();
                cardhand2.remove(del);
                for (int i = 0; i < cardhand2.size(); i++) {
                    handPanel2.add(cardhand2.get(i));
                }
                disdeck2();
            }
        } else if (code.equals("020006")) {

        } else if (code.equals("020007")) {
            defPanel2.removeAll();
            defPanel1.removeAll();
            carddef1.clear();
            carddef2.clear();
        }
    }

    public void switchside() {

        handPanel1.removeAll();
        handPanel2.removeAll();
        reducecost = 0;
        side = 0;
        onlyoneatk = 1;
        defPanel1.removeAll();
        defPanel2.removeAll();
        heroPanel2.removeAll();
        heroPanel1.removeAll();
        ArrayList<defcard> swapdef = new ArrayList<defcard>();
        ArrayList<card> swaphand = new ArrayList<card>();
        swaphand.clear();
        swapdef.clear();
        swapdef.addAll(carddef1);
        carddef1.clear();
        carddef1.addAll(carddef2);
        carddef2.clear();
        carddef2.addAll(swapdef);
        swaphand.addAll(cardhand1);
        cardhand1.clear();
        cardhand1.addAll(cardhand2);
        cardhand2.clear();
        cardhand2.addAll(swaphand);
        swaphand.clear();
        temp = currenthp1;
        currenthp1 = currenthp2;
        currenthp2 = temp;
        hp1.setText(Integer.toString(currenthp1));
        hp2.setText(Integer.toString(currenthp2));
        temp = maxhp1;
        maxhp1 = maxhp2;
        maxhp2 = temp;
        temp = rank1;
        rank1 = rank2;
        rank2 = temp;
        rankarea1.setText(Integer.toString(rank1));
        rankarea2.setText(Integer.toString(rank2));
        name_temp = name1;
        name1 = name2;
        name2 = name_temp;
        nameLabel1.setText(name1);
        nameLabel2.setText(name2);
        
        mana = 3;
        manaarea.setText("3");
        for (int i = 0; i < carddef1.size(); i++) {

            defPanel1.add(carddef1.get(i));
        }
        for (int i = 0; i < carddef2.size(); i++) {
            defPanel2.add(carddef2.get(i));
        }
        for (int i = 0; i < cardhand1.size(); i++) {
            System.out.println(cardhand1.get(i));
            handPanel1.add(cardhand1.get(i));
        }
        for (int i = 0; i < cardhand2.size(); i++) {
            handPanel2.add(cardhand2.get(i));
        }
        atkscore = 0;
        atkcount = 0;
        atkscore += plusatk;

        moreoneon = 0;
        directon = 0;
        plusatk = 0;
        if (cannotdrawon == 1) {
            notdraw = 1;
        } else {
            notdraw = 0;
        }
        if (norankupon == 1) {
            norankup = 1;
        } else {
            norankup = 0;
        }
        if (cannotatk == 1) {
            noatk = 1;
        } else {
            noatk = 0;
        }
        atk.setText(Integer.toString(atkscore));
        if (atkscore > 0) {
            atkcount = 1;
        }
        cannotdrawon = 0;
        countcard = 0;
        refleckon = 0;
        norankupon = 0;
        heropowercount = 1;
        attackon = 0;
        cannotatk = 0;
        turn++;
        if (turn > 2) {
            turn = 1;
            firstturn = 0;
        }
        hero swap;
        swap = hero1;
        hero1 = hero2;
        hero2 = swap;
        swap.removeAll();
        heroPanel2.add(hero2);
        heroPanel1.add(hero1);
        ArrayList<String> swapdeck = new ArrayList<String>();
        swapdeck.clear();
        swapdeck.addAll(deck1);
        deck1.clear();
        deck1.addAll(deck2);
        deck2.clear();
        deck2.addAll(swapdeck);
        swapdeck.clear();
        for (int i = 0; i < cardhand1.size(); i++) {
            cardhand1.get(i).show();
        }
        for (int i = 0; i < cardhand2.size(); i++) {
            cardhand2.get(i).flip();
        }
        drawcard(1);
        deckarea1.setText("" + deck1.size());
        deckarea2.setText("" + deck2.size());
        repaint();
        validate();
    }

    public void disdeck1() {
        if (deck2.isEmpty()) {
            return;
        }
        System.out.println("Disdeck1");
        deck1.remove(0);
        deckarea1.setText("" + deck1.size());
        deckarea2.setText("" + deck2.size());
        repaint();
        validate();
    }

    public void disdeck2() {
        if (deck2.isEmpty()) {
            return;
        }
        System.out.println("Disdeck2");
        deck2.remove(0);
        deckarea1.setText("" + deck1.size());
        deckarea2.setText("" + deck2.size());
        repaint();
        validate();
    }

    public void Attackphase() {

        if (atkcount > 0) {
            attackon = 1;
            System.out.println("Attack");

        }
    }

    public void showeffect(card card) {

    }

    public boolean canusecard(card cardd) {
        String code = cardd.getName();
        boolean cast = false;
        if (cardd.gettype() == 1 && onlyoneatk == 0) {
            return false;
        }
        if (firstturn == 1 && cardd.gettype() == 1) {
            return false;
        }
        if (code.equals("010101")) {
            if ((reducecost + mana) - 1 >= 0 && rank1 >= 1) {
                if (reducecost > 0) {
                    reducecost = 1;
                }
                onlyoneatk=0;
                mana = (reducecost + mana) - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else if (mana - 1 < 0) {
                cast = false;
            }
        } else if (code.equals("010102")) {
            if ((reducecost + mana) - 1 >= 0 && rank1 >= 2) {
                if (reducecost > 0) {
                    reducecost = 1;
                }
                onlyoneatk=0;
                mana = (reducecost + mana) - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010103")) {
            if ((reducecost + mana) - 2 >= 0 && rank1 >= 3) {
                onlyoneatk=0;
                mana = (reducecost + mana) - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010201")) {
            if ((reducecost + mana) - 1 >= 0 && rank1 >= 2) {
                if (reducecost > 0) {
                    reducecost = 1;
                }
                onlyoneatk=0;
                mana = (reducecost + mana) - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010202")) {
            if ((reducecost + mana) - 2 >= 0 && rank1 >= 2) {
                onlyoneatk=0;
                mana = (reducecost + mana) - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010203")) {
            if ((reducecost + mana) - 1 >= 0 && rank1 >= 3) {
                if (reducecost > 0) {
                    reducecost = 1;
                }
                onlyoneatk=0;
                mana = (reducecost + mana) - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010301")) {
            if ((reducecost + mana) - 1 >= 0 && rank1 >= 1) {
                if (reducecost > 0) {
                    reducecost = 1;
                }
                onlyoneatk=0;
                mana = (reducecost + mana) - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010302")) {
            if ((reducecost + mana) - 2 >= 0 && rank1 >= 2) {
                onlyoneatk=0;
                mana = (reducecost + mana) - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010303")) {
            if ((reducecost + mana) - 2 >= 0 && rank1 >= 3) {
                onlyoneatk=0;
                mana = (reducecost + mana) - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010001")) {
            if ((reducecost + mana) - 1 >= 0 && rank1 >= 1) {
                if (reducecost > 0) {
                    reducecost = 1;
                }
                mana = (reducecost + mana) - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010002")) {
            if ((reducecost + mana) - 1 >= 0 && rank1 >= 1) {
                if (reducecost > 0) {
                    reducecost = 1;
                }
                mana = (reducecost + mana) - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010003")) {
            if ((reducecost + mana) - 2 >= 0 && rank1 >= 2) {
                mana = (reducecost + mana) - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010004")) {
            if ((reducecost + mana) - 2 >= 0 && rank1 >= 2) {
                mana = (reducecost + mana) - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010005")) {

            if ((reducecost + mana) - 1 >= 0 && rank1 >= 2) {
                if (reducecost > 2) {
                    reducecost = 1;
                }
                mana = (reducecost + mana) - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010006")) {
            if ((reducecost + mana) - 2 >= 0 && rank1 >= 3) {
                mana = (reducecost + mana) - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("010007")) {
            if ((reducecost + mana) - 2 >= 0 && rank1 >= 3) {
                mana = (reducecost + mana) - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020101")) {
            if (mana - 1 >= 0 && rank1 >= 1 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020102")) {
            if (mana - 1 >= 0 && rank1 >= 2 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020103")) {
            if (mana - 2 >= 0 && rank1 >= 3 && carddef1.size() < 3) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020201")) {
            if (mana - 1 >= 0 && rank1 >= 1 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020202")) {
            if (mana - 1 >= 0 && rank1 >= 2 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020203")) {
            if (mana - 2 >= 0 && rank1 >= 3 && carddef1.size() < 3) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020301")) {
            if (mana - 1 >= 0 && rank1 >= 1 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020302")) {
            if (mana - 2 >= 0 && rank1 >= 2 && carddef1.size() < 3) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020303")) {
            if (mana - 1 >= 0 && rank1 >= 3 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020001")) {
            if (mana - 1 >= 0 && rank1 >= 1 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020002")) {
            if (mana - 1 >= 0 && rank1 >= 1 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020003")) {
            if (mana - 2 >= 0 && rank1 >= 1 && carddef1.size() < 3) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020004")) {
            if (mana - 1 >= 0 && rank1 >= 2 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020005")) {
            if (mana - 1 >= 0 && rank1 >= 2 && carddef1.size() < 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020006")) {
            if (mana - 2 >= 0 && rank1 >= 2 && carddef1.size() < 3) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020007")) {
            if (mana - 2 >= 0 && rank1 >= 3 && carddef1.size() < 3) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("020008")) {
            if (mana - 2 >= 0 && rank1 >= 3 && carddef1.size() < 3) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030101")) {
            if (mana - 1 >= 0 && rank1 >= 1) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030102")) {
            if (mana - 0 >= 0 && rank1 >= 1) {
                mana = mana - 0;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030103")) {
            if (mana - 2 >= 0 && rank1 >= 2) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030201")) {
            if (mana - 1 >= 0 && rank1 >= 1) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030202")) {
            if (mana - 1 >= 0 && rank1 >= 2) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030203")) {
            if (mana - 2 >= 0 && rank1 >= 3) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030301")) {
            if (mana - 1 >= 0 && rank1 >= 1) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030302")) {
            if (mana - 2 >= 0 && rank1 >= 2) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030303")) {
            if (mana - 1 >= 0 && rank1 >= 3) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030001")) {
            if (mana - 1 >= 0 && rank1 >= 1) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030002")) {
            if (mana - 1 >= 0 && rank1 >= 1) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030003")) {
            if (mana - 1 >= 0 && rank1 >= 1 && notdraw == 0) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030004")) {
            if (mana - 1 >= 0 && rank1 >= 1) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030005")) {
            if (mana - 2 >= 0 && rank1 >= 2 && notdraw == 0) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030006")) {
            if (mana - 1 >= 0 && rank1 >= 2) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030007")) {
            if (mana - 1 >= 0 && rank1 >= 2) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030008")) {
            if (mana - 1 >= 0 && rank1 >= 2) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030009")) {
            if (mana - 1 >= 0 && rank1 >= 2) {
                mana = mana - 1;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030010")) {
            if (mana >= 0 && rank1 >= 3) {
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030011")) {
            if (mana >= 0 && rank1 >= 3 && notdraw == 0) {
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030012")) {
            if (mana - 2 >= 0 && rank1 >= 3) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030013")) {
            if (mana - 2 >= 0 && rank1 >= 3 && notdraw == 0) {
                mana = mana - 2;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("030014")) {
            if (mana - 3 >= 0 && rank1 >= 3) {
                mana = mana - 3;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("rankup")) {
            if (mana - 3 >= 0 && rank1 >= 1 && norankup == 0) {
                mana = mana - 3;
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        } else if (code.equals("coin")) {
            if (mana >= 0 && rank1 >= 1) {
                manaarea.setText(Integer.toString(mana));
                repaint();
                validate();
                cast = true;
            } else {
                cast = false;
            }
        }
        reducecost = 0;
        return cast;
    }

    public void drawcard(int x) {
        System.out.println("Cardhand=" + cardhand1.size());
        if (x == 1) {
            if (cardhand1.size() >= 10) {
                return;
            }
            if (deck1.isEmpty()) {
                System.out.println("Nocard");
                winloss(1);
            }
            //int 0 = Math.abs(rand.nextInt()) % deck1.size();
            // System.out.println("Numrand=" + 0+" is="+deck1.get(0));
            String carddraw = deck1.get(0);
            card cardmake = new card(carddraw);
            cardmake.show();
            cardmake.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (e.getClickCount() == 2 && !e.isConsumed()) {

                        if (cardhand1.contains(cardmake)) {
                            System.out.println("Click" + cardmake.getName());
                            if (canusecard(cardmake) == true) {

                                actioncard(cardmake);
                                countcard += 1;
                                handPanel1.remove(cardmake);
                                cardhand1.remove(cardmake);
                                repaint();
                                validate();
                            }
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                    //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                    //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                    showLabel.setIcon(img);
                    showLabel.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    showLabel.setVisible(false);
                }

                public void mouseFocused(MouseEvent e) {
                    showeffect(cardmake);
                }
            });
            handPanel1.add(cardmake);
            deck1.remove(0);
            cardhand1.add(cardmake);
            System.out.println("Card in deck" + deck1.size());
            repaint();
            validate();
        } else if (x == 2) {
            if (cardhand2.size() >= 10) {
                return;
            }
            if (deck2.isEmpty()) {
                winloss(2);
            }
            //int numrand = Math.abs(rand.nextInt()) % deck2.size();
            //System.out.println("Numrand=" + 0+" is="+deck2.get(0));
            String carddraw = deck2.get(0);
            card cardmake = new card(carddraw);
            cardmake.flip();
            cardmake.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (e.getClickCount() == 2 && !e.isConsumed()) {
                        if (cardhand1.contains(cardmake)) {
                            if (canusecard(cardmake) == true) {
                                actioncard(cardmake);
                                countcard += 1;
                                handPanel1.remove(cardmake);
                                cardhand1.remove(cardmake);
                                repaint();
                                validate();
                            }
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                    //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                    //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                    showLabel.setIcon(img);
                    showLabel.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    showLabel.setVisible(false);
                }
            });
            cardhand2.add(cardmake);
            handPanel2.add(cardmake);
            deck2.remove(0);
            repaint();
            validate();
        }
    }

    public void heropower() {
        if (hero1.getrank() == 1 && hero1.getclas() == 1) {
            if (notdraw == 1) {
                System.out.println("Cannot Draw");
                mana = mana + 1;
                return;
            }
            drawtype("1");
        } else if (hero1.getrank() == 2 && hero1.getclas() == 1) {
            JPanel jarr = new JPanel();
            int numselect = 0, numtype, numno;
            Object[] Obj = {"1", "2", "3"};
            ArrayList<String> namecard = new ArrayList<>();
            jarr.setLayout(new FlowLayout());
            while (numselect < 3) {
                numtype = Math.abs(rand.nextInt()) % 4;
                numno = Math.abs(rand.nextInt()) % 7;
                numno += 1;
                if (numtype > 0 && numno <= 3) {
                    String coderand = "010" + Integer.toString(numtype) + "0" + Integer.toString(numno);
                    ImageIcon img =new ImageIcon("resource/card/" + coderand + ".png");
                    img = new ImageIcon(img.getImage().getScaledInstance(357, 496, Image.SCALE_SMOOTH));
                    JLabel addj = new JLabel(img);
                    jarr.add(addj);
                    numselect++;
                    namecard.add(coderand);
                } else if (numtype == 0) {
                    String coderand = "010" + Integer.toString(numtype) + "0" + Integer.toString(numno);
                    numselect++;
                    ImageIcon img =new ImageIcon("resource/card/" + coderand + ".png");
                    img = new ImageIcon(img.getImage().getScaledInstance(357, 496, Image.SCALE_SMOOTH));
                    JLabel addj = new JLabel(img);
                    jarr.add(addj);
                    namecard.add(coderand);
                }

            }
            while (true) {
                try {
                    Object selectedValue = JOptionPane.showInputDialog(null, jarr, "ChooseOne", JOptionPane.INFORMATION_MESSAGE, null, Obj, Obj[0]);
                    String ans = selectedValue.toString();
                    int ansint = Integer.parseInt(ans);
                    ansint = ansint - 1;
                    System.out.println(ans);
                    card cardmake = new card(namecard.get(ansint));
                    cardmake.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {
                                if (cardhand1.contains(cardmake)) {
                                    if (canusecard(cardmake) == true) {
                                        actioncard(cardmake);
                                        countcard += 1;
                                        handPanel1.remove(cardmake);
                                        cardhand1.remove(cardmake);
                                        repaint();
                                        validate();
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                            //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                            //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                            showLabel.setIcon(img);
                            showLabel.setVisible(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            showLabel.setVisible(false);
                        }
                    });
                    handPanel1.add(cardmake);
                    cardhand1.add(cardmake);
                    repaint();
                    validate();
                    break;

                } catch (NumberFormatException ee) {
                    System.err.println("An error occurs.");
                }
            }
        } else if (hero1.getrank() == 3 && hero1.getclas() == 1) {
            JPanel jarr = new JPanel();
            int numselect = 0, numtype, numno;
            Object[] Obj = {"1", "2", "3"};
            ArrayList<String> namecard = new ArrayList<>();
            jarr.setLayout(new FlowLayout());
            while (numselect < 3) {
                numtype = Math.abs(rand.nextInt()) % 4;
                numno = Math.abs(rand.nextInt()) % 7;
                numno += 1;
                if (numtype > 0 && numno <= 3) {
                    String coderand = "010" + Integer.toString(numtype) + "0" + Integer.toString(numno);
                    ImageIcon img =new ImageIcon("resource/card/" + coderand + ".png");
                    img = new ImageIcon(img.getImage().getScaledInstance(357, 496, Image.SCALE_SMOOTH));
                    JLabel addj = new JLabel(img);
                    jarr.add(addj);
                    numselect++;
                    namecard.add(coderand);
                } else if (numtype == 0) {
                    String coderand = "010" + Integer.toString(numtype) + "0" + Integer.toString(numno);
                    numselect++;
                    ImageIcon img =new ImageIcon("resource/card/" + coderand + ".png");
                    img = new ImageIcon(img.getImage().getScaledInstance(357, 496, Image.SCALE_SMOOTH));
                    JLabel addj = new JLabel(img);
                    jarr.add(addj);
                    namecard.add(coderand);
                }

            }
            while (true) {
                try {
                    Object selectedValue = JOptionPane.showInputDialog(null, jarr, "ChooseOne", JOptionPane.INFORMATION_MESSAGE, null, Obj, Obj[0]);
                    String ans = selectedValue.toString();
                    int ansint = Integer.parseInt(ans);
                    ansint = ansint - 1;
                    System.out.println(ans);
                    card cardmake = new card(namecard.get(ansint));
                    cardmake.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {
                                if (cardhand1.contains(cardmake)) {
                                    if (canusecard(cardmake) == true) {
                                        actioncard(cardmake);
                                        countcard += 1;
                                        handPanel1.remove(cardmake);
                                        cardhand1.remove(cardmake);
                                        repaint();
                                        validate();
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                            //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                            //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                            showLabel.setIcon(img);
                            showLabel.setVisible(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            showLabel.setVisible(false);
                        }
                    });
                    int onlyoneattack=1;
                    if(onlyoneatk==0)
                        onlyoneattack=0;
                    actioncard(cardmake);
                    if(onlyoneattack==0)
                    {
                        atkcount=0;
                        attackon=0;

                    }
                    break;

                } catch (NumberFormatException ee) {
                    System.err.println("An error occurs.");
                }
            }
        } else if (hero1.getrank() == 1 && hero1.getclas() == 2) {
            if (notdraw == 1) {
                mana = mana + 1;
                return;
            }
            drawtype("2");
        } else if (hero1.getrank() == 2 && hero1.getclas() == 2) {
            JPanel jarr = new JPanel();
            int numselect = 0, numtype, numno;
            Object[] Obj = {"1", "2", "3"};
            ArrayList<String> namecard = new ArrayList<>();
            jarr.setLayout(new FlowLayout());
            while (numselect < 3) {
                numtype = Math.abs(rand.nextInt()) % 4;
                numno = Math.abs(rand.nextInt()) % 7;
                numno += 1;
                if (numtype > 0 && numno <= 3) {
                    String coderand = "020" + Integer.toString(numtype) + "0" + Integer.toString(numno);
                    JLabel addj = new JLabel(new ImageIcon("resource/card/" + coderand + ".png"));
                    jarr.add(addj);
                    numselect++;
                    namecard.add(coderand);
                } else if (numtype == 0) {
                    String coderand = "020" + Integer.toString(numtype) + "0" + Integer.toString(numno);
                    numselect++;
                    JLabel addj = new JLabel(new ImageIcon("resource/card/" + coderand + ".png"));
                    jarr.add(addj);
                    namecard.add(coderand);
                }

            }
            while (true) {
                try {
                    Object selectedValue = JOptionPane.showInputDialog(null, jarr, "ChooseOne", JOptionPane.INFORMATION_MESSAGE, null, Obj, Obj[0]);
                    String ans = selectedValue.toString();
                    int ansint = Integer.parseInt(ans);
                    ansint = ansint - 1;
                    System.out.println(ans);
                    card cardmake = new card(namecard.get(ansint));
                    cardmake.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {
                                if (cardhand1.contains(cardmake)) {
                                    if (canusecard(cardmake) == true) {
                                        actioncard(cardmake);
                                        countcard += 1;
                                        handPanel1.remove(cardmake);
                                        cardhand1.remove(cardmake);
                                        repaint();
                                        validate();
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                            //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                            //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                            showLabel.setIcon(img);
                            showLabel.setVisible(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            showLabel.setVisible(false);
                        }
                    });
                    handPanel1.add(cardmake);
                    cardhand1.add(cardmake);
                    repaint();
                    validate();
                    break;

                } catch (NumberFormatException ee) {
                    System.err.println("An error occurs.");
                }
            }
        } else if (hero1.getrank() == 3 && hero1.getclas() == 2) {
            JPanel jarr = new JPanel();
            int numselect = 0, numtype, numno;
            Object[] Obj = {"1", "2", "3"};
            ArrayList<String> namecard = new ArrayList<>();
            jarr.setLayout(new FlowLayout());
            while (numselect < 3) {
                numtype = Math.abs(rand.nextInt()) % 4;
                numno = Math.abs(rand.nextInt()) % 7;
                numno += 1;
                if (numtype > 0 && numno <= 3) {
                    String coderand = "020" + Integer.toString(numtype) + "0" + Integer.toString(numno);
                    JLabel addj = new JLabel(new ImageIcon("resource/card/" + coderand + ".png"));
                    jarr.add(addj);
                    numselect++;
                    namecard.add(coderand);
                } else if (numtype == 0) {
                    String coderand = "020" + Integer.toString(numtype) + "0" + Integer.toString(numno);
                    numselect++;
                    JLabel addj = new JLabel(new ImageIcon("resource/card/" + coderand + ".png"));
                    jarr.add(addj);
                    namecard.add(coderand);
                }

            }
            while (true) {
                try {
                    Object selectedValue = JOptionPane.showInputDialog(null, jarr, "ChooseOne", JOptionPane.INFORMATION_MESSAGE, null, Obj, Obj[0]);
                    String ans = selectedValue.toString();
                    int ansint = Integer.parseInt(ans);
                    ansint = ansint - 1;
                    System.out.println(ans);
                    card cardmake = new card(namecard.get(ansint));
                    cardmake.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {

                            if (e.getClickCount() == 2 && !e.isConsumed()) {
                                if (cardhand1.contains(cardmake)) {
                                    if (canusecard(cardmake) == true) {
                                        actioncard(cardmake);
                                        countcard += 1;
                                        handPanel1.remove(cardmake);
                                        cardhand1.remove(cardmake);
                                        repaint();
                                        validate();
                                    }
                                }
                            }
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                            //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                            //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                            showLabel.setIcon(img);
                            showLabel.setVisible(true);
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            showLabel.setVisible(false);
                        }
                    });
                    actioncard(cardmake);
                    break;

                } catch (NumberFormatException ee) {
                    System.err.println("An error occurs.");
                }
            }
        } else if (hero1.getrank() == 1 && hero1.getclas() == 3) {
            card cardmake = new card("coin");
            cardmake.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (e.getClickCount() == 2 && !e.isConsumed()) {
                        if (cardhand1.contains(cardmake)) {
                            if (canusecard(cardmake) == true) {
                                actioncard(cardmake);
                                countcard += 1;
                                handPanel1.remove(cardmake);
                                cardhand1.remove(cardmake);
                                repaint();
                                validate();
                            }
                        }
                    }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                    //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                    //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                    showLabel.setIcon(img);
                    showLabel.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    showLabel.setVisible(false);
                }
            });
            if (cardhand1.size() < 10) {
                cardhand1.add(cardmake);
                handPanel1.add(cardmake);
            }
            repaint();
            validate();

        } else if (hero1.getrank() == 2 && hero1.getclas() == 3) {
            for (int i = 0; i < 2; i++) {
                card cardmake = new card("coin");
                cardmake.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        if (e.getClickCount() == 2 && !e.isConsumed()) {
                            if (cardhand1.contains(cardmake)) {
                                if (canusecard(cardmake) == true) {
                                    actioncard(cardmake);
                                    countcard += 1;
                                    handPanel1.remove(cardmake);
                                    cardhand1.remove(cardmake);
                                    repaint();
                                    validate();
                                }
                            }
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        ImageIcon img = new ImageIcon(cardmake.getImageIcon().getImage().getScaledInstance(showwidth, showheight, Image.SCALE_SMOOTH));
                        //ImageIcon(pichero1.getImage().getScaledInstance(110, 140, Image.SCALE_DEFAULT))
                        //img.getImage().getScaledInstance(showwidth, showheight, Image.SCALE_DEFAULT);
                        showLabel.setIcon(img);
                        showLabel.setVisible(true);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        showLabel.setVisible(false);
                    }
                });
                if (cardhand1.size() < 10) {
                    cardhand1.add(cardmake);
                    handPanel1.add(cardmake);
                }
                repaint();
                validate();
            }
        } else if (hero1.getrank() == 3 && hero1.getclas() == 3) {
            atkscore += 1 + countcard;
            atk.setText(Integer.toString(atkscore));
            repaint();
            validate();
        }

    }

    public void makedeck(String namedeck, int no) {
        namedeck = System.getProperty("user.dir")+"/deck/"+namedeck + ".txt";
        String stradd;
        if (no == 1) {
            try {
                Scanner scan = new Scanner(new File(namedeck));
                String name = scan.nextLine();
                System.out.println(name);
                while (scan.hasNext()) {
                    stradd = scan.nextLine();
                    if (stradd.equals(" ")) {
                        break;
                    }
                    System.out.println("Add=" + stradd);
                    deck1.add(stradd);
                }
                deck1.add("rankup");
                deck1.add("rankup");
                deck1.add("rankup");
                deck1.add("rankup");
                deck1.add("rankup");
                Collections.shuffle(deck1);
            } catch (FileNotFoundException e) {
                System.err.println("Not find file.");
                System.exit(-1);
            }
        } else if (no == 2) {
            try {
                Scanner scan = new Scanner(new File(namedeck));
                String name = scan.nextLine();
                System.out.println(name);
                while (scan.hasNext()) {
                    stradd = scan.nextLine();
                    if (stradd.equals(" ")) {
                        break;
                    }
                    System.out.println("Add=" + stradd);
                    deck2.add(stradd);
                }
                deck2.add("rankup");
                deck2.add("rankup");
                deck2.add("rankup");
                deck2.add("rankup");
                deck2.add("rankup");
                Collections.shuffle(deck2);
            } catch (FileNotFoundException e) {
                System.err.println("Not find file.");
                System.exit(-1);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Play.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Play.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Play.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Play.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Play(name1, name2, deckname1, deckname2).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel atk;
    private javax.swing.JLabel bg;
    private javax.swing.JPanel contentpane;
    private javax.swing.JLabel deckarea1;
    private javax.swing.JLabel deckarea2;
    private javax.swing.JPanel defPanel1;
    private javax.swing.JPanel defPanel2;
    private javax.swing.JButton endturn;
    private javax.swing.JPanel handPanel1;
    private javax.swing.JPanel handPanel2;
    private javax.swing.JPanel heroPanel1;
    private javax.swing.JPanel heroPanel2;
    private javax.swing.JButton heropower;
    private javax.swing.JLabel hp1;
    private javax.swing.JLabel hp2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel line;
    private javax.swing.JLabel line1;
    private javax.swing.JLabel line2;
    private javax.swing.JLabel line3;
    private javax.swing.JLabel manaarea;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel p2_hp;
    private javax.swing.JLabel p2_rank;
    private javax.swing.JLabel rankarea1;
    private javax.swing.JLabel rankarea2;
    private javax.swing.JLabel showCh;
    private javax.swing.JLabel showLabel;
    // End of variables declaration//GEN-END:variables

}

class hero extends JLabel {

    private int maxhp, rank;
    private final int clas;
    private ImageIcon pichero1, pichero2, pichero3;
    private int herowidth = 110, heroheight = 140;

    public ImageIcon getImageIcon() {
        if (rank == 1) {
            return pichero1;
        } else if (rank == 2) {
            return pichero2;
        } else if (rank == 3) {
            return pichero3;
        } else {
            return null;
        }
    }

    public hero(int x) {
        rank = 1;
        clas = x;
        if (x == 1) {
            pichero1 = new ImageIcon("resource/ch/AUDEN_LV1.png");
            pichero2 = new ImageIcon("resource/ch/AUDEN_LV2.png");
            pichero3 = new ImageIcon("resource/ch/AUDEN_LV3.png");
            maxhp = 7;
        } else if (x == 2) {
            pichero1 = new ImageIcon("resource/ch/DICA_LV1.png");
            pichero2 = new ImageIcon("resource/ch/DICA_LV2.png");
            pichero3 = new ImageIcon("resource/ch/DICA_LV3.png");
            maxhp = 6;
        } else if (x == 3) {
            pichero1 = new ImageIcon("resource/ch/NATUS_LV1.png");
            pichero2 = new ImageIcon("resource/ch/NATUS_LV2.png");
            pichero3 = new ImageIcon("resource/ch/NATUS_LV3.png");
            maxhp = 6;
        }
        setIcon(new ImageIcon(pichero1.getImage().getScaledInstance(herowidth, heroheight, Image.SCALE_DEFAULT)));
    }

    public int classup2(int currenthp) {
        rank = 2;
        setIcon(new ImageIcon(pichero2.getImage().getScaledInstance(herowidth, heroheight, Image.SCALE_DEFAULT)));
        if (clas == 1) {
            maxhp = 9;
            currenthp = currenthp + 2;
        } else if (clas == 2) {
            maxhp = 10;
            currenthp = currenthp + 4;
        } else if (clas == 3) {
            maxhp = 9;
            currenthp = currenthp + 3;
        }
        return currenthp;
    }

    public int classup3(int currenthp) {
        rank = 3;
        setIcon(new ImageIcon(pichero3.getImage().getScaledInstance(herowidth, heroheight, Image.SCALE_DEFAULT)));
        if (clas == 1) {
            maxhp = 13;
            currenthp = currenthp + 4;
        } else if (clas == 2) {
            maxhp = 12;
            currenthp = currenthp + 2;
        } else if (clas == 3) {
            maxhp = 11;
            currenthp = currenthp + 2;
        }
        return currenthp;
    }

    public int getrank() {
        return rank;
    }

    public int getclas() {
        return clas;
    }

    public int getmaxhp() {
        return maxhp;
    }
}

class card extends JLabel {

    private ImageIcon frontcard, backcard;
    private int type, clas, flipp;
    private int cardwidth = 65, cardheight = 90;

    public ImageIcon getImageIcon() {
        if (flipp == 1) {
            return frontcard;
        } else {
            return backcard;
        }
    }

    public card(String code) {

        String piccard = "resource/card/" + code + ".png";
        frontcard = new ImageIcon(piccard);
        backcard = new ImageIcon("resource/card/BACK.png");
        char ctype, cclas;
        if (code.equals("coin")) {
            type = 3;
            clas = 0;
        } else if (code.equals("rankup")) {
            type = 3;
            clas = 0;
        } else {
            ctype = code.charAt(1);
            cclas = code.charAt(3);
            type = Integer.parseInt(String.valueOf(ctype));
            clas = Integer.parseInt(String.valueOf(cclas));
        }
        //flip();
        show();
        setName(code);
    }

    public int getclass() {
        return clas;
    }

    public int gettype() {
        return type;
    }

    @Override
    public void show() {
        flipp = 1;
        setIcon(new ImageIcon(frontcard.getImage().getScaledInstance(65, 90, Image.SCALE_DEFAULT)));
        repaint();
        validate();
    }

    public void flip() {
        flipp = 0;
        setIcon(new ImageIcon(backcard.getImage().getScaledInstance(65, 90, Image.SCALE_DEFAULT)));
        repaint();
        validate();
    }
}

class defcard extends JLabel {

    private ImageIcon frontcard, backcard;
    private int type, clas, defscore, defcount, flipp;

    public ImageIcon getImageIcon(boolean yes) {
        if (flipp == 1 || yes == true) {
            return frontcard;
        } else {
            return backcard;
        }
    }

    public defcard(String code) {
        String piccard = "resource/card/" + code + ".png";
        frontcard = new ImageIcon(piccard);
        backcard = new ImageIcon("resource/card/BACK.png");
        char ctype, cclas;
        ctype = code.charAt(1);
        cclas = code.charAt(3);
        type = Integer.parseInt(String.valueOf(ctype));
        clas = Integer.parseInt(String.valueOf(cclas));
        flip();
        defcount = 1;
        setName(code);

        if (code.equals("020101")) {
            defscore = 3;
        } else if (code.equals("020102")) {
            defscore = 4;
        } else if (code.equals("020103")) {
            defscore = 5;
        } else if (code.equals("020201")) {
            defscore = 2;
        } else if (code.equals("020202")) {
            defscore = 1;
        } else if (code.equals("020203")) {
            defscore = 5;
        } else if (code.equals("020301")) {
            defscore = 2;
        } else if (code.equals("020302")) {
            defscore = 3;
        } else if (code.equals("020303")) {
            defscore = 4;
        } else if (code.equals("020001")) {
            defscore = 2;
        } else if (code.equals("020002")) {
            defscore = 1;
        } else if (code.equals("020003")) {
            defscore = 4;
        } else if (code.equals("020004")) {
            defscore = 4;
        } else if (code.equals("020005")) {
            defscore = 7;
        } else if (code.equals("020006")) {
            defscore = 3;
            defcount = 2;
        } else if (code.equals("020007")) {
            defscore = 6;
        } else if (code.equals("020008")) {
            defscore = 7;
        }

    }

    public int getclass() {
        return clas;
    }

    public int getdef() {
        return defscore;
    }

    public void gaindef(int x) {
        defscore = defscore + x;
    }

    public int beattacked() {
        defcount = defcount - 1;
        System.out.println("Defcount=" + defcount);
        return defcount;
    }

    public void negageattacked() {
        defcount = defcount + 1;
    }

    public int gettype() {
        return type;
    }

    @Override
    public void show() {
        setIcon(new ImageIcon(frontcard.getImage().getScaledInstance(65, 90, Image.SCALE_DEFAULT)));
        flipp = 1;
        repaint();
        validate();
    }

    public void flip() {
        setIcon(new ImageIcon(backcard.getImage().getScaledInstance(65, 90, Image.SCALE_DEFAULT)));
        flipp = 0;
        repaint();
        validate();
    }
}
