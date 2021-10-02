/*
6213132 Wasawat Pengprakhon 
*/
import java.awt.*;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.event.*;
import javax.swing.table.*;

class viewDeckCard{
    private String name;
    private String classes;
    private String type;
    private int tier;
    private int hp;
    private int cost;
    private int limit;
    private String effect;
    private String code;
    private MyImageIcon img;
    
    public viewDeckCard(String n,String cl,String ty,String ti,String h,String cs,String lim,String eff,String cd){
        name = n;
        classes = cl;
        type = ty;
        tier = Integer.parseInt(ti);
        hp = Integer.parseInt(h);
        cost = Integer.parseInt(cs);
        limit = Integer.parseInt(lim);
        effect = eff;
        code = cd;
        if(type.equals("Seal")){
            img = new MyImageIcon("resource/ch/"+name.toUpperCase()+"_LV1.png");    
        }
        else 
            img = new MyImageIcon("resource/card/"+code+".png");
    }
    public String getName(){
        return name;
    }
    public void setName(String n){
        name = n;
    }
    public String getType(){
        return type;
    }
    public String getClasses(){
        return classes;
    }
    public String getEff(){
        return effect;
    }
    public int getCost(){
        return cost;
    }
    public int getLimit(){
        return limit;
    }
    public int getHp(){
        return hp;
    }
    public int getTier(){
        return tier;
    }
    public String getCode(){
        return code;
    }
    public MyImageIcon getImg(){
        return img;
    }
    public MyImageIcon getImgNorm(){
        if(type.equals("Seal")){
            return new MyImageIcon("resource/ch/"+name.toUpperCase()+"_LV1.png").resize(287,406);    
        }
        else 
            return  new MyImageIcon("resource/card/"+code+".png").resize(287,406);
    }
    public MyImageIcon getImgSmall(){
        if(type.equals("Seal")){
            return new MyImageIcon("resource/ch/"+name.toUpperCase()+"_LV1.png").resize(76,100);    
        }
        else 
            return  new MyImageIcon("resource/card/"+code+".png").resize(76,100);
    }
}

class CardRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {
        
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        viewDeckCard c = (viewDeckCard)value;
        super.setText(c.getName());
        //cellComponent.set//(c.getName());
        return this;
    }
}

class deckwindow extends JFrame{
    private JPanel  contentpane;
    private JLabel  drawpane;
    private int frameWidth = 1280, frameHeight = 720;
    private viewDeckCard currentCard;
    private Vector<viewDeckCard> currentDeck = new Vector<viewDeckCard>();
    
    public deckwindow(){
        setTitle("View deck");
	setBounds(50, 50, frameWidth, frameHeight);
        setResizable(false);
	setVisible(true);
	setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setBackground(new Color(0,0,0));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contentpane = (JPanel)getContentPane();
        contentpane.setBackground(new Color(0,0,0));
        contentpane.setLayout( new BorderLayout() );
        Addcomponent();
    }
    
    public void Addcomponent(){
        
        BoxLayout boxLayout;
        
        drawpane = new JLabel();
        drawpane.setLayout(new BorderLayout());
        
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout( new BorderLayout() );
        cardPanel.setAlignmentX(LEFT_ALIGNMENT);
        
        JLabel cardImg = new JLabel();
        cardImg.setLayout(null);
        
        JPanel cardInform = new JPanel();
        cardInform.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel labelName = new JLabel("Name: ", JLabel.CENTER);        
        JLabel labelClass = new JLabel("Class: ", JLabel.LEFT);
        JLabel labelType = new JLabel("Type: ", JLabel.LEFT);
        JLabel labelEff = new JLabel("Effect: ", JLabel.CENTER);
        JLabel labelHp = new JLabel("HP: ", JLabel.LEFT);
        JLabel labelCost = new JLabel("Cost: ", JLabel.LEFT);
        JLabel labelLimit = new JLabel("Limit: ", JLabel.LEFT);
        JLabel labelTier = new JLabel("Rank: ", JLabel.LEFT);
        JTextArea cardName =new JTextArea(1,8);
        cardName.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JTextArea cardType =new JTextArea(1,8);
        cardType.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JTextArea cardClass =new JTextArea(1,8);
        cardClass.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JTextArea cardHp =new JTextArea(1,8);
        cardHp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JTextArea cardCost =new JTextArea(1,8);
        cardCost.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JTextArea cardLimit =new JTextArea(1,8);
        cardLimit.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JTextArea cardEff =new JTextArea(5,8);
        cardEff.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JTextArea cardTier =new JTextArea(1,8);
        cardTier.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        cardEff.setLineWrap(true);
        cardEff.setWrapStyleWord(true);
        JScrollPane effScroll = new JScrollPane(cardEff);
        effScroll.setPreferredSize(new Dimension(250,150));
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 0;
        cardInform.add(labelName,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        cardInform.add(cardName,gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        cardInform.add(labelClass,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        cardInform.add(cardClass,gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        cardInform.add(labelType,gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        cardInform.add(cardType,gbc);   
        gbc.gridx = 0;
        gbc.gridy = 4;
        cardInform.add(labelTier,gbc);
        JLabel empty = new JLabel("                   ");
        gbc.gridx = 1;
        gbc.gridy = 4;
        cardInform.add(empty,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        cardInform.add(cardTier,gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        cardInform.add(labelLimit,gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        cardInform.add(cardLimit,gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        cardInform.add(labelHp,gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        cardInform.add(cardHp,gbc);
        gbc.gridx = 2;
        gbc.gridy = 6;
        cardInform.add(labelCost,gbc);
        gbc.gridx = 2;
        gbc.gridy = 7;
        cardInform.add(cardCost,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 0;
        gbc.gridy = 8;
        cardInform.add(labelEff,gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        cardInform.add(cardEff,gbc);
        
        cardPanel.add(cardImg,BorderLayout.CENTER);
        cardPanel.add(cardInform,BorderLayout.SOUTH);
        cardPanel.setVisible(false);
       
        Vector<viewDeckCard> cardvect = new Vector<viewDeckCard>();
        try(Scanner scan = new Scanner(new File("cardInfo.TXT"));){
            while(scan.hasNext()){
                String line = scan.nextLine();
                String[] buf = line.split("`");
                viewDeckCard c = new viewDeckCard(buf[0],buf[1],buf[2],buf[3],buf[4],buf[5],buf[6],buf[7],buf[8]);
                cardvect.add(c);
            }
        }
        catch(FileNotFoundException e){
                System.out.println(e);
        }                
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Card",cardvect);
        JTable cardTable = new JTable(model);
        cardTable.getTableHeader().setEnabled(false);
        cardTable.setDefaultEditor(Object.class, null);
        cardTable.setDefaultRenderer(Object.class, new CardRenderer());
        ListSelectionListener listListener = new ListSelectionListener(){public void valueChanged(ListSelectionEvent e){
                if ( !e.getValueIsAdjusting() )
		{
                    int index = cardTable.getSelectedRow();
                    currentCard = (viewDeckCard)model.getValueAt(index,0);
                    cardName.setText("  "+currentCard.getName());
                    cardType.setText("       "+currentCard.getType());
                    cardEff.setText("       "+currentCard.getEff());
                    cardHp.setText("           "+currentCard.getHp());
                    cardClass.setText("       "+currentCard.getClasses());
                    cardCost.setText("           "+currentCard.getCost());
                    cardLimit.setText("           "+currentCard.getLimit());
                    cardTier.setText("           "+currentCard.getTier());
                    cardImg.setIcon(currentCard.getImgNorm());
                    cardPanel.setVisible(true);
		}
            }};
        cardTable.getSelectionModel().addListSelectionListener(listListener);
        cardTable.setRowHeight(50);
        cardTable.getColumnModel().getColumn(0).setHeaderValue("Card");
        cardTable.setCellSelectionEnabled(true);
        cardTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane cardSP = new JScrollPane(cardTable); 
        cardSP.setPreferredSize(new Dimension(300,720));
        
        JPanel myDeck = new JPanel();
        JLabel deckCount = new JLabel(currentDeck.size()+"/30",JLabel.CENTER);
                
        JPanel deckPane = new JPanel();
        JButton newDeck = new JButton("Create new deck");
        JButton removeDeck = new JButton("Remove deck");
        JLabel nameDeck = new JLabel("Deck: ",JLabel.CENTER); 
        JComboBox allDeck = new JComboBox();
        File directory = new File(System.getProperty("user.dir")+"/deck/"); 
        String[] allFile = directory.list();
        if(allFile.length != 0){
            for(int i=0;i<allFile.length;i++){
                String temp = allFile[i].substring(0,allFile[i].length()-4);
                //System.out.println(temp);
                allDeck.addItem(temp);
            }
            try(Scanner scan = new Scanner(new File(System.getProperty("user.dir")+"/deck/"+allDeck.getSelectedItem()+".txt"));){
                while(scan.hasNext()){
                    String line = scan.nextLine();
                        for(int i=0;i<cardvect.size();i++){
                            if(line.equals(cardvect.get(i).getCode()))
                            //card c = new card(buf[0],buf[1],buf[2],buf[3],buf[4],buf[5],buf[6],buf[7],buf[8]);
                            currentDeck.add(cardvect.get(i));
                    }
                }
            }
            catch(FileNotFoundException e){
                System.out.println("1 "+e);
            }
            deckCount.setText(currentDeck.size()+"/30");
        }
        deckPane.add(nameDeck);
        deckPane.add(allDeck);
        deckPane.add(newDeck);
        deckPane.add(removeDeck);
        newDeck.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String text = JOptionPane.showInputDialog(null,"Name","Create new deck",JOptionPane.QUESTION_MESSAGE);
                boolean check=true;
                for(int i=0;i<allDeck.getItemCount();i++){
                    if(text.equals(allDeck.getItemAt(i)))
                        check=false;
                }
                if(check==false){
                    JOptionPane.showMessageDialog(null, "This name is already exist!","Warning!", JOptionPane.INFORMATION_MESSAGE);
                }
                else if(text!=null||check==true){
                allDeck.addItem(text);
                String outfile = text+".txt";
                try{
                    File deckFile = new File(System.getProperty("user.dir")+"/deck/"+outfile);
                    if (deckFile.createNewFile()) {
                        System.out.println("File created: " + deckFile.getName());
                    } else {
                            System.out.println("File already exists.");
                    }
                }
                catch(FileNotFoundException ex){
                        System.out.println(ex);
                }
                catch(IOException ex){
                        System.out.println(ex);
                }
                }
                String[] allFile = directory.list();
                if(allFile.length != 0){
                    try{
                        FileWriter fw = new FileWriter(new File("ALL_DECKS_NAME.txt"));
                        for(int i=0;i<allFile.length;i++){
                            String temp = allFile[i].substring(0,allFile[i].length()-4);
                            fw.write(temp+",\n");
                        }
                        fw.close();
                    }
                    catch(FileNotFoundException ex){
                        System.out.println("2 "+ex);
                    }
                    catch(IOException ex){
                        System.out.println("3 "+ex);
                    }
                }
            }
        });
        removeDeck.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(allDeck.getItemCount()!=0){
                    int op = JOptionPane.showConfirmDialog(null,"Do you want to remove "+allDeck.getSelectedItem()+" deck ?","Remove deck",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if(op==JOptionPane.YES_OPTION){
                        File targetDeck  = new File(System.getProperty("user.dir")+"/deck/"+allDeck.getSelectedItem()+".txt");
                        if (targetDeck.delete()) { 
                            System.out.println("Deleted the file: " + targetDeck.getName());
                        } else {
                            System.out.println("Failed to delete the file.");
                        } 
                        allDeck.removeItem(allDeck.getSelectedItem());
                    }
                    String[] allFile = directory.list();
                    if(allFile.length != 0){
                        try{
                            FileWriter fw = new FileWriter(new File("ALL_DECKS_NAME.txt"));
                            for(int i=0;i<allFile.length;i++){
                                String temp = allFile[i].substring(0,allFile[i].length()-4);
                                fw.write(temp+",\n");
                            }
                            fw.close();
                        }
                        catch(FileNotFoundException ex){
                            System.out.println("4 "+ex);
                        }
                        catch(IOException ex){
                            System.out.println("5 "+ex);
                        }
                    }
                }
            }
        });
        allDeck.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                myDeck.removeAll();
                String s = (String) allDeck.getSelectedItem();
                currentDeck.removeAllElements();
                File directory = new File(System.getProperty("user.dir")+"/deck"); 
                String[] allFile = directory.list();
                if(allFile.length != 0){
                    try(Scanner scan = new Scanner(new File(System.getProperty("user.dir")+"/deck/"+allDeck.getSelectedItem()+".txt"));){
                        while(scan.hasNext()){
                            String line = scan.nextLine();
                                for(int i=0;i<cardvect.size();i++){
                                    if(line.equals(cardvect.get(i).getCode()))
                                //card c = new card(buf[0],buf[1],buf[2],buf[3],buf[4],buf[5],buf[6],buf[7],buf[8]);
                                    currentDeck.add(cardvect.get(i));
                            }
                            //System.out.println(line);
                        }
                    }
                    catch(FileNotFoundException ex){
                            System.out.println(ex);
                    }
                }
                if(currentDeck.size()==0){
                    myDeck.removeAll();
                    repaint();
                    validate();
                }
                else {
                    for(int i=0;i<currentDeck.size();i++){
                        JLabel newCard = new JLabel();
                        newCard.setIcon(currentDeck.get(i).getImgSmall());
                        newCard.addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent e){
                                for(int i=0;i<myDeck.getComponentCount();i++){
                                    if(e.getComponent().equals(myDeck.getComponent(i))){
                                        currentCard = currentDeck.get(i);
                                        cardName.setText("  "+currentCard.getName());
                                        cardType.setText("       "+currentCard.getType());
                                        cardEff.setText("       "+currentCard.getEff());
                                        cardHp.setText("           "+currentCard.getHp());
                                        cardClass.setText("       "+currentCard.getClasses());
                                        cardCost.setText("           "+currentCard.getCost());
                                        cardLimit.setText("           "+currentCard.getLimit());
                                        cardTier.setText("           "+currentCard.getTier());
                                        cardImg.setIcon(currentCard.getImgNorm());
                                    }
                                }
                            }
                        });
                        myDeck.add(newCard);
                        repaint();
                        validate();
                    }
                }
                deckCount.setText(currentDeck.size()+"/30");
            }
        });
        
        JPanel filterPane = new JPanel();
        ButtonGroup buttonGroup1 = new ButtonGroup ( );
        JButton resetFil = new JButton("Reset filter");
        JLabel filterClass = new JLabel("Class: ",JLabel.CENTER);
        JToggleButton[] fil = new JToggleButton[9];
        fil[1] = new JRadioButton("Auden");  buttonGroup1.add(fil[1]);
        fil[2] = new JRadioButton("Dica"); buttonGroup1.add(fil[2]); 
        fil[3] = new JRadioButton("Natus"); buttonGroup1.add(fil[3]); 
        fil[4] = new JRadioButton("Natural"); buttonGroup1.add(fil[4]);
        fil[5] = new JRadioButton("Seal"); buttonGroup1.add(fil[5]);
        fil[6] = new JRadioButton("Atk"); buttonGroup1.add(fil[6]);
        fil[7] = new JRadioButton("Def"); buttonGroup1.add(fil[7]);
        fil[8] = new JRadioButton("Magic"); buttonGroup1.add(fil[8]);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel> (model);
        cardTable.setRowSorter(sorter);
        Vector<viewDeckCard> filVect1 = new Vector<viewDeckCard>();
        for(int i=0;i<cardvect.size();i++){
            String find = "Auden";
            viewDeckCard c = cardvect.get(i);
            if(c.getClasses().equals(find))
                filVect1.add(c);
        }
        Vector<viewDeckCard> filVect2 = new Vector<viewDeckCard>();
        for(int i=0;i<cardvect.size();i++){
            String find = "Dica";
            viewDeckCard c = cardvect.get(i);
            if(c.getClasses().equals(find))
                filVect2.add(c);
        }
        Vector<viewDeckCard> filVect3 = new Vector<viewDeckCard>();
        for(int i=0;i<cardvect.size();i++){
            String find = "Natus";
            viewDeckCard c = cardvect.get(i);
            if(c.getClasses().equals(find))
                filVect3.add(c);
        }
        Vector<viewDeckCard> filVect4 = new Vector<viewDeckCard>();
        for(int i=0;i<cardvect.size();i++){
            String find = "Natural";
            viewDeckCard c = cardvect.get(i);
            if(c.getClasses().equals(find))
                filVect4.add(c);
        }
        Vector<viewDeckCard> filVect5 = new Vector<viewDeckCard>();
        for(int i=0;i<cardvect.size();i++){
            String find = "Seal";
            viewDeckCard c = cardvect.get(i);
            if(c.getType().equals(find))
                filVect5.add(c);
        }
        Vector<viewDeckCard> filVect6 = new Vector<viewDeckCard>();
        for(int i=0;i<cardvect.size();i++){
            String find = "Atk";
            viewDeckCard c = cardvect.get(i);
            if(c.getType().equals(find))
                filVect6.add(c);
        }
        Vector<viewDeckCard> filVect7 = new Vector<viewDeckCard>();
        for(int i=0;i<cardvect.size();i++){
            String find = "Def";
            viewDeckCard c = cardvect.get(i);
            if(c.getType().equals(find))
                filVect7.add(c);
        }
        Vector<viewDeckCard> filVect8 = new Vector<viewDeckCard>();
        for(int i=0;i<cardvect.size();i++){
            String find = "Magic";
            viewDeckCard c = cardvect.get(i);
            if(c.getType().equals(find))
                filVect8.add(c);
        }
        resetFil.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    buttonGroup1.clearSelection();
                    cardTable.getSelectionModel().removeListSelectionListener(listListener);
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    cardTable.getSelectionModel().addListSelectionListener(listListener);
                    model.addColumn("Card",cardvect);
                    cardTable.setModel(model);
                    cardTable.invalidate();
                    cardTable.repaint();
                }
        });
        fil[1].addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    cardTable.getSelectionModel().removeListSelectionListener(listListener);
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    cardTable.getSelectionModel().addListSelectionListener(listListener);
                    model.addColumn("Card Filter",filVect1);
                    cardTable.setModel(model);
                    cardTable.invalidate();
                    cardTable.repaint();
                    }       
        });
        fil[2].addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    cardTable.getSelectionModel().removeListSelectionListener(listListener);
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    cardTable.getSelectionModel().addListSelectionListener(listListener);
                    model.addColumn("Card Filter",filVect2);
                    cardTable.setModel(model);
                    cardTable.invalidate();
                    cardTable.repaint();
                    }       
        });
        fil[3].addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    cardTable.getSelectionModel().removeListSelectionListener(listListener);
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    cardTable.getSelectionModel().addListSelectionListener(listListener);
                    model.addColumn("Card Filter",filVect3);
                    cardTable.setModel(model);
                    cardTable.invalidate();
                    cardTable.repaint();
                    }       
        });
        fil[4].addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    cardTable.getSelectionModel().removeListSelectionListener(listListener);
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    cardTable.getSelectionModel().addListSelectionListener(listListener);
                    model.addColumn("Card Filter",filVect4);
                    cardTable.setModel(model);
                    cardTable.invalidate();
                    cardTable.repaint();
                    }       
        });
        fil[5].addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    cardTable.getSelectionModel().removeListSelectionListener(listListener);
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    cardTable.getSelectionModel().addListSelectionListener(listListener);
                    model.addColumn("Card Filter",filVect5);
                    cardTable.setModel(model);
                    cardTable.invalidate();
                    cardTable.repaint();
                    }       
        });
        fil[6].addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    cardTable.getSelectionModel().removeListSelectionListener(listListener);
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    cardTable.getSelectionModel().addListSelectionListener(listListener);
                    model.addColumn("Card Filter",filVect6);
                    cardTable.setModel(model);
                    cardTable.invalidate();
                    cardTable.repaint();
                    }       
        });
        fil[7].addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    cardTable.getSelectionModel().removeListSelectionListener(listListener);
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    cardTable.getSelectionModel().addListSelectionListener(listListener);
                    model.addColumn("Card Filter",filVect7);
                    cardTable.setModel(model);
                    cardTable.invalidate();
                    cardTable.repaint();
                    }       
        });
        fil[8].addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    cardTable.getSelectionModel().removeListSelectionListener(listListener);
                    model.setRowCount(0);
                    model.setColumnCount(0);
                    cardTable.getSelectionModel().addListSelectionListener(listListener);
                    model.addColumn("Card Filter",filVect8);
                    cardTable.setModel(model);
                    cardTable.invalidate();
                    cardTable.repaint();
                    }       
        });
        
        filterPane.add(resetFil);
        JPanel filterClassPane = new JPanel();
        filterClassPane.add(filterClass);
        //filterClassPane.add(fil[0]);
        filterClassPane.add(fil[1]);
        filterClassPane.add(fil[2]);
        filterClassPane.add(fil[3]);
        filterClassPane.add(fil[4]);
        filterClassPane.add(fil[5]);
        filterClassPane.add(fil[6]);
        filterClassPane.add(fil[7]);
        filterClassPane.add(fil[8]);
        filterPane.add(filterClassPane);

        
        JPanel topMenu = new JPanel();
        topMenu.setLayout(new GridLayout(3,1));
        topMenu.add(deckPane);
        topMenu.add(filterPane);
        topMenu.add(deckCount);
        
        JPanel nowDeck = new JPanel();
        nowDeck.setLayout(new BorderLayout());
       
        for(int i=0;i<currentDeck.size();i++){
            //System.out.println(currentDeck.get(i).getName());
            JLabel newCard = new JLabel();
            newCard.setIcon(currentDeck.get(i).getImgSmall());
            newCard.addMouseListener(new MouseAdapter(){
                            public void mouseClicked(MouseEvent e){
                                for(int i=0;i<myDeck.getComponentCount();i++){
                                    if(e.getComponent().equals(myDeck.getComponent(i))){
                                        currentCard = currentDeck.get(i);
                                        cardName.setText("  "+currentCard.getName());
                                        cardType.setText("       "+currentCard.getType());
                                        cardEff.setText("       "+currentCard.getEff());
                                        cardHp.setText("           "+currentCard.getHp());
                                        cardClass.setText("       "+currentCard.getClasses());
                                        cardCost.setText("           "+currentCard.getCost());
                                        cardLimit.setText("           "+currentCard.getLimit());
                                        cardTier.setText("           "+currentCard.getTier());
                                        cardImg.setIcon(currentCard.getImgNorm());
                                        cardPanel.setVisible(true);
                                    }
                                }
                            }
                        });
            myDeck.add(newCard);
            repaint();
            validate();
        }
        
        nowDeck.add(myDeck,BorderLayout.CENTER);
        nowDeck.add(topMenu,BorderLayout.NORTH);
        
        JButton buttonAdd = new JButton ( "Add" );
        JButton buttonRemove = new JButton ( "Remove" );
        JPanel button = new JPanel();
        button.setBounds(0,0,200,50);
        button.add(buttonAdd);
        button.add(buttonRemove);
        buttonAdd.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(currentCard!=null&&allDeck.getItemCount()!=0){
                    int cout=0;
                    myDeck.removeAll();
                    if(currentDeck.size()==30)
                        JOptionPane.showMessageDialog(null, "Your deck is full!","Warning!", JOptionPane.INFORMATION_MESSAGE);
                    else if(currentDeck.size()==0&&currentCard.getCode().charAt(0)=='0'){
                        //System.out.println("Testtt");
                            JOptionPane.showMessageDialog(null, "You must add Seal card first!","Warning!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        for(int i=0;i<currentDeck.size();i++){
                            if(currentDeck.get(i).getCode().equals(currentCard.getCode())){
                                cout++;
                            }
                        }
                        if(cout>=currentCard.getLimit()){
                            JOptionPane.showMessageDialog(null, "You can't add this card more than the limit!","Warning!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(currentDeck.size()>0&&currentCard.getType().equals(currentDeck.get(0).getType())){
                            JOptionPane.showMessageDialog(null, "You can't add more than 1 Seal in your deck","Warning!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(currentCard.getType().equals("Seal")){
                            currentDeck.add(0,currentCard);
                        }
                        else{
                            if(!(currentCard.getClasses().equals(currentDeck.get(0).getClasses()))&&!(currentCard.getClasses().equals("Natural"))&&!(currentCard.getClasses().equals("Special"))){
                                JOptionPane.showMessageDialog(null, "You can't use a card that is different from your Seal's class","Warning!", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                                currentDeck.add(currentCard);
                        }
                        try{
                            FileWriter fw = new FileWriter(new File(System.getProperty("user.dir")+"/deck/"+allDeck.getSelectedItem()+".txt"));
                            for(int i=0;i<currentDeck.size();i++)
                            fw.write(currentDeck.get(i).getCode()+"\n");
                            fw.close();
                        }
                        catch(FileNotFoundException ex){
                            System.out.println(ex);
                        }
                        catch(IOException ex){
                            System.out.println(ex);
                        }
                        for(int i=0;i<currentDeck.size();i++){
                            //System.out.println(currentDeck.get(i).getName());
                            JLabel newCard = new JLabel();
                            newCard.setIcon(currentDeck.get(i).getImgSmall());
                            newCard.addMouseListener(new MouseAdapter(){
                                public void mouseClicked(MouseEvent e){
                                    for(int i=0;i<myDeck.getComponentCount();i++){
                                        if(e.getComponent().equals(myDeck.getComponent(i))){
                                            currentCard = currentDeck.get(i);
                                            currentCard = currentDeck.get(i);
                                            cardName.setText("  "+currentCard.getName());
                                            cardType.setText("       "+currentCard.getType());
                                            cardEff.setText("       "+currentCard.getEff());
                                            cardHp.setText("           "+currentCard.getHp());
                                            cardClass.setText("       "+currentCard.getClasses());
                                            cardCost.setText("           "+currentCard.getCost());
                                            cardLimit.setText("           "+currentCard.getLimit());
                                            cardTier.setText("           "+currentCard.getTier());
                                            cardImg.setIcon(currentCard.getImgNorm());
                                        }
                                    }
                                }
                            });
                            myDeck.add(newCard);
                        }
                        deckCount.setText(currentDeck.size()+"/30");
                        repaint();
                        validate();
                    }
                }
            }
        });
        buttonRemove.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(currentCard!=null&&allDeck.getItemCount()!=0){
                    myDeck.removeAll();
                    boolean check=true;
                    for(int i=currentDeck.size()-1;i>=0;i--){
                        if(currentDeck.get(i).getCode().equals(currentCard.getCode())){
                            currentDeck.remove(i);
                            check = false;
                            break;
                        }
                    }
                    if(check==true){
                        JOptionPane.showMessageDialog(null, "You don't have this card in your Deck","Warning!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    try{
                            FileWriter fw = new FileWriter(new File(System.getProperty("user.dir")+"/deck/"+allDeck.getSelectedItem()+".txt"));
                            for(int i=0;i<currentDeck.size();i++)
                             fw.write(currentDeck.get(i).getCode()+"\n");
                            fw.close();
                        }
                        catch(FileNotFoundException ex){
                            System.out.println(ex);
                        }
                        catch(IOException ex){
                            System.out.println(ex);
                        }
                    for(int i=0;i<currentDeck.size();i++){
                            //System.out.println(currentDeck.get(i).getName());
                            JLabel newCard = new JLabel();
                            newCard.setIcon(currentDeck.get(i).getImgSmall());
                            newCard.addMouseListener(new MouseAdapter(){
                                public void mouseClicked(MouseEvent e){
                                    for(int i=0;i<myDeck.getComponentCount();i++){
                                        if(e.getComponent().equals(myDeck.getComponent(i))){
                                            currentCard = currentDeck.get(i);
                                            cardName.setText("  "+currentCard.getName());
                                            cardType.setText("       "+currentCard.getType());
                                            cardEff.setText("       "+currentCard.getEff());
                                            cardHp.setText("           "+currentCard.getHp());
                                            cardClass.setText("       "+currentCard.getClasses());
                                            cardCost.setText("           "+currentCard.getCost());
                                            cardLimit.setText("           "+currentCard.getLimit());
                                            cardTier.setText("           "+currentCard.getTier());
                                            cardImg.setIcon(currentCard.getImgNorm());
                                        }
                                    }
                                }
                            });
                            myDeck.add(newCard);
                        }
                        deckCount.setText(currentDeck.size()+"/30");
                        repaint();
                        validate();
                }
            }
        });
        
        JPanel control = new JPanel();
        control.setLayout(new BorderLayout());
        control.add(button,BorderLayout.SOUTH);
        control.add(cardSP,BorderLayout.CENTER);
    
        contentpane.add(control,BorderLayout.EAST);
        contentpane.add(cardPanel,BorderLayout.WEST);
        contentpane.add(nowDeck,BorderLayout.CENTER);  
        validate();
        
    }
}

class MyImageIcon extends ImageIcon{
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height)
    {
	Image oldimg = this.getImage();
	Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
	return new MyImageIcon(newimg);
    }
}


public class deck {
    public static void main(String[] args) {
        new deckwindow();    
        //new mainMenu();
    }
    
}
