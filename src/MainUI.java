import DB.DebugMode;
import Factory.DAOFactory;
import Pojo.Info;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import java.util.Date;

public class MainUI {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JList list1;
    private JTextField 姓名;
    private JTextField 左眼;
    private JTextField 右眼;
    private JTextField 瞳距;
    private JTextField 镜架尺寸;
    private JTextField 镜片;
    private JTextField 店;
    private JTextField 验光师;
    private JTextField 电话;
    private JTextField 下加光;
    private JTextField 通道;
    private JTextField 瞳高;
    private JButton 修改;
    private JButton 确认;
    private JLabel 标识码;
    private JButton 删除;
    private JTextField 姓名i;
    private JTextField 左眼i;
    private JTextField 右眼i;
    private JTextField 瞳距i;
    private JTextField 镜架尺寸i;
    private JTextField 镜片i;
    private JTextField 店i;
    private JTextField 验光师i;
    private JTextField 电话i;
    private JTextField 下加光i;
    private JTextField 通道i;
    private JTextField 瞳高i;
    private JButton 添加;
    private JButton 重置;
    private JLabel 数据库状态;
    private JLabel 信息数目;
    private JLabel 检测时间;
    private JTextArea 信息提示框;
    private JTextField 价格;
    private JTextField 日期;
    private JTextField 价格i;
    private JTextField 日期i;
    private JProgressBar 进度条;
    private JButton 数据导入;
    private JButton 数据导出;
    private JTextField 导入路径;
    private JTextField 导出文件夹;
    private JButton 开始Button;
    private JButton 确认Button;
    private JTextField 密码;
    private JTextField 命令;
    private JButton 执行;
    private JTextArea 结果;
    private ArrayList<Info> infoes = null;
    private Info info = null;
    private Info insert_info = null;
    private String[] listContent;
    private String importPath = null;

    public MainUI() {
        checkDBStatus();
        textField1.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updateList();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updateList();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updateList();
                    }
                }
        );
        右眼i.getDocument().addDocumentListener((
                new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        checkInsertInfo();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        checkInsertInfo();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        checkInsertInfo();
                    }
                }
        ));
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    updateInfoPanel();
                    setTextFieldStatus(false);
                    修改.setEnabled(true);
                    确认.setEnabled(false);
                    删除.setEnabled(false);
                }
            }
        });
        修改.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextFieldStatus(true);
                修改.setEnabled(false);
                确认.setEnabled(true);
                删除.setEnabled(true);
            }
        });
        确认.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                修改.setEnabled(true);
                删除.setEnabled(false);
                确认.setEnabled(false);
                setTextFieldStatus(false);
                info = getCurrentInfo();
                try {
                    if (DAOFactory.getInfoDaoInstance().updateInfoByID(info.getID(), info)) {
                        JOptionPane.showMessageDialog(null, info.getName() + "的信息修改成功！", "成功!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, info.getName() + "的信息修改失败！", "错误!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        删除.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int okornot = JOptionPane.showConfirmDialog(null, "信息删除是不可恢复的,确认删除吗?", "确认删除?", JOptionPane.OK_CANCEL_OPTION);
                if (okornot == 0) {
                    info = getCurrentInfo();
                    try {
                        boolean flag = DAOFactory.getInfoDaoInstance().deleteInfoByID(info.getID());
                        if (flag) {
                            JOptionPane.showMessageDialog(null, info.getName() + "的信息删除成功！", "成功!", JOptionPane.INFORMATION_MESSAGE);
                            updateList();
                            删除.setEnabled(false);
                            确认.setEnabled(false);
                        } else {
                            JOptionPane.showMessageDialog(null, info.getName() + "的信息删除失败！", "错误!", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        重置.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                姓名i.setText("");
                左眼i.setText("");
                右眼i.setText("");
                瞳距i.setText("");
                镜架尺寸i.setText("");
                镜片i.setText("");
                店i.setText("");
                验光师i.setText("");
                电话i.setText("");
                下加光i.setText("");
                通道i.setText("");
                瞳高i.setText("");
                价格i.setText("");
                日期i.setText("");
            }
        });
        添加.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insert_info = getInsertInfo();
                try {
                    boolean flag = DAOFactory.getInfoDaoInstance().insertInfo(insert_info);
                    if (flag) {
                        JOptionPane.showMessageDialog(null, insert_info.getName() + "的信息添加成功！", "成功!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, insert_info.getName() + "的信息添加失败！", "错误!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        数据导入.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                数据导出.setEnabled(false);
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "选择");
                File file = jfc.getSelectedFile();
                try {
                    if (file.isFile()) {
                        导入路径.setText(jfc.getSelectedFile().getAbsolutePath());
                        开始Button.setEnabled(true);
                    }
                } catch (NullPointerException o) {
                    JOptionPane.showMessageDialog(null, "文件选择未完成", "错误!", JOptionPane.ERROR_MESSAGE);
                    数据导出.setEnabled(true);
                }
            }
        });
        开始Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                开始Button.setEnabled(false);
                if (数据导入.isEnabled()) {
                    try {
                        int counts = Tools.ImportInfo.getDataFromExcel(导入路径.getText(), 进度条);
                        if (进度条.getValue() == 100) {
                            JOptionPane.showMessageDialog(null, counts + "条信息导入成功！", "成功!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        数据导出.setEnabled(true);
                        进度条.setValue(0);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }else {
                    try {
                        ArrayList<Info> myinfoes = DAOFactory.getInfoDaoInstance().queryInfoByName("");

                        int counts = Tools.ExportInfo.exportData(导出文件夹.getText(),进度条,myinfoes);
                        if (进度条.getValue() == 100) {
                            JOptionPane.showMessageDialog(null, myinfoes.size() + "条信息导出成功！", "成功!", JOptionPane.INFORMATION_MESSAGE);
                            数据导入.setEnabled(true);
                        }
                        数据导出.setEnabled(true);
                        进度条.setValue(0);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        数据导出.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                数据导入.setEnabled(false);
                开始Button.setEnabled(true);
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "选择");
                File file = jfc.getSelectedFile();
                try {
                    if (file.isDirectory()) 导出文件夹.setText(jfc.getSelectedFile().getAbsolutePath());


                } catch (NullPointerException o) {
                    JOptionPane.showMessageDialog(null, "文件夹选择未完成", "错误!", JOptionPane.ERROR_MESSAGE);
                    数据导入.setEnabled(true);
                    开始Button.setEnabled(false);
                }
            }
        });
        确认Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(密码.getText().equals("damk")){
                    执行.setEnabled(true);
                    命令.setEditable(true);
                    命令.setEnabled(true);
                }else {
                    JOptionPane.showMessageDialog(null, "密码不正确", "错误!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        执行.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = 命令.getText();
                new DebugMode(结果).executeSQL(sql);
            }
        });
        信息数目.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    checkDBStatus();
                }
            }
        });
    }

    private void checkDBStatus() {
        int counts;
        try {
            检测时间.setText(new Date().toString());
            counts = DAOFactory.getInfoDaoInstance().queryStatus();
            if (counts != -1) {
                数据库状态.setText("正常");
                数据库状态.setForeground(Color.GREEN);
                信息数目.setText(String.valueOf(counts));
            } else {
                数据库状态.setText("异常");
                数据库状态.setForeground(Color.RED);
            }
        } catch (Exception e) {
            数据库状态.setText("异常");
            数据库状态.setForeground(Color.RED);
            e.printStackTrace();
        }
    }

    private void checkInsertInfo() {
        if ((姓名i.getText().equals("")) || (左眼i.getText().equals(""))
                || (右眼i.getText().equals(""))) {
            添加.setEnabled(false);
        } else {
            添加.setEnabled(true);
        }
    }

    private Info getInsertInfo() {
        Info insertInfo = new Info();
        insertInfo.setName(姓名i.getText());
        insertInfo.setRight_eye(右眼i.getText());
        insertInfo.setLeft_eye(左眼i.getText());
        insertInfo.setDistance(Integer.valueOf(瞳距i.getText()));
        insertInfo.setSize(镜架尺寸i.getText());
        insertInfo.setLens(镜片i.getText());
        insertInfo.setStore(店i.getText());
        insertInfo.setPhone(电话i.getText());
        insertInfo.setPerson(验光师i.getText());
        insertInfo.setXwjwgd(下加光i.getText());
        insertInfo.setPass(通道i.getText());
        insertInfo.setHeight(瞳高i.getText());
        insertInfo.setPrice(价格i.getText());
        insertInfo.setDATA(日期i.getText());
        return insertInfo;
    }

    private void setTextFieldStatus(boolean flag) {
        姓名.setEditable(flag);
        左眼.setEditable(flag);
        右眼.setEditable(flag);
        瞳距.setEditable(flag);
        镜架尺寸.setEditable(flag);
        镜片.setEditable(flag);
        店.setEditable(flag);
        验光师.setEditable(flag);
        电话.setEditable(flag);
        下加光.setEditable(flag);
        通道.setEditable(flag);
        瞳高.setEditable(flag);
        价格.setEditable(flag);
        日期.setEditable(flag);
    }

    private void updateInfoPanel() {
        int ID;
        try {
            String idstr = list1.getSelectedValue().toString().split(" - ")[1];
            ID = Integer.valueOf(idstr);
            info = DAOFactory.getInfoDaoInstance().queryInfoByID(ID);
            processInfo();
        } catch (Exception e) {

        }
    }

    private Info getCurrentInfo() {
        Info currentInfo = new Info();
        currentInfo.setID(Integer.valueOf(标识码.getText()));
        currentInfo.setName(姓名.getText());
        currentInfo.setRight_eye(右眼.getText());
        currentInfo.setLeft_eye(左眼.getText());
        currentInfo.setDistance(Integer.valueOf(瞳距.getText()));
        currentInfo.setSize(镜架尺寸.getText());
        currentInfo.setLens(镜片.getText());
        currentInfo.setStore(店.getText());
        currentInfo.setPerson(验光师.getText());
        currentInfo.setXwjwgd(下加光.getText());
        currentInfo.setPhone(电话.getText());
        currentInfo.setPass(通道.getText());
        currentInfo.setHeight(瞳高.getText());
        currentInfo.setPrice(价格.getText());
        currentInfo.setDATA(日期.getText());
        return currentInfo;
    }

    private void updateList() {
        try {
            if (!textField1.getText().equals("")) {
                infoes = DAOFactory.getInfoDaoInstance().queryInfoByName(textField1.getText());
                processInfoes();
                list1.setListData(listContent);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void processInfo() {
        姓名.setText(info.getName());
        右眼.setText(info.getRight_eye());
        左眼.setText(info.getLeft_eye());
        瞳距.setText(String.valueOf(info.getDistance()));
        镜架尺寸.setText(info.getSize());
        镜片.setText(info.getLens());
        店.setText(info.getStore());
        验光师.setText(info.getPerson());
        电话.setText(info.getPhone());
        下加光.setText(info.getXwjwgd());
        通道.setText(info.getPass());
        瞳高.setText(info.getHeight());
        标识码.setText(String.valueOf(info.getID()));
        价格.setText(info.getPrice());
        日期.setText(info.getDATA());
    }

    private void processInfoes() {
        ArrayList<String> names = new ArrayList<>();
        String[] notFound = {"未找到符合条件的人"};
        if (infoes.size() != 0) {
            for (Info each : infoes) {
                names.add(each.getName() + " - " + each.getID());
            }
            listContent = names.toArray(new String[0]);
        } else {
            listContent = notFound;
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }



        JFrame jFrame = new JFrame("眼镜店信息管理系统v1.0");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().add(new MainUI().panel1);
        jFrame.setSize(800, 600);
        int windowWidth = jFrame.getWidth();                     //获得窗口宽
        int windowHeight = jFrame.getHeight();                   //获得窗口高
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();             //获取屏幕的尺寸
        int screenWidth = screenSize.width;                     //获取屏幕的宽
        int screenHeight = screenSize.height;                   //获取屏幕的高
        jFrame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
        jFrame.setVisible(true);
    }


}
