import java.awt.*;  
import java.awt.event.*;  
class First extends Frame{  
TextField tf;  
First(){  
  
tf=new TextField();  
tf.setBounds(60,50,170,20);  
  
Button b=new Button("click me");  
b.setBounds(100,120,80,30);  
  
Second o=new Second(this);  
b.addActionListener(o);//passing outer class instance  
  
add(b);add(tf);  
  
setSize(300,300);  
setLayout(null);  
setVisible(true);  
}  
public static void main(String args[]){  
new First();  
}  
}  