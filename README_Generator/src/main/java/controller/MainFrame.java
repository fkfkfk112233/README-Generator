package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.ProjectInfo;
import service.ProjectService;
import service.ReadmeService;
import service.impl.ProjectServiceImpl;
import service.impl.ReadmeServiceImpl;

import util.FileUtil;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.File;
import java.util.List;

public class MainFrame extends JFrame {
	
	//========================
	// Current Project
	//========================

	private Integer currentProjectId = null;

    //========================
    // TextField
    //========================

    private JTextField txtProjectName;
    private JTextField txtAuthor;
    private JTextField txtVersion;
    private JTextField txtGithub;

    //========================
    // TextArea
    //========================

    private JTextArea txtDescription;
    private JTextArea txtFeatures;
    private JTextArea txtInstallation;
    private JTextArea txtUsage;

    private JTextArea txtPreview;

    //========================
    // ComboBox
    //========================

    private JComboBox<String> cmbTemplate;

    //========================
    // Button
    //========================

    private JButton btnGenerate;
    private JButton btnSave;
    private JButton btnLoad;
    private JButton btnExport;
    
    //========================
    // JTable
    //========================
    
    private JTable tableProject;
    private DefaultTableModel tableModel;

    //========================
    // Constructor
    //========================

    public MainFrame() {

        buildUI();

        initEvent();
        
        refreshProjectTable();

    }

    //========================
    // UI
    //========================

    private void buildUI() {

        setTitle("README Generator V2");

        setSize(1300, 800);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSplitPane splitPane = new JSplitPane();

        splitPane.setDividerLocation(500);

        splitPane.setResizeWeight(0.45);

        splitPane.setLeftComponent(buildLeftPanel());

        splitPane.setRightComponent(buildRightPanel());

        add(splitPane, BorderLayout.CENTER);

        add(buildBottomPanel(), BorderLayout.SOUTH);

    }

    //========================
    // Left Panel
    //========================

    private JPanel buildLeftPanel() {

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5,5,5,5);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.anchor = GridBagConstraints.NORTHWEST;

        int row = 0;

        //--------------------------

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Project Name"), gbc);

        txtProjectName = new JTextField();

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(txtProjectName, gbc);

        //--------------------------

        row++;

        gbc.weightx = 0;

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Author"), gbc);

        txtAuthor = new JTextField();

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(txtAuthor, gbc);

        //--------------------------

        row++;

        gbc.weightx = 0;

        gbc.gridx = 0;
        gbc.gridy = row;

        panel.add(new JLabel("Version"), gbc);

        txtVersion = new JTextField();

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(txtVersion, gbc);

        //--------------------------

        row++;

        gbc.weightx = 0;

        gbc.gridx = 0;
        gbc.gridy = row;

        panel.add(new JLabel("Github"), gbc);

        txtGithub = new JTextField();

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(txtGithub, gbc);

        //--------------------------

        row++;

        gbc.weightx = 0;

        gbc.gridx = 0;
        gbc.gridy = row;

        panel.add(new JLabel("Template"), gbc);

        cmbTemplate = new JComboBox<>();

        cmbTemplate.addItem("Default");

        cmbTemplate.addItem("Professional");

        cmbTemplate.addItem("Minimal");

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(cmbTemplate, gbc);

        //--------------------------

        row++;

        addTextArea(panel, gbc, row, "Description");

        row += 2;

        addTextArea(panel, gbc, row, "Features");

        row += 2;

        addTextArea(panel, gbc, row, "Installation");

        row += 2;

        addTextArea(panel, gbc, row, "Usage");

        return panel;

    }

    //========================
    // Right Panel
    //========================

    private JPanel buildRightPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        //-----------------------
        // JTable
        //-----------------------

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Project");
        tableModel.addColumn("Version");
        tableModel.addColumn("Author");

        tableProject = new JTable(tableModel);

        JScrollPane tableScroll =
                new JScrollPane(tableProject);

        tableScroll.setPreferredSize(
                new Dimension(500,200)
        );

        //-----------------------
        // Preview
        //-----------------------

        txtPreview = new JTextArea();

        txtPreview.setEditable(false);

        txtPreview.setLineWrap(true);

        txtPreview.setWrapStyleWord(true);

        JScrollPane previewScroll =
                new JScrollPane(txtPreview);

        JSplitPane split =
                new JSplitPane(
                        JSplitPane.VERTICAL_SPLIT,
                        tableScroll,
                        previewScroll
                );

        split.setDividerLocation(220);

        panel.add(split);

        return panel;

    }
    
    private void refreshProjectTable() {

        tableModel.setRowCount(0);

        List<ProjectInfo> list =
                projectService.findAll();

        for(ProjectInfo p:list){

            tableModel.addRow(new Object[]{

                    p.getId(),

                    p.getProjectName(),

                    p.getVersion(),

                    p.getAuthor()

            });

        }

    }

    //========================
    // Bottom Panel
    //========================

    private JPanel buildBottomPanel() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnGenerate = new JButton("Generate");

        btnSave = new JButton("Save Project");

        btnLoad = new JButton("Load Project");

        btnExport = new JButton("Export README");

        panel.add(btnGenerate);

        panel.add(btnSave);

        panel.add(btnLoad);

        panel.add(btnExport);

        return panel;

    }

    //========================
    // 建立 TextArea
    //========================

    private void addTextArea(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String title){

        gbc.gridx=0;
        gbc.gridy=row;
        gbc.weightx=0;

        panel.add(new JLabel(title),gbc);

        JTextArea area=new JTextArea();

        area.setLineWrap(true);

        area.setWrapStyleWord(true);

        JScrollPane scroll=new JScrollPane(area);

        scroll.setPreferredSize(new Dimension(300,100));

        gbc.gridx=1;

        gbc.weightx=1;

        gbc.fill=GridBagConstraints.BOTH;

        panel.add(scroll,gbc);

        gbc.fill=GridBagConstraints.HORIZONTAL;

        switch(title){

            case "Description":
                txtDescription=area;
                break;

            case "Features":
                txtFeatures=area;
                break;

            case "Installation":
                txtInstallation=area;
                break;

            case "Usage":
                txtUsage=area;
                break;

        }

    }
    
    //========================
    // Service
    //========================

    private final ProjectService projectService = new ProjectServiceImpl();

    private final ReadmeService readmeService = new ReadmeServiceImpl();

    //========================
    // Event
    //========================

    private void initEvent() {

        btnGenerate.addActionListener(e -> generateReadme());

        btnSave.addActionListener(e -> saveProject());

        btnLoad.addActionListener(e -> loadProject());

        btnExport.addActionListener(e -> exportReadme());
        
        tableProject.getSelectionModel()
        .addListSelectionListener(e -> {


            if(e.getValueIsAdjusting()){
                return;
            }


            int row =
                    tableProject.getSelectedRow();


            if(row == -1){
                return;
            }


            Object value =
                    tableModel.getValueAt(row,0);


            currentProjectId =
                    Integer.parseInt(value.toString());



            ProjectInfo p =
                    projectService.findById(
                            currentProjectId
                    );


            if(p != null){

                setProjectToUI(p);

            }


        });
    }
    
    private void generateReadme() {

        ProjectInfo project = getProjectFromUI();

        String template = cmbTemplate.getSelectedItem().toString();

        String markdown =
                readmeService.generate(project, template);

        txtPreview.setText(markdown);

    }
    
    private void saveProject() {

        ProjectInfo project = getProjectFromUI();

        int id = projectService.insert(project);

        if (id > 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "新增成功\nID：" + id
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "新增失敗"
            );

        }
        
        refreshProjectTable();

    }
    
    private void loadProject() {

        String input = JOptionPane.showInputDialog(
                this,
                "請輸入 Project ID"
        );

        if (input == null || input.isBlank()) {

            return;

        }

        try {

            int id = Integer.parseInt(input);

            ProjectInfo p = projectService.findById(id);

            if (p == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "查無資料"
                );

                return;

            }

            txtProjectName.setText(p.getProjectName());

            txtAuthor.setText(p.getAuthor());

            txtVersion.setText(p.getVersion());

            txtGithub.setText(p.getGithubUrl());

            txtDescription.setText(p.getDescription());

            txtFeatures.setText(p.getFeatures());

            txtInstallation.setText(p.getInstallation());

            txtUsage.setText(p.getUsage());

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID 格式錯誤"
            );

        }

    }
    
    private void exportReadme() {

        JFileChooser chooser = new JFileChooser();

        chooser.setSelectedFile(
                new File("README.md")
        );

        int result = chooser.showSaveDialog(this);

        if (result != JFileChooser.APPROVE_OPTION) {

            return;

        }

        File file = chooser.getSelectedFile();

        FileUtil.save(

                txtPreview.getText(),

                file.getAbsolutePath()

        );

        JOptionPane.showMessageDialog(

                this,

                "README 匯出成功"

        );

    }
    
    private ProjectInfo getProjectFromUI() {

        ProjectInfo p = new ProjectInfo();

        p.setProjectName(txtProjectName.getText());

        p.setAuthor(txtAuthor.getText());

        p.setVersion(txtVersion.getText());

        p.setGithubUrl(txtGithub.getText());

        p.setDescription(txtDescription.getText());

        p.setFeatures(txtFeatures.getText());

        p.setInstallation(txtInstallation.getText());

        p.setUsage(txtUsage.getText());

        return p;

    }
    
    private void updateProject(){


        if(currentProjectId == null){

            JOptionPane.showMessageDialog(
                    this,
                    "請先選擇專案"
            );

            return;
        }


        ProjectInfo project =
                getProjectFromUI();


        project.setId(currentProjectId);


        boolean result =
                projectService.update(project);



        if(result){

            JOptionPane.showMessageDialog(
                    this,
                    "修改成功"
            );


            refreshProjectTable();


        }else{


            JOptionPane.showMessageDialog(
                    this,
                    "修改失敗"
            );

        }

    }
    
    private void deleteProject(){


        if(currentProjectId == null){

            JOptionPane.showMessageDialog(
                    this,
                    "請先選擇專案"
            );

            return;
        }


        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "確定刪除？",
                        "Delete",
                        JOptionPane.YES_NO_OPTION
                );


        if(confirm != JOptionPane.YES_OPTION){

            return;

        }



        boolean result =
                projectService.delete(currentProjectId);



        if(result){


            JOptionPane.showMessageDialog(
                    this,
                    "刪除成功"
            );


            currentProjectId = null;


            clearInput();


            refreshProjectTable();


        }

    }
      
    private void setProjectToUI(ProjectInfo p){

        txtProjectName.setText(p.getProjectName());

        txtAuthor.setText(p.getAuthor());

        txtVersion.setText(p.getVersion());

        txtGithub.setText(p.getGithubUrl());

        txtDescription.setText(p.getDescription());

        txtFeatures.setText(p.getFeatures());

        txtInstallation.setText(p.getInstallation());

        txtUsage.setText(p.getUsage());

    }
    
    private void clearInput(){


        txtProjectName.setText("");

        txtAuthor.setText("");

        txtVersion.setText("");

        txtGithub.setText("");

        txtDescription.setText("");

        txtFeatures.setText("");

        txtInstallation.setText("");

        txtUsage.setText("");


    }

    //========================
    // Main
    //========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new MainFrame().setVisible(true);

        });

    }

}
