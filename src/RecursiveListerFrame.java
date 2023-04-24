import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveListerFrame extends JFrame {
    JPanel mainPnl, titlePnl, displayPnl, btnPnl;

    JLabel titleLbl;

    JScrollPane scroller;
    JTextArea displayTA;

    JButton quitBtn, searchBtn;


    public RecursiveListerFrame() //DONE
    {
        setTitle("Recursive Directory Lister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize((screenWidth / 4) * 3, screenHeight);
        setLocationRelativeTo(null); //centers

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        add(mainPnl);
        createTitlePanel();
        createDisplayPanel();
        createButtonPanel();

        setVisible(true);
    }

    private void createTitlePanel()//DONE
    {
        titlePnl = new JPanel();

        titleLbl = new JLabel("Recursive Directory Lister");
        titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 48));

        //aligns text and image to be stacked not side by side
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);


        titlePnl.add(titleLbl);
        mainPnl.add(titlePnl, BorderLayout.NORTH);
    }


    private void createDisplayPanel() //DONE
    {
        displayPnl = new JPanel();

        displayTA = new JTextArea(36, 70);
        scroller = new JScrollPane(displayTA);

        displayTA.setEditable(false);

        displayPnl.add(scroller);
        mainPnl.add(displayPnl, BorderLayout.CENTER);
    }


    private void createButtonPanel() { //DONE
        btnPnl = new JPanel();
        btnPnl.setLayout(new GridLayout(1, 2));

        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Times New Roman", Font.PLAIN, 48));
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 48));

        btnPnl.add(searchBtn);
        btnPnl.add(quitBtn);

        mainPnl.add(btnPnl, BorderLayout.SOUTH);


        //Quit Button - DONE
        quitBtn.addActionListener(new ActionListener() {
            JOptionPane pane = new JOptionPane();

            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(pane, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });


        //Search Button - DONE
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

    }

    //JFileChooser - searches
    private void search() { //DONE
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose A Directory: ");

        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);


        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File chosenDir = chooser.getSelectedFile();
            displayTA.setText("Chosen Directory:   " + chosenDir + "\n\n"); //names the chosen directory
            displayTA.append("Sub-directories, Files from Sub-directories, and Files from chosen Directory are Listed Below." +"\n\n\n");


            //list the file names + list directory names and sub files and directory names
            listNames(chosenDir);

        } else
            displayTA.append("File not found! Try Again!");
    }

    //list the file names + list directory names and sub files and directory names
    private void listNames(File f) { //DONE
        File Names[] = f.listFiles();

        if (Names != null) {
            for (File n : Names) {

                displayTA.append(n + "\n\n");

                if (n.isDirectory()) {
                    listNames(n);
                }
            }
        }
    }
}