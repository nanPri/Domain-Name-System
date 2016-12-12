import java.awt.event.*;  
class Second implements ActionListener{  
First obj;  
Second(First obj){  
this.obj=obj;  
}  
public void actionPerformed(ActionEvent e){  
obj.tf.setText("welcome");  
}  
}  