package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DATA.article;
import LOGIC.service;

public class upload {
	 private static JTable table;
    /**
     * @param args
     */
	public static void main(String[] args) {
		 GUI();
	}
    public static void GUI() {
        JFrame frame = new JFrame("微信文章");
        frame.setSize(1000, 500);
        frame.setLocation(150, 150);
        //获取全部数据
        ArrayList<article> data=service.getall();
        Object row[][]= new Object[data.size()][14];
        String[] tableHeadName = {"选择","序号","作者", "标题","概要","发布时间","爬取时间"};  //表头
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setDataVector(row,tableHeadName);       
        table = new JTable(tableModel);
        table.getColumn("选择").setMaxWidth(50);
        table.getColumn("选择").setMinWidth(50);
        table.getColumn("序号").setMaxWidth(30);
        table.getColumn("序号").setMinWidth(30);
        table.getColumn("作者").setMaxWidth(50);
        table.getColumn("作者").setMinWidth(50);
        table.getColumn("标题").setMaxWidth(400);
        table.getColumn("标题").setMinWidth(400);
        table.getColumn("概要").setMaxWidth(250);
        table.getColumn("概要").setMinWidth(250);
        table.getColumn("发布时间").setMaxWidth(80);
        table.getColumn("发布时间").setMinWidth(80);
        table.getColumn("爬取时间").setMaxWidth(120);
        table.getColumn("爬取时间").setMinWidth(120);
        JCheckBox jc = new JCheckBox();
        table.getColumnModel().getColumn(0)
                .setCellEditor(new DefaultCellEditor(jc));
        for(int i=0;i<data.size();i++) {      
        	table.setValueAt(i+1, i, 1);
        	table.setValueAt(data.get(i).getAuthor(), i, 2);
        	table.setValueAt(data.get(i).getTitle(), i, 3);
        	table.setValueAt(data.get(i).getSummary(), i, 4);
        	table.setValueAt(data.get(i).getPublish_time(), i, 5);
        	table.setValueAt(data.get(i).getGet_time(), i, 6);
        	//table.setValueAt(false, i, 0);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.NORTH);
        JPanel jp=new JPanel();
        JButton a=new JButton("查看详情");
        JButton b=new JButton("全选");
        JButton c=new JButton("上传");
        jp.setLayout(new FlowLayout());
        jp.add(a);
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 进行逻辑处理即可
                int  a = table.getSelectedRow();
                if(a>=0) {
                String res = data.get(a).getGet_time();
				String article="";
                try {
				 article=service.getArticle(res);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                //System.out.println(article);
                a(article);
                }
            }
        });
        jp.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 进行逻辑处理即可
            	 int count=0; 
            	 for(int i=0;i<data.size();i++) {      
                 	if(String.valueOf(table.getValueAt(i, 0)).equals("true")) {
                 		count++;
                 	}
                 }
            	 if(count==data.size()) {
            		 for(int i=0;i<data.size();i++) {      
            			 table.setValueAt("", i, 0);
                      }
            	 }else {
            		 for(int i=0;i<data.size();i++) {      
            			 table.setValueAt(true, i, 0);
                      }
            	 }
            }
        });
        jp.add(c);
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 进行逻辑处理即可
            	ArrayList<article> result=new ArrayList<article>();
            	for(int i=0;i<data.size();i++) {
            		if(String.valueOf(table.getValueAt(i, 0)).equals("true")) {
                 		result.add(data.get(i));
                 	}
            	}
            	try {
					service.upload(result);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	c();
            	frame.dispose();
            }
        });
        frame.add(jp, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
    
    
    
    public static void a(String a) {
    	  JFrame frame = new JFrame("文章详情");
          frame.setSize(800, 400);
          frame.setLocation(200, 200);
          JScrollPane jp=new JScrollPane();
          JTextArea jt=new JTextArea();
          jt.setText(a);
          jt.setSize(750, 400);
          jt.setLineWrap(true);
          jp.add(jt);
          jp.setViewportView(jt);
          frame.add(jp, BorderLayout.CENTER);
          frame.setVisible(true);
          frame.setResizable(false);
          //frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
    
    public static void c() {
    	 GUI();
  }
}
