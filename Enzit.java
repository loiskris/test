package loiskris;

import java.awt.*;   
import java.awt.event.*;   
import java.applet.Applet;   
import java.awt.Color;   
  
public class Enzit extends Applet implements ActionListener,MouseListener,MouseMotionListener,ItemListener   
{   
int color_Qizi=0;//旗子的颜色标识 0:白子 1:黑子   
int intGame_Start=0;//游戏开始标志 0未开始 1游戏中   
int intGame_Body[][]=new int[16][16]; //设置棋盘棋子状态 0 无子 1 白子 2 黑子   
  
Button b1=new Button("游戏开始");   
Button b2=new Button("重置游戏");   
Label lblWin=new Label(" ");   
Checkbox ckbHB[]=new Checkbox[2];   
CheckboxGroup ckgHB=new CheckboxGroup();   
public void init()   
{   
setLayout(null);   
  
addMouseListener(this);   
add(b1);   
b1.setBounds(330,50,80,30);   
b1.addActionListener(this);   
add(b2);   
b2.setBounds(330,90,80,30);   
b2.addActionListener(this);   
ckbHB[0]=new Checkbox("白子先",ckgHB,false);   
ckbHB[0].setBounds(320,20,60,30);   
ckbHB[1]=new Checkbox("黑子先",ckgHB,false);   
ckbHB[1].setBounds(380,20,60,30);   
add(ckbHB[0]);   
add(ckbHB[1]);   
ckbHB[0].addItemListener(this);   
ckbHB[1].addItemListener(this);   
add(lblWin);   
lblWin.setBounds(330,130,80,30);   
  
Game_start_csh();   
}   
  
public void itemStateChanged(ItemEvent e)   
{   
if (ckbHB[0].getState()) //选择黑子先还是白子先   
{   
   color_Qizi=0;   
}   
else  
{   
   color_Qizi=1;   
}   
}   
  
public void actionPerformed(ActionEvent e)   
{   
Graphics g=getGraphics();   
if (e.getSource()==b1)   
{   
   Game_start();   
}   
else  
{   
   Game_re();   
}   
}   
  
public void mousePressed(MouseEvent e){}   
  
public void mouseClicked(MouseEvent e)   
{   
Graphics g=getGraphics();   
int x1,y1;   
x1=e.getX();   
y1=e.getY();   
if (e.getX()<20 || e.getX()>300 || e.getY()<20 || e.getY()>300)   
{   
   return;   
}   
  
if (x1%20>10)   
{   
   x1+=20;   
}   
  
if(y1%20>10)   
{   
   y1+=20;   
}   
  
x1=x1/20*20;   
y1=y1/20*20;   
set_Qizi(x1,y1);   
  
}   
  
public void mouseEntered(MouseEvent e){}   
public void mouseExited(MouseEvent e){}   
public void mouseReleased(MouseEvent e){}   
public void mouseDragged(MouseEvent e){}   
public void mouseMoved(MouseEvent e){}   
  
public void paint(Graphics g)   
{   
draw_qipan(g);   
}   
  
public void set_Qizi(int x,int y) //落子   
{   
if (intGame_Start==0) //判断游戏未开始   
{   
   return;   
}   
  
if (intGame_Body[x/20][y/20]!=0)   
{   
   return;   
}   
Graphics g=getGraphics();   
  
if (color_Qizi==1)//判断黑子还是白子   
{   
   g.setColor(Color.black);   
   color_Qizi=0;   
}   
else  
{   
   g.setColor(Color.white);   
   color_Qizi=1;   
}   
  
g.fillOval(x-10,y-10,20,20);   
  
intGame_Body[x/20][y/20]=color_Qizi+1;   

if (Game_win_1(x/20,y/20)) //判断输赢   
{   
   lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");   
   intGame_Start=0;   
}   
  
if (Game_win_2(x/20,y/20)) //判断输赢   
{   
   lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");   
   intGame_Start=0;   
}   
  
if (Game_win_3(x/20,y/20)) //判断输赢   
{   
   lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");   
   intGame_Start=0;   
}   
  
if (Game_win_4(x/20,y/20)) //判断输赢   
{   
   lblWin.setText(Get_qizi_color(color_Qizi)+"赢了!");   
   intGame_Start=0;   
}   
}   
  
public String Get_qizi_color(int x)   
{   
if (x==0)   
{   
   return "黑子";   
}   
else  
{   
   return "白子";   
}   
}   
  
public void draw_qipan(Graphics G) //画棋盘 15*15   
{   
G.setColor(Color.lightGray);   
G.fill3DRect(10,10,300,300,true);   
G.setColor(Color.black);   
for(int i=1;i<16;i++)   
{   
   G.drawLine(20,20*i,300,20*i);   
   G.drawLine(20*i,20,20*i,300);   
}   
}   
  
public void Game_start() //游戏开始   
{   
intGame_Start=1;   
Game_btn_enable(false);   
b2.setEnabled(true);   
}   
  
public void Game_start_csh() //游戏开始初始化   
{   
intGame_Start=0;   
Game_btn_enable(true);   
b2.setEnabled(false);   
ckbHB[0].setState(true);   
  
for (int i=0;i<16 ;i++ )   
{   
   for (int j=0;j<16 ;j++ )   
   {   
    intGame_Body[i][j]=0;   
   }   
}   
lblWin.setText("");   
}   
  
public void Game_re() //游戏重新开始   
{   
repaint();   
Game_start_csh();   
}   
  
public void Game_btn_enable(boolean e) //设置组件状态   
{   
b1.setEnabled(e);   
b2.setEnabled(e);   
ckbHB[0].setEnabled(e);   
ckbHB[1].setEnabled(e);   
}   
  
public boolean Game_win_1(int x,int y) //判断输赢 横   
{   
int x1,y1,t=1;   
x1=x;   
y1=y;   
  
for (int i=1;i<5 ;i++ )   
{   
   if (x1>15)   
   {   
    break;   
   }   
   if (intGame_Body[x1+i][y1]==intGame_Body[x][y])   
   {   
    t+=1;   
   }   
   else  
   {   
    break;   
   }   
  
}   
  
for (int i=1;i<5 ;i++ )   
{   
   if (x1<1)   
   {   
    break;   
   }   
  
   if(intGame_Body[x1-i][y1]==intGame_Body[x][y])   
   {   
    t+=1;   
   }   
   else  
   {   
    break;   
   }   
}   
  
if (t>4)   
{   
   return true;   
}   
else  
{   
   return false;   
}   
}   
  
public boolean Game_win_2(int x,int y) //判断输赢 竖   
{   
int x1,y1,t=1;   
x1=x;   
y1=y;   
  
for (int i=1;i<5 ;i++ )   
{   
   if (x1>15)   
   {   
    break;   
   }   
   if (intGame_Body[x1][y1+i]==intGame_Body[x][y])   
   {   
    t+=1;   
   }   
   else  
   {   
    break;   
   }   
  
}   
  
for (int i=1;i<5 ;i++ )   
{   
   if (x1<1)   
   {   
    break;   
   }   
  
   if(intGame_Body[x1][y1-i]==intGame_Body[x][y])   
   {   
    t+=1;   
   }   
   else  
   {   
    break;   
   }   
}   
  
if (t>4)   
{   
   return true;   
}   
else  
{   
   return false;   
}   
}   
  
public boolean Game_win_3(int x,int y) //判断输赢 左斜   
{   
int x1,y1,t=1;   
x1=x;   
y1=y;   
  
for (int i=1;i<5 ;i++ )   
{   
   if (x1>15)   
   {   
    break;   
   }   
   if (intGame_Body[x1+i][y1-i]==intGame_Body[x][y])   
   {   
    t+=1;   
   }   
   else  
   {   
    break;   
   }   
  
}   
  
for (int i=1;i<5 ;i++ )   
{   
   if (x1<1)   
   {   
    break;   
   }   
  
   if(intGame_Body[x1-i][y1+i]==intGame_Body[x][y])   
   {   
    t+=1;   
   }   
   else  
   {   
    break;   
   }   
}   
  
if (t>4)   
{   
   return true;   
}   
else  
{   
   return false;   
}   
}   
  
public boolean Game_win_4(int x,int y) //判断输赢 左斜   
{   
int x1,y1,t=1;   
x1=x;   
y1=y;   
  
for (int i=1;i<5 ;i++ )   
{   
   if (x1>15)   
   {   
    break;   
   }   
   if (intGame_Body[x1+i][y1+i]==intGame_Body[x][y])   
   {   
    t+=1;   
   }   
   else  
   {   
    break;   
   }   
  
}   
  
for (int i=1;i<5 ;i++ )   
{   
   if (x1<1)   
   {   
    break;   
   }   
  
   if(intGame_Body[x1-i][y1-i]==intGame_Body[x][y])   
   {   
    t+=1;   
   }   
   else  
   {   
    break;   
   }   
}   
  
if (t>4)   
{   
   return true;   
}   
else  
{   
   return false;   
}   
}   
}